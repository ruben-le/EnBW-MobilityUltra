package prototype.locations;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SampleLocationDataGeneratorService {

    private final Random r = new Random();

    public List<BusStop> generateSampleBusStops() {
        List<BusStop> busBusStops = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int x = r.nextInt(1000);
            int y = r.nextInt(1000);
            busBusStops.add(new BusStop(x, y));
        }

        return busBusStops;
    }

    public List<CarParking> generateSampleCarParking() {
        List<CarParking> carParking = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int x = r.nextInt(1000);
            int y = r.nextInt(1000);
            carParking.add(new CarParking(x, y));
        }

        return carParking;
    }

    public List<RentableBike> generateSampleRentableBikes() {
        List<RentableBike> rentableBikes = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int x = r.nextInt(1000);
            int y = r.nextInt(1000);
            rentableBikes.add(new RentableBike(x, y));
        }

        return rentableBikes;
    }

    public List<SubwayStation> generateSampleSubwayStations() {
        List<SubwayStation> subwayStations = new ArrayList<>();

        for (int i = 0; i < 10; i++) {
            int x = r.nextInt(1000);
            int y = r.nextInt(1000);
            subwayStations.add(new SubwayStation(x, y));
        }

        return subwayStations;
    }
}
