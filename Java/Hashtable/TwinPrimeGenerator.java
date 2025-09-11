import java.util.NoSuchElementException;

/**
 * Generator for creating twin prime numbers
 * 
 * @author Rhett Edwards
 */
public class TwinPrimeGenerator {

    /* Instance Variables */
    

    /**
     * Default constructor
     */
    public TwinPrimeGenerator() {
        
    }

    /**
     * Generates new pair of twin primes
     */
    public int generateTwinPrimes(int min, int max) {

        // check if input is reasonable
        if (min <= 1 || max <= 1) {
            throw new IllegalArgumentException();
        }

        // initialize last found prime and a base table size
        int lastKnownPrime = 2;
        int resultingTableSize = 0;
        
        // iterate through given min and max values
        for (int i = min; i <= max; i++) {
            if (isPrime(i)) {
                // check if it is twin prime
                if ((i - 2) == lastKnownPrime) {
                    resultingTableSize = i;
                    break;
                }
                lastKnownPrime = i;
            }
        }

        // if no valid table size was found
        if (resultingTableSize == 0) {
            throw new NoSuchElementException();
        }

        return resultingTableSize;
    }

    private boolean isPrime(int num) {
        if (num <= 1) return false;
        for (int i = 2; i <= Math.sqrt(num); i++) {
            if (num % i == 0) return false;
        }
        return true;
    }

}
