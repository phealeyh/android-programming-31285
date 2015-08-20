package exercise4.id11723222.com.exercise4;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import java.net.URL;

import javax.xml.transform.Result;

import static android.view.View.*;


public class MainActivity extends Activity {
    private Button mDownloadFileButton;
    private Context context = this;
    private Spinner spinner;


    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listenForSpinnerSelection();
        listenForDownloadButton();
    }


    private void listenForSpinnerSelection() {
        String[] files = {"deliver-records.zip",
                "recycling-graphics.zip","route-maps.zip"};
        spinner = (Spinner) findViewById(R.id.spinnerFiles);
        ArrayAdapter<String> adapter_state = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, files);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter_state);
        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Object item = parent.getItemAtPosition(position);
                if (item != null) {
                    Toast.makeText(MainActivity.this, item.toString(),
                            Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // TODO Auto-generated method stub
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
        if (id == R.id.action_settings){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void listenForDownloadButton(){
        mDownloadFileButton = (Button)findViewById(R.id.downloadSelectedFileButton);
        mDownloadFileButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadFilesTask(spinner.getSelectedItem().toString()).execute();
            }
        });


    }

    private class DownloadAllAsyncTask extends AsyncTask<String, String, Void>{
        private ProgressBar progressBar;

        public DownloadAllAsyncTask(){
            //initialize progress bar
            progressBar = new ProgressBar(context);
        }

        @Override
        protected void onPreExecute(){

        }

        @Override
        protected Void doInBackground(String... params){
            publishProgress("Hello world"); //sends to onProgresUpdate
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values){
            Log.d("TAG", values[0]);
        }

        @Override
        protected void onPostExecute(Void result){
            super.onPostExecute(result);
        }
    }


    private class DownloadFilesTask extends AsyncTask<Void, Void, Void>{
        private ProgressDialog progressDialog;
        private String fileName;

        public DownloadFilesTask(String fileName){
            //initialise progress dialog
            progressDialog = new ProgressDialog(context);
            //initialise string contents
            this.fileName = fileName;
        }

        @Override
        protected void onPreExecute() {
            //show progress dialog
            progressDialog.setMessage("Downloading " + fileName);
            progressDialog.show();
            progressDialog.setIndeterminate(true);


        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }
            return null;
        }

        // Here is the AsyncTask class:
        //
        // AsyncTask<Params, Progress, Result>.
        //    Params – the type (Object/primitive) you pass to the AsyncTask from .execute()
        //    Progress – the type that gets passed to onProgressUpdate()
        //    Result – the type returns from doInBackground()
        // Any of them can be String, Integer, Void, etc.



        @Override
        protected void onPostExecute(Void nothing){
            Log.d("onPostExecute","Hit");
            progressDialog.dismiss();
            //find the final frame
            //post execute and finish the thread and see what happens
            //post execute thread and finish it
        }
    }

}
