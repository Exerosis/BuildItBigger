package me.exerosis.builditbigger.implementation.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

import retrofit2.http.Query;
import rx.Observable;

public final class JokeFactory implements JokeStore {
    private static final List<Joke> JOKES = new ArrayList<>();
    private static final List<Joke> SHOWN = new ArrayList<>();
    private static final Random RANDOM = new Random();
    private static final JokeFactory instance = new JokeFactory();

    static {
        JOKES.add(new Joke("Why did the Hedgehog cross the road?", "To see his Flat Mate."));
        JOKES.add(new Joke("What do you call a Fish without an eye?", "A 'Fsh'!"));
        JOKES.add(new Joke("Why does it take 1 million sperm to fertilize one egg?", "They won't stop to ask directions."));
        JOKES.add(new Joke("What's the difference between Big Foot and an intelligent man?", "Big Foot's been spotted several times."));
        JOKES.add(new Joke("What do you call a handcuffed man?", "Trustworthy."));
        JOKES.add(new Joke("What did the fish say when he hit a concrete wall?", "'Dam'."));
        JOKES.add(new Joke("Why is divorce so expensive?", "Because it's worth it."));
        JOKES.add(new Joke("What is black, white and red all over?", "A skunk with nappy rash."));
        JOKES.add(new Joke("Why did the man jump out of the window?", "He wanted to catch a butterfly."));
        JOKES.add(new Joke("Why did the monkey put a piece of steak on his head?", "He thought he was a griller."));
        JOKES.add(new Joke("What do you call a deer with no eyes?", "No eye deer."));
        JOKES.add(new Joke("What do you call a deer with no eyes and no legs?", "Still no eye deer."));
        JOKES.add(new Joke("Why was six afraid of seven?", "Because seven eight nine."));
        JOKES.add(new Joke("Why did the dinosaur walk across the road?", "Because chickens were not invented yet."));
        JOKES.add(new Joke("What has four wheels and flies?", "A rubbish truck."));
        JOKES.add(new Joke("What's brown and sticky?", "A stick."));
        JOKES.add(new Joke("When were vowels invented?", "When u and i were born."));
        JOKES.add(new Joke("What's orange and sounds like a parrot?", "A carrot."));
        JOKES.add(new Joke("What do you call a sheep with no head and legs?", "A fuzz ball."));
        JOKES.add(new Joke("What do you call a fairy that hasn't bathed in a year?", "Stinkerbell."));
        JOKES.add(new Joke("When is it a good time to eat a window?", "When it's jammed."));
        JOKES.add(new Joke("Whats the difference between a tractor and a giraffe?", "ne has hydraulics the other has highbollocks."));
        JOKES.add(new Joke("What kind of band doesn't play music?", "A highbred."));
        JOKES.add(new Joke("If one is single and two is a couple and three is a crowd, what is four and five?", "9 (5+4)"));
        JOKES.add(new Joke("What do you get when you cross a parrot and a lion?", "I don't know, but when it talks you'd better listen."));
        JOKES.add(new Joke("Why do giraffes have long necks?", "Because they have smelly feet."));
        JOKES.add(new Joke("Why did the orange use suntan lotion?", "He started to peel."));
        JOKES.add(new Joke("Where does an elephant go when he wants to lie down?", "Anywhere he pleases."));
        JOKES.add(new Joke("What did the big chimney say to the little chimney?", "'You are too young to smoke.'"));
        JOKES.add(new Joke("What did the fish say to the seashores?", "'Can I ride on you seashorse?'"));
        JOKES.add(new Joke("Why did Cinderella get kicked off the baseball team?", "Because she ran away from the ball."));
        JOKES.add(new Joke("A man went to play golf for the day. He took his golf clubs and two pairs of pants. What were the extra pants for?", "In case he got a hole-in-one."));
        JOKES.add(new Joke("Why didn't the skeleton cross the road?", "Because it had no guts."));
        JOKES.add(new Joke("I have 3 heads, 5 legs, 7 arms and 444 fingers. What am I?", "A liar."));
        JOKES.add(new Joke("What do you get when you cross an elephant with a kangaroo?", "Holes all over Australia."));
        JOKES.add(new Joke("What goes ha, ha plonk?", "Someone laughing their head off."));
        JOKES.add(new Joke("What do you get when you cross a duck with cheese?", "Cheese and quackers."));
        JOKES.add(new Joke("What monster sits on the end of your finger?", "The bogie man."));
        JOKES.add(new Joke("What did the computer screen say to the keyboard after it went for a ride?", "That was a harddrive."));
        JOKES.add(new Joke("What is a pirate's favourite letter?", "R(Arrrrrggggh)"));
        JOKES.add(new Joke("Why did the cow jump over the moon?", "Because the farmer had cold hands."));
        JOKES.add(new Joke("How do you make a tissue dance?", "Put a little boogie in it."));
        JOKES.add(new Joke("How do you get a man to stop biting his nails?", "Make him wear shoes."));
        JOKES.add(new Joke("Why do chicken coops have two doors?", "Because if it had four doors it's be a chicken sedan."));
        JOKES.add(new Joke("What is the difference between a tick and a lawyer?", "A tick falls off you when you die."));
        JOKES.add(new Joke("What's the definition of mixed emotions?", "When you see your mother-in-law backing off a cliff in your new car."));
        JOKES.add(new Joke("What do call a lawyer with an IQ of 50?", "Your Honor."));
        JOKES.add(new Joke("How do you know if a restaurant has a clown as a chef?", "When the food tastes funny"));
    }

    public static JokeFactory getInstance() {
        return instance;
    }

    private JokeFactory() {

    }

    @Override
    public Observable<Collection<Joke>> getJokes(@Query("count") int count) {
        Collection<Joke> jokes = new ArrayList<>(count);
        for (int i = 0; i < count; i++) {
            if (SHOWN.size() >= JOKES.size())
                SHOWN.clear();
            Joke joke = null;
            while (joke == null || SHOWN.contains(joke))
                joke = JOKES.get(RANDOM.nextInt(JOKES.size()));
            SHOWN.add(joke);
            jokes.add(joke);
        }
        return Observable.just(jokes);
    }
}