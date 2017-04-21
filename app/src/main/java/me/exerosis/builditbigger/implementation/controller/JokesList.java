package me.exerosis.builditbigger.implementation.controller;

import com.google.android.gms.ads.AdRequest;

import me.exerosis.builditbigger.implementation.controller.adapters.JokesListAdapter;
import me.exerosis.builditbigger.mvc.Adaptable;
import me.exerosis.builditbigger.mvc.ViewBase;

public interface JokesList extends ViewBase, Adaptable<JokesListAdapter> {
    void loadAd(AdRequest request);

    void setRefreshing(boolean refreshing);

    boolean isRefreshing();
}