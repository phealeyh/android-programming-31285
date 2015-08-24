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
import android.widget.Spinner;
import android.widget.Toast;
import static android.view.View.*;


public class MainActivity extends Activity {

    private Button mDownloadFileButton, mDownloadAllFileButton, mCalculateButton;
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
        listenForDownloadAllButton();
        listenForCalculateButton();
    }

    private void listenForCalculateButton(){
        mCalculateButton = (Button)findViewById(R.id.calculateButton);
        mCalculateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalculateTriangularAsyncTask(1000000).execute();
            }
        });
    }


    private void listenForSpinnerSelection(){
        spinner = (Spinner) findViewById(R.id.spinnerFiles);
        ArrayAdapter<CharSequence> adapter_state = ArrayAdapter.createFromResource(this,
                R.array.files, android.R.layout.simple_spinner_item);
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

    private void listenForDownloadAllButton(){
        mDownloadAllFileButton = (Button)findViewById(R.id.downloadAllFilesButton);
        mDownloadAllFileButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new instance of DownloadAllAsyncTask
                new DownloadAllAsyncTask(spinner.getAdapter().getCount()).execute(getResources().getStringArray(R.array.files));
            }
        });
    }

    private class CalculateTriangularAsyncTask extends AsyncTask<Void, Void, Long>{

        private int number;
        private ProgressDialog mProgressBar;

        public CalculateTriangularAsyncTask(int number){
            this.number = number;
            mProgressBar = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute(){
            mProgressBar.setMax(number);
            mProgressBar.setIndeterminate(false);
            mProgressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            mProgressBar.setMessage(getResources().getString(R.string.calculation));
            mProgressBar.show();

        }

        @Override
        protected Long doInBackground(Void... params){
            long result = 0L;
            int count = 0;
            for(int i = 0; i <= number; i++){
                //introduce a count variable to track the 50th number
                count++;
                result += number;
                //update at 50
                if(count == 50){
                    publishProgress();
                    count = 0;
                }
            }
            return result / 2;
        }

        @Override
        protected void onProgressUpdate(Void... params){
            mProgressBar.incrementProgressBy(50);
        }


        @Override
        protected void onPostExecute(Long result){
            mProgressBar.dismiss();
            String text = getResources().getString(R.string.tri) + number +
                    getResources().getString(R.string.trinumber)+ result;
            Toast.makeText(MainActivity.this,text,Toast.LENGTH_LONG).show();
        }

    }

    private class DownloadAllAsyncTask extends AsyncTask<String, String, Void>{

        private ProgressDialog progressBar;
        private int numFiles;

        public DownloadAllAsyncTask(int numFiles){
            //initialize progress bar
            progressBar = new ProgressDialog(context);
            //initialise number of files
            this.numFiles = numFiles;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setMax(numFiles);
            progressBar.setIndeterminate(false);
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar.setMessage(R.string.downloading + R.string.delivery + getResources().getString(R.string.dots));
            progressBar.show();
        }

        @Override
        protected Void doInBackground(String... params){
            for(int i = 0; i < params.length; i++) {
                try{
                    publishProgress(params[i]);
                    Thread.sleep(2000L);
                }catch(Exception e){
                    Log.d(getResources().getString(R.string.error), e.toString());
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values){
            progressBar.setMessage(R.string.downloading + values[0] + R.string.dots);
            progressBar.incrementProgressBy(1);
        }

        @Override
        protected void onPostExecute(Void result){
            progressBar.dismiss();
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
        protected void onPreExecute(){
            //show progress dialog
            progressDialog.setMessage(R.string.downloading + fileName);
            progressDialog.show();
            progressDialog.setIndeterminate(true);
        }

        @Override
        protected Void doInBackground(Void... params){
            try {
                Thread.sleep(2000L);
            } catch (InterruptedException e1){
                e1.printStackTrace();
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void nothing){
            progressDialog.dismiss();
        }
    }



}
