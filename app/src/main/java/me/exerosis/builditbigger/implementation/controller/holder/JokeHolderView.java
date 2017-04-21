package me.exerosis.builditbigger.implementation.controller.holder;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import me.exerosis.builditbigger.R;
import me.exerosis.builditbigger.implementation.controller.PunchlineContainerActivity;
import me.exerosis.builditbigger.jokes.Joke;

import static me.exerosis.builditbigger.implementation.controller.PunchlineContainerActivity.ARGS_PUNCHLINE;

public class JokeHolderView extends RecyclerView.ViewHolder implements JokeHolder {
    private Joke joke;

    public JokeHolderView(LayoutInflater inflater, ViewGroup container) {
        super(inflater.inflate(R.layout.joke_holder_view, container, false));

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (joke == null)
                    return;
                Context context = getRoot().getContext();
                context.startActivity(new Intent(context, PunchlineContainerActivity.class).
                        putExtra(ARGS_PUNCHLINE, joke.getPunchline()));
            }
        });
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
    }
}