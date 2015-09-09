package exercise6.id11723222.com.exercise6;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class AddFriendDataActivity extends AppCompatActivity {
    private final Context CONTEXT = this;
    private Button mAddButton, mCancelButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_data);
        listenToAddButton();
        listenForCancelButton();
    }

    private void listenForCancelButton(){
        mCancelButton = (Button)findViewById(R.id.cancel_action);
        mCancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void listenToAddButton(){
        mAddButton = (Button)findViewById(R.id.add_friend);
        mAddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //put in data into database
                String name, occupation, city;
                name = ((EditText)findViewById(R.id.editText1)).getText().toString();
                occupation = ((EditText)findViewById(R.id.editText2)).getText().toString();
                city = ((EditText)findViewById(R.id.editText3)).getText().toString();
                FriendData friend = new FriendData(1,name,occupation,city,2015);
                FriendDatabaseHelper.getInstance(CONTEXT).addFriend(friend);
                Toast.makeText(CONTEXT,"Friend data successfully added", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_friend_data, menu);
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
