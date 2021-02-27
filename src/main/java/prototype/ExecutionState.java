package prototype;

import prototype.locations.Parking;
import prototype.locations.Stop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ExecutionState {

    private boolean finished;
    private int xPos;
    private int yPos;
    private final Plane plane;
    private final List<Stop> stops = new ArrayList<>();
    private final List<Parking> parking = new ArrayList<>();
    private final Random r = new Random();
    private final Scanner scanner = new Scanner(System.in);

    public ExecutionState(boolean finished) {
        this.finished = finished;
        generateSampleLocationData();
        this.plane = new Plane(1000, 1000, stops, parking);
    }


    public boolean isFinished() {
        return finished;
    }

    public void setFinished() {
        this.finished = true;
    }

    public void getPositionData() {
        System.out.println("Input your x position (Max " + plane.getXMax() + "):");
        xPos = scanner.nextInt();
        System.out.println("Input your y position (Max " + plane.getYMax() + "):");
        yPos = scanner.nextInt();
        System.out.println("Your position is: " + xPos + "/" + yPos + " (x/y), press enter to continue.");
    }

    public void outputPos() {
        System.out.println("Your Position: " + xPos + "/" + yPos);
    }

    public void printLocations() {
        stops.forEach(System.out::println);
        parking.forEach(System.out::println);
    }

    public void generateSampleLocationData() {

        for (int i = 0; i < 10; i++) {
            int x = r.nextInt(1000);
            int y = r.nextInt(1000);
            stops.add(new Stop(x, y));
        }
        for (int i = 0; i < 10; i++) {
            int x = r.nextInt(1000);
            int y = r.nextInt(1000);
            parking.add(new Parking(x, y));
        }
    }
}
