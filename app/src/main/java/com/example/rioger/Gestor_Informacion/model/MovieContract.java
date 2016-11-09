package com.example.rioger.Gestor_Informacion.model;

/**
 * Created by Rioger on 06/11/2016.
 */

import android.provider.BaseColumns;

public class MovieContract {

    private MovieContract(){}

    public static class tablaMovie implements BaseColumns {

        public final static String TABLE_NAME = "movies";

        public final static String COL_NAME_ID = _ID;
        public final static String COL_TITLE = "title";
        public final static String COL_OVERVIEW = "overview";
        public final static String COL_RELEASE_DATE = "releasedate";
        public final static String COL_VOTE_AVERAGE = "voteaverage";

    }
}
