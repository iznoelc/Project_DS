import java.awt.*;

public class BlumBlumShub {
    //range made by p and q
    int range;
    //starting number, must not be divisible by p or q
    int seed;
    //where are we right now in the process?
    int current_num;

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

    public void setUp(int p, int q, int seed){
        //if p and q are prime, seed is valid, then set values
        if(isPrime(p) == true && isPrime(q) == true && seed%p != 0 && seed%q != 0){
            this.range = p*q;
            this.seed = seed;
            this.current_num = seed;
        } else {
            System.out.println("ERROR: Invalid Input");
        }
    }

    public int getBit(){
        //square current number and update within set range
        current_num = (current_num * current_num) % range;
        //return whether the random number is even or odd, this will be called recursively later
        return current_num % 2;
    }

    public int getRandomNumber(int max){
        int random_num = 0;
        for(int bit = 0; bit < max; bit++){
            random_num += getBit();
        }
        return random_num;
    }

}
