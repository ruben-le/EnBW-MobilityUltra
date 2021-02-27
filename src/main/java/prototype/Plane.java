package prototype;

import prototype.locations.CarParking;
import prototype.locations.BusStop;
import prototype.locations.RentableBike;
import prototype.locations.SubwayStation;

import java.util.List;

public class Plane {

    private final int xMax;
    private final int yMax;
    private final List<BusStop> busStops;
    private final List<CarParking> carParking;
    private final List<RentableBike> rentableBikes;
    private final List<SubwayStation> subwayStations;

    public Plane(int xMax,
                 int yMax,
                 List<BusStop> busStops,
                 List<CarParking> carParking,
                 List<RentableBike> rentableBikes,
                 List<SubwayStation> subwayStations
    ) {
        this.xMax = xMax;
        this.yMax = yMax;
        this.busStops = busStops;
        this.carParking = carParking;
        this.rentableBikes = rentableBikes;
        this.subwayStations = subwayStations;
    }

    public int getXMax() {
        return xMax;
    }

    public int getYMax() {
        return yMax;
    }

    public List<BusStop> getBusStops() {
        return busStops;
    }

    public List<CarParking> getCarParking() {
        return carParking;
    }

    public List<RentableBike> getRentableBikes() {
        return rentableBikes;
    }

    public List<SubwayStation> getSubwayStations() {
        return subwayStations;
    }
}
