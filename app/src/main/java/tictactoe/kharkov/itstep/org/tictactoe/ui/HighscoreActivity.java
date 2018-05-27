package tictactoe.kharkov.itstep.org.tictactoe.ui;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.ArrayMap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;

import tictactoe.kharkov.itstep.org.tictactoe.R;
import tictactoe.kharkov.itstep.org.tictactoe.game.Game;
import tictactoe.kharkov.itstep.org.tictactoe.helpers.MapUtil;

public class HighscoreActivity extends AppCompatActivity {

    int[] colors = new int[2];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_highscore);

        colors[1] = Color.WHITE;
        colors[0] = Color.LTGRAY;

        LinearLayout linLayout = (LinearLayout) findViewById(R.id.linearList);
        LayoutInflater ltInflater = getLayoutInflater();

        Map<String, Integer> players = (Map<String, Integer>)Game.SharedPref.getAll();

        players = MapUtil.sortByValue(players);

        int i = 0;
        for (Map.Entry entry : players.entrySet()) {
            i++;
            Log.d("myLogs", "k = " + entry.getKey() + " - v = " + entry.getValue());

            View item = ltInflater.inflate(R.layout.item, linLayout, false);
            TextView name = (TextView)item.findViewById(R.id.playerName);
            name.setText(entry.getKey().toString());
            TextView score = (TextView)item.findViewById(R.id.playerScore);
            score.setText(entry.getValue().toString());
            item.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;
            item.setBackgroundColor(colors[i % 2]);
            linLayout.addView(item);

        }
    }
}
