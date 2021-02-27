package prototype;

import prototype.locations.Parking;
import prototype.locations.Stop;

import java.util.List;

public class Plane {

    private final int xMax;
    private final int yMax;
    private final List<Stop> stops;
    private final List<Parking> parking;

    public Plane(int xMax, int yMax, List<Stop> stops, List<Parking> parking) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.stops = stops;
        this.parking = parking;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public List<Stop> getStops() {
        return stops;
    }

    public List<Parking> getParking() {
        return parking;
    }
}
