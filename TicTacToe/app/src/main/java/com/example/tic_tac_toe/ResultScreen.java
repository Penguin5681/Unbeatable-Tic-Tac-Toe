package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class ResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen);

        Button play_again_btn = findViewById(R.id.play_again_btn);
        Button exit_btn = findViewById(R.id.exit_btn);

        play_again_btn.setOnClickListener(view -> {
            Toast.makeText(this, "Playing Again", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, PlayScreen.class);
            startActivity(intent);
        });

        exit_btn.setOnClickListener(view -> {
            android.os.Process.killProcess(android.os.Process.myPid());   // Terminates the process
            System.exit(1);
        });
    }
}