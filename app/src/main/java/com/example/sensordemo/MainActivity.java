package com.example.sensordemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor mLight;
    private Sensor mMagneticField;
    private Sensor mAccelerometer;
    private Sensor mLinearAcceleration;

    private TextView tvLight;
    private TextView tvMagneticFieldX;
    private TextView tvMagneticFieldY;
    private TextView tvMagneticFieldZ;
    private TextView tvAccelerometerX;
    private TextView tvAccelerometerY;
    private TextView tvAccelerometerZ;
    private TextView tvLinearAccelerationX;
    private TextView tvLinearAccelerationY;
    private TextView tvLinearAccelerationZ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mLight = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
        mMagneticField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
        mAccelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        mLinearAcceleration = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

        tvLight = findViewById(R.id.light);
        tvMagneticFieldX = findViewById(R.id.magnetic_field_x);
        tvMagneticFieldY = findViewById(R.id.magnetic_field_y);
        tvMagneticFieldZ = findViewById(R.id.magnetic_field_z);
        tvAccelerometerX = findViewById(R.id.accelerometer_x);
        tvAccelerometerY = findViewById(R.id.accelerometer_y);
        tvAccelerometerZ = findViewById(R.id.accelerometer_z);
        tvLinearAccelerationX = findViewById(R.id.linear_acceleration_x);
        tvLinearAccelerationY = findViewById(R.id.linear_acceleration_y);
        tvLinearAccelerationZ = findViewById(R.id.linear_acceleration_z);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (mLight != null) {
            sensorManager.registerListener(this, mLight, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mMagneticField != null) {
            sensorManager.registerListener(this, mMagneticField, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mAccelerometer != null) {
            sensorManager.registerListener(this, mAccelerometer, SensorManager.SENSOR_DELAY_NORMAL);
        }

        if (mLinearAcceleration != null) {
            sensorManager.registerListener(this, mLinearAcceleration, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {}

    @Override
    public void onSensorChanged(SensorEvent event) {
        Sensor sensor = event.sensor;

        if(sensor.getType() == Sensor.TYPE_LIGHT) {
            float data = event.values[0];
            tvLight.setText(String.valueOf(data));
        }

        if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
            float x = event.values[0], y = event.values[1], z = event.values[2];
            tvMagneticFieldX.setText(String.valueOf(x));
            tvMagneticFieldY.setText(String.valueOf(y));
            tvMagneticFieldZ.setText(String.valueOf(z));
        }

        if(sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            float x = event.values[0], y = event.values[1], z = event.values[2];
            tvAccelerometerX.setText(String.valueOf(x));
            tvAccelerometerY.setText(String.valueOf(y));
            tvAccelerometerZ.setText(String.valueOf(z));
        }

        if(sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION) {
            float x = event.values[0], y = event.values[1], z = event.values[2];
            tvLinearAccelerationX.setText(String.valueOf(x));
            tvLinearAccelerationY.setText(String.valueOf(y));
            tvLinearAccelerationZ.setText(String.valueOf(z));
        }
    }
}