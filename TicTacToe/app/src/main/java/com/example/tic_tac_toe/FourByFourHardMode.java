package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import java.util.Objects;

public class FourByFourHardMode extends AppCompatActivity {
    private final String[][] board = {
            {" ", " ", " ", " "},
            {" ", " ", " ", " "},
            {" ", " ", " ", " "},
            {" ", " ", " ", " "}
    };

    private final Button[][] buttons = new Button[4][4];
    private boolean isPlayerTurn = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_four_by_four_hard_mode);
        Toast.makeText(this, "Playing Hardcore 4x4", Toast.LENGTH_SHORT).show();

        initButtons();

        Button goto_main_four_by_four_hardcore_btn = findViewById(R.id.goto_main_four_by_four_hardcore);

        goto_main_four_by_four_hardcore_btn.setOnClickListener(view -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }

    private void initButtons() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                @SuppressLint("DiscouragedApi") int btnId = getResources().getIdentifier("hardcore_four_by_four_box_" + i + "_" + j, "id", getPackageName());
                buttons[i][j] = findViewById(btnId);
                int finalI = i;
                int finalJ = j;
                buttons[i][j].setOnClickListener(view -> onButtonClick(finalI, finalJ));
            }
        }
    }

    private void onButtonClick(int row, int col) {
        if (board[row][col].equals(" ") && isPlayerTurn) {
            makeMove(row, col, "X");
            checkGameStatus();
            makeComputerMove();
            checkGameStatus();
        }
    }

    private void makeMove(int row, int col, String player) {
        board[row][col] = player;
        buttons[row][col].setText(player);
        isPlayerTurn = !isPlayerTurn;
    }

    private void makeComputerMove() {
        int[] bestMove = findBestMove(board);
        makeMove(bestMove[0], bestMove[1], "O");
    }

    private void resetGame() {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                board[i][j] = "";
                buttons[i][j].setText("");
                buttons[i][j].setEnabled(true);
            }
        }
        isPlayerTurn = true;
    }

    private void checkGameStatus() {
        if (checkWinner(board).equals("X")) {
            resetGame();
        } else if (checkWinner(board).equals("O")) {
            Toast.makeText(this, "You lost against 20 lines of code", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, LostAgainstHardMode.class);
            startActivity(intent);
        } else if (isBoardFull(board)) {
            Toast.makeText(this, "It's a draw", Toast.LENGTH_SHORT).show();
            resetGame();
        }
    }

    private int[] findBestMove(String[][] currentBoard) {
        int[] bestMove = new int[]{-1, -1};
        int bestScore = Integer.MIN_VALUE;
        int alpha = Integer.MIN_VALUE;
        int beta = Integer.MAX_VALUE;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (currentBoard[i][j].equals(" ")) {
                    currentBoard[i][j] = "O";
                    int score = minimax(currentBoard, 0, false, alpha, beta);
                    currentBoard[i][j] = " ";

                    if (score > bestScore) {
                        bestScore = score;
                        bestMove[0] = i;
                        bestMove[1] = j;
                    }
                }
            }
        }
        return bestMove;
    }

    private int minimax(String[][] currentBoard, int depth, boolean isMaximizing, int alpha, int beta) {
        String winner = checkWinner(currentBoard);
        if (winner.equals("X"))
            return -1;
        else if (winner.equals("O"))
            return 1;
        else if (isBoardFull(currentBoard))
            return 0;

        int bestScore;
        if (isMaximizing) {
            bestScore = Integer.MIN_VALUE;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (currentBoard[i][j].equals(" ")) {
                        currentBoard[i][j] = "O";
                        bestScore = Math.max(bestScore, minimax(currentBoard, depth + 1, false, alpha, beta));
                        currentBoard[i][j] = " ";

                        alpha = Math.max(alpha, bestScore);
                        if (beta <= alpha)
                            break;
                    }
                }
            }
        }
        else {
            bestScore = Integer.MAX_VALUE;
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    if (currentBoard[i][j].equals(" ")) {
                        currentBoard[i][j] = "X";
                        bestScore = Math.min(bestScore, minimax(currentBoard, depth + 1, true, alpha, beta));
                        currentBoard[i][j] = " ";

                        beta = Math.min(beta, bestScore);
                        if (beta <= alpha)
                            break;
                    }
                }
            }
        }
        return bestScore;
    }

    private String checkWinner(String[][] currentBoard) {
        // Check rows
        for (int i = 0; i < 4; i++) {
            if (currentBoard[i][0].equals(currentBoard[i][1]) &&
                    currentBoard[i][1].equals(currentBoard[i][2]) &&
                    currentBoard[i][2].equals(currentBoard[i][3]) &&
                    !currentBoard[i][0].equals(" ")) {
                return currentBoard[i][0];
            }
        }

        // Check columns
        for (int j = 0; j < 4; j++) {
            if (currentBoard[0][j].equals(currentBoard[1][j]) &&
                    currentBoard[1][j].equals(currentBoard[2][j]) &&
                    currentBoard[2][j].equals(currentBoard[3][j]) &&
                    !currentBoard[0][j].equals(" ")) {
                return currentBoard[0][j];
            }
        }

        // Check diagonals
        if (currentBoard[0][0].equals(currentBoard[1][1]) &&
                currentBoard[1][1].equals(currentBoard[2][2]) &&
                currentBoard[2][2].equals(currentBoard[3][3]) &&
                !currentBoard[0][0].equals(" ")) {
            return currentBoard[0][0];
        }

        if (currentBoard[0][3].equals(currentBoard[1][2]) &&
                currentBoard[1][2].equals(currentBoard[2][1]) &&
                currentBoard[2][1].equals(currentBoard[3][0]) &&
                !currentBoard[0][3].equals(" ")) {
            return currentBoard[0][3];
        }

        return " "; // No winner yet
    }

    private boolean isBoardFull(String[][] currentBoard) {
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (Objects.equals(currentBoard[i][j], " "))
                    return false;
            }
        }
        return true;
    }
}