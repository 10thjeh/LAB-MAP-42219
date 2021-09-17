package umn.ac.id.week04b_42219;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button halaman1, halaman2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        halaman1 = findViewById(R.id.main_button_change_1);
        halaman2 = findViewById(R.id.main_button_change_2);

        halaman1.setOnClickListener(view->{
            Intent intent = new Intent(this, SecondActivity.class);
            startActivity(intent);
        });

        halaman2.setOnClickListener(view->{
            Intent intent = new Intent(this, ThirdActivity.class);
            startActivity(intent);
        });
    }
}