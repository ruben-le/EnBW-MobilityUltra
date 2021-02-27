package prototype.locations;

import java.util.Random;

public class CityDataService {

    private final double airCondition;
    private final double trafficIndex;
    private final double publicTransportLoad;

    public CityDataService() {
        Random r = new Random();
        this.airCondition = 0.3 + (1 - 0.3) * r.nextDouble();
        this.trafficIndex = 0.3 + (1 - 0.3) * r.nextDouble();
        this.publicTransportLoad = 0.3 + (1 - 0.3) * r.nextDouble();
    }

    public double getAirCondition() {
        return airCondition;
    }

    public double getTrafficLoad() {
        return trafficIndex;
    }

    public double getPublicTransportLoad() {
        return publicTransportLoad;
    }
}
