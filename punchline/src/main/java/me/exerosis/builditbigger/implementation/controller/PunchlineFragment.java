package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.builditbigger.implementation.view.PunchlineView;

public class PunchlineFragment extends Fragment implements PunchlineController {
    private PunchlineView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new PunchlineView(inflater, container);

        return view.getRoot();
    }
}