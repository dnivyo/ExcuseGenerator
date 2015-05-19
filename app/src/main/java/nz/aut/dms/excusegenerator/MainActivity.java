package nz.aut.dms.excusegenerator;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.DatabaseHandler;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.Excuse;

//The development team consists of:
//Oeyvind,
//Ingvild
//
//
public class MainActivity extends ActionBarActivity {

    DatabaseHandler dbHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        dbHandler = new DatabaseHandler(getApplicationContext());
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int dbCount = dbHandler.getExcuseCount();
        TextView dbCountV = (TextView) findViewById(R.id.textView);
        dbCountV.setText("Db: " + dbCount);
        Excuse newExcuse = dbHandler.getExcuse(1);
        TextView excuseV = (TextView) findViewById(R.id.textView2);
        excuseV.setText("Excuse: " + newExcuse.getExcuse() + " MinAge:" + newExcuse.getMinAge()
                + " MaxAge: " + newExcuse.getMaxAge() + " Sex: " + newExcuse.getSex()
                + " Used on: " + newExcuse.getUsedOn());

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

    //Will send you to the excuseView.
    public void onRandomExcuseClick(View view) {

    }
    
    public void onLoginClick(View view) {

    }

    public void onRegisterClick(View view) {

    }

    public void onAboutClick(View view) {

    }

}
