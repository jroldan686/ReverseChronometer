package jroldan.deint.reversechronometer;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private ReverseChronometer reverseChronometer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        reverseChronometer = findViewById(R.id.rvcTime);
        reverseChronometer.setOverallTime(120);
        reverseChronometer.setWarningTime(10);
        reverseChronometer.setText("Valor inicial: 120");
    }

    @Override
    protected void onPause() {
        super.onPause();
        reverseChronometer.stop();
    }

    @Override
    protected void onResume() {
        super.onResume();
        reverseChronometer.run();
    }
}
