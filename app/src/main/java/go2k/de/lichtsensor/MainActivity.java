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

    }

}
