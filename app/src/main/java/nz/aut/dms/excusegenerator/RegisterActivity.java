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
import android.widget.Switch;


public class RegisterActivity extends Activity {

    private String username;
    private char sex;
    private int age;
    EditText usernameView;
    Switch sexView;
    EditText ageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        EditText usernameView = (EditText) findViewById(R.id.usernameRegister);
        Switch sexView = (Switch) findViewById(R.id.sexRegister);
        EditText ageView = (EditText) findViewById(R.id.ageRegister);
        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USERNAME);
        sex = intent.getStringExtra(MainActivity.SEX).charAt(0);
        age = Integer.parseInt(intent.getStringExtra(MainActivity.AGE));
        if (!username.equals("")){
            usernameView.setText(username);
            //sexView.setChecked();
            ageView.setText(age);
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
        Intent intent = new Intent(this, TailorExcuseActivity.class);

        EditText usernameView = (EditText) findViewById(R.id.usernameRegister);
        Switch sexView = (Switch) findViewById(R.id.sexRegister);
        EditText ageView = (EditText) findViewById(R.id.ageRegister);

        username = usernameView.getText().toString();
        sex = sexView.getText().charAt(0); //not 100% sure if this will work
        age = Integer.parseInt(ageView.getText().toString());

        intent.putExtra(MainActivity.USERNAME, username);
        intent.putExtra(MainActivity.SEX, sex);
        intent.putExtra(MainActivity.AGE, age);

        SharedPreferences sharedPreferences = getSharedPreferences(MainActivity.PREFS_FILE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(getString(R.string.prefs_file_saved_username), username);
        editor.putString(getString(R.string.prefs_file_saved_sex), String.valueOf(sex));
        editor.putInt(getString(R.string.prefs_file_saved_age), age);
        editor.commit();

        startActivity(intent);
    }
}
