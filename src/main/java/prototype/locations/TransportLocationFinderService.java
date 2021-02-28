package prototype.locations;

import prototype.ExecutionState;
import prototype.Plane;

import java.util.ArrayList;
import java.util.List;

public class TransportLocationFinderService {

    private final Plane plane;
    private final CityDataService cityDataService;
    private final int xPos;
    private final int yPos;

    public TransportLocationFinderService(Plane plane, ExecutionState executionState) {
        this.plane = plane;
        this.cityDataService = executionState.getCityDataService();
        this.xPos = executionState.getxPos();
        this.yPos = executionState.getyPos();
    }

    public TransportLocation getBestTransportLocation() {
        BusStop nearestBusStop = getNearestBusStop();
        CarParking nearestCarParking = getNearestCarParking();
        RentableBike nearestRentableBike = getNearestRentableBike();
        SubwayStation nearestSubwayStation = getNearestSubwayStation();
        TrainStation nearestTrainStation = getNearestTrainStation();

        double distanceToCarParking = getDistance(nearestCarParking.getxPos(), nearestCarParking.getyPos());
        double distanceToBusStop = getDistance(nearestBusStop.getxPos(), nearestBusStop.getyPos());
        double distanceToRentableBike = getDistance(nearestRentableBike.getxPos(), nearestRentableBike.getyPos());
        double distanceToSubwayStation = getDistance(nearestSubwayStation.getxPos(), nearestSubwayStation.getyPos());
        double distanceToTrainStation = getDistance(nearestTrainStation.getxPos(), nearestTrainStation.getyPos());

        // Rentable bikes are preferred if the air condition is high
        if(cityDataService.getAirCondition() > 0.8) {
            distanceToRentableBike = distanceToRentableBike / 2;
        } else if (cityDataService.getAirCondition() > 0.9){
            distanceToRentableBike = distanceToRentableBike / 3;
        }

        // Public transport is preferred if the Load is low
        if(cityDataService.getPublicTransportLoad() < 0.5) {
            distanceToSubwayStation = distanceToSubwayStation / 2;
            distanceToTrainStation = distanceToTrainStation / 2;
            distanceToBusStop = distanceToBusStop / 2;
        } else if (cityDataService.getPublicTransportLoad() < 0.35) {
            distanceToSubwayStation = distanceToSubwayStation / 3;
            distanceToTrainStation = distanceToTrainStation / 3;
            distanceToBusStop= distanceToBusStop / 3;
        }

        if(distanceToBusStop < distanceToCarParking
                && distanceToBusStop < distanceToRentableBike
                && distanceToBusStop < distanceToSubwayStation
                && distanceToBusStop < distanceToTrainStation
                && cityDataService.getPublicTransportLoad() < 0.9
                && cityDataService.getTrafficLoad() < 0.8)
        {
            return nearestCarParking;
        }

        if(distanceToRentableBike < distanceToBusStop
                && distanceToRentableBike < distanceToSubwayStation
                && distanceToRentableBike < distanceToCarParking
                && distanceToRentableBike < distanceToTrainStation
                && cityDataService.getAirCondition() > 0.5)
        {
            return nearestRentableBike;
        }

        if(distanceToSubwayStation < distanceToBusStop
                && distanceToSubwayStation < distanceToCarParking
                && distanceToSubwayStation < distanceToRentableBike
                && distanceToSubwayStation < distanceToTrainStation
                && cityDataService.getPublicTransportLoad() < 0.85)
        {
            return nearestSubwayStation;
        }

        if(distanceToTrainStation < distanceToBusStop
                && distanceToTrainStation < distanceToCarParking
                && distanceToTrainStation < distanceToSubwayStation
                && distanceToTrainStation < distanceToRentableBike &&
                cityDataService.getPublicTransportLoad() < 0.85)
        {
            return nearestTrainStation;
        }

        return nearestCarParking;
    }

    public List<TransportLocation> getBestTransportLocations() {
        List<TransportLocation> otherLocations = new ArrayList<>();

        otherLocations.add(getNearestBusStop());
        otherLocations.add(getNearestRentableBike());
        otherLocations.add(getNearestCarParking());
        otherLocations.add(getNearestSubwayStation());
        otherLocations.add(getNearestTrainStation());

        return otherLocations;
    }

    /**
     * Calculates the distance between the user and a given point
     * @param xTwo x coordinate of the point
     * @param yTwo y coordinate of the point
     * @return distance between the user and the point
     */
    public double getDistance(int xTwo, int yTwo) {
        return Math.sqrt((xPos - xTwo) * (xPos - xTwo) + (yPos - yTwo) * (yPos - yTwo));
    }

    private BusStop getNearestBusStop() {
        double nearestDistance = 2000;
        BusStop nearestBusStop = plane.getBusStops().get(0);

        for (BusStop busStop : plane.getBusStops()) {
            double distance = getDistance(busStop.getxPos(), busStop.getyPos());
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestBusStop = busStop;
            }
        }

        return nearestBusStop;
    }

    private CarParking getNearestCarParking() {

        double nearestDistance = 2000;
        CarParking nearestCarParking = plane.getCarParking().get(0);

        for (CarParking carParking : plane.getCarParking()) {
            double distance = getDistance(carParking.getxPos(), carParking.getyPos());
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestCarParking = carParking;
            }
        }

        return nearestCarParking;
    }

    private SubwayStation getNearestSubwayStation() {

        double nearestDistance = 2000;
        SubwayStation nearestSubwayStation = plane.getSubwayStations().get(0);

        for (SubwayStation subwayStation : plane.getSubwayStations()) {
            double distance = getDistance(subwayStation.getxPos(), subwayStation.getyPos());
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestSubwayStation = subwayStation;
            }
        }

        return nearestSubwayStation;
    }

    private RentableBike getNearestRentableBike() {
        double nearestDistance = 2000;
        RentableBike nearestRentableBike = plane.getRentableBikes().get(0);

        for (RentableBike rentableBike : plane.getRentableBikes()) {
            double distance = getDistance(rentableBike.getxPos(), rentableBike.getyPos());
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestRentableBike = rentableBike;
            }
        }

        return nearestRentableBike;
    }

    private TrainStation getNearestTrainStation() {
        double nearestDistance = 2000;
        TrainStation nearestTrainStation = plane.getTrainStations().get(0);

        for (TrainStation trainStation : plane.getTrainStations()) {
            double distance = getDistance(trainStation.getxPos(), trainStation.getyPos());
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestTrainStation = trainStation;
            }
        }

        return nearestTrainStation;
    }
}
