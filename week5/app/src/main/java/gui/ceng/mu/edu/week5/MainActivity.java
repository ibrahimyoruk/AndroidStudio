// MainActivity.java
package gui.ceng.mu.edu.week5;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private boolean playerXTurn = true;
    private String[] board = new String[9];
    private TextView statusTextView;
    private Button[] buttons = new Button[9];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        statusTextView = findViewById(R.id.statusTextView);
        GridLayout gridLayout = findViewById(R.id.gridLayout);

        for (int i = 0; i < 9; i++) {
            buttons[i] = (Button) gridLayout.getChildAt(i);
            final int index = i;
            buttons[i].setOnClickListener(v -> onButtonClick(index));
        }

        Button resetButton = findViewById(R.id.resetButton);
        resetButton.setOnClickListener(v -> resetGame());

        resetGame();
    }

    private void onButtonClick(int index) {
        if (board[index] == null) {
            board[index] = playerXTurn ? "X" : "O";
            buttons[index].setText(board[index]);
            buttons[index].setContentDescription(board[index]);

            if (checkWin()) {
                statusTextView.setText("Player " + board[index] + " Wins!");
                disableButtons();
            } else if (isBoardFull()) {
                statusTextView.setText("It's a Draw!");
            } else {
                playerXTurn = !playerXTurn;
                statusTextView.setText("Player " + (playerXTurn ? "X" : "O") + "'s Turn");
            }
        }
    }

    private boolean checkWin() {
        int[][] winPositions = {
                {0, 1, 2}, {3, 4, 5}, {6, 7, 8}, // Satırlar
                {0, 3, 6}, {1, 4, 7}, {2, 5, 8}, // Sütunlar
                {0, 4, 8}, {2, 4, 6}             // Çaprazlar
        };

        for (int[] pos : winPositions) {
            if (board[pos[0]] != null && board[pos[0]].equals(board[pos[1]]) && board[pos[1]].equals(board[pos[2]])) {
                return true;
            }
        }
        return false;
    }

    private boolean isBoardFull() {
        for (String cell : board) {
            if (cell == null) return false;
        }
        return true;
    }

    private void resetGame() {
        playerXTurn = true;
        for (int i = 0; i < 9; i++) {
            board[i] = null;
            buttons[i].setText("");
            buttons[i].setEnabled(true);
            buttons[i].setContentDescription("Empty");
        }
        statusTextView.setText("Player X's Turn");
    }

    private void disableButtons() {
        for (Button button : buttons) {
            button.setEnabled(false);
        }
    }
}
