package jroldan.deint.reversechronometer;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by usuario on 18/01/18.
 */

@SuppressLint("AppCompatCustomView")
public class ReverseChronometer extends TextView implements Runnable {

    /**
     * Estado del componente que deben ser accesibles en el xml.
     * overallTime: tiempo total en segundos
     * warning: tiempo en segundos en el que cambia de color
     */
    private long overallTime;
    private long warningTime;
    private long startTime;

    public ReverseChronometer(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray attribute = getContext().obtainStyledAttributes(attrs, R.styleable.ReverseChronometer);
        overallTime = attribute.getInteger(R.styleable.ReverseChronometer_overall_time, 60);
        warningTime = attribute.getInteger(R.styleable.ReverseChronometer_warning_time, 10);
        //setText("Valor inicial: " + overallTime);
        reset();
    }

    public void setOverallTime(long overallTime) {
        this.overallTime = overallTime;
    }

    public void setWarningTime(long warningTime) {
        this.warningTime = warningTime;
    }

    @Override
    public void run() {
        long elapsedSeconds = (SystemClock.elapsedRealtime() - startTime) / 1000;
        if(elapsedSeconds<overallTime) {
            // Actualizar los tiempos
            long remainingSecond = overallTime-elapsedSeconds;
            long minutes = remainingSecond/60;
            //long seconds = remainingSecond-(60*minutes);
            long seconds = remainingSecond % 60;
            setText(String.format("%02d:%02d", minutes, seconds));
            // En el caso que nos encontremos en tiempo de warning
            if(warningTime > 0 && remainingSecond<warningTime)
                setTextColor(Color.RED);

            postDelayed(this, 1000);
        } else {
            setText("00:00");
            setTextColor(Color.BLACK);
        }
    }

    public void reset() {
        startTime = SystemClock.elapsedRealtime();
        setText("--:--");
        setTextColor(Color.BLACK);
    }

    public void stop() {

    }
}
