package exercise3.id11723222.com.exercise3;


import android.app.Activity;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityOne extends Activity{

    private static final String TAG = "MAD";
    private Button clearButton, resetButton, rotateButton; //use m variables
    private TextView binView;
    private EditText typeText, sizeText, nrText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        listenForNrField();
        listenForClearButton();
        listenForResetButton();
        listenForTypeText();
        listenForSizeText();
        listenForRotateButton();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putString("Type", ((EditText) findViewById(R.id.typeView)).getText().toString());
        super.onSaveInstanceState(outState);
        Log.d(TAG, "In the onSaveInstanceState() event");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState){
        typeText.setText(savedInstanceState.get("Type").toString());
        super.onRestoreInstanceState(savedInstanceState);
        Log.d(TAG, "In the onRestoreInstanceState() event");
    }


    public void onStart(){
        super.onStart();
        Log.d(TAG, "In the onStart() event");
    }

    public void onRestart(){
        super.onRestart();
        Log.d(TAG,"In the onRestart() event");
    }

    public void onResume(){
        super.onResume();
        Log.d(TAG,"In the onResume() event");
    }

    public void onPause(){
        super.onPause();
        Log.d(TAG,"In the onPause() event");
    }

    public void onStop(){
        super.onStop();
        Log.d(TAG,"In the onStop() event");
    }

    public void onDestroy(){
        super.onDestroy();
        Log.d(TAG, "In the onDestroy() event");
    }


    private void listenForClearButton(){
            clearButton = (Button) findViewById(R.id.clearButton);
            clearButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        EditText text = (EditText) findViewById(R.id.sizeView);
                        text.setText("");
                        text = (EditText) findViewById(R.id.nrView);
                        text.setText("");
                        text = (EditText) findViewById(R.id.typeView);
                        text.setText("");
                    }catch(Exception e){
                        Log.e(TAG,"Error received");
                    }
                }
            });
    }

    private void listenForRotateButton(){
        rotateButton = (Button) findViewById(R.id.rotateButton);
        rotateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rotateScreen();
                Log.d(TAG,"Rotate button clicked");
            }
        });
    }

    private void rotateScreen(){
        Configuration config = getResources().getConfiguration();
        if(config.orientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT){ //in potrait mode
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{ //current configuration is set to landscape
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        }
    }

    private void listenForResetButton(){
        resetButton = (Button) findViewById(R.id.resetButton);
        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText text = (EditText) findViewById(R.id.nrView);
                text.setText("");
            }
        });
    }


    private void listenForNrField(){
        nrText = (EditText)findViewById(R.id.nrView);
        nrText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Context context = getApplicationContext();
                if(hasFocus) {
                    CharSequence text = "Bin Quantity HAS focus";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();

                } else {
                    CharSequence text = "Bin Quantity LOST focus";
                    Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void listenForTypeText(){
        typeText = (EditText)findViewById(R.id.typeView);
        typeText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    CharSequence text = "Bin Quantity LOST focus";
                    Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void listenForSizeText(){
        sizeText = (EditText)findViewById(R.id.sizeView);
        sizeText.setOnFocusChangeListener(new OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {
                    CharSequence text = "Bin Quantity LOST focus";
                    Toast.makeText(getApplication(), text, Toast.LENGTH_SHORT).show();
                }
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_one, menu);
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
