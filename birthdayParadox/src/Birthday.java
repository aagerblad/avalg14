import java.util.HashSet;
import java.util.Random;

/**
 * Created by Andreas on 2014-11-21.
 */
public class Birthday {

    public static void main(String[] args) {
//        int N = 10000;
        int N = 1000;
//        double c = 1.72;
//        double d = 0.1;
//        double c = 1.9;
        double c = 2.2;
        double a = 0.66;
        double d = 0.1;
//        double target = 0.77;
        double target = 0.50;
        double delta = 0.001;
        int numOfExperiments = 100000;

        System.out.println("C: 1.3 a1: 0.7037744140625  a2: 0.7351098632812498  a2-a1: 0.03133544921874987\n" +
                "C: 1.4000000000000001 a1: 0.6935937499999999  a2: 0.7178125000000001  a2-a1: 0.024218750000000178\n" +
                "C: 1.5000000000000002 a1: 0.683486328125  a2: 0.7037499999999999  a2-a1: 0.02026367187499989\n" +
                "C: 1.6000000000000003 a1: 0.6730859375  a2: 0.6878320312499998  a2-a1: 0.0147460937499998\n" +
                "C: 1.7000000000000004 a1: 0.6649560546875  a2: 0.67875  a2-a1: 0.0137939453125\n" +
                "C: 1.8000000000000005 a1: 0.6566796875  a2: 0.662276611328125  a2-a1: 0.005596923828124933\n" +
                "C: 1.9000000000000006 a1: 0.6488518959283829  a2: 0.65375  a2-a1: 0.004898104071617171\n" +
                "C: 2.0000000000000004 a1: 0.6416406250000002  a2: 0.6394921875000001  a2-a1: -0.0021484375000000444\n" +
                "C: 2.1000000000000005 a1: 0.634365234375  a2: 0.6289453125000002  a2-a1: -0.005419921874999845\n" +
                "C: 2.2000000000000006 a1: 0.6275781250000001  a2: 0.6225000000000002  a2-a1: -0.005078124999999933");
        for (int i = 0; i < 10; i++) {
            c += 0.1;
            double a1 = Bday2(N, c, a, d, target, delta, numOfExperiments);
            double a2 = Bday2(N/10, c, a, d, target, delta, numOfExperiments);

            System.out.println("C: " + c + " a1: " + a1 + "  a2: " + a2 + "  a2-a1: " + (a2-a1));

        }

    }

    private static void Bday(int n, double c, double d, double target, double delta, int numOfExperiments) {
        double averageNumberOfHits = 0;
        Random random = new Random();
        while (true) {
            System.out.println("C: " + c + " Avg: " + averageNumberOfHits);
            int T = (int) (c*Math.sqrt((double) n));

            for (int i = 0; i < numOfExperiments; i++) {
                HashSet<Integer> set = new HashSet<Integer>();
                int value;
                double numOfValues = 0;
                boolean containsDouble = false;

                while (numOfValues < T) {
                    value = random.nextInt(n);
                    if (set.contains(value)) {
                        containsDouble = true;
                        break;
                    }
                    set.add(value);
                    numOfValues++;
                }

//            averageNumberOfHits += numOfValues;
                averageNumberOfHits += (containsDouble ? 1 : 0);
            }

            averageNumberOfHits /= numOfExperiments;

            if (averageNumberOfHits < target - delta) {
                c += d;
                d /= 2;
            } else if (averageNumberOfHits > target + delta) {
                c -= d;
                d /= 2;
            } else {
                break;
            }

        }

        System.out.println("Average number of hits: " + averageNumberOfHits);
        System.out.println("C= " + c);
    }

    private static double Bday2(int n, double c, double a,  double d, double target, double delta, int numOfExperiments) {
        double averageNumberOfHits = 0;
        Random random = new Random();
        while (true) {
//            System.out.println("C: " + c + "A: " + a + " Avg: " + averageNumberOfHits);
            averageNumberOfHits = foo(n, c, a, numOfExperiments, averageNumberOfHits, random);

            averageNumberOfHits /= numOfExperiments;

            if (averageNumberOfHits < target - delta) {
                a += d;
                d /= 2;
            } else if (averageNumberOfHits > target + delta) {
                a -= d;
                d /= 2;
            } else {
                break;
            }

        }

//        System.out.println("\nAverage number of hits: " + averageNumberOfHits);
//        System.out.println("C= " + c);
//        System.out.println("A= " + a);
        return a;
    }

    private static double foo(int n, double c, double a, int numOfExperiments, double averageNumberOfHits, Random random) {
//        int T = (int) (c*Math.sqrt((double) n));
        int T = (int) (c*Math.pow(n, a));

        for (int i = 0; i < numOfExperiments; i++) {
            HashSet<Integer> set1 = new HashSet<Integer>();
            HashSet<Integer> set2 = new HashSet<Integer>();
            int value;
            double numOfValues = 0;
            boolean containsTriple = false;

            while (numOfValues < T) {
                value = random.nextInt(n);
                if (set1.contains(value)) {

                    if (set2.contains(value)){
                        containsTriple = true;
                        break;
                    }

                    set2.add(value);

                }
                set1.add(value);
                numOfValues++;
            }

//            averageNumberOfHits += numOfValues;
            averageNumberOfHits += (containsTriple ? 1 : 0);
        }
        return averageNumberOfHits;
    }
}
