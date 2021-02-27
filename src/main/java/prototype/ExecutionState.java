package prototype;

import prototype.locations.*;

import java.util.*;

public class ExecutionState {

    private boolean finished;
    private int xPos;
    private int yPos;
    private final Plane plane;
    private final Scanner scanner = new Scanner(System.in);
    private TransportLocationFinderService transportLocationFinderService;
    public final SampleLocationDataGeneratorService sampleLocationDataGeneratorService;
    private final CityDataService cityDataService;

    public ExecutionState(boolean finished) {
        this.sampleLocationDataGeneratorService = new SampleLocationDataGeneratorService();
        this.finished = finished;
        this.cityDataService = new CityDataService();

        this.plane = new Plane(
                1000,
                1000,
                sampleLocationDataGeneratorService.generateSampleBusStops(),
                sampleLocationDataGeneratorService.generateSampleCarParking(),
                sampleLocationDataGeneratorService.generateSampleRentableBikes(),
                sampleLocationDataGeneratorService.generateSampleSubwayStations(),
                sampleLocationDataGeneratorService.generateSampleSTrainStations()
        );
        this.transportLocationFinderService = new TransportLocationFinderService(plane, this);
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished() {
        this.finished = true;
    }

    /**
     * Gets the position data of the user
     */
    public void getPositionData() {
        System.out.println("Input your x position (Max " + plane.getXMax() + "):");
        xPos = scanner.nextInt();
        System.out.println("Input your y position (Max " + plane.getYMax() + "):");
        yPos = scanner.nextInt();
        System.out.println("Your position is: " + xPos + "/" + yPos + " (x/y), press enter to continue.");
        this.transportLocationFinderService = new TransportLocationFinderService(this.plane, this);
    }

    /**
     * Prints the Users Location to console
     */
    public void outputPos() {
        System.out.println("Your Position: " + xPos + "/" + yPos);
    }

    /**
     * Prints available transport locations to console
     */
    public void printLocations() {
        plane.getBusStops().forEach(System.out::println);
        plane.getBusStops().forEach(stop -> System.out.println("Distance: " + transportLocationFinderService.getDistance(stop.getxPos(), stop.getyPos())));
        System.out.println();
        plane.getCarParking().forEach(System.out::println);
        plane.getCarParking().forEach(parking -> System.out.println("Distance: " + transportLocationFinderService.getDistance(parking.getxPos(), parking.getyPos())));
        System.out.println();
        plane.getRentableBikes().forEach(System.out::println);
        plane.getRentableBikes().forEach(bike -> System.out.println("Distance: " + transportLocationFinderService.getDistance(bike.getxPos(), bike.getyPos())));
        System.out.println();
        plane.getSubwayStations().forEach(System.out::println);
        plane.getSubwayStations().forEach(subwayStation -> System.out.println("Distance: " + transportLocationFinderService.getDistance(subwayStation.getxPos(), subwayStation.getyPos())));
        System.out.println();
        plane.getTrainStations().forEach(System.out::println);
        plane.getTrainStations().forEach(trainStation -> System.out.println("Distance: " + transportLocationFinderService.getDistance(trainStation.getxPos(), trainStation.getyPos())));
        System.out.println();
    }

    public void printCityData() {
        System.out.println("Current Air Condition Index: " + cityDataService.getAirCondition());
        System.out.println("Current Public Transport Load: " + cityDataService.getPublicTransportLoad());
        System.out.println("Current Traffic Load: " + cityDataService.getTrafficLoad());
    }

    /**
     * Prints the nearest Transport location to console
     */
    public void printNearestLocation() {
        TransportLocation nearestLocation = transportLocationFinderService.getNearestLocation();
        double distance = transportLocationFinderService.getDistance(nearestLocation.getxPos(), nearestLocation.getyPos());
        System.out.println("Nearest Transportation Point at: " + Arrays.toString(nearestLocation.getCoordinates()) + ", " + distance + " away from you.");
        System.out.println("Type: " + nearestLocation.getType());
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }

    public CityDataService getCityDataService() {
        return cityDataService;
    }
}
