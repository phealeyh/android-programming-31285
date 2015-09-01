package exercise5.id11723222.com.exercise5;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by phealeyhang on 25/08/15.
 */
public class TrainAdapter extends BaseAdapter{

    private ArrayList<TrainData> trainList;
    private View view;
    private Context mContext;

    public TrainAdapter(Context context, ArrayList<TrainData> trainList){
        mContext = context;
        this.trainList = trainList;
    }

    public void addData(){
        trainList.add(new TrainData("South West", 4, "late", "Canley Vale", "4:40"));
        notifyDataSetChanged();
    }

    public void deleteAll(){
        //remove all elements within the container
        trainList.removeAll(trainList);
        notifyDataSetChanged();
    }

    public void refreshTime(int position){
        //refresh time based on position
        for(int i = 0; i < trainList.size(); i++){
            if(trainList.get(i) == trainList.get(position)){
                trainList.get(i).setmArrivalTime(new Random().nextInt(20) + 1);
            }
        }
        notifyDataSetChanged();
    }

    public void generateNewTimes(){
        for(TrainData train: trainList) {
            train.setmArrivalTime(new Random().nextInt(20) + 1);
        }
        notifyDataSetChanged();
    }

    @Override
    public int getCount(){
        return trainList.size();
    }

    @Override
    public Object getItem(int position){
        return trainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    /**
     *
     * Function: getView
     * -------------------
     * This will get a selected view from the given data source
     */


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView arriving_in_time, platform_name, arrival_time, status, destination_time,
                destination_location;
        View view;
        //helps with performance
        if(convertView != null){
            view = convertView;
        }
        //parse a new view from xml
        else{
            //The LayoutInflater takes your layout XML-files
            //and creates different View-objects from its contents.
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, null);
        }
        //fill in view with data
        arriving_in_time = (TextView) view.findViewById(R.id.arrival_time_text);
        arriving_in_time.setText(Integer.toString(trainList.get(position).getmArrivalTime())
        + " min");
        platform_name = (TextView) view.findViewById(R.id.platform_text);
        platform_name.setText(trainList.get(position).getmPlatform());
        arrival_time = (TextView) view.findViewById(R.id.arrival_text);
        arrival_time.setText(trainList.get(position).getmDestinationTime());
        arrival_time.setTextColor(Color.BLACK);
        status = (TextView) view.findViewById(R.id.status_text);
        status.setText(trainList.get(position).getmStatus());
        if(trainList.get(position).getmStatus().equals("on-time")){
            status.setTextColor(Color.GREEN);
        }
        else{
            status.setTextColor(Color.RED);
        }
        destination_time = (TextView) view.findViewById(R.id.destination_time_text);
        destination_time.setText(trainList.get(position).getmDestinationTime());
        destination_time.setTextColor(Color.BLUE);
        destination_location = (TextView) view.findViewById(R.id.destination_location);
        destination_location.setText(trainList.get(position).getmDestination());
        destination_location.setTypeface(null, Typeface.BOLD);
        return view;
    }





}
