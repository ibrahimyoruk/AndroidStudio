package gui.ceng.mu.edu.week6;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements MovieFragment.OnMovieSelected {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Başlangıçta MovieFragment'i ekle
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, new MovieFragment())
                    .commit();
        }
    }

    // Eksik olan movieSelected metodunu ekleyin
    @Override
    public void movieSelected(Movie movie) {
        // Ekranın dikey veya yatay modda olup olmadığını kontrol edin
        int display_mode = getResources().getConfiguration().orientation;
        if (display_mode == Configuration.ORIENTATION_PORTRAIT) {
            // Dikey moddaysa, DetailsActivity'yi başlat
            Intent intent = new Intent(this, DetailsActivity.class);
            intent.putExtra("movie", movie);
            startActivity(intent);
        } else {
            // Yatay moddaysa, DetailsFragment'i güncelle
            DetailsFragment df = DetailsFragment.newInstance(movie);
            FragmentTransaction fts = getSupportFragmentManager().beginTransaction();
            fts.replace(R.id.container, df);
            fts.commit();
        }
    }
}

