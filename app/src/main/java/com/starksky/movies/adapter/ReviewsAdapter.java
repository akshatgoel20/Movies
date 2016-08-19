package com.starksky.movies.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.starksky.movies.R;
import com.starksky.movies.model.ArrayMovieDetails;

/**
 * Created by akshat on 19/08/16.
 */
public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ViewHolder> {



    public static class ViewHolder extends RecyclerView.ViewHolder {

        protected final TextView name;
        protected final TextView content;


        public ViewHolder(View view) {
            super(view);
            name = (TextView) view.findViewById(R.id.name_review_detail);
            content = (TextView) view.findViewById(R.id.content_review_detail);

        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_detail_cell, parent, false);
        return new ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.name.setText(ArrayMovieDetails.getReviewDetailsArrayList().get(position).getAuthor());
        holder.content.setText(ArrayMovieDetails.getReviewDetailsArrayList().get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return ArrayMovieDetails.getReviewDetailsArrayList().size();
    }
}
