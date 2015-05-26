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
import android.widget.TextView;

import java.util.Random;

import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.DatabaseHandler;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.Excuse;

//The development team consists of:
//Oeyvind,
//Ingvild
//David
//
public class MainActivity extends Activity {
    public final static String USERNAME = "nz.aut.dms.excusegenerator.USERNAME";
    public final static String SEX = "nz.aut.dms.excusegenerator.SEX";
    public final static String AGE = "nz.aut.dms.excusegenerator.AGE";
    public final static String PREFS_FILE = "PREFS_FILE";
    public static final String EXTRA_MESSAGE ="";


    private String username;
    private char sex;
    private int age;





    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHandler = new DatabaseHandler(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //MediaPlayer mp = MediaPlayer.create(MainActivity.this,R.raw.concerto);
        //mp.start();
        int dbCount = dbHandler.getExcuseCount();
        TextView dbCountV = (TextView) findViewById(R.id.textView);
        dbCountV.setText("Db: " + dbCount);
        Excuse newExcuse = dbHandler.getExcuse(1);
        TextView excuseV = (TextView) findViewById(R.id.textView2);
        excuseV.setText("Excuse: " + newExcuse.getExcuse() + " MinAge:" + newExcuse.getMinAge()
                + " MaxAge: " + newExcuse.getMaxAge() + " Sex: " + newExcuse.getSex()
                + " Used on: " + newExcuse.getUsedOn());

    }
    public void randomExcuseIntent(View view){
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.button);
        mp.start();
        Intent intent = new Intent(this, ExcuseOutputActivity.class);
        int min =1;
        int dbCount = dbHandler.getExcuseCount();
       Random random =new Random();
        int randomInt = random.nextInt(dbCount)+min;
                Excuse newExcuse = dbHandler.getExcuse(randomInt);
        String message=(newExcuse.getExcuse());
        intent.putExtra(EXTRA_MESSAGE, message);
        startActivity(intent);

    }

    public void profileRegister(View view){
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.button);
        mp.start();
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);

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

    public void soundPlay (View view) {


        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.button);
        mp.start();

    }
    /**
     * Loading saved userinformation from file.
     * If userinformation is unavailable, the Login-button should be un-clickable.
     */
    public void getPrefsOnStartu() {
        SharedPreferences sharedPreferences = this.getSharedPreferences(PREFS_FILE, Context.MODE_PRIVATE);
        username = sharedPreferences.getString(getString(R.string.prefs_file_saved_username), "");
        sex = sharedPreferences.getString(getString(R.string.prefs_file_saved_sex), "").charAt(0);
        age = sharedPreferences.getInt(getString(R.string.prefs_file_saved_age), 0);

        if (username.equals("")) {
            //Set loginbutton clickable to false.
        }
    }

    /**
     * Will send the user to the excuseView.
     * @param view
     */
    public void onMainRandomExcuseClick(View view) {

    }

    /**
     * Will forward the user to the MainLoggedinActivity.
     * @param view
     */
    public void onMainLoginClick(View view) {
        Intent intent = new Intent(this, MainLoggedinActivity.class);
        intent.putExtra(USERNAME, username);
        intent.putExtra(SEX, sex);
        intent.putExtra(AGE, age);
        startActivity(intent);
    }

    /**
     * Will forward the user to the RegisterActivity.
     * @param view
     */
    public void onMainRegisterClick(View view) {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void onAboutClick(View view) {

    }

}
