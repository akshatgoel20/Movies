package com.starksky.movies.sync;

import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.starksky.movies.db.MovieContract;
import com.starksky.movies.model.ArrayMovieDetails;

/**
 * Created by akshat on 01/09/16.
 */
public class MovieSync {
Context mContext ;
    public MovieSync(Context context){
        this.mContext = context ;
    }

   public  void checkMovieExist(String  movieId, int position) {
        long movID ;
        // First, check if the location with this city name exists in the db
        Cursor locationCursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                new String[]{MovieContract.MovieEntry._ID},
                MovieContract.MovieEntry.COL_MOVIE_ID + " = ?",
                new String[]{movieId},
                null);

        if (locationCursor.moveToFirst()) {
            int movieIdindex = locationCursor.getColumnIndex(MovieContract.MovieEntry._ID);
            movID = locationCursor.getLong(movieIdindex);
        } else {
            // Now that the content provider is set up, inserting rows of data is pretty simple.
            // First create a ContentValues object to hold the data you want to insert.
            ContentValues movieValues = new ContentValues();

            // Then add the data, along with the corresponding name of the data type,
            // so the content provider knows what kind of value is being inserted.
            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_ID, ArrayMovieDetails.getArrayList().get(position).getMovie_id());
            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_TITLE, ArrayMovieDetails.getArrayList().get(position).getTitle());
            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_POSTER_PATH, ArrayMovieDetails.getArrayList().get(position).getPoster_path());
            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_OVERVIEW, ArrayMovieDetails.getArrayList().get(position).getSynopsis());
            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_VOTE_AVERAGE, ArrayMovieDetails.getArrayList().get(position).getUser_rating());
            movieValues.put(MovieContract.MovieEntry.COLUMN_MOVIE_RELEASE_DATE, ArrayMovieDetails.getArrayList().get(position).getRelease_date());

            // Finally, insert movie data into the database.
            Uri insertedUri = mContext.getContentResolver().insert(
                    MovieContract.MovieEntry.CONTENT_URI,
                    movieValues
            );

            // The resulting URI contains the ID for the row.  Extract the locationId from the Uri.
            movID = ContentUris.parseId(insertedUri);
        }

        locationCursor.close();
    }
}
