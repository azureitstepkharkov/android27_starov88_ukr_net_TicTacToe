package tictactoe.kharkov.itstep.org.tictactoe.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

import tictactoe.kharkov.itstep.org.tictactoe.R;
import tictactoe.kharkov.itstep.org.tictactoe.game.Game;

public class AuthActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);

        findViewById(R.id.btnGo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Go();
            }
        });

        findViewById(R.id.btnBack).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Back();
            }
        });
    }

    private void Go(){
        EditText playerX = (EditText) findViewById(R.id.tbPlayerX);
        EditText playerO = (EditText) findViewById(R.id.tbPlayerO);

        String playerXtext = playerX.getText().toString().trim();
        String playerOtext = playerO.getText().toString().trim();

        if(playerXtext.isEmpty() || playerOtext.isEmpty() || playerOtext.equals(playerXtext)){

            Toast.makeText(this, "Введите разные имена двух игроков", Toast.LENGTH_SHORT).show();
        }
        else{
            Game.Players = new HashMap<String, String>();
            Game.Players.put("O", playerOtext);
            Game.Players.put("X", playerXtext);

            if(!Game.SharedPref.contains(Game.Players.get("X"))){
                SharedPreferences.Editor ed = Game.SharedPref.edit();
                ed.putInt(Game.Players.get("X"), 0);
                ed.commit();
            }
            if(!Game.SharedPref.contains(Game.Players.get("O"))){
                SharedPreferences.Editor ed = Game.SharedPref.edit();
                ed.putInt(Game.Players.get("O"), 0);
                ed.commit();
            }

            Intent intent = new Intent(AuthActivity.this, GameActivity.class);
            startActivity(intent);
        }
    }

    private void Back() {
        Intent intent = new Intent(AuthActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
