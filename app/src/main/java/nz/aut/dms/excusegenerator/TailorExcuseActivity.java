package nz.aut.dms.excusegenerator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.util.List;

import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.DatabaseHandler;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.Excuse;


public class TailorExcuseActivity extends ActionBarActivity {
    DatabaseHandler dbHandler;
    List<Excuse> excuses;

    private String username;
    private char sex;
    private int age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHandler = new DatabaseHandler(this);
        excuses = dbHandler.getAllExcuses();
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tailor_excuse);
    }

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



}
