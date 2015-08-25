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
    private Context mContext = this;
    private Spinner mSpinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listenForSpinnerSelection();
        listenForDownloadButton();
        listenForDownloadAllButton();
        listenForCalculateButton();
    }

    /**
     * When the calculate button is clicked, a new instance of
     * the calculate triangular async task class is created with
     *  the given tri number of 1000000.
     */
    private void listenForCalculateButton(){
        mCalculateButton = (Button)findViewById(R.id.calculateButton);
        mCalculateButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new CalculateTriangularAsyncTask(getResources().getInteger(R.integer.number)).execute();
            }
        });
    }


    /**
     * When a selection for a spinner element is selected,
     * a toast appears with the selected spinner along with the
     * the spinner setting its selection to that given input
     */
    private void listenForSpinnerSelection(){
        mSpinner = (Spinner) findViewById(R.id.spinnerFiles);
        ArrayAdapter<CharSequence> adapter_state = ArrayAdapter.createFromResource(this,
                R.array.files, android.R.layout.simple_spinner_item);
        adapter_state.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mSpinner.setAdapter(adapter_state);
        mSpinner.setOnItemSelectedListener(new OnItemSelectedListener() {
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
    public boolean onOptionsItemSelected(MenuItem item){
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

    /**
     * This listens for the download button to be clicked from the user;
     * once clicked the selected element within the spinner will then
     * begin to start downloading that specific file
     */

    private void listenForDownloadButton(){
        mDownloadFileButton = (Button)findViewById(R.id.downloadSelectedFileButton);
        mDownloadFileButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadSelectedAsyncTask(mSpinner.getSelectedItem().toString()).execute();
            }
        });
    }

    /**
     * This will listen for the user clicking on the download all button;
     * once this button is clicked, the user will download all of the files
     * found within the array of the strings xml.
     */

    private void listenForDownloadAllButton(){
        mDownloadAllFileButton = (Button)findViewById(R.id.downloadAllFilesButton);
        mDownloadAllFileButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                //create new instance of DownloadAllAsyncTask
                new DownloadAllAsyncTask(mSpinner.getAdapter().getCount()).
                        execute(getResources().getStringArray(R.array.files));
            }
        });
    }

    /**
     * Take the given number and use that as the nth number.
     * Everytime the loop gets to the 50th number, increment the progress bar
     * by 50 and then return the result divided by 2.
     * Dismiss the horizontal progress bar and then show the given result
     * calculated.
     */

    private class CalculateTriangularAsyncTask extends AsyncTask<Void, Void, Long>{

        private int number;
        private ProgressDialog mProgressBar;

        public CalculateTriangularAsyncTask(int number){
            this.number = number;
            mProgressBar = new ProgressDialog(mContext);
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

    /**
     * Take the number of files found within the spinner and then use that
     * to set the maximum for the horizontal progress bar. The for loop
     * will go through each file found within the spinner array and
     * increment the progress bar by one when each selected file has been
     * downloaded. Once the for loop has been completed, the progress bar
     * is dismissed.
     */

    private class DownloadAllAsyncTask extends AsyncTask<String, String, Void>{

        private ProgressDialog progressBar;
        private int numFiles;

        public DownloadAllAsyncTask(int numFiles){
            //initialize progress bar
            progressBar = new ProgressDialog(mContext);
            //initialise number of files
            this.numFiles = numFiles;
        }


        @Override
        protected void onPreExecute() {
            progressBar.setMax(numFiles);
            progressBar.setIndeterminate(false);
            progressBar.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressBar.setMessage(R.string.downloading + R.string.delivery
                    + getResources().getString(R.string.dots));
            progressBar.show();
        }

        @Override
        protected Void doInBackground(String... params){
            for(int i = 0; i < params.length; i++) {
                try{
                    publishProgress(params[i]);
                    Thread.sleep(getResources().getInteger(R.integer.sleep));
                }catch(Exception e){
                    Log.d(getResources().getString(R.string.error)
                            , e.toString());
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values){
            progressBar.setMessage(R.string.downloading
                    + values[0] + R.string.dots);
            progressBar.incrementProgressBy(1);
        }

        @Override
        protected void onPostExecute(Void result){
            progressBar.dismiss();
        }
    }

    /**
     * The DownloadSelectedAsyncTask will take a selected filename as an argument
     * and then download that by showing a progress dialog
     * that is indeterminate (spinning animation). It will do this for 2 seconds and then
     * the progress dialog will be dismissed.
     */

    private class DownloadSelectedAsyncTask extends AsyncTask<Void, Void, Void>{
        private ProgressDialog progressDialog;
        private String fileName;

        public DownloadSelectedAsyncTask(String fileName){
            //initialise progress dialog
            progressDialog = new ProgressDialog(mContext);
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
