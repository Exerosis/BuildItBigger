package me.exerosis.builditbigger.implementation.controller;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.builditbigger.implementation.controller.container.PunchlineContainerController;
import me.exerosis.builditbigger.implementation.view.container.PunchlineContainer;
import me.exerosis.builditbigger.implementation.view.container.PunchlineContainerView;
import me.exerosis.builditbigger.mvc.Container;

public class PunchlineContainerActivity extends AppCompatActivity implements PunchlineContainerController {
    private static final String TAG_PUNCHLINE_FRAGMENT = "PUNCHLINE_FRAGMENT";
    private PunchlineContainer view;

    @Override
    protected void onCreate(Bundle in) {
        super.onCreate(in);
        view = new PunchlineContainerView(getLayoutInflater());
        setContentView(view.getRoot());

        view.removeProgressBar();
        getSupportFragmentManager().beginTransaction().replace(view.getContainerID(),
                PunchlineFragment.newInstance(getIntent().getExtras()), TAG_PUNCHLINE_FRAGMENT).commit();
    }
}