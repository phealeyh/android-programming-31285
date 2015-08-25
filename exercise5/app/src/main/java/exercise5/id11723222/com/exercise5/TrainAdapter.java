package exercise5.id11723222.com.exercise5;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

/**
 * Created by phealeyhang on 25/08/15.
 */
public class TrainAdapter extends BaseAdapter{

    private ArrayList<TrainData> trainList;
    private View view;

    public TrainAdapter(Context context, ArrayList trainList){
        this.trainList = trainList;
    }

    @Override
    public int getCount(){
        return trainList.size();
    }

    @Override
    public Object getItem(int position) {
        return trainList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return (View)trainList.get(position);
    }

}
