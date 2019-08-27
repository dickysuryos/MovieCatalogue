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
import com.example.dickysuryo.moviecatalogue.Adapter.ListAdapter_MovieNewest;
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

import static android.support.constraint.Constraints.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class MovieListFragment extends Fragment {
    private static final String TAG = MainActivity.class.getSimpleName();
    private RecyclerView recycle_view;
    RetrofitClientInstance retrofitClientInstance;

    public MovieListFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movie_list, container, false);
        Call<MovieNewest_Model> call;
        recycle_view = (RecyclerView) rootView.findViewById(R.id.recycle_view);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        recycle_view.setLayoutManager(linearLayoutManager);



        call = retrofitClientInstance.getInstance().getApiInterface().getAllNewestMovie("");
        call.enqueue(new Callback<MovieNewest_Model>() {
            @Override
            public void onResponse(Call<MovieNewest_Model> call, Response<MovieNewest_Model> response) {
                final List<MovieNewest_Model.Result> items = response.body().getResults();
                ListAdapter_MovieNewest customAdapter = new ListAdapter_MovieNewest(getContext(),items);
                recycle_view.setAdapter(customAdapter);

            }

            @Override
            public void onFailure(Call<MovieNewest_Model> call, Throwable t) {
                Log.e(TAG, t.toString());
            }
        });
        return rootView;
    }

}

