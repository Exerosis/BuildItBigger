package me.exerosis.builditbigger;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeStore;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class JokeStoreTest {
    @Test
    public void useAppContext() throws Exception {
        Context context = InstrumentationRegistry.getTargetContext();
        assertEquals("me.exerosis.builditbigger", context.getPackageName());

        JokeStore jokeStore = new Retrofit.Builder().
                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
                addConverterFactory(GsonConverterFactory.create()).
                baseUrl("http://192.168.1.4:8080/_ah/api/").build().create(JokeStore.class);

        Joke joke = jokeStore.getJoke().toBlocking().first();
        assertNotNull(joke);
        assertNotNull(joke.getSetup());
        assertNotNull(joke.getPunchline());
        assertFalse(joke.getSetup().isEmpty());
        assertFalse(joke.getPunchline().isEmpty());
    }
}