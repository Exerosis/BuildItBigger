package me.exerosis.builditbigger.implementation.controller;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import me.exerosis.builditbigger.R;
import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapter;
import me.exerosis.builditbigger.implementation.controller.adapters.JokeListAdapterListener;
import me.exerosis.builditbigger.implementation.controller.container.PunchlineContainerActivity;
import me.exerosis.builditbigger.implementation.view.JokeListView;
import me.exerosis.builditbigger.implementation.model.Joke;

import static me.exerosis.builditbigger.implementation.controller.PunchlineFragment.ARGS_PUNCHLINE;

public class JokeListFragment extends Fragment implements JokeListController, JokeListAdapterListener {
    private JokeListView view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = new JokeListView(inflater, container);
        JokeListAdapter adapter = JokeListAdapter.getInstance();
        adapter.setListener(this);
        view.setAdapter(adapter);
        return view.getRoot();
    }

    @Override
    public void onLoaded() {
        view.setRefreshing(false);
    }

    @Override
    public void onError() {
        Toast.makeText(getContext(), R.string.error_loading_joke, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(Joke joke) {
        getContext().startActivity(new Intent(getContext(), PunchlineContainerActivity.class).
                putExtra(ARGS_PUNCHLINE, joke.getPunchline()));
    }
}