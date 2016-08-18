package com.starksky.movies.view.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.starksky.movies.R;
import com.starksky.movies.common.AppUrl;
import com.starksky.movies.model.ArrayMovieDetails;
import com.starksky.movies.utils.FetchMovieReviews;
import com.starksky.movies.utils.FetchMovieTrailers;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MovieDetailFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MovieDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MovieDetailFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    int position;
    @BindView(R.id.detail_movie_poster) ImageView movie_detail_image;
    @BindView(R.id.detail_movie_title) TextView movie_detail_title;
    @BindView(R.id.detail_movie_rating) TextView movie_detail_rating;
    @BindView(R.id.detail_movie_reldate) TextView movie_detail_reldate;
    @BindView(R.id.movie_detail_synopsis) TextView movie_detail_synopsis;

    private OnFragmentInteractionListener mListener;

    public MovieDetailFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MovieDetailFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MovieDetailFragment newInstance(String param1, String param2) {
        MovieDetailFragment fragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
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
        ButterKnife.bind(this, rootView);
        loadContent();
        return rootView;
    }

    private void loadContent(){
        new FetchMovieTrailers(getActivity(),position).execute();
        new FetchMovieReviews(getActivity(), position).execute();
        String url = AppUrl.BASE_URL_IMAGE.concat(ArrayMovieDetails.getArrayList().get(position).getPoster_path());
        Picasso.with(getActivity())
                .load(url)
                .into(movie_detail_image);
        movie_detail_title.setText(ArrayMovieDetails.getArrayList().get(position).getTitle());
        movie_detail_reldate.setText(ArrayMovieDetails.getArrayList().get(position).getRelease_date());
        movie_detail_rating.setText(ArrayMovieDetails.getArrayList().get(position).getUser_rating());
        movie_detail_synopsis.setText(ArrayMovieDetails.getArrayList().get(position).getSynopsis());

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
