package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapterListener;
import me.exerosis.builditbigger.implementation.controller.adapters.JokesListAdapter;
import me.exerosis.builditbigger.implementation.view.JokesListView;

public class JokeListFragment extends Fragment implements JokeListController, JokeListAdapterListener {
    private JokesListView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new JokesListView(inflater, container);
        JokesListAdapter adapter = JokesListAdapter.getInstance();
        view.setAdapter(adapter);
        return view.getRoot();
    }

    @Override
    public void onLoaded() {
        view.setRefreshing(false);
    }
}