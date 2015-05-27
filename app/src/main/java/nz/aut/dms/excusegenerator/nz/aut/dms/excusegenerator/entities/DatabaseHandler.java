package nz.aut.dms.excusegenerator.nz.aut.dms.excusegenerator.entities;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Got the SQLiteAssetHelper to work. Have included a populated database as well, but for now
 * it's just one excuse. At least we can deploy the app with our own database. Used SQLiteStudio
 * to edit the database.
 *
 * This retreives the ExcusesList and can add and delete single excuses. It can also retreive
 * certain excuses, but there are no complicated search functions. Thought we might just do that
 * in the ArrayList that we get from the db instead.
 *
 *
 * Created by Oeyvind on 14.05.2015.
 */
public class DatabaseHandler extends SQLiteAssetHelper {

    private static final int DATABASE_VERSION = 1;

    private static final String DATABASE_NAME = "excuses";

    private static final String TABLE_EXCUSES = "excuses";
    private static final String KEY_ID = "_id";
    private static final String KEY_PERSON = "_person";
    private static final String KEY_QUALITY = "_quality";
    private static final String KEY_EXCUSE = "_excuse";
    private static final String KEY_SEX = "_sex";
    private static final String KEY_MIN_AGE = "_minage";
    private static final String KEY_MAX_AGE = "_maxage";
    private static final String KEY_USED_ON = "_usedOn";


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
        values.put(KEY_USED_ON, excuse.getUsedOn());

        db.insert(TABLE_EXCUSES, null, values);
        db.close();
    }

    public Excuse getExcuse(int id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_EXCUSES, new String[] { KEY_ID, KEY_PERSON, KEY_QUALITY,KEY_EXCUSE, KEY_SEX, KEY_MIN_AGE, KEY_MAX_AGE, KEY_USED_ON},
                KEY_ID + "=?", new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Excuse excuse = new Excuse(Integer.parseInt(cursor.getString(0)), cursor.getString(1),
                cursor.getString(2).charAt(0), cursor.getString(3),
                cursor.getString(4).charAt(0), Integer.parseInt(cursor.getString(5)),
                Integer.parseInt((cursor.getString(6))), cursor.getString(7));
        db.close();
        cursor.close();
        return excuse;
    }



    public void deleteExcuse(Excuse excuse){
        SQLiteDatabase db = getWritableDatabase();
        db.delete(TABLE_EXCUSES, KEY_ID + "=?", new String[]{String.valueOf(excuse.getId())});
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
        values.put(KEY_PERSON, excuse.getPerson());
        values.put(KEY_QUALITY, String.valueOf(excuse.getQuality()));
        values.put(KEY_EXCUSE, excuse.getExcuse());
        values.put(KEY_SEX, String.valueOf(excuse.getSex()));
        values.put(KEY_MIN_AGE, String.valueOf(excuse.getMinAge()));
        values.put(KEY_MAX_AGE, String.valueOf(excuse.getMaxAge()));
        values.put(KEY_USED_ON, excuse.getUsedOn());

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
                        cursor.getString(1), cursor.getString(2).charAt(0), cursor.getString(3),
                        cursor.getString(4).charAt(0), Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt((cursor.getString(6))), cursor.getString(7));
                excuseList.add(excuse);
            }
            while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return excuseList;
    }
    //seb
   public ArrayList<String> getAllPersons() {
       ArrayList<String> personList = new ArrayList<String>();
       //List<Excuse> personList = new ArrayList<Excuse>();
       SQLiteDatabase db = getWritableDatabase();
       Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXCUSES, null);

       if (cursor.moveToFirst()) {
           do {
               Excuse excuse = new Excuse(Integer.parseInt(cursor.getString(0)),
                       cursor.getString(1), cursor.getString(2).charAt(0), cursor.getString(3),
                       cursor.getString(4).charAt(0), Integer.parseInt(cursor.getString(5)),
                       Integer.parseInt((cursor.getString(6))), cursor.getString(7));
               if(personList.contains(cursor.getString(1))){
                   //true
               }else{
                   personList.add(cursor.getString(1));
               }
           }
           while (cursor.moveToNext());
       }
       db.close();
       cursor.close();
       return personList;
   }
       public ArrayList<String> getAllQuality() {
           ArrayList<String> qualityList = new ArrayList<String>();
           SQLiteDatabase db = getWritableDatabase();
           Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_EXCUSES, null);

           if (cursor.moveToFirst()) {
               do {
                   Excuse excuse = new Excuse(Integer.parseInt(cursor.getString(0)),
                           cursor.getString(1), cursor.getString(2).charAt(0), cursor.getString(3),
                           cursor.getString(4).charAt(0), Integer.parseInt(cursor.getString(5)),
                           Integer.parseInt((cursor.getString(6))), cursor.getString(7));
                   if(qualityList.contains(cursor.getString(2))){
                       //true
                   }else{
                       qualityList.add(cursor.getString(2));
                   }
               }
               while (cursor.moveToNext());
           }
           db.close();
           cursor.close();
           return qualityList;
       }
       public ArrayList<String> getAllVailedExcuses(String person,String quality,char sex,int age) {
        ArrayList<String> vailedExcusesList = new ArrayList<String>();
        SQLiteDatabase db = getWritableDatabase();
           String stringSex = Character.toString(sex);
            String[]SelectionArgs={person,quality};
           Cursor cursor = db.query(TABLE_EXCUSES, new String[]{KEY_ID, KEY_PERSON, KEY_QUALITY, KEY_EXCUSE, KEY_SEX, KEY_MIN_AGE, KEY_MAX_AGE, KEY_USED_ON}, KEY_PERSON + "=? AND "+KEY_QUALITY + "=? ",SelectionArgs, null, null, null);//working Query
          // Cursor cursor = db.query(TABLE_EXCUSES, new String[]{KEY_ID, KEY_PERSON, KEY_QUALITY, KEY_EXCUSE, KEY_SEX, KEY_MIN_AGE, KEY_MAX_AGE, KEY_USED_ON},KEY_PERSON + "=?", new String[]{person} , null, null, null);//working Query
        if (cursor.moveToFirst()) {
            do {
                Excuse excuse = new Excuse(Integer.parseInt(cursor.getString(0)),
                        cursor.getString(1), cursor.getString(2).charAt(0), cursor.getString(3),
                        cursor.getString(4).charAt(0), Integer.parseInt(cursor.getString(5)),
                        Integer.parseInt((cursor.getString(6))), cursor.getString(7));
                if(age>= Integer.parseInt(cursor.getString(5))&& age<=Integer.parseInt(cursor.getString(6))) {
                    vailedExcusesList.add(cursor.getString(3));
                }
            }
            while (cursor.moveToNext());
        }
        db.close();
        cursor.close();
        return vailedExcusesList;
    }
       //seb




}
