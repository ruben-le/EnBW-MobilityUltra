package prototype.locations;

import prototype.ExecutionState;
import prototype.Plane;

public class NearestLocationFinder {

    private final Plane plane;
    private final ExecutionState executionState;

    public NearestLocationFinder(Plane plane, ExecutionState executionState) {
        this.plane = plane;
        this.executionState = executionState;
    }

    public Location getNearestLocation() {

        int xPos = executionState.getxPos();
        int yPos = executionState.getyPos();

        double nearestDistance = 2000;
        Stop nearestStop = plane.getStops().get(0);
        Parking nearestParking = plane.getParking().get(0);

        for (Stop stop : plane.getStops()) {
            double distance = getDistance(xPos, yPos, stop.getxPos(), stop.getyPos());
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestStop = stop;
            }
        }

        for (Parking parking : plane.getParking()) {
            double distance = getDistance(xPos, yPos, parking.getxPos(), parking.getyPos());
            if (distance <= nearestDistance) {
                nearestDistance = distance;
                nearestParking = parking;
            }
        }

        double distanceToParking = getDistance(xPos, yPos, nearestParking.getxPos(), nearestParking.getyPos());
        double distanceToStop = getDistance(xPos, yPos, nearestStop.getxPos(), nearestStop.getyPos());

        return distanceToParking < distanceToStop ? nearestParking : nearestStop;
    }

    public double getDistance(int xOne, int yOne, int xTwo, int yTwo) {
        return Math.sqrt((xOne - xTwo) * (xOne - xTwo) + (yOne - yTwo) * (yOne - yTwo));
    }
}
