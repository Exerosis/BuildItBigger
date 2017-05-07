package me.exerosis.builditbigger.implementation.model;

import java.util.Collection;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface JokeStore {
    @GET("getJokes")
    Observable<Collection<Joke>> getJokes(@Query("count") int count);
}