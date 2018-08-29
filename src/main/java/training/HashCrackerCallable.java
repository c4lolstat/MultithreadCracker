package training;

import java.util.concurrent.Callable;

public class HashCrackerCallable implements Callable<String> {

    private char minCharValue = 97;
    private char maxCharValue = 122;
    private int length = 0;
    private String hashCode = "";
    private Guess guess;

    public HashCrackerCallable(int length, String searchHashCode) {
        this.length = length;
        this.hashCode = searchHashCode;
        char[] initial = new char[length];

        for (int i = 0; i< length; i++){
            initial[i] = minCharValue;
        }
        this.guess = new Guess(initial);
    }

    @Override
    public String call() throws Exception {
        do {
           //System.out.println(guess.getGuess());
            if (guess.getGuess().equals(hashCode)){
                return guess.getGuess();
            }
            incrementGuess();
        } while(canIncrementGuess() && !HashCracker.done);
        return "";
    }

    private void incrementGuess()
    {
        boolean incremented = false;

        for(int x = (guess.getLength() - 1);x >= 0 && !incremented; x--)
        {
            if(guess.getChar(x) < this.maxCharValue)
            {
                guess.incrementCharAt(x);
                if(x < (guess.getLength()-1))
                {
                    guess.setCharAt(x + 1, this.minCharValue);
                }
                incremented = true;
            }
        }
    }

    boolean canIncrementGuess()
    {
        boolean canIncrement = false;

        for(int x=0; x < guess.getLength(); x++)
        {
            if(guess.getChar(x) < this.maxCharValue) canIncrement = true;
        }
        return canIncrement;
    }

}
