package me.exerosis.builditbigger.mvc;

public interface Listenable<T> {
    T getListener();

    void setListener(T listener);
}
