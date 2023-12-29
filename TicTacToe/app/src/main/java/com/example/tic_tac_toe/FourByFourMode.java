package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;
import java.util.Random;

public class FourByFourMode extends AppCompatActivity {

    String[][] board = new String[4][4];
    String currentPlayer = "O";
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_by_four_mode);

        initializeBoard();
        setBtnListeners();

        Toast.makeText(this, "Playing 4x4 Easy", Toast.LENGTH_SHORT).show();

        Button main_menu_4_by_4_btn = findViewById(R.id.goto_main_four_by_four);
        main_menu_4_by_4_btn.setOnClickListener(view -> {
            resetGame();
        });

    }

    private void initializeBoard() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = " ";
            }
        }
    }

    private void onCellClick(int row, int col) {
        Log.d("Tic-Tac-Toe", "Clicked on cell: " + "(" + row + "," + col + ")");
        if (board[row][col].equals(" ")) {
            @SuppressLint("DiscouragedApi") int btnId = getResources().getIdentifier("four_by_four" + "_box_" + row + "_" + col, "id", getPackageName());
            Button btn = findViewById(btnId);
            btn.setText(String.valueOf(currentPlayer));
            board[row][col] = currentPlayer;

            if (checkForWinner()) {
                Intent intent = new Intent(this, ResultScreenForEasyWin.class);
                startActivity(intent);
            }
            else if (isBoardFull()) {
                showResult("It's a draw");
            }
            else {
                currentPlayer = (Objects.equals(currentPlayer, "X")) ? "O" : "X";

                if (currentPlayer.equals("X"))
                    computerMove();
            }

        }
    }

    private void computerMove() {
        int r, c;
        do {
            r = random.nextInt(4);
            c = random.nextInt(4);
        } while (!Objects.equals(board[r][c], " "));

        @SuppressLint("DiscouragedApi") int btnId = getResources().getIdentifier("four_by_four" + "_box_" + r + "_" + c, "id", getPackageName());
        Button btn = findViewById(btnId);
        btn.setText("X");
        board[r][c] = "X";

        if (checkForWinner()) {
            showResult("Lmao. You Lose");
        } else if (isBoardFull()) {
            Toast.makeText(this, "It's a draw", Toast.LENGTH_SHORT).show();
        } else {
            currentPlayer = "O";
        }
    }

    private void showResult(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        resetGame();
    }

    private void resetGame() {
        initializeBoard();
        setBtnListeners();

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                @SuppressLint("DiscouragedApi") int btnId = getResources().getIdentifier("four_by_four" + "_box_" + i + "_" + j, "id", getPackageName());
                Button btn = findViewById(btnId);
                btn.setText(" ");
            }
        }

        if (currentPlayer.equals("X"))
            computerMove();
    }

    private boolean checkForWinner() {
        // row
        for (int i = 0; i < 4; i++) {
            if (Objects.equals(board[i][0], currentPlayer) && Objects.equals(board[i][1], currentPlayer) && Objects.equals(board[i][2], currentPlayer) && Objects.equals(board[i][3], currentPlayer))
                return true;
        }
        // column
        for (int j = 0; j < 4; j++) {
            if (Objects.equals(board[0][j], currentPlayer) && Objects.equals(board[1][j], currentPlayer) && Objects.equals(board[2][j], currentPlayer) && Objects.equals(board[3][j], currentPlayer))
                return true;
        }

        if (Objects.equals(board[0][3], currentPlayer) && Objects.equals(board[1][2], currentPlayer) && Objects.equals(board[2][1], currentPlayer) && Objects.equals(board[3][0], currentPlayer))
                return true;

        return (Objects.equals(board[0][0], currentPlayer) && Objects.equals(board[1][1], currentPlayer) && Objects.equals(board[2][2], currentPlayer) && Objects.equals(board[3][3], currentPlayer));
    }

    private boolean isBoardFull() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(board[i][j], " "))
                    return false;
            }
        }
        return true;
    }

    private void setBtnListeners() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                @SuppressLint("DiscouragedApi") int btnId = getResources().getIdentifier("four_by_four" + "_box_" + i + "_" + j, "id", getPackageName());
                Button btnFourByFour = findViewById(btnId);
                final int finalI = i;
                final int finalJ = j;
                btnFourByFour.setOnClickListener(view -> {
                    onCellClick(finalI, finalJ);
                });
            }
        }
    }
}