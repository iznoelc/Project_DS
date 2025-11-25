import java.awt.*;
import java.math.BigInteger;
import java.util.HashSet;
import java.util.Scanner;

public class BlumBlumShub {
    //range made by p and q
    int range;
    //starting number, must not be divisible by p or q
    int seed;
    //where are we right now in the process?
    int current_state;

    /**
     *
     * @param num
     * @return true or false related to prime
     */
    //helper methods for math stuff
    public boolean isPrime(int num){
        //if the number is 1 or negative it is not prime
        if(num <= 1){return false;}
        //if the number is 2 it is prime
        if(num == 2){return true;}
        //check every number up to the square of the num we are looking at
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

    /**
     *
     * @param a
     * @param b
     * @return true or false related to coprime
     */
    private boolean isCoprime(int a, int b) {
        //make big integer objects to be plugged in later
        BigInteger bigA = BigInteger.valueOf(a);
        BigInteger bigB = BigInteger.valueOf(b);
        //use built in gcd method, it checks if it's a coprime or not
        BigInteger gcd = bigA.gcd(bigB);
        //convert result to int
        int result = gcd.intValue();
        //check int to find result
        if(result == 1){
            return true;
        } else {
            return false;
        }
    }

    /**
     * set values, sudoConstructor for internal use
     */
    public void defaultSetup(){
        this.range = 499*547;
        this.seed = 12345;
    }

    /**
     * set values, sudoConstructor for user use
     */
    public void setUp(){
        //get ready for user input
        Scanner scanner = new Scanner(System.in);

        //get input
        System.out.println("What is your p? (must be prime and 3%4 congruent, big numbers work better like 499 and 547)");
        int p = scanner.nextInt();
        System.out.println("What is your q? (must be prime and 3%4 congruent, big numbers work better like 499 and 547)");
        int q = scanner.nextInt();

        //check validity
        System.out.println("Checking p and q validity...");
        if(isPrime(p) && isPrime(q) && p % 4 == 3 && q % 4 == 3){
            System.out.println("Variables p and q are valid! Setting values now in the system...\n\n");
            this.range = p*q;

            //if valid continue on to next user input
            System.out.println("What is your seed? (must be coprime of p and q, big numbers work better, like 12345)");
            int seed = scanner.nextInt();

            //check seed validity
            System.out.println("Checking seed validity...");
            if(isCoprime(seed,p) && isCoprime(seed,q)){
                System.out.println("Seed is valid! Setting seed value now...");
                this.seed = seed;
                this.current_state = seed;
                return;
            } else {
                System.out.println("ERROR: Invalid seed Input");
            }

        } else {
            System.out.println("ERROR: Invalid p or q Input");
        }
        //if invalid ask eitehr to set default or run recursively
        System.out.println("Uh oh seems there's been an issue. Would you like to:\n" +
                "1. use defaults \n" +
                "2. try again\n");
        int input = scanner.nextInt();
        switch (input){
            case 1:
                this.range = 499*547;
                this.seed = 12345;
                this.current_state = seed;
                break;
            case 2:
                this.setUp();
                break;
            default:
                System.out.println("I'm sorry I don't recognize that input. Please choose option 1 or 2.");
        }
    }

    /**
     * getter
     * @return current state
     */
    //deals with
    private int nextState() {
        //squares state, makes sure its in range, multiplies, and finally updates
        current_state = (int)((long)current_state * current_state % range); // avoid overflow
        return current_state;
    }

    /**
     * move onto teh next state
     * @return the next state which will be set as current state
     */
    //advances using nextbit and returns either 0 or 1
    public int nextBit() {
        return nextState() & 1;
    }

    /**
     *
     * @param bitNum
     * @return random number
     */
    //loops 8 times, either advances one or takes a random bit
    public int nextByte(int bitNum) {
        int b = 0;
        for (int i = 0; i < bitNum; i++) {
            b = (b << 1) | nextBit();
        }
        return b;
    }

    /**
     *
     * @param size
     * @param bitNum
     * @return hashset of random numbers
     */
    //generates arrays of bytes
    public HashSet<Integer> buffer(int size, int bitNum) {
        HashSet<Integer> buf = new HashSet<>();
        for (int i = 0; i < size; i++) {
            buf.add(nextByte(bitNum));
        }
        return buf;
    }
}
