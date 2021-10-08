package umn.ac.id.uts_lab;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView detail_name, detail_description;
    Button play_button;
    MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        detail_name = findViewById(R.id.detail_name);
        detail_description = findViewById(R.id.detail_description);
        play_button = findViewById(R.id.play_button);

        Intent intent = getIntent();
        Sound sound = intent.getParcelableExtra("SOUND");

        detail_name.setText(sound.getName());
        detail_description.setText(sound.getDescription());
        setTitle(sound.getName());
        mediaPlayer = MediaPlayer.create(this, sound.getSound_id());

        play_button.setOnClickListener(v->{

            mediaPlayer.start();
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mediaPlayer.stop();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mediaPlayer.stop();
    }
}
