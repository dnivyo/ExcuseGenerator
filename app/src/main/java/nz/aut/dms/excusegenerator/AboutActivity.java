package nz.aut.dms.excusegenerator;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;


public class AboutActivity extends ActionBarActivity {


//    String abouth = getResources().getString(R.string.about_h);
//    String aboutp1 = getResources().getString(R.string.about_p1);
//    String aboutp2 = getResources().getString(R.string.about_p2);
//
//    String infoh = getResources().getString(R.string.information_h);
//    String infop1 = getResources().getString(R.string.information_p1);
//    String infop2 = getResources().getString(R.string.information_p2);
//    String infop3 = getResources().getString(R.string.information_p3);
    //public static final String EXTRA_MESSAGE ="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        //Intent intent = getIntent();
        //String value = intent.getStringExtra("key");
        //String message = intent.getStringExtra(AboutActivity.EXTRA_MESSAGE);


//        TextView view1 = (TextView) findViewById(R.id.abouth);
//        view1.setText(abouth);
//        TextView view2 = (TextView) findViewById(R.id.aboutp1);
//        view1.setText(aboutp1);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        //getMenuInflater().inflate(R.menu.menu_about, menu);
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
