package me.exerosis.builditbigger;

import org.junit.Test;

import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeFactory;
import me.exerosis.builditbigger.jokes.JokeStore;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JokeStoreTest {
    @Test
    public void factoryReturnsJoke() throws Exception {
        Joke joke = JokeFactory.getInstance().getJoke().toBlocking().first();
        assertNotNull(joke);
        assertNotNull(joke.getSetup());
        assertNotNull(joke.getPunchline());
        assertFalse(joke.getSetup().isEmpty());
        assertFalse(joke.getPunchline().isEmpty());
    }

    @Test
    public void retrofitReturnsJoke() throws Exception {
        JokeStore jokeStore = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                baseUrl("http://localhost:8080/").build().create(JokeStore.class);

        Joke joke = jokeStore.getJoke().toBlocking().first();
        assertNotNull(joke);
        assertNotNull(joke.getSetup());
        assertNotNull(joke.getPunchline());
        assertFalse(joke.getSetup().isEmpty());
        assertFalse(joke.getPunchline().isEmpty());
    }
}