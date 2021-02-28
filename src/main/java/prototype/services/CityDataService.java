package prototype.services;

import java.util.Random;

public class CityDataService {

    private final double airCondition; // Air Condition index in the city
    private final double trafficLoad; // Traffic Load index in the city
    private final double publicTransportLoad; // Public Transport Load in the city

    public CityDataService() {
        Random r = new Random();
        this.airCondition = 0.3 + (1 - 0.3) * r.nextDouble();
        this.trafficLoad = 0.3 + (1 - 0.3) * r.nextDouble();
        this.publicTransportLoad = 0.3 + (1 - 0.3) * r.nextDouble();
    }

    public double getAirCondition() {
        return airCondition;
    }

    public double getTrafficLoad() {
        return trafficLoad;
    }

    public double getPublicTransportLoad() {
        return publicTransportLoad;
    }
}
