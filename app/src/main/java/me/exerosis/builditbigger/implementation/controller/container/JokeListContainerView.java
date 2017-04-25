package me.exerosis.builditbigger.implementation.controller.container;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import me.exerosis.builditbigger.R;

public class JokeListContainerView implements JokeListContainer {
    private final View view;

    public JokeListContainerView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.joke_list_container_view, null, false);
    }

    @Override
    public int getContainerID() {
        return R.id.joke_list_container;
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