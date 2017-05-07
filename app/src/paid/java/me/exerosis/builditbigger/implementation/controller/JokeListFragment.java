package me.exerosis.builditbigger.implementation.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter;

import java.util.Collection;

import me.exerosis.builditbigger.R;
import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapter;
import me.exerosis.builditbigger.implementation.controller.container.PunchlineContainerActivity;
import me.exerosis.builditbigger.implementation.model.Joke;
import me.exerosis.builditbigger.implementation.model.JokeStore;
import me.exerosis.builditbigger.implementation.view.JokeListView;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;

import static me.exerosis.builditbigger.implementation.controller.PunchlineFragment.ARGS_PUNCHLINE;

public class JokeListFragment extends Fragment implements JokeListController {
    private JokeListView view;
    private EndlessRecyclerViewAdapter adapter;
    private Observable<Collection<Joke>> jokesObservable;


    @Override
    public void onCreate(@Nullable Bundle in) {
        jokesObservable = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                baseUrl("http://192.168.1.4:8080/").build().create(JokeStore.class).
                getJokes(LOAD_QUANTITY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        adapter = new EndlessRecyclerViewAdapter(getContext(), JokeListAdapter.getInstance(this), new EndlessRecyclerViewAdapter.RequestToLoadMoreListener() {
            @Override
            public void onLoadMoreRequested() {
                jokesObservable.subscribe(new Action1<Collection<Joke>>() {
                    @Override
                    public void call(Collection<Joke> jokes) {
                        ((JokeListAdapter) adapter.getWrappedAdapter()).append(jokes);
                        adapter.onDataReady(true);
                    }
                }, new Action1<Throwable>() {
                    @Override
                    public void call(Throwable throwable) {
                        Toast.makeText(getContext(), R.string.error_loading_joke, Toast.LENGTH_SHORT).show();
                        adapter.onDataReady(false);
                    }
                });
            }
        });

        super.onCreate(in);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new JokeListView(inflater, container);
        view.setAdapter(adapter);
        return view.getRoot();
    }

    @Override
    public void onClick(Joke joke) {
        getContext().startActivity(new Intent(getContext(), PunchlineContainerActivity.class).
                putExtra(ARGS_PUNCHLINE, joke.getPunchline()));
    }
}