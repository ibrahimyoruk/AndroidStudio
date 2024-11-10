package gui.ceng.mu.edu.week6;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class DetailsActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        // Movie objesini Intent'ten al
        Movie movie = getIntent().getParcelableExtra("movie");

        // DetailsFragment'i başlat ve Movie objesini gönder
        if (movie != null) {
            DetailsFragment detailsFragment = DetailsFragment.newInstance(movie);
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            transaction.replace(R.id.container, detailsFragment);
            transaction.commit();
        }
    }
}

