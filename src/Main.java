import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static int alternativeWalks = 0;
    private static List<Integer> stepsCalculated = new ArrayList<>();
    private static final Honeycomb honeycomb = new Honeycomb();
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {

            int s = scanner.nextInt(); //s = 2
            if(stepsCalculated.contains(s)) {
                continue;
            }
            if (s < 1 || s > 14) {
                break;
            }
            honeycomb.init(s);
            stepsCalculated.add(s);
        }
    }
}
