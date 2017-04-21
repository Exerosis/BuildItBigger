package me.exerosis.builditbigger.jokes;

public final class JokeGenerator {
    private JokeGenerator() {

    }


    public static Joke generateJoke() {
        return new Joke("Setup1", "Punchline1");
    }

}
