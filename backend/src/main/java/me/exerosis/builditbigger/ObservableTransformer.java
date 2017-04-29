package me.exerosis.builditbigger;

import com.google.api.server.spi.config.Transformer;

import rx.Observable;

public class ObservableTransformer<T> implements Transformer<Observable, T> {
    @Override
    @SuppressWarnings("unchecked")
    public T transformTo(Observable observable) {
        return (T) observable.toBlocking().first();
    }

    @Override
    public Observable<T> transformFrom(T t) {
        return Observable.just(t);
    }
}
