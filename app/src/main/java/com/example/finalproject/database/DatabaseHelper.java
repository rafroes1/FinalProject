package com.example.finalproject.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.finalproject.model.House;

import java.util.ArrayList;
import java.util.List;

/*
    TODO: How to use data base
        1- To get one house by its ID:
            DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
            House house = db.getHouse(Integer.parseInt(id));
        2- To get all houses in database:
            DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
            List<House> houses = db.getAllHouses();
        3- To insert a house to the db
            DatabaseHelper db = DatabaseHelper.getInstance(getActivity());
            long houseId = db.insertHouse(house);
        P.S.: To insert a house, create a house object and SET ALL DATA REQUIRED, unless ID.
        You have to insert some data to begin with otherwise you wil not have anything
        on the database to select from. You also might need to get latitude and longitude
        on google maps.
 */

public class DatabaseHelper extends SQLiteOpenHelper {
    //singleton db instance
    private static DatabaseHelper instance;

    //DB info
    private static final String DATABASE_NAME = "houseDatabase";

    //Table info
    private static final String TABLE_NAME = "houses";

    //Columns
    private static final String HOUSE_ID = "id";
    private static final String STREET_ADRESS = "streetAdress";
    private static final String CITY = "city";
    private static final String PROVINCE = "province";
    private static final String COUNTRY = "country";
    private static final String ZIPCODE = "zipcode";
    private static final String DESCRIPTION = "description";
    private static final String BEDS = "beds";
    private static final String BATHS = "baths";
    private static final String PRICE = "price";
    private static final String LATITUDE = "latitue";
    private static final String LONGITUDE = "longiteude";

    //Drop and Create table queries
    private static final String DROP_TABLE = "DROP TABLE IF EXISTS " + TABLE_NAME;

    private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME +
            "(" +
            HOUSE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            STREET_ADRESS + " TEXT," +
            CITY + " TEXT," +
            PROVINCE + " TEXT," +
            COUNTRY + " TEXT," +
            ZIPCODE + " TEXT," +
            DESCRIPTION + " TEXT," +
            BEDS + " INTEGER," +
            BATHS + " INTEGER," +
            PRICE + " REAL," +
            LATITUDE + " REAL," +
            LONGITUDE + " REAL" +
            ")";

    //creates database
    private DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    //singleton method to get databasehelper instance to keep one single
    //reference to the database across the whole project
    public static synchronized DatabaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseHelper(context);
        }
        return instance;
    }

    //select all houses from the table
    public List<House> getAllHouses() {
        List<House> list = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME, null);

        //iterate through the cursor and for each row it creates a new House sets,
        //its properties and finally add to the list that is going to be returned
        try {
            if (cursor.moveToFirst()) {
                do {
                    House house = new House();
                    house.setId(cursor.getInt(cursor.getColumnIndex(HOUSE_ID)));
                    house.setStreetAdress(cursor.getString(cursor.getColumnIndex(STREET_ADRESS)));
                    house.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
                    house.setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE)));
                    house.setCountry(cursor.getString(cursor.getColumnIndex(COUNTRY)));
                    house.setZipcode(cursor.getString(cursor.getColumnIndex(ZIPCODE)));
                    house.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                    house.setBeds(cursor.getInt(cursor.getColumnIndex(BEDS)));
                    house.setBaths(cursor.getInt(cursor.getColumnIndex(BATHS)));
                    house.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
                    house.setLatitude(cursor.getDouble(cursor.getColumnIndex(LATITUDE)));
                    house.setLongitude(cursor.getDouble(cursor.getColumnIndex(LONGITUDE)));
                    list.add(house);
                } while (cursor.moveToNext());
            }
        } catch (Exception e) {
            Log.e("SELECTION", "getAllHouses: selection error: " + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return list;
    }

    //select house for specified ID
    public House getHouse(int id) {
        House house = new House();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + TABLE_NAME + " where " + HOUSE_ID + " = " + id, null);

        //if there are records, move cursor to first, sets houses data and return the house
        try {
            if (cursor.moveToFirst()) {
                house.setId(cursor.getInt(cursor.getColumnIndex(HOUSE_ID)));
                house.setStreetAdress(cursor.getString(cursor.getColumnIndex(STREET_ADRESS)));
                house.setCity(cursor.getString(cursor.getColumnIndex(CITY)));
                house.setProvince(cursor.getString(cursor.getColumnIndex(PROVINCE)));
                house.setCountry(cursor.getString(cursor.getColumnIndex(COUNTRY)));
                house.setZipcode(cursor.getString(cursor.getColumnIndex(ZIPCODE)));
                house.setDescription(cursor.getString(cursor.getColumnIndex(DESCRIPTION)));
                house.setBeds(cursor.getInt(cursor.getColumnIndex(BEDS)));
                house.setBaths(cursor.getInt(cursor.getColumnIndex(BATHS)));
                house.setPrice(cursor.getDouble(cursor.getColumnIndex(PRICE)));
                house.setLatitude(cursor.getDouble(cursor.getColumnIndex(LATITUDE)));
                house.setLongitude(cursor.getDouble(cursor.getColumnIndex(LONGITUDE)));
            }
        } catch (Exception e) {
            Log.e("SELECTION", "getHouse: selection error: " + e.getMessage());
        } finally {
            if (cursor != null && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return house;
    }

    public long insertHouse(House house) {
        //in case it doesnt insert house for some reason, we will know by having -1 as return
        long houseId = -1;

        SQLiteDatabase db = getWritableDatabase();

        //creates contentValues and inserts all house data on it
        ContentValues cv = new ContentValues();
        cv.put(STREET_ADRESS, house.getStreetAdress());
        cv.put(CITY, house.getCity());
        cv.put(PROVINCE, house.getProvince());
        cv.put(COUNTRY, house.getCountry());
        cv.put(ZIPCODE, house.getZipcode());
        cv.put(DESCRIPTION, house.getDescription());
        cv.put(BEDS, house.getBeds());
        cv.put(BATHS, house.getBaths());
        cv.put(PRICE, house.getPrice());
        cv.put(LATITUDE, house.getLatitude());
        cv.put(LONGITUDE, house.getLongitude());

        //begin transaction for consistency and inserts the contentValue to the database
        db.beginTransaction();
        try {
            houseId = db.insertOrThrow(TABLE_NAME, null, cv);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("INSERTION", "insertHouse: insertion error: " + e.getMessage());
        } finally {
            db.endTransaction();
        }

        return houseId;
    }

    //delete all records from table houses, this was created for testing purposes
    public void deleteAllRecords() {
        SQLiteDatabase db = getWritableDatabase();
        db.beginTransaction();

        try {
            db.delete(TABLE_NAME, null, null);
            db.setTransactionSuccessful();
        } catch (Exception e) {
            Log.e("DELETION", "deleteAllRecords: error on deletion: " + e.getMessage());
        } finally {
            db.endTransaction();
        }
    }
}
