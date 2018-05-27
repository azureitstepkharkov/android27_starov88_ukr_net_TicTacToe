package tictactoe.kharkov.itstep.org.tictactoe.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.graphics.Color;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import tictactoe.kharkov.itstep.org.tictactoe.R;
import tictactoe.kharkov.itstep.org.tictactoe.game.Game;

public class GameActivity extends AppCompatActivity {
    private Game game;
    private List<Button> buttons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        initGameBoard();
        game = new Game(3);
    }

    private void initGameBoard() {
        LinearLayout gameBoard = (LinearLayout) findViewById(R.id.gameBoard);
        buttons = new ArrayList<>();
        for (int i = 0; i < gameBoard.getChildCount(); i++) {
            if (gameBoard.getChildAt(i) instanceof LinearLayout) {
                LinearLayout layout = (LinearLayout) gameBoard.getChildAt(i);
                for (int j = 0; j < layout.getChildCount(); j++) {
                    Button button = (Button) layout.getChildAt(j);
                    buttons.add(button);
                    button.setOnClickListener(new GameBoardButtonOnClickListener(j, i));
                }
            }
        }
    }

    private void cleanUpBoard() {
        for (Button button : buttons) {
            button.setText("");
            button.setEnabled(true);
        }
    }

    private class GameBoardButtonOnClickListener implements View.OnClickListener {
        private int x = 0;
        private int y = 0;

        public GameBoardButtonOnClickListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void onClick(View view) {
            Game.Player player = game.getCurrentPlayer();
            game.makeTurn(x, y);

            Button button = (Button) view;
            button.setText(player.toString());
            if (player.equals(Game.Player.PLAYER_1)) {
                button.setTextColor(Color.BLACK);
            } else {
                button.setTextColor(Color.BLUE);
            }
            button.setEnabled(false);

            if (game.isFinished()) {
                Game.Player winner = game.getWinner();
                Toast toast;
                if (winner == null) {
                    toast = Toast.makeText(getApplicationContext(), getString(R.string.no_one_won), Toast.LENGTH_SHORT);
                } else {
                    Game.SaveStatistic(winner);
                    toast = Toast.makeText(getApplicationContext(), getString(R.string.player_won, winner.toString()), Toast.LENGTH_SHORT);
                }
                toast.show();
                game.reset();
                cleanUpBoard();
            }
        }
    }
}