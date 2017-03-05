
import java.util.Scanner;

public class Main {
    private static int numberOfTestCases = 0;
    private static int result = 0;

    public static void main(String[] args) {
        int writtenNumbers = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            writtenNumbers++;
            int s = scanner.nextInt(); //s = 2
            if (numberOfTestCases == 0) {
                numberOfTestCases = s;
                continue;
            }
            if (numberOfTestCases > 0) {
                if (s < 1 || s > 14) {
                    break;
                }
                System.out.println("result: " + init(s));
                numberOfTestCases--;
            }
        }
    }

    public static int init(int s) {
        result = 0;
        result = calc(0, 0, s);
        return result;
    }

    public static int calc(int x, int y, int steps) {
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

        int recursiveInt = (calc((x - 1), (y - 1), (steps - 1))
                + calc((x), (y - 1), (steps - 1))
                + calc((x + 1), (y), (steps - 1))
                + calc((x + 1), (y + 1), (steps - 1))
                + calc((x), (y + 1), (steps - 1))
                + calc((x - 1), (y), (steps - 1)));

        return recursiveInt;

    }

}

