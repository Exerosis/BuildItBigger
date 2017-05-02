package me.exerosis.builditbigger;

import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeStore;
import rx.Observable;

public class JokeTestStore implements JokeStore {
    @Override
    public Observable<Joke> getJoke() {
        return Observable.just(new Joke("TESt1", "test2"));
    }
}