package nz.aut.dms.excusegenerator;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.DatabaseHandler;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.Excuse;


public class TailorExcuseActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private static final String EXTRA_MESSAGE ="";
    DatabaseHandler dbHandler;
    List<Excuse> excuses;
    Spinner spinnerPerson, spinnerQuality, spinnerExcuse;
    String Person,Quality,Excuse;
    private String username;
    private char sex;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHandler = new DatabaseHandler(this);
        excuses = dbHandler.getAllExcuses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor_excuse);
        // seb Added Code


        final ArrayList<String> Excuse  = new ArrayList<String>();
        Excuse.add("Please Select Your Target First");

        spinnerPerson = (Spinner) findViewById(R.id.spinPerson);
        ArrayAdapter<String> adapterPerson = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, dbHandler.getAllPersons());
        spinnerPerson.setAdapter(adapterPerson); // Apply the adapter to the spinner
        spinnerPerson.setOnItemSelectedListener(this);

        /* Method 2 - Use String Array in string resources */
        spinnerQuality = (Spinner) findViewById(R.id.spinQuality);
        ArrayAdapter<String> adapterQuality = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,dbHandler.getAllQuality());
        spinnerQuality.setAdapter(adapterQuality);
        spinnerQuality.setOnItemSelectedListener(this);

        spinnerExcuse = (Spinner) findViewById(R.id.spinExcuse);
            ArrayAdapter<String> adapterExcuse = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, Excuse);
            spinnerExcuse.setAdapter(adapterExcuse); // Apply the adapter to the spinner
        spinnerExcuse.setOnItemSelectedListener(this);
        //seb Added code
    }
    //seb
    public void getExcuse(View view) {
        Person = spinnerPerson.getSelectedItem().toString();
        Quality = spinnerQuality.getSelectedItem().toString();
        showToast("Spinner1: Person=" + Person + ",Spinner2: Person=" + Quality);

        //test
        sex='g';
        age=20;
        ArrayAdapter<String> adapterExcuse = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,dbHandler.getAllVailedExcuses(Person, Quality,sex,age));
        spinnerExcuse.setAdapter(adapterExcuse);
        //test
    }
    public void shareExcuse(View view) {
        Excuse = spinnerExcuse.getSelectedItem().toString();
        if(Excuse != "Please Select Your Target First") {
            Intent intent = new Intent(this, ExcuseOutputActivity.class);
            String message = ("Dear " + Person + " " + Excuse);
            intent.putExtra(EXTRA_MESSAGE, message);
            startActivity(intent);
        }
    }
    //seb
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_tailor_excuse, menu);
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


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
       //Seb
        switch(parent.getId()) {
            case R.id.spinPerson:
                // Do stuff for spinner1
                showToast("Spinner1: position=" + position );
                break;
            case R.id.spinQuality:
                // Do stuff for spinner2
                showToast("In switch-statement for spinner2. Value=" + parent.getItemAtPosition(position));
                break;
            case R.id.spinExcuse:
                // Do stuff for spinner2
                showToast("In switch-statement for spinner3. Value=" + parent.getItemAtPosition(position));
                break;
            default:
                showToast("Read error log"); // DEBUG
                Log.d("DEBUG", "a different spinner was selected");
                break;
            //seb
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
       //seb
        Toast.makeText(this, "You selected nothing", Toast.LENGTH_LONG).show();
        //seb
    }
    //seb
    void showToast(CharSequence msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
    //seb
}
