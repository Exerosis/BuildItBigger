package me.exerosis.builditbigger.implementation.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderListener;
import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderView;
import me.exerosis.builditbigger.implementation.model.Joke;
import me.exerosis.builditbigger.mvc.Listenable;

public class JokeListAdapter extends RecyclerView.Adapter<JokeHolderView> implements Listenable<JokeHolderListener> {
    private static JokeListAdapter instance = new JokeListAdapter();
    private final List<Joke> jokes = new ArrayList<>();
    private JokeHolderListener listener;

    public static JokeListAdapter getInstance() {
        return instance;
    }

    public static JokeListAdapter getInstance(JokeHolderListener listener) {
        instance.setListener(listener);
        return instance;
    }

    public void append(Collection<Joke> jokes) {
        this.jokes.addAll(jokes);
        notifyItemRangeInserted(this.jokes.size() - (jokes.size() + 1), jokes.size());
    }

    @Override
    public JokeHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        JokeHolderView holder = new JokeHolderView(LayoutInflater.from(parent.getContext()), parent);
        holder.setListener(new JokeHolderListener() {
            @Override
            public void onClick(Joke joke) {
                if (listener != null)
                    listener.onClick(joke);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(JokeHolderView holder, int position) {
        holder.setJoke(jokes.get(position));
    }

    @Override
    public int getItemCount() {
        return jokes.size();
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
