package exercise5.id11723222.com.exercise5;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity{
    private ListView mList_item;
    private TrainAdapter mAdapter;
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mList_item = (ListView) findViewById(R.id.list_view);
        mContext = this;
        ArrayList<TrainData> trainList = new ArrayList<TrainData>();
        setUpTrains(trainList);
        //an adapter is a binding between the data source and the given views
        mAdapter = new TrainAdapter(this,trainList);
        mList_item.setAdapter(mAdapter);
        listenForListItem();
        //use onItemClickListener to setup the interaction between a view and the user

    }




    private void listenForListItem(){
        mList_item.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //make the time refresh for that specific row
                mAdapter.refreshTime(position);
            }
        });
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
        else if(id == R.id.add){
            mAdapter.addData();
            return true;

        }
        else if(id == R.id.refresh){
            //hide list view
            mList_item.setVisibility(View.INVISIBLE);
            //show progressbar for 3 seconds
            new RefreshProgress().execute();
            //show changed arrival times
            mAdapter.generateNewTimes();
        }

        else if(id == R.id.delete_all){
            mAdapter.deleteAll();
            return true;

        }
        else if(id == R.id.quit){
            //quit the application
            finish();
            return true;

        }

        return super.onOptionsItemSelected(item);
    }

    private class RefreshProgress extends AsyncTask<Void, Void, Void> {
        private ProgressDialog progressDialog;

        public RefreshProgress(){
            //initialise progress dialog
            progressDialog = new ProgressDialog(mContext);
        }

        @Override
        protected void onPreExecute(){
            //show progress dialog
            progressDialog.setMessage(getResources().getString(R.string.refreshing));
            progressDialog.show();
            progressDialog.setIndeterminate(true);
        }

        @Override
        protected Void doInBackground(Void... params){
            try {
                //pause for 3 seconds
                Thread.sleep(3000L);
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void nothing){
            progressDialog.dismiss();
            mList_item.setVisibility(View.VISIBLE);
        }
    }

}
