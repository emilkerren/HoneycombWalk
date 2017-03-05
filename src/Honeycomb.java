import java.util.ArrayList;
import java.util.List;

public class Honeycomb {

    private int result = 0;

    public int init(int s) {
        result = 0;
        result = calc(0, 0, s);
        return result;
    }

    public int calc(int x, int y, int steps) {
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
