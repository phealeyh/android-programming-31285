package exercise2.id11723222.com.exercise2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class ActivityTwo extends Activity{

    private Button submitButton;
    private final static int ACTIVITY_CODE = 2;
    private final Context context = this;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        setTextFields();
        listenForSubmitButton();
    }


    private void setTextFields(){
        TextView nameText = (TextView) findViewById(R.id.nameView);
        TextView phoneText = (TextView) findViewById(R.id.phoneView);
        TextView emailText = (TextView) findViewById(R.id.emailView);

        nameText.setText((String) getIntent().getExtras().get("name"));
        phoneText.setText((String) getIntent().getExtras().get("phone"));
        emailText.setText((String) getIntent().getExtras().get("email"));

    }



    private void listenForSubmitButton(){
        submitButton = (Button)findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ActivityOne.class);
                CheckBox check = (CheckBox) findViewById(R.id.agreeCheckBox);
                if (check.isChecked()) intent.putExtra("Selected",true);
                setResult(RESULT_FIRST_USER, intent); //data that you will send back
                finish(); //calls onActivityResult when the activity ends
                showMessage();
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



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_two, menu);
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
