package gui.ceng.mu.edu.panic_atack_app;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AnxietyTracker extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anxiety_tracker);

        // Checklist elemanlarını kontrol et
        CheckBox cbRapidHeartbeat = findViewById(R.id.cb_rapid_heartbeat);
        CheckBox cbShortnessBreath = findViewById(R.id.cb_shortness_breath);
        CheckBox cbTremblingShaking = findViewById(R.id.cb_trembling_shaking);
        CheckBox cbDizziness = findViewById(R.id.cb_dizziness);
        CheckBox cbChestPain = findViewById(R.id.cb_chest_pain);

        // Kullanıcı seçimlerini göstermek için örnek
        cbRapidHeartbeat.setOnCheckedChangeListener((buttonView, isChecked) -> {
            String message = isChecked ? "Rapid heartbeat selected" : "Rapid heartbeat unselected";
            Toast.makeText(AnxietyTracker.this, message, Toast.LENGTH_SHORT).show();
        });
    }
}
