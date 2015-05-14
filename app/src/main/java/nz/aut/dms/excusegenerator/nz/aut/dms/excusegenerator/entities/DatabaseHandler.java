package nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Got the SQLiteAssetHelper to work. Have included a populated database as well, but for now
 * it's just random strings. At least we can deploy the app with our own database. Used SQLiteStudio
 * to edit the database.
 *
 * This retreives the ExcusesList and can add and delete single excuses. It can also retreive
 * certain excuses, but there are no complicated search functions. Thought we might just do that
 * in the ArrayList instead.
 *
 *
 * Created by Oeyvind on 14.05.2015.
 */
public class DatabaseHandler extends SQLiteAssetHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "excuses";

    private static final String TABLE_EXCUSES = "excuses",
    KEY_ID = "id",
    KEY_EXCUSE = "excuse",
    KEY_AGERANGE = "agerange";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXCUSES);
        onCreate(db);
    }

    public void createExcuse(Excuse excuse) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();

        values.put(KEY_EXCUSE, excuse.getExcuse());
        values.put(KEY_AGERANGE, excuse.getAgeRange());

        db.insert(TABLE_EXCUSES, null, values);
        db.close();
    }

    public Excuse getExcuse(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_EXCUSES, new String[] { KEY_ID, KEY_EXCUSE, KEY_AGERANGE },
                KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Excuse excuse = new Excuse(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2));
        db.close();
        cursor.close();
        return excuse;
    }

    public void deleteExcuse(Excuse excuse){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_EXCUSES, KEY_ID + "=?", new String[] { String.valueOf(excuse.getId()) });
        db.close();
    }

    public int getExcuseCount() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXCUSES, null);
        int count = cursor.getCount();
        db.close();
        cursor.close();
        return count;
    }

    public int updateExcuse(Excuse excuse) {
        SQLiteDatabase db = getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_ID, excuse.getId());
        values.put(KEY_EXCUSE, excuse.getExcuse());
        values.put(KEY_AGERANGE, excuse.getAgeRange());

        int returnVariable = db.update(TABLE_EXCUSES, values, KEY_ID + "=?",
                new String[]{String.valueOf(excuse.getId())});
        db.close();
        return returnVariable;
    }


    public List<Excuse> getAllExcuses() {
        List<Excuse> excuseList = new ArrayList<Excuse>();
        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXCUSES, null);

        if (cursor.moveToFirst()) {
            do {
                Excuse excuse = new Excuse(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2));
                excuseList.add(excuse);
            }
            while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return excuseList;
    }

}
