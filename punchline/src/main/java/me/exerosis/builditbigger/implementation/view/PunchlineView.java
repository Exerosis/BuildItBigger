package me.exerosis.builditbigger.implementation.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.exerosis.builditbigger.punchline.R;

public class PunchlineView implements Punchline {
    private final View view;
    private final TextView punchline;

    public PunchlineView(LayoutInflater inflater, ViewGroup container) {
        view = inflater.inflate(R.layout.punchline_view, container, false);
        punchline = (TextView) view.findViewById(R.id.punchline);
    }


    @Override
    public void setPunchline(String punchline) {
        this.punchline.setText(punchline);
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