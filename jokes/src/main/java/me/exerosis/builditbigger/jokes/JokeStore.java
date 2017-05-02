package me.exerosis.builditbigger.jokes;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface JokeStore {
    @GET("getJoke")
    Observable<Joke> getJoke();
}
