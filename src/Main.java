import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static List<HexagonalPoint> hexGrid = new ArrayList<>();
    private static int alternativeWalks = 0;
    private static List<Integer> stepsCalculated = new ArrayList<>();
    public static void main(String[] args) {

        hexagonalGrid();

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextInt()) {

            int s = scanner.nextInt(); //s = 2
            if(stepsCalculated.contains(s)) {
                continue;
            }
            if (s < 1 || s > 14) {
                break;
            }
            List<HexagonalPoint> origoNeighborHexes = new ArrayList<>();
            HexagonalPoint origo = new HexagonalPoint(0, 0, 0);
            origoNeighborHexes = getSuroundingPointHexes(origo);

            int result = 0;
            for (HexagonalPoint origoNeighbor : origoNeighborHexes) {
                result += calculateNumberOfSteps(origoNeighbor, s);
            }
            System.out.println(result);
            stepsCalculated.add(s);
        }
    }

    private static List<HexagonalPoint> getSuroundingPointHexes(HexagonalPoint startingHex) {
        int steps = 1;
        List<HexagonalPoint> allWithinRadius = new ArrayList<>();

        //westNeighbor
        allWithinRadius.add(0, hexGrid.stream()
                .filter(hex -> hex.x == startingHex.x - 1 && hex.y == startingHex.y + 1 && hex.z ==  startingHex.z).findFirst().orElseGet(null));
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

        return allWithinRadius;
    }

    /*
     * Creates the grid with x, y, z, and distance to origo hex values
     */
    private static final void hexagonalGrid() {
        final int size = 13;
        final int half = size / 2;

        for (int row = 0; row < size; row++) {
            int cols = (size - java.lang.Math.abs(row - half));

            for (int col = 0; col < cols; col++) {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int zLbl = (xLbl + yLbl) * -1;
                HexagonalPoint hex = new HexagonalPoint(xLbl, yLbl, zLbl);
//                int higestValue = Math.abs(xLbl) >= Math.abs(yLbl) ? Math.abs(xLbl) : Math.abs(yLbl);
                int higestValue = Math.max(Math.max(Math.abs(xLbl),Math.abs(yLbl)),Math.abs(zLbl));
                hex.setDistanceToOrigo(higestValue);
                hexGrid.add(hex);
            }
        }
    }

    /*
     * initiates the calculations with one of the hexes that is neighbor with origo hex
     */
    private static int calculateNumberOfSteps(HexagonalPoint startingHex, int steps) {
        int stepsLeft = steps - 1;//one step taken
        int result = findNumberOfWalks(stepsLeft, startingHex);
        alternativeWalks = 0;
        return result;
    }

    private static int findNumberOfWalks(int stepsLeft, HexagonalPoint array) {
        List<HexagonalPoint> neighbors = getSuroundingPointHexes(array);
        HexagonalPoint westNeighbor = neighbors.get(0);
        HexagonalPoint southwestNeighbor = neighbors.get(1);
        HexagonalPoint northwestNeighbor = neighbors.get(2);
        HexagonalPoint eastNeighbor = neighbors.get(3);
        HexagonalPoint southeastNeighbor = neighbors.get(4);
        HexagonalPoint northeastNeighbor = neighbors.get(5);

        if (null != westNeighbor && ((stepsLeft-1) >= westNeighbor.getDistanceToOrigo())) {
            boolean endOfLine = stepsLeft - 1 == westNeighbor.getDistanceToOrigo();
            if (endOfLine) {
                alternativeWalks++;
            } else {
                findNumberOfWalks(stepsLeft - 1, westNeighbor);
            }
        }
        if (null != southwestNeighbor && ((stepsLeft-1) >= southwestNeighbor.getDistanceToOrigo())) {
            boolean endOfLine = stepsLeft - 1 == southwestNeighbor.getDistanceToOrigo();
            if (endOfLine) {
                alternativeWalks++;
            } else {
                findNumberOfWalks(stepsLeft - 1, southwestNeighbor);
            }
        }
        if (null != northwestNeighbor && ((stepsLeft-1) >= northwestNeighbor.getDistanceToOrigo())) {
            boolean endOfLine = stepsLeft - 1 == northwestNeighbor.getDistanceToOrigo();
            if (endOfLine) {
                alternativeWalks++;
            } else {
                findNumberOfWalks(stepsLeft - 1, northwestNeighbor);
            }
        }
        if (null != eastNeighbor && ((stepsLeft-1) >= eastNeighbor.getDistanceToOrigo())) {
            boolean endOfLine = stepsLeft - 1 == eastNeighbor.getDistanceToOrigo();
            if (endOfLine) {
                alternativeWalks++;
            } else {
                findNumberOfWalks(stepsLeft - 1, eastNeighbor);
            }
        }
        if (null != southeastNeighbor && ((stepsLeft-1) >= southeastNeighbor.getDistanceToOrigo())) {
            boolean endOfLine = stepsLeft - 1 == southeastNeighbor.getDistanceToOrigo();
            if (endOfLine) {
                alternativeWalks++;
            } else {
                findNumberOfWalks(stepsLeft - 1, southeastNeighbor);
            }
        }
        if (null != northeastNeighbor && ((stepsLeft-1) >= northeastNeighbor.getDistanceToOrigo())) {
            boolean endOfLine = stepsLeft - 1 == northeastNeighbor.getDistanceToOrigo();
            if (endOfLine) {
                alternativeWalks++;
            } else {
                findNumberOfWalks(stepsLeft - 1, northeastNeighbor);
            }
        }
        return alternativeWalks;
    }

}
