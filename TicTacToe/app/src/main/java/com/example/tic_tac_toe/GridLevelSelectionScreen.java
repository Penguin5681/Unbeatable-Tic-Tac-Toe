package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class GridLevelSelectionScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_level_selection_screen);

        Button three_by_three_grid_select_btn = findViewById(R.id.three_by_three_grid_select);
        Button four_by_four_grid_select_btn = findViewById(R.id.four_by_four_grid_select);
        Button exit_grid_select_btn = findViewById(R.id.exit_grid_select);

        three_by_three_grid_select_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, ThreeByThreeDifficultySelect.class);
            startActivity(intent);
        });

        four_by_four_grid_select_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, FourByFourDifficultySelect.class);
            startActivity(intent);
        });

        exit_grid_select_btn.setOnClickListener(view -> {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
    }
}