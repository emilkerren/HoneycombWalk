
import java.math.BigInteger;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {
    private static int numberOfTestCases = 0;
    private static int result = 0;
    private static int[] numbersHolder = new int[14];
    static Map<Integer, BigInteger> memo = new TreeMap<Integer, BigInteger>();


    static {
        for (int i = 1; i < 15; i++) {
            numbersHolder[i-1] = init(i);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            int s = scanner.nextInt();
            if (numberOfTestCases == 0) {
                numberOfTestCases = s;
                continue;
            }
            if (numberOfTestCases > 0) {
                if (s < 1 || s > 14) {
                    continue;
                }
                System.out.println(numbersHolder[s-1]);
                numberOfTestCases--;
            }
        }
    }

    private static int init(int s) {
        result = 0;
        result = calc(0, 0, s);
        return result;
    }

    private static int calc(int x, int y, int steps) {
        if ((steps == 0)) {
            if ((y == 0)) {
                if (x == 0) {
                    return 1;
                } else {
                    return 0;
                }
            } else {
                return 0;
            }
        }

        return ((calc(x - 1, (y - 1), (steps - 1))
                + calc((x), (y - 1), (steps - 1))
                + calc((x + 1), (y), (steps - 1))
                + calc((x + 1), (y + 1), (steps - 1))
                + calc((x), (y + 1), (steps - 1))
                + calc((x - 1), (y), (steps - 1))));

    }

}

