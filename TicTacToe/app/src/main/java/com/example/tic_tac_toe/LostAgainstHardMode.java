package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class LostAgainstHardMode extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lost_against_hard_mode);

        Button play_again_hardlose_result_btn = findViewById(R.id.play_again_hardlose_result);
        Button exit_btn_hardlose_result_btn = findViewById(R.id.exit_btn_hardlose_result);

        play_again_hardlose_result_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, FourByFourMode.class);
            startActivity(intent);
        });

        exit_btn_hardlose_result_btn.setOnClickListener(view -> {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
    }
}