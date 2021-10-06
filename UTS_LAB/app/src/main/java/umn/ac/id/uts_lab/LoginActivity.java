package umn.ac.id.uts_lab;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    Button next_button;
    EditText name;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        next_button = findViewById(R.id.next_button);

        setTitle("Login");
        getSupportActionBar().hide();

        next_button.setOnClickListener(v -> {
            Intent intent = new Intent(this, LibraryActivity.class);
            name = findViewById(R.id.login_edittext);
            String message = name.getText().toString();
            if(message.trim().length() > 0){
                intent.putExtra("NAMA", message);
                startActivity(intent);
                return;
            }
            name.setError("Required!");
            Toast.makeText(this, "Please input your name", Toast.LENGTH_SHORT).show();
        });
    }

}
