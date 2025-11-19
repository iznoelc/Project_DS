import java.awt.*;
import java.math.BigInteger;

public class BlumBlumShub {
    //range made by p and q
    int range;
    //starting number, must not be divisible by p or q
    int seed;
    //where are we right now in the process?
    int current_state;



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



    public void setUp(int p, int q, int seed){
        //check p and q are prime and congruent to 3%4 for randomness
        if(isPrime(p) && isPrime(q) && p % 4 == 3 && q % 4 == 3){
            this.range = p*q;
            //check seed is coprime to q and p (not divisible by them)
            if(isCoprime(seed,p) && isCoprime(seed,q)){
                this.seed = seed;
                this.current_state = seed;
            } else {
                System.out.println("ERROR: Invalid seed Input");
            }

        } else {
            System.out.println("ERROR: Invalid p or q Input");
        }
    }

    //deals with
    private int nextState() {
        //squares state, makes sure its in range, multiplies, and finally updates
        current_state = (int)((long)current_state * current_state % range); // avoid overflow
        return current_state;
    }

    //advances using nextbit and returns either 0 or 1
    public int nextBit() {
        return nextState() & 1;
    }

    //loops 8 times, either advances one or takes a random bit
    public int nextByte(int bitNum) {
        int b = 0;
        for (int i = 0; i < bitNum; i++) {
            b = (b << 1) | nextBit();
        }
        return b;
    }

    //generates arrays of bytes
    public byte[] buffer(int size) {
        byte[] buf = new byte[size];
        for (int i = 0; i < size; i++) {
            buf[i] = (byte) nextByte(5);
        }
        return buf;
    }
}
