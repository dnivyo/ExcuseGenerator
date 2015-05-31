package nz.aut.dms.excusegenerator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;


public class RegisterActivity extends Activity {

    private String username;
    private String sex;
    private int age;
    public final String AGE = "[0-9]";
    public final String AGE_LENGTH = "{1,2}";
    public final String USERNAME = "[a-zA-Z������-]";
    public final String USERNAME_LENGTH = "{2,20}";
    EditText usernameView;
    ToggleButton sexView;
    EditText ageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernameView = (EditText) findViewById(R.id.usernameRegister);
        sexView = (ToggleButton) findViewById(R.id.sexRegister);
        ageView = (EditText) findViewById(R.id.ageRegister);
        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USERNAME);
        if (!username.equals("")) {
            sex = intent.getStringExtra(MainActivity.SEX);
            age = intent.getIntExtra(MainActivity.AGE, -1);
            usernameView.setText(username);
            if (sex.charAt(0) == 'f') {
                sexView.setChecked(true);
            } else {
                sexView.setChecked(false);
            }
            ageView.setText(age+"");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_register, menu);
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
     * Getting input. Saving it in the preference file, stores it in the intent and
     * forwards the user to the Tailor Excuse Activity
     * @param view
     */
    public void onRegisterButtonClick(View view) {


        usernameView = (EditText) findViewById(R.id.usernameRegister);
        sexView = (ToggleButton) findViewById(R.id.sexRegister);
        ageView = (EditText) findViewById(R.id.ageRegister);

        username = usernameView.getText().toString();
        if (sexView.isChecked()) {
            sex = "f";
        } else {
            sex = "m";
        }

        if (isValidAge(ageView.getText().toString()) && isValidUsername(username)) {
            Intent intent = new Intent(this, TailorExcuseActivity.class);
            age = Integer.parseInt(ageView.getText().toString());

            intent.putExtra(MainActivity.USERNAME, username);
            intent.putExtra(MainActivity.SEX, sex);
            intent.putExtra(MainActivity.AGE, age);

            SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFS_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.prefs_file_saved_username), username);
            editor.putString(getString(R.string.prefs_file_saved_sex), String.valueOf(sex));
            //editor.putInt(getString(R.string.prefs_file_saved_age), age);
            editor.commit();

            startActivity(intent);
        } else if (!isValidAge(ageView.getText().toString())) {
            Toast.makeText(this, "Please enter a valid age (0-99) and username", Toast.LENGTH_SHORT).show();
            SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFS_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putInt(getString(R.string.prefs_file_saved_age), age);

        } else {

            SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFS_FILE, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString(getString(R.string.prefs_file_saved_username), username);
            editor.putString(getString(R.string.prefs_file_saved_sex), String.valueOf(sex));
            editor.putInt(getString(R.string.prefs_file_saved_age), age);
            editor.commit();

        }


    }

    public boolean isValidUsername(String username){
        return username.matches("^" + USERNAME + USERNAME_LENGTH);
    }
    public boolean isValidAge(String age) {
        return age.matches("^" + AGE + AGE_LENGTH);
    }
}
