package com.starksky.movies.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.starksky.movies.R;
import com.starksky.movies.common.AppUrl;
import com.starksky.movies.model.ArrayMovieDetails;

/**
 * Created by akshat on 23/07/16.
 */
public class GridPosterAdapter extends BaseAdapter {
    private static LayoutInflater inflater = null;
    Context mContext;
    ImageView imageView;



    public GridPosterAdapter(Context context) {
        this.mContext = context;
    }


    @Override
    public int getCount() {
        return ArrayMovieDetails.getArrayList().size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View cellview;
        inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        cellview = inflater.inflate(R.layout.grid_image, null);
        imageView = (ImageView) cellview.findViewById(R.id.gridimage);
        //imageView.setImageResource(R.mipmap.ic_launcher);
        String url = AppUrl.BASE_URL_IMAGE.concat(ArrayMovieDetails.getArrayList().get(i).getPoster_path());
        Picasso.with(mContext)
                .load(url)
                .placeholder(R.drawable.image_placeholder)
                .error(R.drawable.image_placeholder)
                .into(imageView);
        return cellview;

    }
}
