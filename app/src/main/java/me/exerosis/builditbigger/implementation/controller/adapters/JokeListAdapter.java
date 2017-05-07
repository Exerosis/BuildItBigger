package me.exerosis.builditbigger.implementation.controller.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.github.yasevich.endlessrecyclerview.EndlessRecyclerView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderListener;
import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderView;
import me.exerosis.builditbigger.implementation.model.Joke;
import me.exerosis.builditbigger.implementation.model.JokeStore;
import me.exerosis.builditbigger.mvc.Listenable;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

public class JokeListAdapter extends RecyclerView.Adapter<JokeHolderView> implements EndlessRecyclerView.Pager, Listenable<JokeListAdapterListener>, JokeHolderListener {
    private static final int LOAD_QUANTITY = 20;
    private static JokeListAdapter instance = new JokeListAdapter();
    private final List<Joke> jokes = new ArrayList<>();
    private final Observable<Collection<Joke>> jokesObservable;
    private boolean loading = false;
    private JokeListAdapterListener listener;

    public static JokeListAdapter getInstance() {
        return instance;
    }

    private JokeListAdapter() {
        jokesObservable = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                baseUrl("http://192.168.1.4:8080/").build().create(JokeStore.class).
                getJokes(LOAD_QUANTITY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        loadNextPage();
    }

    @Override
    public JokeHolderView onCreateViewHolder(ViewGroup parent, int viewType) {
        JokeHolderView holder = new JokeHolderView(LayoutInflater.from(parent.getContext()), parent);
        holder.setListener(this);
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
    public boolean shouldLoad() {
        return !loading;
    }

    @Override
    public void loadNextPage() {
        loading = true;
        jokesObservable.subscribe(new Action1<Collection<Joke>>() {
            @Override
            public void call(Collection<Joke> newJokes) {
                jokes.addAll(newJokes);
                notifyItemRangeInserted(jokes.size() - (newJokes.size() + 1), newJokes.size());
                doneLoading();
            }
        }, new Action1<Throwable>() {
            @Override
            public void call(Throwable throwable) {
                if (listener != null)
                    listener.onError();
                doneLoading();
            }
        });
    }

    private void doneLoading() {
        if (listener != null)
            listener.onLoaded();
        loading = false;
    }

    @Override
    public JokeListAdapterListener getListener() {
        return listener;
    }

    @Override
    public void setListener(JokeListAdapterListener listener) {
        this.listener = listener;
    }

    @Override
    public void onClick(Joke joke) {
        if (listener != null)
            listener.onClick(joke);
    }
}