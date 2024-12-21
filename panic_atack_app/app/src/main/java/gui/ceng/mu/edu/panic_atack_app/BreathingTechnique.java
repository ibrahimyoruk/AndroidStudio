package gui.ceng.mu.edu.panic_atack_app;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreathingTechnique extends AppCompatActivity {

    private TextView timerText;
    private Button startButton;
    private Button audioButton;
    private MediaPlayer mediaPlayer;
    private Handler handler;
    private int step = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique);

        timerText = findViewById(R.id.tv_timer);
        startButton = findViewById(R.id.btn_start);
        audioButton = findViewById(R.id.btn_audio);

        handler = new Handler();

        // Ses dosyası çalma
        mediaPlayer = MediaPlayer.create(this, R.raw.breathing_audio);
        audioButton.setOnClickListener(v -> {
            if (mediaPlayer.isPlaying()) {
                mediaPlayer.pause();
                audioButton.setText("Play Audio Guide");
            } else {
                mediaPlayer.start();
                audioButton.setText("Pause Audio Guide");
            }
        });

        // Egzersiz Başlatma
        startButton.setOnClickListener(v -> startBreathingExercise());
    }

    private void startBreathingExercise() {
        step = 0; // Egzersiz adımlarını sıfırla
        handler.post(breathingRunnable);
    }

    private final Runnable breathingRunnable = new Runnable() {
        @Override
        public void run() {
            switch (step) {
                case 0:
                    timerText.setText("Breathe In (4 seconds)");
                    step++;
                    handler.postDelayed(this, 4000);
                    break;
                case 1:
                    timerText.setText("Hold Breath (7 seconds)");
                    step++;
                    handler.postDelayed(this, 7000);
                    break;
                case 2:
                    timerText.setText("Exhale (8 seconds)");
                    step++;
                    handler.postDelayed(this, 8000);
                    break;
                default:
                    timerText.setText("Exercise Complete!");
                    break;
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
            mediaPlayer = null;
        }
        handler.removeCallbacks(breathingRunnable);
    }
}
