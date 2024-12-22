package gui.ceng.mu.edu.panic_atack_app;

import android.animation.ObjectAnimator;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BreathingTechnique extends AppCompatActivity {

    private TextView timerText;
    private Button startButton;
    private Button audioButton;
    private ProgressBar progressBar;
    private MediaPlayer mediaPlayer;
    private Handler handler;
    private int step = 0;
    private ObjectAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breathing_technique);

        timerText = findViewById(R.id.tv_timer);
        startButton = findViewById(R.id.btn_start);
        audioButton = findViewById(R.id.btn_audio);
        progressBar = findViewById(R.id.progress_circular);

        handler = new Handler();

        // ProgressBar animasyonu
        animator = ObjectAnimator.ofInt(progressBar, "progress", 0, 100);
        animator.setDuration(0); // İlk başta çalışmasın
        animator.setRepeatCount(0);

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
        step = 0;
        progressBar.setProgress(0); // İlerlemeyi sıfırla
        animator.setDuration(0); // Animasyonu baştan sıfırla
        handler.post(breathingRunnable);
    }

    private final Runnable breathingRunnable = new Runnable() {
        @Override
        public void run() {
            switch (step) {
                case 0:
                    timerText.setText("Breathe In");
                    startCountdown(4); // 4 saniye geri sayım
                    animateProgressBar(0, 40, 4000);
                    handler.postDelayed(() -> timerText.setText("Breathe In - Complete!"), 4000);
                    step++;
                    handler.postDelayed(this, 6000); // Ekstra 2 saniye okuma süresi
                    break;
                case 1:
                    timerText.setText("Hold Breath");
                    startCountdown(7); // 7 saniye geri sayım
                    animateProgressBar(40, 70, 7000);
                    handler.postDelayed(() -> timerText.setText("Hold Breath - Complete!"), 7000);
                    step++;
                    handler.postDelayed(this, 9000); // Ekstra 2 saniye okuma süresi
                    break;
                case 2:
                    timerText.setText("Exhale");
                    startCountdown(8); // 8 saniye geri sayım
                    animateProgressBar(70, 100, 8000);
                    handler.postDelayed(() -> timerText.setText("Exhale - Complete!"), 8000);
                    step++;
                    handler.postDelayed(this, 10000); // Ekstra 2 saniye okuma süresi
                    break;
                default:
                    timerText.setText("Exercise Complete!");
                    progressBar.setProgress(100);
                    break;
            }
        }
    };

    private void animateProgressBar(int start, int end, int duration) {
        animator.setDuration(duration);
        animator.setIntValues(start, end);
        animator.start();
    }

    private void startCountdown(int seconds) {
        new CountDownTimer(seconds * 1000L, 1000) {
            int remainingTime = seconds;

            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText(String.valueOf(--remainingTime)); // Geri sayımı göster
            }

            @Override
            public void onFinish() {
                // Bir sonraki adıma geçerken geri sayım duracak
            }
        }.start();
    }

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
