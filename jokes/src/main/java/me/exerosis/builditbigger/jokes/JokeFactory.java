package me.exerosis.builditbigger.jokes;

import rx.Observable;

public final class JokeFactory implements JokeStore {
    private static int number;
    private static JokeFactory instance = new JokeFactory();

    public static JokeFactory getInstance() {
        return instance;
    }

    private JokeFactory() {

    }

    @Override
    public Observable<Joke> getJoke() {
        return Observable.just(new Joke("Setup" + ++number, "Punchline" + number));
    }
}
