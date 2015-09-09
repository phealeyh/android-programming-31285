package exercise7.id11723222.com.exercise7;

import android.app.IntentService;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.provider.SyncStateContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Button mStartButton;
    private BroadcastReceiver broadcastReceiver;
    private final String FILTER = "exercise7.id11723222.com.exercise7";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mStartButton = (Button) findViewById(R.id.start);
        mStartButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button button = (Button)findViewById(R.id.start);
                button.setEnabled(false);
                listenForStart(v);
            }
        });
        broadcastReceiver = new BroadcastReceiver(){
            @Override
            public void onReceive(Context context, Intent intent) {
                TextView status = (TextView) findViewById(R.id.statusView);
                String name = intent.getStringExtra("max value");
                Log.d("HIT", "HIT");
            }
        };


    }


    private void listenForStart(View v){
        Intent intent = new Intent(this,MyIntentService.class);
        intent.putExtra("max value", 10);
        startService(intent);
    }

    @Override
    public void onStart(){
        super.onStart();
        registerReceiver(broadcastReceiver, new IntentFilter(FILTER));
    }

    @Override
    public void onStop(){
        super.onStop();
        unregisterReceiver(broadcastReceiver);
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
}
