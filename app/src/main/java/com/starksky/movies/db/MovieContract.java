package com.starksky.movies.db;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by akshat on 21/08/16.
 */
public class MovieContract {
    public static final String CONTENT_AUTHORITY = "com.starksky.movies";
    public static final String COLUMN_MOVIE_ID_KEY = "movie_id";
    public static final String PATH_MOVIES = "movies";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);
    public static final String PATH_FAVOURITES = "favourites";


    public static final class MovieEntry implements BaseColumns {

        public static final Uri CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_MOVIES).build();

        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES;

        public static final String TABLE_NAME = "movies";

        public static final String COLUMN_ORIGINAL_TITLE = "original_title";
        public static final String COLUMN_OVERVIEW = "overview";
        public static final String COLUMN_RELEASE_DATE = "release_date";
        public static final String COLUMN_POSTER_PATH = "poster_path";
        public static final String COLUMN_POPULARITY = "popularity";
        public static final String COLUMN_TITLE = "title";
        public static final String COLUMN_AVERAGE_VOTE = "vote_average";
        public static final String COLUMN_VOTE_COUNT = "vote_count";
        public static final String COLUMN_BACKDROP_PATH = "backdrop_path";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY, " +
                        COLUMN_ORIGINAL_TITLE + " TEXT, " +
                        COLUMN_OVERVIEW + " TEXT, " +
                        COLUMN_RELEASE_DATE + " TEXT, " +
                        COLUMN_POSTER_PATH + " TEXT, " +
                        COLUMN_POPULARITY + " REAL, " +
                        COLUMN_TITLE + " TEXT, " +
                        COLUMN_AVERAGE_VOTE + " REAL, " +
                        COLUMN_VOTE_COUNT + " INTEGER," +
                        COLUMN_BACKDROP_PATH + " TEXT " +
                        " );";

        private static final String[] COLUMNS = {_ID, COLUMN_ORIGINAL_TITLE, COLUMN_OVERVIEW,
                COLUMN_RELEASE_DATE, COLUMN_POSTER_PATH, COLUMN_POPULARITY, COLUMN_TITLE,
                COLUMN_AVERAGE_VOTE, COLUMN_VOTE_COUNT, COLUMN_BACKDROP_PATH};

        private MovieEntry() {
        }

        public static Uri buildMovieUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static long getIdFromUri(Uri uri) {
            return ContentUris.parseId(uri);
        }

        public static String[] getColumns() {
            return COLUMNS.clone();
        }
    }


    public static final class Favourites implements BaseColumns {
        public static final Uri CONTENT_URI =
                MovieEntry.CONTENT_URI.buildUpon().appendPath(PATH_FAVOURITES).build();
        public static final String CONTENT_DIR_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_MOVIES
                        + "/" + PATH_FAVOURITES;

        public static final String TABLE_NAME = "favorites";

        public static final String SQL_CREATE_TABLE =
                "CREATE TABLE " + TABLE_NAME + " (" +
                        _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COLUMN_MOVIE_ID_KEY + " INTEGER NOT NULL, " +

                        " FOREIGN KEY (" + COLUMN_MOVIE_ID_KEY + ") REFERENCES " +
                        MovieEntry.TABLE_NAME + " (" + MovieEntry._ID + ") " +

                        " );";


    }


}

