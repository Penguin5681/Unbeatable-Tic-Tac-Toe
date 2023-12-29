package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class FourByFourDifficultySelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_by_four_difficulty_select);

        Button easy_level_4By4_select_btn = findViewById(R.id.easy_level_4By4_select);
        Button hardcore_level_4By4_select_btn = findViewById(R.id.hardcore_level_4By4_select);
        Button exit_grid_4By4_select_btn = findViewById(R.id.exit_grid_4By4_select);

        easy_level_4By4_select_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, FourByFourMode.class);
            startActivity(intent);
        });

        hardcore_level_4By4_select_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, FourByFourHardMode.class);
            startActivity(intent);
        });

        exit_grid_4By4_select_btn.setOnClickListener(view -> {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
    }
}