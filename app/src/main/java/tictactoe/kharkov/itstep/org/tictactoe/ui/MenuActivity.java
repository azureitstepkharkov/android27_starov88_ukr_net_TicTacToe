package tictactoe.kharkov.itstep.org.tictactoe.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;

import tictactoe.kharkov.itstep.org.tictactoe.R;
import tictactoe.kharkov.itstep.org.tictactoe.game.Game;


public class MenuActivity extends AppCompatActivity {

    final int DIALOG = 1;
    Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        Game.SharedPref = getPreferences(MODE_PRIVATE);

        findViewById(R.id.newGame).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AuthActivity.class);
                startActivity(intent);
                //showDialog(DIALOG);
            }
        });

        findViewById(R.id.records).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, HighscoreActivity.class);
                startActivity(intent);
            }
        });

        findViewById(R.id.about).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MenuActivity.this, AboutActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG) {
            Log.d("Dialog", "Create");
            AlertDialog.Builder adb = new AlertDialog.Builder(this);
            adb.setTitle("Title");
            adb.setMessage("Message");
            adb.setPositiveButton("OK", null);
            adb.setNegativeButton("Cancel", null);
            dialog = adb.create();
// обработчик отображения
            dialog.setOnShowListener(new DialogInterface.OnShowListener() {
                public void onShow(DialogInterface dialog) {
                    Log.d("Dialog", "Show");
                }
            });
// обработчик отмены
            dialog.setOnCancelListener(new OnCancelListener() {
                public void onCancel(DialogInterface dialog) {
                    Log.d("Dialog", "Cancel");
                }
            });
            dialog.setOnDismissListener(new OnDismissListener() {
                public void onDismiss(DialogInterface dialog) {
                    Log.d("Dialog", "Dismiss");
                }
            });
            return dialog;
        }
        return super.onCreateDialog(id);
    }


    //https://stackoverflow.com/questions/5235237/dialog-box-or-form-where-user-can-choose-to-enter-details-or-cancel


}