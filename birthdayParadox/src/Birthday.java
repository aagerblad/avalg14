import java.util.HashSet;
import java.util.Random;

/**
 * Created by Andreas on 2014-11-21.
 */
public class Birthday {

    public static void main(String[] args) {
//        int N = 10000;
        int N = 10000;
        double c = 1.72; //For 0.77
//        double d = 0.1;
//        double c = 1.9;
//        double c = 1.94; //For 0.5, 3 collision
        double a = 0.66;
        double d = 0.1;
        double target = 0.77;
//        double target = 0.50;
        double delta = 0.001;
        int numOfExperiments = 100000;

        Bday(N, c, d, target, delta, numOfExperiments);

//        for (int i = 0; i < 10; i++) {
//            c += 0.005;
//            double a1 = Bday2(N, c, a, d, target, delta, numOfExperiments);
//            double a2 = Bday2(N/9, c, a, d, target, delta, numOfExperiments);
//
//            System.out.println("C: " + c + " a1: " + a1 + "  a2: " + a2 + "  a2-a1: " + (a2-a1));
//
//        }

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
