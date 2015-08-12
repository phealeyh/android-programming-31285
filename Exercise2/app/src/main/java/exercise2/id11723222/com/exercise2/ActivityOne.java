package exercise2.id11723222.com.exercise2;

import android.app.Activity;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

public class ActivityOne extends Activity{

    private final static String EXTRA_MESSAGE = "exercise2.id11723222.com.exercise2";
    private static final String PHONE = "phone";
    private Button submitButton, clearAllButton, exitButton;
    private final Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one);
        listenForSubmitButton();
        listenForClearAllButton();
        listenForExitButton();

    }


    private void listenForSubmitButton(){
        submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new OnClickListener() { //anonymous inner class
            @Override
            public void onClick(View v) {
                showMessage();
                Intent intent = new Intent(context, ActivityTwo.class);
                intent.putExtra("name", ((EditText) findViewById(R.id.editText)).getText().toString());
                intent.putExtra("email", ((EditText) findViewById(R.id.editText2)).getText().toString());
                intent.putExtra(PHONE, ((EditText) findViewById(R.id.editText3)).getText().toString());
                startActivityForResult(intent, 1);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(data.getBooleanExtra("Selected",false)){
            userAccepted();
        }
        else{
            userDeclined();
        }
    }

    private void userDeclined(){
        Context context = getApplicationContext();
        CharSequence text = getString(R.string.text);
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void userAccepted(){
        Context context = getApplicationContext();
        CharSequence text = "User Accepted";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }



    private void listenForClearAllButton() {

        clearAllButton = (Button) findViewById(R.id.clearButton);
        clearAllButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner) findViewById(R.id.entries);
                EditText text = (EditText) findViewById(R.id.editText);
                text.setText("Your name");
                text = (EditText) findViewById(R.id.editText2);
                text.setText("Your email");
                text = (EditText) findViewById(R.id.editText3);
                text.setText("Your phone");
                spinner.setSelection(0); //sets spinner array to the first element
                showClearAllMessage();
            }
        });
    }
    private void listenForExitButton(){
        exitButton = (Button)findViewById(R.id.exitButton);
        exitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                showExitMessage();
                android.os.Process.killProcess(android.os.Process.myPid());
                System.exit(1);

            }
        });

    }

    private void showMessage(){
        Context context = getApplicationContext();
        CharSequence text = "Submitted";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

    }


    private void showClearAllMessage(){
        Context context = getApplicationContext();
        CharSequence text = "Cleared";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    private void showExitMessage(){
        Context context = getApplicationContext();
        CharSequence text = "Exited";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

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
        if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
