package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ResultScreenForEasyWin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_screen_for_easy_win);

        Button play_again_4x4_easy_btn = findViewById(R.id.play_again_btn_4x4_easy);
        Button exit_4x4_easy_btn = findViewById(R.id.exit_btn_4x4_easy);

        play_again_4x4_easy_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, FourByFourMode.class);
            startActivity(intent);
        });

        exit_4x4_easy_btn.setOnClickListener(view -> {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
    }
}