package exercise6.id11723222.com.exercise6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private FriendDatabaseHelper mDatabase;
    private final Context CONTEXT = this;
    private Button mAddButton, mListFriends, mSearchFriend;
    private static final String DB_NAME = "friends";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mDatabase = new FriendDatabaseHelper(this,DB_NAME,null,4);
        FriendDatabaseHelper.setDatabaseHelper(mDatabase);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPreferences(Activity.MODE_PRIVATE);
        listenToAddFriendButton();
        listenForListFriends();
        listenForSearchButton();
    }

    private void listenForListFriends(){
        mListFriends = (Button)findViewById(R.id.list_friends);
        mListFriends.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CONTEXT, ListFriendsActivity.class));
                //list selected friends from the database in a list view
            }
        });

    }

    private void listenForSearchButton(){
        mSearchFriend = (Button)findViewById(R.id.search_friend);
        mSearchFriend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CONTEXT, ListFriendsActivity.class));
                //list only selected friend from the edit text field
                //use query to retrieve the data member
            }
        });
    }

    private void listenToAddFriendButton(){
        mAddButton = (Button)findViewById(R.id.add_friend);
        mAddButton.setOnClickListener(new View.OnClickListener() {
        //anonymous inner class
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CONTEXT, AddFriendDataActivity.class);
                intent.putExtra(getString(R.string.name),
                        ((EditText) findViewById(R.id.editText)).getText().toString());
                startActivityForResult(intent, 1);
            }
        });
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
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
