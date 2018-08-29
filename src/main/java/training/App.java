package training;

public class App {

    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        
        HashCracker crack = new HashCracker("titkos");
        String pass = crack.crack();
        long finishTime = System.currentTimeMillis();
        
        System.out.println("The password is "+pass);
        System.out.println("Time: "+ (finishTime-startTime));
    }
}
