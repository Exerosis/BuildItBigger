package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.MobileAds;

import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapter;
import me.exerosis.builditbigger.implementation.view.JokeListView;

public class JokeListFragment extends Fragment implements JokeListController {
    public static final String APP_ID = "ca-app-pub-5347337988962999~4039446869";
    private JokeListView view;

    @Override
    public void onCreate(Bundle in) {
        MobileAds.initialize(getActivity().getApplicationContext(), APP_ID);
        super.onCreate(in);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new JokeListView(inflater, container);
        view.loadAd(new AdRequest.Builder().setRequestAgent("android_studio:ad_template").
                addTestDevice("FE9CEF644E44E6BF4F25050E1FC879CA").build());
        JokeListAdapter adapter = JokeListAdapter.getInstance();
        adapter.setListener(this);
        view.setAdapter(adapter);
        return view.getRoot();
    }

    @Override
    public void onLoading() {
        view.setRefreshing(true);
    }

    @Override
    public void onLoaded() {
        view.setRefreshing(false);
    }
}