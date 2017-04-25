package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;

import me.exerosis.builditbigger.implementation.controller.container.PunchlineContainerController;
import me.exerosis.builditbigger.implementation.view.container.PunchlineContainerView;
import me.exerosis.builditbigger.mvc.Container;

public class PunchlineContainerActivity extends AppCompatActivity implements PunchlineContainerController {
    public static final String ARGS_PUNCHLINE = "PUNCHLINE";
    private static final String TAG_PUNCHLINE_FRAGMENT = "PUNCHLINE_FRAGMENT";
    private static final String PUNCHLINE_INTERSTITIAL = "ca-app-pub-5347337988962999/7653891268";
    private InterstitialAd interstitialAd;
    private Container view;

    @Override
    protected void onCreate(Bundle in) {
        super.onCreate(in);
        view = new PunchlineContainerView(getLayoutInflater());
        setContentView(view.getRoot());

        interstitialAd = new InterstitialAd(this);
        interstitialAd.setAdUnitId(PUNCHLINE_INTERSTITIAL);
        interstitialAd.setAdListener(new AdListener() {
            @Override
            public void onAdLoaded() {
                interstitialAd.show();
            }

            @Override
            public void onAdFailedToLoad(int errorCode) {
                Toast.makeText(PunchlineContainerActivity.this, "Ad loading failed!", Toast.LENGTH_SHORT).show();
                onAdClosed();
            }

            @Override
            public void onAdClosed() {
                getSupportFragmentManager().beginTransaction().replace(view.getContainerID(), new PunchlineFragment(), TAG_PUNCHLINE_FRAGMENT).commit();
                loadAd();
            }
        });
        loadAd();
    }

    public void loadAd() {
        interstitialAd.loadAd(new AdRequest.Builder().setRequestAgent("android_studio:ad_template").build());
    }
}