package com.example.rioger.Gestor_Informacion.model;

/**
 * Created by Rioger on 07/11/2016.
 */

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;

public class MoviesProvider extends ContentProvider {

    private static final String AUTHORITY = MoviesProvider.class.getPackage().getName() + ".MoviesProvider";
    private static final String ENTITY = "MovieEntity";

    private static final String uri = "content://" + AUTHORITY + "/" + ENTITY;
    private static final Uri CONTENT_URI = Uri.parse(uri);

    private static final int ID_URI_MOVIES = 1;
    private static final UriMatcher uriMatcher;

    static {
        uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
        uriMatcher.addURI(AUTHORITY, ENTITY, ID_URI_MOVIES);
    }

    private StoreMovie movies;

    @Override
    public boolean onCreate() {
        movies = new StoreMovie(getContext());

        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        String where = selection;

        SQLiteDatabase db = movies.getReadableDatabase();

        return db.query(
                MovieContract.tablaMovie.TABLE_NAME,
                projection,
                where,
                selectionArgs,
                null, null, sortOrder);
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {

        String where = selection;

        SQLiteDatabase db = movies.getWritableDatabase();

        return db.update(
                MovieContract.tablaMovie.TABLE_NAME,
                values,
                where,
                selectionArgs
        );
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        String where = selection;

        SQLiteDatabase db = movies.getWritableDatabase();

        return db.delete(
                MovieContract.tablaMovie.TABLE_NAME,
                where,
                selectionArgs
        );
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        long regId;

        SQLiteDatabase db = movies.getWritableDatabase();
        regId = db.insert(
                MovieContract.tablaMovie.TABLE_NAME,
                null,
                values
        );

        return ContentUris.withAppendedId(CONTENT_URI, regId);
    }


    @Override
    public String getType(Uri uri) {

        switch (uriMatcher.match(uri)) {
            case ID_URI_MOVIES:
                return "vnd.android.cursor.dir/vnd.com.example.provider.movieentity";

            default:
                return null;
        }
    }
}
