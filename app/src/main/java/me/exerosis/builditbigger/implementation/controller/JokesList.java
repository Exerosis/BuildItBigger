package me.exerosis.builditbigger.implementation.controller;

import me.exerosis.builditbigger.implementation.controller.adapters.JokesListAdapter;
import me.exerosis.builditbigger.mvc.Adaptable;
import me.exerosis.builditbigger.mvc.ViewBase;

public interface JokesList extends ViewBase, Adaptable<JokesListAdapter> {
    void setRefreshing(boolean refreshing);

    boolean isRefreshing();
}