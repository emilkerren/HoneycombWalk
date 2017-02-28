import java.util.ArrayList;
import java.util.List;

/**
 * Created by Emil on 2017-02-27.
 */
public class Honeycomb {

    private final List<HexagonalPoint> hexGrid = new ArrayList<>();

    public Honeycomb() {
        hexagonalGrid();
    }

    public void init(int s) {
        List<HexagonalPoint> origoNeighborHexes = new ArrayList<>();
        HexagonalPoint origo = new HexagonalPoint(0, 0, 0);
        int result = 0;
        result += MathHandler.calculateNumberOfSteps(s, origo, hexGrid);

        System.out.println(result);
    }

    /*
     * Creates the grid with x, y, z, and distance to origo hex values
     */
    private final void hexagonalGrid() {
        final int size = 13;
        final int half = size / 2;

        for (int row = 0; row < size; row++) {
            int cols = (size - java.lang.Math.abs(row - half));

            for (int col = 0; col < cols; col++) {
                int xLbl = row < half ? col - row : col - half;
                int yLbl = row - half;
                int zLbl = (xLbl + yLbl) * -1;
                HexagonalPoint hex = new HexagonalPoint(xLbl, yLbl, zLbl);
                int higestValue = Math.max(Math.max(Math.abs(xLbl), Math.abs(yLbl)), Math.abs(zLbl));
                hex.setDistanceToOrigo(higestValue);
                hexGrid.add(hex);
            }
        }
    }
}
