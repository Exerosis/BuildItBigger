package me.exerosis.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Collection;

import me.exerosis.builditbigger.implementation.model.Joke;
import me.exerosis.builditbigger.implementation.model.JokeStore;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static junit.framework.Assert.assertFalse;
import static junit.framework.Assert.assertNotNull;
import static junit.framework.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class AppJokeStoreTest {
    @Test
    public void retrofitReturnsJoke() throws Exception {
        JokeStore jokeStore = new Retrofit.Builder().
                addConverterFactory(GsonConverterFactory.create()).
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                baseUrl("http://192.168.1.4:8080/").build().create(JokeStore.class);

        Collection<Joke> jokes = jokeStore.getJokes(3).toBlocking().first();
        assertNotNull(jokes);
        assertTrue(jokes.size() == 3);
        for (Joke joke : jokes) {
            assertNotNull(joke.getSetup());
            assertNotNull(joke.getPunchline());
            assertFalse(joke.getSetup().isEmpty());
            assertFalse(joke.getPunchline().isEmpty());
        }
    }
}