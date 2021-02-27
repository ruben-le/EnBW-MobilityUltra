package prototype.locations;

import java.util.Arrays;

public class Location {

    private final int[] coordinates = new int[2];
    private final String locationType;

    public Location(int x, int y, String locationType) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.locationType = locationType;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    @Override
    public String toString() {
        return "Location{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", locationType='" + locationType + '\'' +
                '}';
    }
}
