package exercise5.id11723222.com.exercise5;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private TextView mLeftView, mMiddleView, mRightView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_item);
        setUpViews();
        //data model
        ArrayList<TrainData> trainList = new ArrayList<TrainData>();
        //set the array up
        setUpTrains(trainList);
        //an adapter is a binding between the data source and the given views
        TrainAdapter adapter = new TrainAdapter(this,trainList);
        //use onItemClickListener to setup the interaction between a view and the user

    }

    /**Function:setUpViews
     * --------------------
     * This function will set up the views by linking them with their
     * corresponding views within the xml. Bring xml to java.
     */

    private void setUpViews(){
        //list views are responsible for taking the data from a value from an adapter
        //and responsible for displaying the actual data from that adapter
        mLeftView = (ListView) findViewById(R.id.left_pane);
        mMiddleView = (ListView) findViewById(R.id.middle_pane);
        mRightView = (ListView) findViewById(R.id.right_pane);


    }



    private void setUpTrains(ArrayList<TrainData> trainList){
        trainList.add(0,new TrainData("Inner West",3,"on-time","Central","4:00"));
        trainList.add(0,new TrainData("Inner West",1,"late","Central","2:00"));
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
