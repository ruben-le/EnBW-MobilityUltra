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
    private final TransportEnvironmentService transportEnvironmentService;
    private final TransportSpeedService transportSpeedService;
    public final SampleLocationDataGeneratorService sampleLocationDataGeneratorService;
    private final CityDataService cityDataService;

    public ExecutionState(boolean finished) {
        this.sampleLocationDataGeneratorService = new SampleLocationDataGeneratorService();
        this.cityDataService = new CityDataService();
        this.transportEnvironmentService = new TransportEnvironmentService();
        this.transportSpeedService = new TransportSpeedService(cityDataService);
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
        System.out.println("Geben sie die x-koordinate Ihrer Position ein: (Max " + plane.getXMax() + "):");
        xPos = scanner.nextInt();
        System.out.println("Geben sie die y-koordinate Ihrer Position ein: (Max " + plane.getYMax() + "):");
        yPos = scanner.nextInt();
        outputPos();
        this.transportLocationFinderService = new TransportLocationFinderService(this.plane, this);
    }

    /**
     * Prints the Users Location to console
     */
    public void outputPos() {
        System.out.println("Deine Position: " + xPos + "/" + yPos);
    }

    /**
     * Prints available transport locations to console
     */
    public void printLocations() {
        plane.getBusStops().forEach(System.out::println);
        plane.getBusStops().forEach(stop -> System.out.println("Distanz: " + transportLocationFinderService.getDistance(stop.getxPos(), stop.getyPos())));
        System.out.println();
        plane.getCarParking().forEach(System.out::println);
        plane.getCarParking().forEach(parking -> System.out.println("Distanz: " + transportLocationFinderService.getDistance(parking.getxPos(), parking.getyPos())));
        System.out.println();
        plane.getRentableBikes().forEach(System.out::println);
        plane.getRentableBikes().forEach(bike -> System.out.println("Distanz: " + transportLocationFinderService.getDistance(bike.getxPos(), bike.getyPos())));
        System.out.println();
        plane.getSubwayStations().forEach(System.out::println);
        plane.getSubwayStations().forEach(subwayStation -> System.out.println("Distanz: " + transportLocationFinderService.getDistance(subwayStation.getxPos(), subwayStation.getyPos())));
        System.out.println();
        plane.getTrainStations().forEach(System.out::println);
        plane.getTrainStations().forEach(trainStation -> System.out.println("Distanz: " + transportLocationFinderService.getDistance(trainStation.getxPos(), trainStation.getyPos())));
        System.out.println();
    }

    public void printCityData() {
        System.out.println("Aktuelle Luftqualität: " + (int)(cityDataService.getAirCondition() * 100) + "%");
        System.out.println("Aktuelle Auslastung öffentlicher Verkehrsmittel: " + (int) (cityDataService.getPublicTransportLoad() * 100) + "%");
        System.out.println("Aktuelle Verkehrsauslastung: " + (int) (cityDataService.getTrafficLoad() * 100) + "%");
        System.out.println();
    }

    /**
     * Prints the nearest Transport location to console
     */
    public void printNearestLocation() {
        TransportLocation nearestLocation = transportLocationFinderService.getNearestLocation();
        double distance = transportLocationFinderService.getDistance(nearestLocation.getxPos(), nearestLocation.getyPos());
        System.out.println("Nächstgelegene Transportmöglichkeit: " + Arrays.toString(nearestLocation.getCoordinates()) + ", " + (int) distance + "m von dir entfernt.");
        System.out.println("Typ: " + nearestLocation.getType());
        System.out.println("Umweltbelastung: " + transportEnvironmentService.getEnvironmentalStatus(nearestLocation));
        System.out.println("Geschwindigkeit: " + transportSpeedService.getTransportationSpeed(nearestLocation));
        System.out.println();
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
