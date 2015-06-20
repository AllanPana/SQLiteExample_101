package com.sqlite.pana.allan.sqliteexample_01;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by allan on 20/06/15.
 */
public class MyDataBaseAdapter  {

    MyDataBaseHelper myDataBaseHelper;

    MyDataBaseAdapter(Context context){
        myDataBaseHelper = new MyDataBaseHelper(context);
    }


    /**
     *
     * @param name
     * @param password
     * @return = long, if long lessthan zero insert operation is not successful
     */
    public long insertData(String name, String password){
        SQLiteDatabase database = myDataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDataBaseHelper.NAME,name);
        contentValues.put(MyDataBaseHelper.PASSWORD,password);

        long id = database.insert(MyDataBaseHelper.TABLE_NAME,null,contentValues);
        return id;
    }

    public List<MyData> viewallData(){
        List<MyData> stringList = new ArrayList<>();
        SQLiteDatabase database = myDataBaseHelper.getWritableDatabase();
        String [] columns = {MyDataBaseHelper.UID,MyDataBaseHelper.NAME,MyDataBaseHelper.PASSWORD};
        Cursor cursor = database.query(MyDataBaseHelper.TABLE_NAME, columns, null, null, null, null, null);

        while (cursor.moveToNext()){
            int columnId = cursor.getColumnIndex(MyDataBaseHelper.UID);
            int columnName = cursor.getColumnIndex(MyDataBaseHelper.NAME);
            int columnPassword = cursor.getColumnIndex(MyDataBaseHelper.PASSWORD);

            int _id = cursor.getInt(columnId);
            String _name = cursor.getString(columnName);
            String _password = cursor.getString(columnPassword);

            MyData myData = new MyData(_id,_name,_password);
            stringList.add(myData);

        }
        return stringList;
    }


    public String getUserDetails(String name){
        SQLiteDatabase database = myDataBaseHelper.getWritableDatabase();
        String [] columns = {MyDataBaseHelper.UID,MyDataBaseHelper.NAME,MyDataBaseHelper.PASSWORD};
        String query= MyDataBaseHelper.NAME+" = '"+name+"'";
        Cursor cursor = database.query(MyDataBaseHelper.TABLE_NAME, columns, query, null, null, null, null);
        String data = "";
        while (cursor.moveToNext()){
            int columnId = cursor.getColumnIndex(MyDataBaseHelper.UID);
            int columnName = cursor.getColumnIndex(MyDataBaseHelper.NAME);
            int columnPassword = cursor.getColumnIndex(MyDataBaseHelper.PASSWORD);

            int _id = cursor.getInt(columnId);
            String _name = cursor.getString(columnName);
            String _password = cursor.getString(columnPassword);

            data = "ID         : " + _id +
                   "\nName     : " + _name +
                   "\nPassword : " + _password;
        }
        return data;
    }


    //Update TABLE_NAME SET Name='theNewName' where Name=? 'theOldName'
    public long  updateData(String oldName, String newName){
        SQLiteDatabase database = myDataBaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(MyDataBaseHelper.NAME,newName);
        String [] whereArgs = {oldName};
        long update= database.update(MyDataBaseHelper.TABLE_NAME,contentValues,MyDataBaseHelper.NAME+" =? ",whereArgs );

        return update;
    }



    //Delete * from TABLE_NAME WHERE NAME = thename
    public long deleteData(String nameToDelete){
        SQLiteDatabase database = myDataBaseHelper.getWritableDatabase();
        String [] whereArgs = {nameToDelete};
        return database.delete(MyDataBaseHelper.TABLE_NAME,MyDataBaseHelper.NAME+ " =? ", whereArgs);
    }



    /**
     * innner classs
     */
    static class MyDataBaseHelper extends SQLiteOpenHelper{
        private static final String DATABASE_NAME = "mydb";
        private static final String TABLE_NAME = "mytable";
        private static final int DATABASE_VERSION = 1;
        private static final String UID = "_id";
        private static final String NAME = "Name";
        private static final String PASSWORD = "Password";
        private static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
                +" ("+UID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +NAME + " VARCHAR(255), "
                +PASSWORD + " VARCHAR(255));";
        private static final String DROP_TABLE = "DROP TABLE IF EXIST " + TABLE_NAME;
        private Context context;


        /**
         * Contructor
         * @param context
         */
        public MyDataBaseHelper(Context context) {
            super(context, DATABASE_NAME, null, DATABASE_VERSION);
            this.context = context;
            Toast.makeText(context,"Constructor called..........",Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{
                db.execSQL(CREATE_TABLE);
                Toast.makeText(context,"Oncreate called..........",Toast.LENGTH_LONG).show();
            }catch (SQLException aqle){
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            try{
                db.execSQL(DROP_TABLE);
                onCreate(db);
                Toast.makeText(context,"onUpgrade called..........",Toast.LENGTH_LONG).show();
            }catch (SQLException sqle){

            }
        }
    }
}
