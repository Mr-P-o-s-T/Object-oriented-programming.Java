package post.com.exam.lab3;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainScreen extends AppCompatActivity implements SensorEventListener {
    static final long dT = 100;
    static  final float e = 0.3f;

    private TextView acc_x, acc_y, acc_z, acc_res, gyr_x, gyr_y, gyr_z, gyr_res;

    private long acc_T_prev = 0, gyr_T_prev = 0;

    private float[] acc_prev, gyr_prev;

    private double acc_dist = 0.0, gyr_dist = 0.0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        acc_x = (TextView) findViewById(R.id.Acc_X);
        acc_y = (TextView) findViewById(R.id.Acc_Y);
        acc_z = (TextView) findViewById(R.id.Acc_Z);
        acc_res = (TextView) findViewById(R.id.Acc_Res);

        gyr_x = (TextView) findViewById(R.id.Gyr_X);
        gyr_y = (TextView) findViewById(R.id.Gyr_Y);
        gyr_z = (TextView) findViewById(R.id.Gyr_Z);
        gyr_res = (TextView) findViewById(R.id.Gyr_Res);

        SensorManager mngr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        Sensor accelerometer, gyroscope;

        if ((accelerometer = mngr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)) == null) {
            acc_res.setText(getString(R.string.acclrmtr) + " " + getString(R.string.noSupport));
        }
        else {
            mngr.registerListener(MainScreen.this, accelerometer, mngr.SENSOR_DELAY_NORMAL);
        }

        if ((gyroscope = mngr.getDefaultSensor(Sensor.TYPE_GYROSCOPE)) == null) {
            gyr_res.setText(getString(R.string.grscp) + " " + getString(R.string.noSupport));
        }
        else {
            mngr.registerListener(MainScreen.this, gyroscope, mngr.SENSOR_DELAY_NORMAL);
        }
        acc_prev = new float[3];
        gyr_prev = new float[3];
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        long curr_T = System.currentTimeMillis();
        if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {
            acc_x.setText("X: " + event.values[0]);
            acc_y.setText("Y: " + event.values[1]);
            acc_z.setText("Z: " + event.values[2]);

            if ((curr_T - acc_T_prev) > dT) {
                acc_T_prev = curr_T;
                acc_dist += getDistance(event.values, acc_prev);
            }

            acc_prev[0] = event.values[0];
            acc_prev[1] = event.values[1];
            acc_prev[2] = event.values[2];

            acc_res.setText(getString(R.string.dist) + " " + acc_dist);
        }

        if (event.sensor.getType() == Sensor.TYPE_GYROSCOPE) {
            gyr_x.setText("X: " + event.values[0]);
            gyr_y.setText("Y: " + event.values[1]);
            gyr_z.setText("Z: " + event.values[2]);

            if ((curr_T - gyr_T_prev) > dT) {
                gyr_T_prev = curr_T;
                gyr_dist += getDistance(event.values, gyr_prev);
            }

            gyr_prev[0] = event.values[0];
            gyr_prev[1] = event.values[1];
            gyr_prev[2] = event.values[2];

            gyr_res.setText(getString(R.string.dist) + " " + gyr_dist);
        }
    }

    static double getDistance(float[] curr, float[] prev) {
        double res = 0.0;
        for (int i = 0; i < curr.length; i++) res += (curr[i] - prev[i]) * (curr[i] - prev[i]);
        res = Math.sqrt(res);
        if (res < e) return 0.0;
        else return res;
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {

    }
}
