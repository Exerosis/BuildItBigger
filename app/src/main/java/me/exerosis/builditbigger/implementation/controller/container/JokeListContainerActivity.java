package me.exerosis.builditbigger.implementation.controller.container;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.util.AttributeSet;
import android.view.View;

import me.exerosis.builditbigger.implementation.controller.JokeListFragment;

public class JokeListContainerActivity extends AppCompatActivity implements JokeListContainerController {
    private JokeListContainerView view;

    @Override
    public View onCreateView(String name, Context context, AttributeSet attrs) {
        view = new JokeListContainerView(getLayoutInflater());
        getSupportFragmentManager().beginTransaction().replace(view.getContainerID(), new JokeListFragment()).commit();
        return view.getRoot();
    }
}