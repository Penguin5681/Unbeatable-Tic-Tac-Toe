package com.example.tic_tac_toe;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play_btn_main_menu = findViewById(R.id.play_main_menu);
        Button options_btn_main_menu = findViewById(R.id.options_main_menu);
        Button exit_btn_main_menu = findViewById(R.id.exit_main_menu);

        play_btn_main_menu.setOnClickListener(view -> {
            Intent intent = new Intent(this, GridLevelSelectionScreen.class);
            startActivity(intent);
        });

        options_btn_main_menu.setOnClickListener(view -> {
            Toast.makeText(this, "Under Development", Toast.LENGTH_SHORT).show();
        });

        exit_btn_main_menu.setOnClickListener(view -> {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        });
    }
}