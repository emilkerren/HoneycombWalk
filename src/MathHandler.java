import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emil on 2017-02-27.
 */
public class MathHandler {

    private static int alternativeWalks = 0;

    /*
    * initiates the calculations with one of the hexes that is neighbor with origo hex
    */
    public static int calculateNumberOfSteps(int steps, HexagonalPoint startingHex, List<HexagonalPoint> grid) {

        int result = findNumberOfWalks(steps, startingHex, grid);
        alternativeWalks = 0;
        return result;
    }

    public static int findNumberOfWalks(int stepsLeft, HexagonalPoint startingHex, List<HexagonalPoint> grid) {
        List<HexagonalPoint> neighbors = retrireveSuroundingPointHexes(startingHex, grid);
        //we only need to calculate one of the origo neighbor and multiply by 6
        HexagonalPoint neighbor = neighbors.get(0);
        calculateNumberOfAvailbleOptionsFromNeigbors(stepsLeft - 1, grid, neighbor);
        return alternativeWalks * 6;
    }

    private static void calculateNumberOfAvailbleOptionsFromNeigbors(int stepsLeft, List<HexagonalPoint> grid, HexagonalPoint startHex) {
        if (null != startHex && ((stepsLeft) >= startHex.getDistanceToOrigo())) {
            //keep walking back and forth
            if (null == startHex && (stepsLeft % 2 != 0)) {
                alternativeWalks++;
            }
            if ((stepsLeft) == startHex.getDistanceToOrigo()) {
                alternativeWalks++;
            } else {
                List<HexagonalPoint> neighbors = retrireveSuroundingPointHexes(startHex, grid);
                for (HexagonalPoint neighborOfStartHex : neighbors) {
                    int neighborStep = stepsLeft - 1;
                    //no alternative, go home
                    if (null != neighborOfStartHex && (neighborStep == 1 && neighborOfStartHex.getDistanceToOrigo() == 1)) {
                        alternativeWalks++;
                    }
                    //start looking for ways home
                    else if (null != neighborOfStartHex && (neighborOfStartHex.getDistanceToOrigo() <= neighborStep)) {
                        List<HexagonalPoint> neighborsThatWillTakeMeHome = retrireveSuroundingPointHexes(neighborOfStartHex, grid);
                        for (HexagonalPoint point : neighborsThatWillTakeMeHome) {
                            if (null != point && (point.getDistanceToOrigo() == (neighborStep - 1))) {
                                alternativeWalks++;
                            }
                        }
                    } else if (null != neighborOfStartHex && (neighborOfStartHex.getDistanceToOrigo() < (stepsLeft - 1))) {
                        calculateNumberOfAvailbleOptionsFromNeigbors(neighborStep, grid, neighborOfStartHex);
                    }
                    //origo is a neighbor and we have steps to spam back and forth and land on origo again
                    else if (null == neighborOfStartHex && ((stepsLeft - 1) % 2 == 0)) {
                        alternativeWalks += 6;
                    }
                }
            }
        }
    }


    public static List<HexagonalPoint> retrireveSuroundingPointHexes(HexagonalPoint startingHex, List<HexagonalPoint> hexGrid) {
        List<HexagonalPoint> allWithinRadius = new ArrayList<>();

        //westNeighbor
        allWithinRadius.add(0, hexGrid.stream()
                .filter(hex -> hex.x == startingHex.x - 1 && hex.y == startingHex.y + 1 && hex.z == startingHex.z)
                .findFirst().orElseGet(null));

        //southwestNeighbor
        allWithinRadius.add(1, hexGrid.stream()
                .filter(hex -> hex.x == startingHex.x - 1 && hex.y == startingHex.y && hex.z == startingHex.z + 1).findFirst().orElseGet(null));

        //northwestNeighbor
        allWithinRadius.add(2, hexGrid.stream()
                .filter(hex -> hex.x == startingHex.x && hex.y == startingHex.y + 1 && hex.z == startingHex.z - 1).findFirst().orElseGet(null));

        //eastNeighbor
        allWithinRadius.add(3, hexGrid.stream()
                .filter(hex -> hex.x == startingHex.x + 1 && hex.y == startingHex.y - 1 && hex.z == startingHex.z).findFirst().orElseGet(null));

        //southeastNeighbor
        allWithinRadius.add(4, hexGrid.stream()
                .filter(hex -> hex.x == startingHex.x && hex.y == startingHex.y - 1 && hex.z == startingHex.z + 1).findFirst().orElseGet(null));

        //northeastNeighbor
        allWithinRadius.add(5, hexGrid.stream()
                .filter(hex -> hex.x == startingHex.x + 1 && hex.y == startingHex.y && hex.z == startingHex.z - 1).findFirst().orElseGet(null));


        checkIfOrigo(allWithinRadius);

        return allWithinRadius;
    }

    private static void checkIfOrigo(List<HexagonalPoint> allWithinRadius) {
        for (int i = 0; i < allWithinRadius.size(); i++) {
            HexagonalPoint withinRadius = allWithinRadius.get(i);
            if (withinRadius.x == 0 && withinRadius.y == 0 && withinRadius.z == 0) {
                allWithinRadius.set(i, null);
            }
        }
    }
}
