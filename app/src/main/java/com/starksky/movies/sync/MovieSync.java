package com.starksky.movies.sync;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;

import com.starksky.movies.db.MovieContract;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.model.MovieDetailsModel;

import java.util.ArrayList;

/**
 * Created by akshat on 01/09/16.
 */
public class MovieSync {
    Context mContext;
    ArrayList<MovieDetailsModel> arrayMovieDetailses = new ArrayList<>();

    public MovieSync(Context context) {
        this.mContext = context;
    }

    public boolean checkMovieExist(String movieId) {
        long movID;
        // First, check if the location with this city name exists in the db
        Cursor movieCursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                new String[]{MovieContract.MovieEntry._ID},
                MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?",
                new String[]{movieId},
                null);

        if (movieCursor.moveToFirst()) {
            int movieIdindex = movieCursor.getColumnIndex(MovieContract.MovieEntry._ID);
            movID = movieCursor.getLong(movieIdindex);
            movieCursor.close();
            return true;
        } else {
            movieCursor.close();
            return false;
            // The resulting URI contains the ID for the row.  Extract the locationId from the Uri.
            // movID = ContentUris.parseId(insertedUri);
        }


    }

    public void insertMovieIntoFav(int position) { // Now that the content provider is set up, inserting rows of data is pretty simple.
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
    }

    public void deleteMovieFromFav(String movieId) {
        mContext.getContentResolver().delete(MovieContract.MovieEntry.CONTENT_URI, MovieContract.MovieEntry.COLUMN_MOVIE_ID + " = ?", new String[]{movieId});
    }

    public void setFavMovie() {
        Cursor movieCursor = mContext.getContentResolver().query(
                MovieContract.MovieEntry.CONTENT_URI,
                MovieContract.MovieEntry.MOVIE_COLUMNS,
                null,
                null,
                null);
        if (movieCursor.moveToFirst()) {
            do {
                MovieDetailsModel movieDetailsModel = new MovieDetailsModel();
                movieDetailsModel.setTitle(movieCursor.getString(MovieContract.MovieEntry.COL_MOVIE_TITLE));
                movieDetailsModel.setMovie_id(movieCursor.getString(MovieContract.MovieEntry.COL_MOVIE_ID));
                movieDetailsModel.setPoster_path(movieCursor.getString(MovieContract.MovieEntry.COL_MOVIE_POSTER_PATH));
                movieDetailsModel.setUser_rating(movieCursor.getString(MovieContract.MovieEntry.COL_MOVIE_VOTE_AVERAGE));
                movieDetailsModel.setSynopsis(movieCursor.getString(MovieContract.MovieEntry.COL_MOVIE_OVERVIEW));
                movieDetailsModel.setRelease_date(movieCursor.getString(MovieContract.MovieEntry.COL_MOVIE_RELEASE_DATE));
                arrayMovieDetailses.add(movieDetailsModel);
            } while (movieCursor.moveToNext());
            new ArrayMovieDetails().setArrayList(arrayMovieDetailses);
        }
        movieCursor.close();
    }

}

