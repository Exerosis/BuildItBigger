package me.exerosis.builditbigger.implementation.controller;

import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapter;
import me.exerosis.builditbigger.mvc.Adaptable;
import me.exerosis.builditbigger.mvc.ViewBase;

public interface JokeList extends ViewBase, Adaptable<JokeListAdapter> {
    void setRefreshing(boolean refreshing);

    boolean isRefreshing();
}