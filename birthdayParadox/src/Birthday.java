import java.util.HashSet;
import java.util.Random;

/**
 * Created by Andreas on 2014-11-21.
 */
public class Birthday {

    public static void main(String[] args) {
        int N = 10000;
//        double c = 1.72;
//        double d = 0.1;
        double c = 1.17;
        double d = 0.1;
//        double target = 0.77;
        double target = 0.50;
        double delta = 0.001;
        int numOfExperiments = 100000;


        Bday2(N, c, d, target, delta, numOfExperiments);
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

    private static void Bday2(int n, double c, double d, double target, double delta, int numOfExperiments) {
        double averageNumberOfHits = 0;
        Random random = new Random();
        while (true) {
            System.out.println("C: " + c + " Avg: " + averageNumberOfHits);
            averageNumberOfHits = foo(n, c, numOfExperiments, averageNumberOfHits, random);

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

    private static double foo(int n, double c, int numOfExperiments, double averageNumberOfHits, Random random) {
        int T = (int) (c*Math.sqrt((double) n));

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
