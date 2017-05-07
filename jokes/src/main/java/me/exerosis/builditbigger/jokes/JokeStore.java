package me.exerosis.builditbigger.jokes;

import java.util.Collection;

import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryName;
import rx.Observable;

public interface JokeStore {
    @GET("getJokes")
    Observable<Collection<Joke>> getJokes(@Query("count") int count);
}
