package aoc6;

public class MapField {
    public int x;
    public int y;
    public boolean isObstacle;
    public boolean isOpen;

    public MapField(int x, int y, boolean obstacle) {
        this.x = x;
        this.y = y;
        this.isObstacle = obstacle;
    }
}
