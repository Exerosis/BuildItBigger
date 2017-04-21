package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import me.exerosis.builditbigger.R;
import me.exerosis.builditbigger.implementation.controller.adapters.JokesListAdapter;

public class JokesListView implements JokesList {
    private final View view;
    private final AdView bannerAd;
    private final EndlessRecyclerView jokesList;

    public JokesListView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.jokes_list_view, container, false);
        bannerAd = (AdView) view.findViewById(R.id.jokes_list_banner_ad);
        jokesList = (EndlessRecyclerView) view.findViewById(R.id.jokes_list);

        jokesList.setProgressView(new ProgressBar(view.getContext()));
    }


    @Override
    public JokesListAdapter getAdapter() {
        return (JokesListAdapter) jokesList.getAdapter();
    }

    @Override
    public void setAdapter(@NonNull JokesListAdapter adapter) {
        jokesList.setAdapter(adapter);
        jokesList.setPager(adapter);
    }

    @Override
    public void loadAd(AdRequest request) {
        bannerAd.loadAd(request);
    }

    @Override
    public void setRefreshing(boolean refreshing) {
        jokesList.setRefreshing(refreshing);
    }

    @Override
    public boolean isRefreshing() {
        return jokesList.isRefreshing();
    }

    @Override
    public View getRoot() {
        return view;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }
}