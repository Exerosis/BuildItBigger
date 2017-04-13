package me.exerosis.builditbigger.implementation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.builditbigger.punchline.R;

public class PunchlineView implements Punchline {
    private final View view;

    public PunchlineView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.punchline_view, container, false);
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