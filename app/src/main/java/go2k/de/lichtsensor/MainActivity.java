package go2k.de.lichtsensor;

import android.app.Activity;
import android.app.Service;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;


public class MainActivity extends Activity implements SensorEventListener {

    private TextView tvType;
    private TextView tvTypeString;
    private TextView tvValue;
    private TextView tvID;
    private TextView tvName;
    private TextView tvVendor;
    private TextView tvVersion;
    private TextView tvMinDelay;
    private TextView tvMaxRange;
    private TextView tvResolution;
    private TextView tvPower;
    private TextView tvReportingMode;
    private TextView tvIsAdditionalInfoSupported;
    private TextView tvIsWakeUpSensor;
    private TextView tvIsDynamicSensor;
    private SeekBar seekBar;
    private TextView tvSeekBar;
    private float BackLightValue = (float) 0.7500;

    WindowManager.LayoutParams layoutParams;

    private SensorManager sensorManager;
    private Sensor sensor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initComponents();

        sensorManager = (SensorManager) getSystemService(Service.SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        readSensorValues();
        layoutParams = getWindow().getAttributes();

        initEvents();
    }


    private void readSensorValues() {
        tvType.setText(String.valueOf(sensor.getType()));
        tvTypeString.setText(sensor.getStringType());
        tvName.setText(sensor.getName());
        tvID.setText(String.valueOf(sensor.getId()));
        tvVendor.setText(String.valueOf(sensor.getVendor()));
        tvVersion.setText(String.valueOf(sensor.getVersion()));
        tvMaxRange.setText(String.valueOf(sensor.getMaximumRange()));
        tvMinDelay.setText(String.valueOf(sensor.getMinDelay()));
        tvResolution.setText(String.valueOf(sensor.getResolution()));
        tvPower.setText(String.valueOf(sensor.getPower()));
        tvIsAdditionalInfoSupported.setText(String.valueOf(String.valueOf(sensor.isAdditionalInfoSupported())));
        tvIsWakeUpSensor.setText(String.valueOf(String.valueOf(sensor.isWakeUpSensor())));
        tvIsDynamicSensor.setText(String.valueOf(String.valueOf(sensor.isDynamicSensor())));
        String mode = "";
        switch (sensor.getReportingMode()) {
            case Sensor.REPORTING_MODE_CONTINUOUS:
                mode = "CONTINUOUS";
                break;
            case Sensor.REPORTING_MODE_ON_CHANGE:
                mode = "ON_CHANGE";
                break;
            case Sensor.REPORTING_MODE_ONE_SHOT:
                mode = "ONE_SHOT";
                break;
            case Sensor.REPORTING_MODE_SPECIAL_TRIGGER:
                mode = "SPECIAL_TRIGGER";
                break;
        }
        tvReportingMode.setText(mode);
    }

    private void initComponents() {
        tvType = findViewById(R.id.tvType);
        tvTypeString = findViewById(R.id.tvTypeString);
        tvValue = findViewById(R.id.tvValue);
        tvID = findViewById(R.id.tvID);
        tvName = findViewById(R.id.tvName);
        tvVendor = findViewById(R.id.tvVendor);
        tvVersion = findViewById(R.id.tvVersion);
        tvMinDelay = findViewById(R.id.tvMinDelay);
        tvMaxRange = findViewById(R.id.tvMaxRange);
        tvResolution = findViewById(R.id.tvResolution);
        tvPower = findViewById(R.id.tvPower);
        tvReportingMode = findViewById(R.id.tvReportingMode);
        tvIsAdditionalInfoSupported = findViewById(R.id.tvIsAdditionalInfoSupported);
        tvIsDynamicSensor = findViewById(R.id.tvIsDynamicSensor);
        tvIsWakeUpSensor = findViewById(R.id.tvIsWakeUpSensor);
        seekBar = findViewById(R.id.seekBar);
        seekBar.setProgress(Math.round(BackLightValue * 10000));
        tvSeekBar = findViewById(R.id.tvSeekBarValue);
        tvSeekBar.setText(String.valueOf(BackLightValue));
    }


    private void initEvents() {
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BackLightValue = (float) progress / (float) 10000;
                layoutParams.screenBrightness = BackLightValue;
                getWindow().setAttributes(layoutParams);
                tvSeekBar.setText(String.valueOf(BackLightValue));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sensorManager.registerListener(this, sensor, sensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        if (event.sensor.getType() == sensor.TYPE_LIGHT) {
            tvValue.setText("" + event.values[0]);
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
