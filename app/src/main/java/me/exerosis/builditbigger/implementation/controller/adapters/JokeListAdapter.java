package me.exerosis.builditbigger.implementation.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderView;
import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeStore;
import me.exerosis.builditbigger.mvc.Listenable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class JokeListAdapter extends RecyclerView.Adapter<JokeHolderView> implements EndlessRecyclerView.Pager, Listenable<JokeListAdapterListener> {
    public static final int INITIAL_COUNT = 5;
    private static final int LOAD_QUANTITY = 5;
    private static JokeListAdapter instance = new JokeListAdapter();
    private final List<Joke> jokes = new ArrayList<>();
    private JokeListAdapterListener listener;
    private final JokeStore jokeStore;
    private boolean loading = false;

    public static JokeListAdapter getInstance() {
        return instance;
    }

    private JokeListAdapter() {
        jokeStore = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                baseUrl("http://192.168.1.4:8080/").build().create(JokeStore.class);
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
        return !loading;
    }

    @Override
    public void loadNextPage() {
        loading = true;
        jokeStore.getJokes(LOAD_QUANTITY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Action1<Collection<Joke>>() {
            @Override
            public void call(Collection<Joke> newJokes) {
                jokes.addAll(newJokes);
                notifyItemRangeInserted(jokes.size() - (newJokes.size() + 1), newJokes.size());
                if (listener != null)
                    listener.onLoaded();
                loading = false;
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