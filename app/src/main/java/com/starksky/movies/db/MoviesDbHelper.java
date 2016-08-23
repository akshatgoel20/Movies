package com.starksky.movies.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by akshat on 21/08/16.
 */
public class MoviesDbHelper extends SQLiteOpenHelper {

    static final String DATABASE_NAME = "movies.db";
    private static final int DATABASE_VERSION = 1;
    private static final String SQL_DROP_TABLE_IF_EXISTS = "DROP TABLE IF EXISTS ";


    public MoviesDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(MovieContract.MovieEntry.SQL_CREATE_TABLE);
        db.execSQL(MovieContract.Favourites.SQL_CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DROP_TABLE_IF_EXISTS + MovieContract.MovieEntry.TABLE_NAME);
        db.execSQL(SQL_DROP_TABLE_IF_EXISTS + MovieContract.Favourites.TABLE_NAME);
        onCreate(db);

    }
}
