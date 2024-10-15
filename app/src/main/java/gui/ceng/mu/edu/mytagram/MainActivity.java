package gui.ceng.mu.edu.mytagram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.List;

import gui.ceng.mu.edu.mytagram.Post;
import gui.ceng.mu.edu.mytagram.PostAdapter;
import gui.ceng.mu.edu.mytagram.R;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    List<Post>  posts = new ArrayList<>();
    Button btnPost;
    static final  int POST_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        PostAdapter postAdapter = new PostAdapter(this,posts);

        listView.setAdapter(postAdapter);

        btnPost = findViewById(R.id.btnPost);
        btnPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, postActivity.class);
                startActivityForResult(intent, POST_REQUEST);
            }
        });

    }
}