package com.usefi.lifecyclelogsapp;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class SqLiteDbHelper extends SQLiteOpenHelper {

    String tableName = "SavedMovies";
    public SqLiteDbHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE_QUERY = "CREATE TABLE " + tableName + "("
                + "movie_id INTEGER PRIMARY KEY"
                + ")";

        db.execSQL(CREATE_TABLE_QUERY);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public  void InsertToDB(int movie_id){
        String INSERT_INTO_QUERY = "INSERT INTO " + tableName + "(movie_id) VALUES("
                + movie_id
                +")";
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(INSERT_INTO_QUERY);
        db.close();
    }

    public void DeleteFromDB(int id){
        String DELETE_FROM_DB = "DELETE FROM " + tableName + " WHERE movie_id = " + id;
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(DELETE_FROM_DB);
        db.close();
    }

    public boolean GetMovieInfo(int id) {
        String GET_INFO_QUERY = "SELECT movie_id FROM " + tableName +
                " WHERE movie_id = " + id;
        boolean hasResult = false ;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(GET_INFO_QUERY,null);
        while (cursor.moveToNext()){
            hasResult = true;
        }
        cursor.close();
        db.close();
        return hasResult;
    }

}
