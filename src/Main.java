
import java.util.Scanner;

public class Main {
    private static final Honeycomb honeycomb = new Honeycomb();
    private static int numberOfTestCases = 0;
    public static void main(String[] args) {
        int writtenNumbers = 0;
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {
            writtenNumbers ++;
            int s = scanner.nextInt(); //s = 2
            if(numberOfTestCases == 0) {
                numberOfTestCases = s;
                continue;
            }
            if(numberOfTestCases > 0 ) {
                if (s < 1 || s > 14) {
                    break;
                }
                System.out.println("result: "+honeycomb.init(s));
                numberOfTestCases--;
            }
        }
    }
}
