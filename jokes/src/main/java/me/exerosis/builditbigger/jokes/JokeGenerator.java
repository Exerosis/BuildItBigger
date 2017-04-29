package me.exerosis.builditbigger.jokes;

public final class JokeGenerator {
    private static int number;

    private JokeGenerator() {

    }


    public static Joke generateJoke() {
        return new Joke("Setup" + ++number, "Punchline" + number);
    }

}
