package me.exerosis.builditbigger.implementation.view;

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
import me.exerosis.builditbigger.implementation.controller.JokeList;
import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapter;

public class JokeListView implements JokeList {
    private final View view;
    private final AdView bannerAd;
    private final EndlessRecyclerView jokesList;

    public JokeListView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.jokes_list_view, container, false);
        bannerAd = (AdView) view.findViewById(R.id.joke_list_banner_ad);
        jokesList = (EndlessRecyclerView) view.findViewById(R.id.joke_list);

        jokesList.setProgressView(new ProgressBar(view.getContext()));
    }

    public void loadAd(AdRequest request) {
        bannerAd.loadAd(request);
    }

    public void setAdUnitId(String id) {
        bannerAd.setAdUnitId(id);
    }

    @Override
    public JokeListAdapter getAdapter() {
        return (JokeListAdapter) jokesList.getAdapter();
    }

    @Override
    public void setAdapter(@NonNull JokeListAdapter adapter) {
        jokesList.setAdapter(adapter);
        jokesList.setPager(adapter);
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