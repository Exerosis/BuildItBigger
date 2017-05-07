package me.exerosis.builditbigger.implementation.model;

public class Joke {
    private final String setup;
    private final String punchline;

    public Joke(String setup, String punchline) {
        this.setup = setup;
        this.punchline = punchline;
    }

    public String getSetup() {
        return setup;
    }

    public String getPunchline() {
        return punchline;
    }
}