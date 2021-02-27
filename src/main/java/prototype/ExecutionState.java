package prototype;

import prototype.locations.*;

import java.util.*;

public class ExecutionState {

    private boolean finished;
    private int xPos;
    private int yPos;
    private final Plane plane;
    private final Scanner scanner = new Scanner(System.in);
    private final TransportLocationFinderService transportLocationFinderService;
    public final SampleLocationDataGeneratorService sampleLocationDataGeneratorService;

    public ExecutionState(boolean finished) {
        this.sampleLocationDataGeneratorService = new SampleLocationDataGeneratorService();
        this.finished = finished;
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
        System.out.println();
        plane.getCarParking().forEach(System.out::println);
        System.out.println();
        plane.getRentableBikes().forEach(System.out::println);
        System.out.println();
        plane.getSubwayStations().forEach(System.out::println);
        System.out.println();
        plane.getTrainStations().forEach(System.out::println);
        System.out.println();
    }

    /**
     * Prints the nearest Transport location to console
     */
    public void printNearestLocation() {
        TransportLocation nearestLocation = transportLocationFinderService.getNearestLocation();
        System.out.println("Nearest Transportation Point at: " + Arrays.toString(nearestLocation.getCoordinates()));
        System.out.println("Type: " + nearestLocation.getType());
    }

    public int getxPos() {
        return xPos;
    }

    public int getyPos() {
        return yPos;
    }
}
