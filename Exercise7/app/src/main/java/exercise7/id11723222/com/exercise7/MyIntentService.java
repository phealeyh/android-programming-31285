package exercise7.id11723222.com.exercise7;

import android.app.IntentService;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MyIntentService extends IntentService {

    private int maxValue;

    public MyIntentService(String name) {
        super(name);
    }

    public MyIntentService() {
        super("hit");
    }



    @Override
    protected void onHandleIntent(Intent intent){
        int maxValue = intent.getIntExtra("max value",10);
        for(int i = 0; i < maxValue; i++){
            intent = new Intent("exercise7.id11723222.com.exercise7");
            intent.putExtra("number",i);
            Log.d("hit", Integer.toString(i));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            sendBroadcast(intent);
        }
        //sends last broadcast
        intent.putExtra("finished","Task Completed!");
        sendBroadcast(intent);
    }
}
