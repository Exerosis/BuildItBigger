package me.exerosis.builditbigger.implementation.view.container;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import me.exerosis.builditbigger.punchline.R;


public class PunchlineContainerView implements PunchlineContainer {
    private final View view;

    public PunchlineContainerView(LayoutInflater inflater) {
        view = inflater.inflate(R.layout.punchline_container_view, null, false);
    }

    @Override
    public int getContainerID() {
        return R.id.punchline_container;
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