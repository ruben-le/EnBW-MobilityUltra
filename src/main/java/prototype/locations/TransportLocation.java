package prototype.locations;

import java.util.Arrays;

public class TransportLocation {

    private final int[] coordinates = new int[2];
    private final String locationType;

    public TransportLocation(int x, int y, String locationType) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
        this.locationType = locationType;
    }

    public int[] getCoordinates() {
        return coordinates;
    }

    public int getxPos() {
        return coordinates[0];
    }

    public int getyPos() {
        return coordinates[1];
    }

    public String getType() {
        return locationType;
    }

    @Override
    public String toString() {
        return "Location{" +
                "coordinates=" + Arrays.toString(coordinates) +
                ", locationType='" + locationType + '\'' +
                '}';
    }
}
