package creationsofali.expressbooking;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.Time;

/**
 * Created by Swai on 9/13/2016.
 */
public class EBookingDatabase extends SQLiteOpenHelper {

    //Database name and version
    static final String DB_NAME = "EBooking.db";
    static final int DB_VERSION = 3;

    //Table names
    static final String TABLE_BUS = "bus";
    static final String TABLE_COMPANY = "company";
    static final String TABLE_ROUTE = "route";
    static final String TABLE_PASSENGER = "passenger";
    static final String TABLE_PAYMENT = "payment";
    static final String TABLE_RESERVERD = "reserved";

    //TABLE_BUS columns
    static final String COL_PLATENO = "plateno";
    static final String COL_MODEL = "model";
    static final String COL_TOTAL_SEAT = "totalseat";
    static final String COL_FARE = "fare";
    static final String COL_DEPARTURE = "departure";
    static final String COL_ARRIVAL = "arrival";

    //TABLE_COMPANY columns
    static final String COL_COMP_ID = "companyid";
    static final String COL_COMP_NAME = "name";
    static final String COL_COMP_LOCATION = "location";

    //TABLE_ROUTE columns
    static final String COL_ROUTE_ID = "routeid";
    static final String COL_ROUTE_ORIGIN = "origin";
    static final String COL_ROUTE_DESTINATION = "destination";

    //TABLE_PAYMENT columns
    private static final String COL_TRANSAC_ID = "transaction_id";

    //TABLE_PASSENGER columns
    private static final String COL_PID = "pid";
    private static final String COL_FNAME = "fname";
    private static final String COL_MNAME = "mname";
    private static final String COL_LNAME = "lname";
    private static final String COL_EMAIL = "email";
    private static final String COL_PHONE = "phone_number";
    private static final String COL_DATE = "date";

    //TABLE_RESERVED columns
    private static final String COL_SEAT_NO = "seatno";


    //Constructor
    public EBookingDatabase(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    //Creating TABLE_BUS statements
    private static final String CREATE_BUS = "CREATE TABLE "+ TABLE_BUS +"("
            + COL_PLATENO +" TEXT PRIMARY KEY,"
            + COL_MODEL +" TEXT,"
            + COL_TOTAL_SEAT +" INTEGER,"
            + COL_COMP_ID +" INTEGER,"
            + COL_FARE +" REAL,"
            + COL_DEPARTURE +" TEXT,"
            + COL_ARRIVAL +" TEXT,"
            + COL_ROUTE_ID +" INTEGER" +")";

    //Creating TABLE_COMPANY statement
    private static final String CREATE_COMPANY = "CREATE TABLE "+ TABLE_COMPANY +"("
            + COL_COMP_ID +" INTEGER PRIMARY KEY,"
            + COL_COMP_NAME +" TEXT,"
            + COL_COMP_LOCATION +" TEXT" +")";

    //Creating TABLE_ROUTE statement
    private static final String CREATE_ROUTE = "CREATE TABLE "+ TABLE_ROUTE +"("
            + COL_ROUTE_ID +" INTEGER PRIMARY KEY,"
            + COL_ROUTE_ORIGIN +" TEXT,"
            + COL_ROUTE_DESTINATION +" TEXT" +")";

    //Creating TABLE_PAYMENT statement
    private static final String CREATE_PAYMENT = "CREATE TABLE "+ TABLE_PAYMENT + "("
            + COL_TRANSAC_ID + " INTEGER PRIMARY KEY,"
            + COL_PID + " INTEGER" +")";

    //Creating TABLE_PASSENGER statement
    private static final String CREATE_PASSENGER = "CREATE TABLE "+ TABLE_PASSENGER +"("
            + COL_PID +" INTEGER PRIMARY KEY,"
            + COL_FNAME +" TEXT,"
            + COL_MNAME +" TEXT,"
            + COL_LNAME +" TEXT,"
            + COL_EMAIL +" TEXT,"
            + COL_PHONE +" INTEGER"
            + COL_DATE +" TEXT,"
            + COL_PLATENO +" INTEGER"
            + COL_ROUTE_ID +" INTEGER" +")";

    //Creating TABLE_RESERVED statement
    private static final String CREATE_RESERVED = "CREATE TABLE "+ TABLE_RESERVERD+"("
            + COL_PLATENO +" INTEGER"
            + COL_SEAT_NO +" INTEGER" +")";


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATE_BUS);
        database.execSQL(CREATE_COMPANY);
        database.execSQL(CREATE_ROUTE);
        database.execSQL(CREATE_PASSENGER);
        database.execSQL(CREATE_PAYMENT);
    }

    @Override
    public void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_BUS);
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_COMPANY);
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_ROUTE);
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_PASSENGER);
        database.execSQL("DROP TABLE IF EXISTS "+ TABLE_PAYMENT);

        onCreate(database);
    }


    //Insert into TABLE_BUS
    public void insertBusDetails(String number, String model, int totalSeats, int compId,
                                 double fare, String takeOff, String arrival, int routeId){
        //Open db for inserting
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_PLATENO, number);
        values.put(COL_MODEL, model);
        values.put(COL_TOTAL_SEAT, totalSeats);
        values.put(COL_COMP_ID, compId);
        values.put(COL_FARE, fare);
        values.put(COL_DEPARTURE, takeOff);
        values.put(COL_ARRIVAL, arrival);
        values.put(COL_ROUTE_ID, routeId);

        database.insert(TABLE_BUS, null, values);
    }

    //Insert into TABLE_COMPANY
    public void insertCompanyDetails(int compId, String name, String location){
        //Open db for inserting
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_COMP_ID, compId);
        values.put(COL_COMP_NAME, name);
        values.put(COL_COMP_LOCATION, location);

            database.insert(TABLE_COMPANY, null, values);
    }

    //Insert into TABLE_ROUTE
    public void insertRouteDetails(int routeId, String origin, String destination){
        //Open db for inserting
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ROUTE_ID, routeId);
        values.put(COL_ROUTE_ORIGIN, origin);
        values.put(COL_ROUTE_DESTINATION, destination);

        database.insert(TABLE_ROUTE, null, values);
    }

    //Insert into TABLE_PASSENGER
    public void insertPassengerDetails(){
        //Open db for inserting
        SQLiteDatabase database = getWritableDatabase();
    }

    //Method to retrieve data from db
    public Cursor retrieveData(String selectQuery){

        //open db for retrieving
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        return cursor;
    }

   public void dropTable(String table){
       SQLiteDatabase db = getWritableDatabase();
       db.execSQL("DROP TABLE IF EXISTS "+ table);
   }


}
