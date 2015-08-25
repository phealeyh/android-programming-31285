package exercise5.id11723222.com.exercise5;

/**
 * Created by phealeyhang on 25/08/15.
 */
public class TrainData {
    private String mPlatform;
    private int mArrivalTime;
    private String mStatus;
    private String mDestination;
    private String mDestinationTime;

    public TrainData(String platform, int arrivalTime, String status, String destination,
                     String destinationTime){
        mPlatform = platform;
        mArrivalTime = arrivalTime;
        mStatus = status;
        mDestination = destination;
        mDestinationTime = destinationTime;

    }

    public String getmPlatform() {
        return mPlatform;
    }

    public void setmPlatform(String mPlatform) {
        this.mPlatform = mPlatform;
    }

    public int getmArrivalTime() {
        return mArrivalTime;
    }

    public void setmArrivalTime(int mArrivalTime) {
        this.mArrivalTime = mArrivalTime;
    }

    public String getmStatus() {
        return mStatus;
    }

    public void setmStatus(String mStatus) {
        this.mStatus = mStatus;
    }

    public String getmDestination() {
        return mDestination;
    }

    public void setmDestination(String mDestination) {
        this.mDestination = mDestination;
    }

    public String getmDestinationTime() {
        return mDestinationTime;
    }

    public void setmDestinationTime(String mDestinationTime) {
        this.mDestinationTime = mDestinationTime;
    }
}
