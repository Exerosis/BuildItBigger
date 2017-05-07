package me.exerosis.builditbigger.implementation.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.builditbigger.R;
import me.exerosis.builditbigger.implementation.controller.JokeList;

public class JokeListView implements JokeList {
    private final View view;
    private final RecyclerView jokesList;

    public JokeListView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.joke_list_view, container, false);
        jokesList = (RecyclerView) view.findViewById(R.id.joke_list);

        jokesList.setLayoutManager(new LinearLayoutManager(view.getContext()));
    }

    @Override
    public RecyclerView.Adapter getAdapter() {
        return jokesList.getAdapter();
    }

    @Override
    public void setAdapter(@NonNull RecyclerView.Adapter adapter) {
        jokesList.setAdapter(adapter);
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