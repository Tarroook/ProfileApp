package com.tarook.controlegamerz;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;

public class SQLiteManager extends SQLiteOpenHelper {

    private static SQLiteManager instance;
    private static final String DB_NAME = "profileDB";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "profiles";
    private static final String COUNTER = "counter";
    private static final String ID_FIELD = "id";
    private static final String NAME_FIELD = "name";
    private static final String AGE_FIELD = "age";
    private static final String ADDRESS_FIELD = "address";
    private static final String EMAIL_FIELD = "email";
    private static final String PICTURE_FIELD = "picture";

    public SQLiteManager(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    public static SQLiteManager getInstance(Context context) {
        if(instance == null)
            instance = new SQLiteManager(context);
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        StringBuilder sql;
        sql = new StringBuilder().append("CREATE TABLE ").append(TABLE_NAME).append(" (")
                .append(COUNTER).append(" INTEGER PRIMARY KEY AUTOINCREMENT, ")
                .append(ID_FIELD).append(" INT, ")
                .append(NAME_FIELD).append(" TEXT, ")
                .append(AGE_FIELD).append(" TEXT, ")
                .append(ADDRESS_FIELD).append(" TEXT, ")
                .append(EMAIL_FIELD).append(" TEXT, ")
                .append(PICTURE_FIELD).append(" BLOB);");
        db.execSQL(sql.toString());
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {}

    public void addProfile(Profile profile){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FIELD, profile.getId());
        values.put(NAME_FIELD, profile.getName());
        values.put(AGE_FIELD, profile.getAge());
        values.put(ADDRESS_FIELD, profile.getAddress());
        values.put(EMAIL_FIELD, profile.getEmail());
        byte[] data = getBitmapAsByteArray(profile.getPicture());
        values.put(PICTURE_FIELD, data);

        db.insert(TABLE_NAME, null, values);
    }

    public void populateList(){
        SQLiteDatabase db = getReadableDatabase();
        try(Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null)){
            if(result.getCount() != 0){
                while(result.moveToNext()){
                    int id = result.getInt(1);
                    String name = result.getString(2);
                    String age = result.getString(3);
                    String address = result.getString(4);
                    String email = result.getString(5);
                    byte[] data = result.getBlob(6);
                    Bitmap picture = getBitmapFromByteArray(data);
                    Profile.profiles.add(new Profile(id, name, age, address, email, picture));
                }
            }
        }
    }

    public void updateProfileInDB(Profile profile){
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(ID_FIELD, profile.getId());
        values.put(NAME_FIELD, profile.getName());
        values.put(AGE_FIELD, profile.getAge());
        values.put(ADDRESS_FIELD, profile.getAddress());
        values.put(EMAIL_FIELD, profile.getEmail());
        byte[] data = getBitmapAsByteArray(profile.getPicture());
        values.put(PICTURE_FIELD, data);

        db.update(TABLE_NAME, values, ID_FIELD + " = ?", new String[]{String.valueOf(profile.getId())});
    }

    private Bitmap getBitmapFromByteArray(byte[] data) {
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }

    public static byte[] getBitmapAsByteArray(Bitmap bitmap) {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, outputStream);
        return outputStream.toByteArray();
    }


}
