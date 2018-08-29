package training;

/**
 * Created by Farkas on 2018.08.07..
 */
public class Guess {

    private char[] guess;

    public Guess (char[] guess){
        this.guess = guess;
    }

    public String getGuess(){
        return new String(this.guess);
    }

    public int getLength (){
        return guess.length;
    }

    public char getChar(int i){
        return guess[i];
    }

    public void incrementCharAt(int i){
        guess[i]++;
    }

    public void setCharAt(int i, char value){
        guess[i] = value;
    }
}
