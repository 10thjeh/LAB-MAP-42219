package umn.ac.id.uts_lab;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class LibraryActivity extends AppCompatActivity {

    ArrayList<Sound> soundList = new ArrayList<Sound>();
    RecyclerView sound_recyclerView;
    String nama;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_library);
        generateSoundList();
        Intent intent = getIntent();
        if(intent.getStringExtra("NAMA") != null){
            nama = intent.getStringExtra("NAMA");
        }
        setTitle(nama);
        Toast.makeText(this, "Selamat datang, " + nama, Toast.LENGTH_SHORT).show();

        sound_recyclerView = findViewById(R.id.sound_RecyclerView);

        SoundRecyclerViewAdapter adapter = new SoundRecyclerViewAdapter(this, soundList);
        sound_recyclerView.setAdapter(adapter);
        sound_recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_library, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.to_profile:
                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                return true;
            case R.id.to_home:
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void generateSoundList(){
        String name[] = {
                "Za Warudo",
                "Za Warudo",
                "I Like your cut",
                "Whats 9 + 10?",
                "Among us rant #1",
                "Among us rant #2",
                "Monster inc"

        };

        String description[] = {
                "Toki wo tomare!",
                "But it's Jotaro",
                "G",
                "You stupid",
                "Stop posting about among us",
                "Cerealbox complaining about among us",
                "DO NOT PLAY THIS"
        };

        int sound_resource[] = {
                R.raw.za_warudo,
                R.raw.za_warudo_star_platinum,
                R.raw.i_like_your_cut_g,
                R.raw.you_stupid,
                R.raw.stop_among_us,
                R.raw.spongebob_among_us,
                R.raw.monster_inc
        };

        for (int i = 0; i < name.length ; i++){
            Sound sound = new Sound(name[i], description[i], sound_resource[i]);
            soundList.add(sound);
        }
    }
}
