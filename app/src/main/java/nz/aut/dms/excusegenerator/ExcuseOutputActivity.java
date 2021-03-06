package nz.aut.dms.excusegenerator;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.DatabaseHandler;
import nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities.Excuse;


public class ExcuseOutputActivity extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_excuse_output);
        Intent intent = getIntent();
        String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        EditText messageText = (EditText) findViewById(R.id.outPutText);
        messageText.setText(message);

    }


    public void musicStop(View v) {

        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.concerto);
        mp.stop();

    }

    public void soundPlay(View v) {


        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.button);
        mp.start();

    }

    public void share(View view) {
        MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.button);
        mp.start();
        Intent sharingIntent = new Intent();
        sharingIntent.setAction(Intent.ACTION_SEND);
        sharingIntent.setType("text/plain");
        EditText editText = (EditText) findViewById(R.id.outPutText);
        String shareMessage = editText.getText().toString();
        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareMessage);
        startActivity(Intent.createChooser(sharingIntent, "Share via"));
        //mp.start();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_excuse_output, menu);
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

    public void onShareClick(View view) {

    }
}
