package com.example.lab4;

import static android.provider.Settings.System.SCREEN_BRIGHTNESS;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.WindowManager;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView mAccelerometerX;
    TextView mAccelerometerY;
    TextView mAccelerometerZ;
    TextView accel;
    TextView mMagneticX;
    TextView mMagneticY;
    TextView mMagneticZ;
    TextView mProximity;
    TextView mLight;
    SensorManager sensorManager;
    Sensor mAccelerometerSensor;
    Sensor mProximitySensor;
    Sensor mMagneticSensor;
    Sensor mLightSensor;
    float mMaxValue;
    float mValue;
    float x, y;
    @SuppressLint({"CutPasteId", "MissingInflatedId"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAccelerometerX = (TextView)findViewById(R.id.textView2);
        mAccelerometerY = (TextView)findViewById(R.id.textView3);
        mAccelerometerZ = (TextView)findViewById(R.id.textView4);
        accel = (TextView) findViewById(R.id.textView13);
        mMagneticX = (TextView)findViewById(R.id.textView6);
        mMagneticY = (TextView)findViewById(R.id.textView7);
        mMagneticZ = (TextView)findViewById(R.id.textView8);
        mProximity = (TextView)findViewById(R.id.textView9);
        mLight = (TextView)findViewById(R.id.textView11);
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        mAccelerometerSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mMagneticSensor =
                sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mProximitySensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
        mLightSensor = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mMaxValue = mLightSensor.getMaximumRange();
    }
    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent event)
    {
        if(event.sensor.getType()==Sensor.TYPE_ACCELEROMETER){
            mAccelerometerX.setText(Float.toString(event.values[0]));
            mAccelerometerY.setText(Float.toString(event.values[1]));
            mAccelerometerZ.setText(Float.toString(event.values[2]));

            x = event.values[0];
            y = event.values[1];
            accel.setX(accel.getX() - (x * 10));
            accel.setY(accel.getY() + (y * 10));
            DisplayMetrics displayMetrics = new DisplayMetrics();
            getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int height = displayMetrics.heightPixels;
            int width = displayMetrics.widthPixels;
            int textWidth =  accel.getWidth();
            int textHeight =  accel.getHeight();

            if (accel.getX() < 0)
            {
                accel.setX(0);
            }
            if (accel.getX() > width - textWidth) {
                accel.setX(width - accel.getWidth());
            }
            if (accel.getY() < 0)
            {
                accel.setY(0);
            }
            if (accel.getY() > height - textHeight) {
                accel.setY(height - accel.getHeight());
            }

        }
        if(event.sensor.getType()==Sensor.TYPE_MAGNETIC_FIELD){
            mMagneticX.setText(Float.toString(event.values[0]));
            mMagneticY.setText(Float.toString(event.values[1]));
            mMagneticZ.setText(Float.toString(event.values[2]));
        }
        if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
            mProximity.setText(Float.toString(event.values[0]));
        }
        if(event.sensor.getType()==Sensor.TYPE_LIGHT){
            mLight.setText("" + event.values[0]);
            WindowManager.LayoutParams layout = getWindow().getAttributes();
            layout.screenBrightness = 255f * event.values[0] / mMaxValue;
            getWindow().setAttributes(layout);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager.registerListener(this, mAccelerometerSensor,
                SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, mMagneticSensor,
                SensorManager.SENSOR_DELAY_UI);
        sensorManager.registerListener(this, mProximitySensor,
                SensorManager.SENSOR_DELAY_FASTEST);
        sensorManager.registerListener(this, mLightSensor,
                SensorManager.SENSOR_DELAY_NORMAL);
    }
    @Override
    protected void onStop() {
        super.onStop();
        super.onStop();
        sensorManager.unregisterListener(this, mAccelerometerSensor);
        sensorManager.unregisterListener(this, mMagneticSensor);
        sensorManager.unregisterListener(this, mProximitySensor);
        sensorManager.unregisterListener(this, mLightSensor);
    }
    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}