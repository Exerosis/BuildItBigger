package me.exerosis.builditbigger.implementation.controller.container;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import me.exerosis.builditbigger.implementation.controller.JokeListFragment;

public class JokeListContainerActivity extends AppCompatActivity implements JokeListContainerController {
    private JokeListContainerView view;

    @Override
    protected void onCreate(Bundle in) {
        view = new JokeListContainerView(getLayoutInflater());
        setContentView(view.getRoot());
        super.onCreate(in);

        getSupportFragmentManager().beginTransaction().replace(view.getContainerID(), new JokeListFragment()).commit();
    }
}