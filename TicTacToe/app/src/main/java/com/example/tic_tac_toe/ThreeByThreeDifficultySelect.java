package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class ThreeByThreeDifficultySelect extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three_by_three_difficulty_select);

        Button easy_level_3By3_select_btn = findViewById(R.id.easy_level_3By3_select);
        Button hardcore_level_3By3_select_btn = findViewById(R.id.hardcore_level_3By3_select);
        Button exit_grid_3By3_select_btn = findViewById(R.id.exit_grid_3By3_select);

        easy_level_3By3_select_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, PlayScreen.class);
            startActivity(intent);
        });

        hardcore_level_3By3_select_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, hardcore_mode.class);
            startActivity(intent);
        });

        exit_grid_3By3_select_btn.setOnClickListener(view -> {
            Intent intent = new Intent();
        });
    }
}