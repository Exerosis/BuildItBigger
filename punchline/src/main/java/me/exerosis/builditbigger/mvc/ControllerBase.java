package me.exerosis.builditbigger.mvc;

public interface ControllerBase <T extends ViewBase> {
    T getRootView();
}
