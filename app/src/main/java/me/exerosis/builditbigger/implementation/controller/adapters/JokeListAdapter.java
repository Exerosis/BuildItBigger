package me.exerosis.builditbigger.implementation.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderView;
import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeGenerator;
import me.exerosis.builditbigger.mvc.Listenable;

public class JokeListAdapter extends RecyclerView.Adapter<JokeHolderView> implements EndlessRecyclerView.Pager, Listenable<JokeListAdapterListener> {
    private static JokeListAdapter instance = new JokeListAdapter();
    private final List<Joke> jokes = new ArrayList<>();
    private JokeListAdapterListener listener;

    public static JokeListAdapter getInstance() {
        return instance;
    }

    private JokeListAdapter() {
    }

    @Override
    public JokeHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        return new JokeHolderView(LayoutInflater.from(parent.getContext()), parent);
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
    public boolean shouldLoad() {
        //Check Online
        return true;
    }

    @Override
    public void loadNextPage() {
        jokes.add(JokeGenerator.generateJoke());
    }

    @Override
    public JokeListAdapterListener getListener() {
        return listener;
    }

    @Override
    public void setListener(JokeListAdapterListener listener) {
        this.listener = listener;
    }
}