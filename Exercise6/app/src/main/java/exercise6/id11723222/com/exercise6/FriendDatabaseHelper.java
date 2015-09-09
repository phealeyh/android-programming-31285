package exercise6.id11723222.com.exercise6;

import android.content.ContentValues;
import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by phealeyhang on 8/09/15.
 */
public class FriendDatabaseHelper extends SQLiteOpenHelper{


    private static FriendDatabaseHelper databaseHelper;
    private SQLiteDatabase mDatabase;
    //database constants along with field name constants for the table
    private static final String DB_NAME = "friend.db";
    private static final int DATABASE_VERSION = 1;
    private static final String ID = "_id";
    private static final String NAME = "name";
    private static final String OCCUPATION = "occupation";
    private static final String CITY = "city";
    private static final String FRIEND_SINCE = "friend_since";
    //database execution commands in the form of constants
    private static final String CREATE_TABLE = "CREATE TABLE "
            + DB_NAME + "(" + ID + " INTEGER primary key autoincrement," +
            NAME + " TEXT," + OCCUPATION + " TEXT," + CITY + " TEXT," + FRIEND_SINCE + " INTEGER" +
            ")";

    private FriendDatabaseHelper(Context context) {
        super(context, DB_NAME, null, DATABASE_VERSION);
    }


    public FriendDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public FriendDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version, DatabaseErrorHandler errorHandler) {
        super(context, name, factory, version, errorHandler);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // create the database here called friend db
        mDatabase = SQLiteDatabase.openOrCreateDatabase(DB_NAME, null);
        //create the friend table
        mDatabase.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //update the schema when modified
    }

    public void addFriend(FriendData friend){
        //add the new friend into the database as a new row
        ContentValues newValues = new ContentValues();
        newValues.put(friend.getmName(),friend.getmName());
        newValues.put(friend.getmCity(), friend.getmCity());
        newValues.put(friend.getmOccupation(), friend.getmOccupation());
        //add row to the database
        mDatabase.insertOrThrow(DB_NAME,null,newValues);

    }

    public static synchronized FriendDatabaseHelper getInstance(Context context) {
        // Use the application context, which will ensure that you
        // don't accidentally leak an Activity's context.
        // See this article for more information: http://bit.ly/6LRzfx
        if (databaseHelper == null) {
            databaseHelper = new FriendDatabaseHelper(context.getApplicationContext());
        }
        return databaseHelper;
    }

    public static void setDatabaseHelper(FriendDatabaseHelper databaseHelper){
        FriendDatabaseHelper.databaseHelper = databaseHelper;
    }


    public static FriendDatabaseHelper getDatabaseHelper(){
        return databaseHelper;
    }




}
