package me.exerosis.builditbigger.implementation.controller.holder;

import me.exerosis.builditbigger.implementation.model.Joke;
import me.exerosis.builditbigger.mvc.Listenable;
import me.exerosis.builditbigger.mvc.ViewBase;

public interface JokeHolder extends ViewBase, Listenable<JokeHolderListener> {
    void setJoke(Joke joke);
}
