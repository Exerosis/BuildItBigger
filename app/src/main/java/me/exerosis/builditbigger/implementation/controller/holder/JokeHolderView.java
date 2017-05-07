package me.exerosis.builditbigger.implementation.controller.holder;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.exerosis.builditbigger.R;
import me.exerosis.builditbigger.implementation.model.Joke;

public class JokeHolderView extends RecyclerView.ViewHolder implements JokeHolder {
    private final TextView punchline;
    private Joke joke;
    private JokeHolderListener listener;

    public JokeHolderView(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.joke_holder_view, container, false));
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (joke != null || listener != null)
                    listener.onClick(joke);
            }
        });
        punchline = (TextView) itemView.findViewById(R.id.joke_holder_punchline);
    }

    @Override
    public View getRoot() {
        return itemView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void setJoke(Joke joke) {
        this.joke = joke;
        punchline.setText(joke.getSetup());
    }

    @Override
    public JokeHolderListener getListener() {
        return listener;
    }

    @Override
    public void setListener(JokeHolderListener listener) {
        this.listener = listener;
    }
}