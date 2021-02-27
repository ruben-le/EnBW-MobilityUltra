package prototype.locations;

import prototype.ExecutionState;
import prototype.Plane;

public class TransportLocationFinderService {

    private final Plane plane;
    private final int xPos;
    private final int yPos;

    public TransportLocationFinderService(Plane plane, ExecutionState executionState) {
        this.plane = plane;
        this.xPos = executionState.getxPos();
        this.yPos = executionState.getyPos();
    }

    public TransportLocation getNearestLocation() {
        BusStop nearestBusStop = getNearestBusStop();
        CarParking nearestCarParking = getNearestCarParking();
        RentableBike nearestRentableBike = getNearestRentableBike();
        SubwayStation nearestSubwayStation = getNearestSubwayStation();

        double distanceToCarParking = getDistance(nearestCarParking.getxPos(), nearestCarParking.getyPos());
        double distanceToStop = getDistance(nearestBusStop.getxPos(), nearestBusStop.getyPos());
        double distanceToRentableBike = getDistance(nearestRentableBike.getxPos(), nearestRentableBike.getyPos());
        double distanceToSubwayStation = getDistance(nearestSubwayStation.getxPos(), nearestSubwayStation.getyPos());

        if(distanceToCarParking < distanceToStop && distanceToCarParking < distanceToRentableBike && distanceToCarParking < distanceToSubwayStation) {
            return nearestCarParking;
        }

        if(distanceToRentableBike < distanceToStop && distanceToRentableBike < distanceToSubwayStation && distanceToRentableBike < distanceToCarParking) {
            return nearestRentableBike;
        }

        if(distanceToSubwayStation < distanceToStop && distanceToSubwayStation < distanceToCarParking && distanceToSubwayStation < distanceToRentableBike) {
            return nearestSubwayStation;
        }

        return nearestBusStop;
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

    public double getDistance(int xTwo, int yTwo) {
        return Math.sqrt((xPos - xTwo) * (xPos - xTwo) + (yPos - yTwo) * (yPos - yTwo));
    }
}
