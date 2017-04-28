package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapter;
import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapterListener;
import me.exerosis.builditbigger.implementation.view.JokeListView;

public class JokeListFragment extends Fragment implements JokeListController, JokeListAdapterListener {
    private JokeListView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new JokeListView(inflater, container);
        JokeListAdapter adapter = JokeListAdapter.getInstance();
        adapter.setListener(this);
        view.setAdapter(adapter);
        return view.getRoot();
    }

    @Override
    public void onLoaded() {
        view.setRefreshing(false);
    }
}