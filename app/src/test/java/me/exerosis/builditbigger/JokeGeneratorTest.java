package me.exerosis.builditbigger;

import org.junit.Test;

import me.exerosis.builditbigger.jokes.Joke;
import me.exerosis.builditbigger.jokes.JokeGenerator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

public class JokeGeneratorTest {
    @Test
    public void returnsJoke() throws Exception {
        Joke joke = JokeGenerator.generateJoke();
        assertNotNull(joke);
        assertNotNull(joke.getSetup());
        assertNotNull(joke.getPunchline());
        assertFalse(joke.getSetup().isEmpty());
        assertFalse(joke.getPunchline().isEmpty());
    }
}