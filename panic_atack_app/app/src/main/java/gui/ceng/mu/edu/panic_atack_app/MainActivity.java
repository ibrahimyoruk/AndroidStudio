package gui.ceng.mu.edu.panic_atack_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Butonları Tanımlama
        Button emergencyButton = findViewById(R.id.btn_emergency_support);
        Button affirmationsButton = findViewById(R.id.btn_positive_affirmations);
        Button trackerButton = findViewById(R.id.btn_anxiety_tracker);

        // Emergency Support Butonu İşlevi
        emergencyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // EmergencySupportActivity'ye geçiş yap
                Intent intent = new Intent(MainActivity.this, EmergencySupport.class);
                startActivity(intent);
            }
        });

        // Positive Affirmations Butonu İşlevi
        affirmationsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "Positive Affirmations Açılıyor...", Toast.LENGTH_SHORT).show();
                // Buraya positive affirmations sayfasına geçiş kodu eklenebilir
            }
        });

        // Anxiety Tracker Butonu İşlevi
        trackerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AnxietyTracker.class);
                startActivity(intent);
            }
        });

    }
}
