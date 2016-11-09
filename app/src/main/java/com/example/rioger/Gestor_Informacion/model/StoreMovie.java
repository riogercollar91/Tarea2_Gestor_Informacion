package com.example.rioger.Gestor_Informacion.model;

/**
 * Created by Rioger on 06/11/2016.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import static com.example.rioger.Gestor_Informacion.model.MovieContract.tablaMovie;

public class StoreMovie extends SQLiteOpenHelper {
    public static final String DATABASE_FILE = tablaMovie.TABLE_NAME + ".db";

    public static final int NUM_VERSION = 1;

    public StoreMovie(Context context, int version) {
        super(context, DATABASE_FILE, null, NUM_VERSION);
    }

    public StoreMovie(Context context) {
        super(context, DATABASE_FILE, null, NUM_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String consultaSQL = "CREATE TABLE " + tablaMovie.TABLE_NAME + "( " +
                tablaMovie.COL_NAME_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                tablaMovie.COL_TITLE + " TEXT, " +
                tablaMovie.COL_OVERVIEW + " TEXT, " +
                tablaMovie.COL_RELEASE_DATE + " TEXT, " +
                tablaMovie.COL_VOTE_AVERAGE + " DOUBLE " +
                ");";

        sqLiteDatabase.execSQL(consultaSQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        String consultaSQL = "DROP TABLE" + tablaMovie.TABLE_NAME;
        sqLiteDatabase.execSQL(consultaSQL);
    }

    public long count() {
        SQLiteDatabase db = getReadableDatabase();
        return DatabaseUtils.queryNumEntries(db, tablaMovie.TABLE_NAME);
    }

    public void insertMovie(String title, String overview, String releasedate, double voteaverage) {

        ContentValues contentValues = new ContentValues();
        contentValues.put(tablaMovie.COL_TITLE, title);
        contentValues.put(tablaMovie.COL_OVERVIEW, overview);
        contentValues.put(tablaMovie.COL_RELEASE_DATE, releasedate);
        contentValues.put(tablaMovie.COL_VOTE_AVERAGE, voteaverage);

        SQLiteDatabase db = getReadableDatabase();
        db.insert(tablaMovie.TABLE_NAME, null, contentValues);
    }

    public Boolean exitsMovie(String title) {

        String CREATE_SQL = "SELECT *"
                + " FROM " + tablaMovie.TABLE_NAME + " WHERE "
                + tablaMovie.COL_TITLE + "=" + "'" + title + "'" + ";";

        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery(CREATE_SQL, null);

        if (cursor.moveToFirst()) {
            return true;
        } else
            return false;
    }


    public void deleteAllMovies() {

        SQLiteDatabase db = getReadableDatabase();
        db.delete(tablaMovie.TABLE_NAME, null, null);
    }

}
