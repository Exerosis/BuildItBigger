package me.exerosis.builditbigger.implementation.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.ads.doubleclick.PublisherAdRequest;
import com.google.android.gms.ads.doubleclick.PublisherInterstitialAd;
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

import static com.rockerhieu.rvadapter.endless.EndlessRecyclerViewAdapter.RequestToLoadMoreListener;
import static me.exerosis.builditbigger.implementation.controller.PunchlineFragment.ARGS_PUNCHLINE;

public class JokeListFragment extends Fragment implements JokeListController {
    public static final String APP_ID = "ca-app-pub-5347337988962999~4039446869";
    private static final String PUNCHLINE_INTERSTITIAL = "ca-app-pub-5347337988962999/7653891268";
    private JokeListView view;
    private PublisherInterstitialAd interstitialAd;
    private Joke joke;
    private PublisherAdRequest addRequest;
    private Observable<Collection<Joke>> jokesObservable;
    private EndlessRecyclerViewAdapter adapter;

    @Override
    public void onCreate(Bundle in) {
        MobileAds.initialize(getActivity().getApplicationContext(), APP_ID);

        addRequest = new PublisherAdRequest.Builder().setRequestAgent("android_studio:ad_template").build();

        jokesObservable = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                baseUrl("http://192.168.1.4:8080/").build().create(JokeStore.class).
                getJokes(LOAD_QUANTITY).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());

        adapter = new EndlessRecyclerViewAdapter(getContext(), JokeListAdapter.getInstance(this), new RequestToLoadMoreListener() {
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
        }, R.layout.joke_list_progress_view, true);

        interstitialAd = new PublisherInterstitialAd(getContext());
        interstitialAd.setAdUnitId(PUNCHLINE_INTERSTITIAL);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {

            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(getContext(), R.string.error_loading_ad, Toast.LENGTH_SHORT).show();
                startPunchlineActivity();
            }

            @Override
            public void onAdClosed() {
                startPunchlineActivity();
            }
        });
        interstitialAd.loadAd(addRequest);
        super.onCreate(in);
    }

    @Override
    public void onDestroy() {
        startPunchlineActivity();
        super.onDestroy();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new JokeListView(inflater, container);
        view.loadAd(addRequest);
        view.setAdapter(adapter);
        return view.getRoot();
    }

    @Override
    public void onClick(Joke joke) {
        this.joke = joke;

        if (interstitialAd.isLoaded())
            interstitialAd.show();
        else
            startPunchlineActivity();
    }

    private void startPunchlineActivity() {
        if (!interstitialAd.isLoading() && !interstitialAd.isLoaded())
            interstitialAd.loadAd(addRequest);
        if (this.joke != null && getContext() != null)
            getContext().startActivity(new Intent(getContext(), PunchlineContainerActivity.class).
                    putExtra(ARGS_PUNCHLINE, joke.getPunchline()));
        joke = null;
    }
}