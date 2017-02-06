package com.sampleappkiranjatty.happyabode;

/**
 * Created by kiranjatty on 11/27/16.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
//import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DataBaseHelper extends SQLiteOpenHelper {

    //The Android's default system path of your application database.
    private static String DB_PATH = "/data/data/com.sampleappkiranjatty.happyabode/databases/";

    private static String DB_NAME = "HomeAbode_db.db";

    private SQLiteDatabase myDataBase;

    private final Context myContext;

    /**
     * Constructor
     * Takes and keeps a reference of the passed context in order to access to the application assets and resources.
     *
     * @param context
     */
    public DataBaseHelper(Context context) {

        super(context, DB_NAME, null, 1);
        this.myContext = context;
    }

    /**
     * Creates a empty database on the system and rewrites it with your own database.
     */
    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            //do nothing - database already exist
            //copyDataBase();
        } else {

            //By calling this method and empty database will be created into the default system path
            //of your application so we are gonna be able to overwrite that database with our database.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copying database");

            }
        }

    }

    /**
     * Check if the database already exist to avoid re-copying the file each time you open the application.
     *
     * @return true if it exists, false if it doesn't
     */
    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {

            //database does't exist yet.

        }

        if (checkDB != null) {

            checkDB.close();

        }

        return checkDB != null ? true : false;
    }

    /**
     * Copies your database from your local assets-folder to the just created empty database in the
     * system folder, from where it can be accessed and handled.
     * This is done by transfering bytestream.
     */
    private void copyDataBase() throws IOException {

        //Open your local db as the input stream
        InputStream myInput = myContext.getAssets().open(DB_NAME);

        // Path to the just created empty db
        String outFileName = DB_PATH + DB_NAME;

        //Open the empty db as the output stream
        OutputStream myOutput = new FileOutputStream(outFileName);

        //transfer bytes from the inputfile to the outputfile
        byte[] buffer = new byte[1024];
        int length;
        while ((length = myInput.read(buffer)) > 0) {
            myOutput.write(buffer, 0, length);
        }

        //Close the streams
        myOutput.flush();
        myOutput.close();
        myInput.close();

    }

    public void openDataBase() throws SQLException {

        //Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public Login searchPass(String user) {

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select user_name, password from " + "logindetails";
        // send the query and result is stored in cursor
        Cursor cursor = db.rawQuery(query, null);
        //  cursor is moved to first position
        String a;
        Login log = new Login();
        log.set_name("not found");    // default return value if username not found
        if (cursor.moveToFirst()) {
            do {      //loop to check if username and password are equal
                a = cursor.getString(0);
                if (a.equals(user)) {
                    log.set_name(cursor.getString(1));
                    log.set_role(0);
                    break;
                }
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        if(log.getName()=="not found"){
        db = this.getReadableDatabase();
            String qu = "select user_name, password from " + "admindetails";
            cursor = db.rawQuery(qu, null);
        if(cursor.moveToFirst()) {
            do {      //loop to check if username and password are equal
                a = cursor.getString(0);
                if (a.equals(user)) {
                    log.set_name(cursor.getString(1));
                    log.set_role(1);
                    break;
                }
            } while (cursor.moveToNext());
          }
        }
        return log;
    }

    public String query_db(String s) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select apt_no, user from " + "apartmentinfo";
        Cursor cursor = db.rawQuery(query, null);
        String a, b="notfound";
        if (cursor.moveToFirst()) {
            do {
                a = cursor.getString(1);
                if (a.equals(s)) {
                    b = cursor.getString(0);
                    break;
                }
            } while (cursor.moveToNext());
        }
        return b;
    }
    public void insert(NewRequest req){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        String q ="select * from handlerequests";
        Cursor cursor = db.rawQuery(q,null);
        int count = cursor.getCount();
        values.put("room", req.get_room());
        values.put("type", req.get_type());
        values.put("time", req.get_time());
        values.put("description", req.get_description());
        values.put("status", req.get_status());
        values.put("user", req.get_user());
        values.put("attachment", req.get_byte_stream());
        values.put("apt_no", req.get_apt_no());
        // insert values in database with table name "TABLE_NAME"
        long i = db.insert("handlerequests", null, values);
        if(i!=-1)
        Toast.makeText(myContext, "New request filed, request id: " + i, Toast.LENGTH_SHORT).show();
        else
        Toast.makeText(myContext, "Something wrong", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public String[] get_active(){
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT apt_no FROM handlerequests WHERE status = ?" ;
        Cursor cursor = db.rawQuery("SELECT apt_no,key_id FROM handlerequests WHERE status = ?", new String[] {"0"});
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> values = new ArrayList<Integer>();

        while (!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("apt_no")));
           // i[count] = cursor.getLong(cursor.getColumnIndex("rowid"));
            values.add(cursor.getInt(cursor.getColumnIndex("key_id")));
            cursor.moveToNext();
        }
        cursor.close();
        ArrayList<String> newList = new ArrayList<String>(values.size());
        for (Integer myInt : values) {
            newList.add(String.valueOf(myInt));
        }
        //return names.toArray(new String[names.size()]);
        return newList.toArray(new String[newList.size()]);
        //return i;
    }
    public NewRequest get_Req(String str){
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT apt_no FROM handlerequests WHERE status = ?" ;
        Cursor cursor = db.rawQuery("SELECT * FROM handlerequests WHERE key_id = ?", new String[] {str});
        cursor.moveToFirst();
        NewRequest request = new NewRequest();
            request.set_apt_no(cursor.getString(cursor.getColumnIndex("apt_no")));
            // i[count] = cursor.getLong(cursor.getColumnIndex("rowid"));
            request.set_type(cursor.getString(cursor.getColumnIndex("type")));
            request.set_user(cursor.getString(cursor.getColumnIndex("user")));
            request.set_room(cursor.getString(cursor.getColumnIndex("room")));
            request.set_time(cursor.getString(cursor.getColumnIndex("time")));
            request.set_description(cursor.getString(cursor.getColumnIndex("description")));
            request.set_status(cursor.getInt(cursor.getColumnIndex("status")));
            request.set_byte_stream(cursor.getBlob(cursor.getColumnIndex("attachment")));
            //cursor.moveToNext();
        cursor.close();
        //return names.toArray(new String[names.size()]);
        return request;
        //return i;
    }
    public void delete_request(String str){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery("SELECT status FROM handlerequests WHERE key_id = ?", new String[] {str});
        ContentValues values = new ContentValues();
        //int count = cursor.getCount();
        values.put("status", "1");
        db.update("handlerequests", values, "key_id"+ "=?", new String[]{str});
    }
    public void register(apartment a){
        SQLiteDatabase db = this.getWritableDatabase();
        String qu;
        Cursor cursor = db.rawQuery("select user_name from " + "logindetails " + "where apt_no =?", new String[] {a.get_apartment()});
        cursor.moveToFirst();
        qu = cursor.getString(cursor.getColumnIndex("user_name"));
        if(qu==null) {
            ContentValues values = new ContentValues();
            values.put("user_name", a.get_username());
            values.put("password", a.get_password());
            values.put("apt_no", a.get_apartment());
            long i = db.insert("logindetails", null, values);
            if (i != -1)
                Toast.makeText(myContext, "New apartment added ", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(myContext, "Something wrong", Toast.LENGTH_SHORT).show();
            ContentValues v = new ContentValues();
            v.put("apt_no", a.get_apartment());
            v.put("user", a.get_username());
            long in = db.insert("apartmentinfo", null, v);
            //if (in != -1)
                //Toast.makeText(myContext, "Filed a new request, id: " + in, Toast.LENGTH_SHORT).show();
            //else
                //Toast.makeText(myContext, "Something wrong", Toast.LENGTH_SHORT).show();
        }
        else{
            ContentValues values = new ContentValues();
            values.put("password", a.get_password());
            long i =db.update("logindetails", values, "apt_no"+ "=?", new String[]{a.get_apartment()});
            Toast.makeText(myContext, "Apartment info Updated", Toast.LENGTH_SHORT).show();
        }
        db.close();
    }
    public String[] myrequests(String str){
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT apt_no FROM handlerequests WHERE status = ?" ;
        Cursor cursor = db.rawQuery("SELECT key_id FROM handlerequests WHERE apt_no = ?", new String[] {str});
        cursor.moveToFirst();
        //ArrayList<String> names = new ArrayList<String>();
        ArrayList<Integer> values = new ArrayList<Integer>();

        while (!cursor.isAfterLast()) {
            //names.add(cursor.getString(cursor.getColumnIndex("apt_no")));
            // i[count] = cursor.getLong(cursor.getColumnIndex("rowid"));
            values.add(cursor.getInt(cursor.getColumnIndex("key_id")));
            cursor.moveToNext();
        }
        cursor.close();
        ArrayList<String> newList = new ArrayList<String>(values.size());
        for (Integer myInt : values) {
            newList.add(String.valueOf(myInt));
        }
        //return names.toArray(new String[names.size()]);
        return newList.toArray(new String[newList.size()]);
    }
    public void add_announcement(announcement anno){
        SQLiteDatabase db = this.getWritableDatabase();
        //String q ="select * from handlerequests";
        //Cursor cursor = db.rawQuery(q,null);
        ContentValues values = new ContentValues();
        values.put("description", anno.get_announcement());
        values.put("apartment", anno.get_apt());
        long i= db.insert("announcements",null,values);
        if(i!=-1)
            Toast.makeText(myContext, "New announcement added", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(myContext, "Something wrong", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public void changePassword(apartment a){
        SQLiteDatabase db = this.getWritableDatabase();
        //Cursor cursor = db.rawQuery("SELECT status FROM handlerequests WHERE key_id = ?", new String[] {str});
        ContentValues values = new ContentValues();
        //int count = cursor.getCount();
        values.put("password", a.get_password());
        long i = db.update("logindetails", values, "user_name"+ "=?", new String[]{a.get_username()});
        if(i!=-1)
            Toast.makeText(myContext, "Password changed!! ", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(myContext, "Something wrong", Toast.LENGTH_SHORT).show();
        db.close();
    }
    public String[] get_active_announcements(String str){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT description FROM announcements WHERE apartment = ?", new String[] {str});
        cursor.moveToFirst();
        ArrayList<String> names = new ArrayList<String>();
        while (!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("description")));
            //Toast.makeText(myContext, "Announcements " , Toast.LENGTH_SHORT).show();
            // i[count] = cursor.getLong(cursor.getColumnIndex("rowid"));
            cursor.moveToNext();
        }
        cursor.close();
        cursor = db.rawQuery("SELECT description FROM announcements WHERE apartment = ?", new String[] {"all"});
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            names.add(cursor.getString(cursor.getColumnIndex("description")));
            //Toast.makeText(myContext, "Announcements " , Toast.LENGTH_SHORT).show();
            // i[count] = cursor.getLong(cursor.getColumnIndex("rowid"));
            cursor.moveToNext();
        }
        cursor.close();
        return names.toArray(new String[names.size()]);
    }
}