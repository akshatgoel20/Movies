package com.starksky.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starksky.movies.R;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.view.fragment.MovieDetailFragment;

/**
 * Created by akshat on 21/08/16.
 */
public class TrailersAdapter extends RecyclerView.Adapter<TrailersAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.trailer_detail_cell, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.title.setText(ArrayMovieDetails.getTrailerDetailsArrayList().get(position).getName());
    }

    @Override
    public int getItemCount() {
        return ArrayMovieDetails.getTrailerDetailsArrayList().size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {


        protected final TextView title;


        public ViewHolder(View view) {
            super(view);

            title = (TextView) view.findViewById(R.id.title_trailer_detail);

        }
    }


}
