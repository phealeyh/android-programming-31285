package exercise3.id11723222.com.exercise3;

import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityOne extends ActionBarActivity{
    private static final String TAG = "TAG";
    private Button clearButton, resetButton;
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
                    EditText text = (EditText) findViewById(R.id.sizeView);
                    text.setText("");
                    text = (EditText) findViewById(R.id.nrView);
                    text.setText("");
                    text = (EditText) findViewById(R.id.typeView);
                    text.setText("");
                }
            });
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
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();

                } else {
                    CharSequence text = "Bin Quantity LOST focus";
                    Toast.makeText(context, text, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplication(), text, Toast.LENGTH_LONG).show();
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
                    Toast.makeText(getApplication(), text, Toast.LENGTH_LONG).show();
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
