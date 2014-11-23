import java.math.BigInteger;
import java.util.HashSet;
import java.util.LinkedList;

/**
 * Created by Andreas on 2014-11-22.
 */
public class CarmichaelGenerator {

    static int[] primes = { 2,   3,   5,   7,  11,  13,  17,  19,  23,  29,  31,  37,  41,
            43,  47,  53,  59,  61,  67,  71,  73,  79,  83,  89,  97, 101,

            103, 107, 109, 113, 127, 131, 137, 139, 149, 151, 157, 163, 167,
            173, 179, 181, 191, 193, 197, 199, 211, 223, 227, 229, 233, 239,

            241, 251, 257, 263, 269, 271, 277, 281, 283, 293, 307, 311, 313,
            317, 331, 337, 347, 349, 353, 359, 367, 373, 379, 383, 389, 397,

            401, 409, 419, 421, 431, 433, 439, 443, 449, 457, 461, 463, 467,
            479, 487, 491, 499, 503, 509, 521, 523, 541, 547, 557, 563, 569,

            571, 577, 587, 593, 599, 601, 607, 613, 617, 619, 631, 641, 643,
            647, 653, 659, 661, 673, 677, 683, 691, 701, 709, 719, 727, 733,

            739, 743, 751, 757, 761, 769, 773, 787, 797, 809, 811, 821, 823,
            827, 829, 839, 853, 857, 859, 863, 877, 881, 883, 887, 907, 911,

            919, 929, 937, 941, 947, 953, 967, 971, 977, 983, 991, 997};

    public static void main(String[] args) {

        int limit = 1000000;

        LinkedList<Integer> carmichaelNumbers = new LinkedList<Integer>();
//        for (int i = 561; i < limit; i+= 2) {
        int n = 0;
        for (int i = 561; i < limit; i+= 2) {

            if (isCarmichael(i)) {
                n++;
                carmichaelNumbers.add(i);
                System.out.print(i + ", ");
                if (n > 5) {
                    System.out.print("\n");
                    n = 0;
                }
            }

        }

//        for (Integer i : carmichaelNumbers)
//            System.out.print(" " + i);
    }

    private static boolean isCarmichael(int number) {

//        int sqrt = (int) Math.sqrt((double) number);
        int i = 0;

        if (isPrime(number))
            return false;

        LinkedList<BigInteger> f = PollardRho.factor(BigInteger.valueOf(number));
        HashSet<BigInteger> factors = new HashSet<BigInteger>(f);
        if (f.size() != factors.size())
            return false;
//        Systemout.println("Found factors");
        for (BigInteger bi : factors) {
            int prime = bi.intValue();
            int foo = (number-1) / (prime-1);
            if (foo*(prime-1) != number-1) {
                return false;
            }
//            if (gcd(prime, number) != 1) {
//
//            }
        }

//        while (i < primes.length && primes[i] < number) {
//            int prime = primes[i];
//
//
//
//            if (gcd(prime, number) != 1) {
//
//                int foo = (number-1) / (prime-1);
//                if (foo*(prime-1) != number-1) {
//                    return false;
//                }
//            }
//
//            i++;
//
//        }

        return true;
    }

    private static int gcd(int a, int b) {
        int r = BigInteger.valueOf(a).gcd(BigInteger.valueOf(b)).intValue();
        return r;
    }

    private static boolean isPrime(int n) {
        return BigInteger.valueOf(n).isProbablePrime(100);
    }
}
