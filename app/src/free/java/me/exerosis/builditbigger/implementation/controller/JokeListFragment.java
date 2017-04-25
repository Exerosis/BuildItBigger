package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;

import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapter;
import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapterListener;
import me.exerosis.builditbigger.implementation.view.JokeListView;

public class JokeListFragment extends Fragment implements JokeListController, JokeListAdapterListener {
    private JokeListView view;
    public static final String JOKES_LIST_BANNER = "ca-app-pub-5347337988962999/6434510063";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new JokeListView(inflater, container);
        view.loadAd(new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build());
        view.setAdUnitId(JOKES_LIST_BANNER);
        JokeListAdapter adapter = JokeListAdapter.getInstance();
        view.setAdapter(adapter);
        return view.getRoot();
    }

    @Override
    public void onLoaded() {
        view.setRefreshing(false);
    }
}