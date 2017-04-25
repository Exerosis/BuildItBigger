package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.builditbigger.implementation.view.PunchlineView;

public class PunchlineFragment extends Fragment implements PunchlineController {
    public static final String ARGS_PUNCHLINE = "PUNCHLINE";
    private PunchlineView view;

    public static PunchlineFragment newInstance(Bundle bundle) {
        PunchlineFragment fragment = new PunchlineFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new PunchlineView(inflater, container);
        view.setPunchline(getArguments().getString(ARGS_PUNCHLINE));
        return view.getRoot();
    }
}