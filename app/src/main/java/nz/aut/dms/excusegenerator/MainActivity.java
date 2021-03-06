package nz.aut.dms.excusegenerator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.Random;

import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.DatabaseHandler;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.Excuse;

public class MainActivity extends Activity {
    public final static String USERNAME = "nz.aut.dms.excusegenerator.USERNAME";
    public final static String SEX = "nz.aut.dms.excusegenerator.SEX";
    public final static String AGE = "nz.aut.dms.excusegenerator.AGE";
    public final static String PREFS_FILE = "PREFS_FILE";
    public static final String EXTRA_MESSAGE ="";

    private String username;
    private String sex;
    private int age;

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHandler = new DatabaseHandler(getApplicationContext());
        getSharedPrefs();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Will send the user to the excuseView.
     * @param view
     */
    public void randomExcuseIntent(View view) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.button);
        mp.start();
        Intent intent = new Intent(this, ExcuseOutputActivity.class);
        int min = 1;
        int dbCount = dbHandler.getExcuseCount();
        Random random = new Random();
        int randomInt = random.nextInt(dbCount) + min;
        Excuse newExcuse = dbHandler.getExcuse(randomInt);
        String message = (newExcuse.getExcuse());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getSharedPrefs();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Loading saved userinformation from file.
     * If userinformation is unavailable, the Login-button should be un-clickable.
     */
    public void getSharedPrefs() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(getString(R.string.prefs_file_saved_username), "");
        sex = sharedPreferences.getString(getString(R.string.prefs_file_saved_sex), "");
        age = sharedPreferences.getInt(getString(R.string.prefs_file_saved_age), -1);
    }


    /**
     * Will forward the user to the RegisterActivity.
     * @param view
     */
    public void onMainRegisterClick(View view) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.button);
        mp.start();
        Intent intent = new Intent(this, RegisterActivity.class);
        getSharedPrefs();
        intent.putExtra(USERNAME, username);
        intent.putExtra(AGE, age);
        intent.putExtra(SEX, sex);
        startActivity(intent);
    }

    /**
     * Will forward the user to the AboutActivity.
     * @param view
     */
    public void onAboutClick(View view) {

        ImageButton T = (ImageButton) findViewById(R.id.imageButton12);
        T.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Intent i = new Intent(getApplicationContext(), AboutActivity.class);
                startActivity(i);
            }
        });
    }
    /**
     * Will Send the user to the TailorExcuseActivity so long as they have registered
     * @param view
     */

    public void onTailorExcuse(View view) {
        getSharedPrefs();
      if (!username.equals("")){
           Intent intent = new Intent(this, TailorExcuseActivity.class);
           intent.putExtra(USERNAME, username);
           intent.putExtra(AGE, age);
           intent.putExtra(SEX, sex);
           startActivity(intent);
       } else {
           this.onMainRegisterClick(view);
        }
    }

}
