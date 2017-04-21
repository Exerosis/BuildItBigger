package me.exerosis.builditbigger.implementation.controller.holder;

import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.mvc.ViewBase;

public interface JokeHolder extends ViewBase {
    void setJoke(Joke joke);
}
