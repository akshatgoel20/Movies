package com.starksky.movies.view.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.starksky.movies.R;
import com.starksky.movies.adapter.ReviewsAdapter;
import com.starksky.movies.adapter.TrailersAdapter;
import com.starksky.movies.common.AppUrl;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.sync.MovieSync;
import com.starksky.movies.utils.RecyclerItemClickListener;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass
 * Activities that contain this fragment must implement the
 * {@link MovieDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "position";
    static RecyclerView movie_reviews;
    static RecyclerView movie_trailers;
    int position;
    ImageView movie_detail_image;
    @BindView(R.id.detail_movie_title)
    TextView movie_detail_title;
    @BindView(R.id.detail_movie_rating)
    TextView movie_detail_rating;
    @BindView(R.id.detail_movie_reldate)
    TextView movie_detail_reldate;
    @BindView(R.id.movie_detail_synopsis)
    TextView movie_detail_synopsis;
    @BindView(R.id.mark_as_fav_button)
    Button fav_button;
    String youtubeURL = AppUrl.BASE_YOUTUBE_URL;
    boolean isMovieFav;
    MovieSync movieSync;
    private OnFragmentInteractionListener mListener;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param position Parameter 2.
     * @return A new instance of fragment MovieDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance(int position) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        //   args.putString(ARG_PARAM1, param1);
        args.putInt(ARG_PARAM2, position);
        fragment.setArguments(args);
        return fragment;
    }

    public static void updateReviewAdapter() {
        movie_reviews.setLayoutManager(new LinearLayoutManager(movie_reviews.getContext()));
        movie_reviews.setAdapter(new ReviewsAdapter());

    }

    public static void updateTrailerAdapter() {
        movie_trailers.setLayoutManager(new LinearLayoutManager(movie_trailers.getContext()));
        movie_trailers.setAdapter(new TrailersAdapter());
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            Bundle bundle = getArguments();
            position = bundle.getInt("position");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        movie_reviews = (RecyclerView) rootView.findViewById(R.id.movie_reviews);
        movie_trailers = (RecyclerView) rootView.findViewById(R.id.movie_videos);
        movie_detail_image = (ImageView) rootView.findViewById(R.id.detail_movie_poster);
        fav_button = (Button) rootView.findViewById(R.id.mark_as_fav_button);
        movieSync = new MovieSync(getActivity());
        isMovieFav = new MovieSync(getActivity()).checkMovieExist(ArrayMovieDetails.getArrayList().get(position).getMovie_id());
        if (isMovieFav) {
            fav_button.setText("Marked as favourite");
        } else {
            fav_button.setText("Mark as favourite");
        }
        movie_trailers.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        youtubeURL = AppUrl.BASE_YOUTUBE_URL.concat(ArrayMovieDetails.getTrailerDetailsArrayList().get(position).getSource());
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeURL)));

                    }
                })
        );
        fav_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (movieSync.checkMovieExist(ArrayMovieDetails.getArrayList().get(position).getMovie_id())) {
                    fav_button.setText("Mark As Favourite");
                    movieSync.deleteMovieFromFav(ArrayMovieDetails.getArrayList().get(position).getMovie_id());
                } else {
                    fav_button.setText("Marked As Favourite");
                    movieSync.insertMovieIntoFav(position);
                }


            }
        });


        ButterKnife.bind(this, rootView);
        loadContent();
        return rootView;
    }

    private void loadContent() {

        String url = AppUrl.BASE_URL_IMAGE.concat(ArrayMovieDetails.getArrayList().get(position).getPoster_path());
        Glide.with(getActivity())
                .load(url)
                .into(movie_detail_image);
        movie_detail_title.setText(ArrayMovieDetails.getArrayList().get(position).getTitle());
        movie_detail_reldate.setText(ArrayMovieDetails.getArrayList().get(position).getRelease_date());
        movie_detail_rating.setText(ArrayMovieDetails.getArrayList().get(position).getUser_rating());
        movie_detail_synopsis.setText(ArrayMovieDetails.getArrayList().get(position).getSynopsis());
        updateReviewAdapter();
        updateTrailerAdapter();

    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

   /* @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }*/

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
