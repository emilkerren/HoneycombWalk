/**
 * Created by Emil on 2017-02-25.
 */
public class HexagonalPoint {
    public int x;
    public int y;
    public int z;
    private int distanceToOrigo;

    public HexagonalPoint(int x, int y, int z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public int getDistanceToOrigo() {
        return this.distanceToOrigo;
    }

    public void setDistanceToOrigo(int distanceToOrigo) {
        this.distanceToOrigo = distanceToOrigo;
    }
}
