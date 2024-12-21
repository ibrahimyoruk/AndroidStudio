package gui.ceng.mu.edu.panic_atack_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmergencySupport extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emergency_support);

        // 4-7-8 Technique Butonu
        Button btn478Technique = findViewById(R.id.btn_478_technique);
        btn478Technique.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencySupport.this, BreathingTechnique.class);
                startActivity(intent);
            }
        });

        // Visual/Audio Guide Screen Butonu
        Button btnVisualAudio = findViewById(R.id.btn_visual_audio_guide);
        btnVisualAudio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EmergencySupport.this, GuideScreen.class);
                startActivity(intent);
            }
        });
    }
}
