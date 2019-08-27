package com.example.dickysuryo.moviecatalogue.Fragment;


import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.dickysuryo.moviecatalogue.Adapter.ListAdapter_Movie;
import com.example.dickysuryo.moviecatalogue.DetailActivity;
import com.example.dickysuryo.moviecatalogue.MainActivity;
import com.example.dickysuryo.moviecatalogue.Model.DetailPopular_Model;
import com.example.dickysuryo.moviecatalogue.Model.MovieNewest_Model;
import com.example.dickysuryo.moviecatalogue.Model.MoviePopular_Model;
import com.example.dickysuryo.moviecatalogue.Network.RetrofitClientInstance;
import com.example.dickysuryo.moviecatalogue.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class MoviePopularFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recycle_view;
    RetrofitClientInstance retrofitClientInstance;

    public MoviePopularFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
                // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie_popular, container, false);
        Call<MoviePopular_Model> call;

        recycle_view = (RecyclerView) rootView.findViewById(R.id.recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycle_view.setLayoutManager(linearLayoutManager);

        call = retrofitClientInstance.getInstance().getApiInterface().getAllPopularMovie("");
        call.enqueue(new Callback<MoviePopular_Model>() {
            @Override
            public void onResponse(Call<MoviePopular_Model> call, Response<MoviePopular_Model> response) {
                final List<DetailPopular_Model> items = response.body().getResults();
                ListAdapter_Movie customAdapter = new ListAdapter_Movie(getContext(),items);
                recycle_view.setAdapter(customAdapter);
            }

            @Override
            public void onFailure(Call<MoviePopular_Model> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        return rootView;
    }
}
