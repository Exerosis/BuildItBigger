package me.exerosis.builditbigger.implementation.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;

import java.util.ArrayList;
import java.util.List;

import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderView;
import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeStore;
import me.exerosis.builditbigger.mvc.Listenable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.functions.Action1;

public class JokeListAdapter extends RecyclerView.Adapter<JokeHolderView> implements EndlessRecyclerView.Pager, Listenable<JokeListAdapterListener> {
    public static final int INITIAL_COUNT = 5;
    private static JokeListAdapter instance = new JokeListAdapter();
    private final List<Joke> jokes = new ArrayList<>();
    private JokeListAdapterListener listener;
    private final JokeStore jokeStore;

    public static JokeListAdapter getInstance() {
        return instance;
    }

    private JokeListAdapter() {
        jokeStore = new Retrofit.Builder().
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl("http://192.168.1.4:8080/_ah/api/").build().create(JokeStore.class);

        for (int i = 0; i < INITIAL_COUNT; i++)
            loadNextPage();
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
        jokeStore.getJoke().subscribe(new Action1<Joke>() {
            @Override
            public void call(Joke joke) {
                jokes.add(joke);
                if (listener != null)
                    listener.onLoaded();
            }
        });
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