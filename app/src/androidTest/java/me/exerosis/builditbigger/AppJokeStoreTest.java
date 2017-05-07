package me.exerosis.builditbigger;

import android.support.test.runner.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import okhttp3.OkHttpClient;
import okhttp3.Request;

import static junit.framework.Assert.assertFalse;

@RunWith(AndroidJUnit4.class)
public class AppJokeStoreTest {
    @Test
    public void retrofitReturnsJoke() throws Exception {
//        JokeStore jokeStore = new Retrofit.Builder().
//                addConverterFactory(GsonConverterFactory.create()).
//                addCallAdapterFactory(RxJavaCallAdapterFactory.createAsync()).
//                baseUrl("http://192.168.1.4:8080/").build().create(JokeStore.class);
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url("http://192.168.1.4:8080/getJokes?count=3")
                .build();
        String string = client.newCall(request).execute().body().string();
        assertFalse(string.isEmpty());
//        Collection<Joke> jokes = jokeStore.getJokes(3).toBlocking().first();
//        assertNotNull(jokes);
//        assertTrue(jokes.size() == 3);
//        for (Joke joke : jokes) {
//            assertNotNull(joke.getSetup());
//            assertNotNull(joke.getPunchline());
//            assertFalse(joke.getSetup().isEmpty());
//            assertFalse(joke.getPunchline().isEmpty());
//        }
    }
}