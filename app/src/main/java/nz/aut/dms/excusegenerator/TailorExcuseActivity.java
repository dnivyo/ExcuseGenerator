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
import java.util.Objects;

import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.DatabaseHandler;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.Excuse;


public class TailorExcuseActivity extends Activity implements AdapterView.OnItemSelectedListener {
    private static final String EXTRA_MESSAGE ="";
    DatabaseHandler dbHandler;
    List<Excuse> excuses;
    Spinner spinnerPerson, spinnerQuality, spinnerExcuse;
    String Person,Quality,Excuse;
     String username;
     String sexString;
     char sex;
     int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHandler = new DatabaseHandler(this);
        excuses = dbHandler.getAllExcuses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor_excuse);

        Intent intent = getIntent();
        username = intent.getStringExtra(MainActivity.USERNAME);
        sexString = intent.getStringExtra(MainActivity.SEX);
        sex =sexString.charAt(0);
        ArrayList<String> personList  = new ArrayList<String>();
        personList.add("parents");
        personList.add("student");
        personList.add("teacher");
        personList.add("friend");
        personList.add("employer");
        personList.add("lover");
        ArrayList<String> qualityList  = new ArrayList<String>();
        qualityList.add("good");
        qualityList.add("bad");
        final ArrayList<String> Excuse  = new ArrayList<String>();
        Excuse.add("Please Select Your Target First");

        spinnerPerson = (Spinner) findViewById(R.id.spinPerson);
        ArrayAdapter<String> adapterPerson = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, personList);
        spinnerPerson.setAdapter(adapterPerson);
        spinnerPerson.setOnItemSelectedListener(this);

        spinnerQuality = (Spinner) findViewById(R.id.spinQuality);
        ArrayAdapter<String> adapterQuality = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,qualityList);
        spinnerQuality.setAdapter(adapterQuality);
        spinnerQuality.setOnItemSelectedListener(this);

        spinnerExcuse = (Spinner) findViewById(R.id.spinExcuse);
            ArrayAdapter<String> adapterExcuse = new ArrayAdapter<String>(this,
                    android.R.layout.simple_list_item_1, Excuse);
            spinnerExcuse.setAdapter(adapterExcuse);
        spinnerExcuse.setOnItemSelectedListener(this);
    }
    public void getExcuse(View view) {
        String inputPerson="";
        String inputQuality="";
        Person = spinnerPerson.getSelectedItem().toString();
        Quality = spinnerQuality.getSelectedItem().toString();

        if (Person == "parents") {
            inputPerson = "p";
        }
        if (Person == "student") {
            inputPerson ="s";
        }
        if (Person == "teacher") {
            inputPerson ="t";
        }
        if (Person == "friend") {
            inputPerson ="f";
        }
        if (Person == "employer") {
            inputPerson ="e";
        }
        if (Person == "lover") {
            inputPerson ="l";
        }

        if(Quality == "good") {
            inputQuality = "g";
        }
        if (Quality == "bad"){
            inputQuality = "b";
        }
        ArrayAdapter<String> adapterExcuse = new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,dbHandler.getAllVailedExcuses(inputPerson, inputQuality,sex,age));
        spinnerExcuse.setAdapter(adapterExcuse);

    }
    public void shareExcuse(View view) {
        Excuse = spinnerExcuse.getSelectedItem().toString();
        if(Excuse != "Please Select Your Target First") {
            Intent intent = new Intent(this, ExcuseOutputActivity.class);
            String message = (Excuse);
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
        switch(parent.getId()) {
            case R.id.spinPerson:

                break;
            case R.id.spinQuality:

                break;
            case R.id.spinExcuse:

                break;
            default:
                break;
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
