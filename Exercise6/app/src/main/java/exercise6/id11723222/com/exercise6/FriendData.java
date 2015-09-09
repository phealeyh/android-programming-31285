package exercise6.id11723222.com.exercise6;

/**
 * Created by phealeyhang on 8/09/15.
 */
public class FriendData {

    private int mId;
    private String mName, mOccupation, mCity;
    private long mFriendSince;

    public FriendData(int mId, String mName, String mOccupation, String mCity, long mFriendSince){
        this.mId = mId;
        this.mName = mName;
        this.mOccupation = mOccupation;
        this.mCity = mCity;
        this.mFriendSince = mFriendSince;
    }



    public long getmFriendSince() {
        return mFriendSince;
    }

    public void setmFriendSince(long mFriendSince) {
        this.mFriendSince = mFriendSince;
    }

    public String getmCity() {
        return mCity;
    }

    public void setmCity(String mCity) {
        this.mCity = mCity;
    }

    public String getmOccupation() {
        return mOccupation;
    }

    public void setmOccupation(String mOccupation) {
        this.mOccupation = mOccupation;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

}
