package me.exerosis.builditbigger.implementation.controller.adapters;

import me.exerosis.builditbigger.implementation.controller.holder.JokeHolderListener;

public interface JokeListAdapterListener extends JokeHolderListener {
    void onLoaded();

    void onError();
}
