package prototype;

import java.util.Scanner;

public class Controller {

    private ExecutionState executionState;
    private Scanner scanner;

    public Controller() {
        this.executionState = new ExecutionState(false);
        this.scanner = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Controller controller = new Controller();
        controller.navigation(controller.executionState);

    }

    private void navigation(ExecutionState executionState) {
        printHelp();
        executionState.printCityData();
        while(!executionState.isFinished()) {
            String input = scanner.nextLine().toLowerCase();
            if(input.equals("data")) {
                executionState.printCityData();
            }
            if(input.equals("loc")) {
                executionState.printLocations();
            }
            if(input.equals("ipos")) {
                executionState.getPositionData();
            }
            if(input.equals("pos")) {
                executionState.outputPos();
            }
            if(input.equals("go")) {
                executionState.printNearestLocation();
            }
            if(input.equals("help")) {
                printHelp();
            }
            if(input.equals("quit")) {
                executionState.setFinished();
            }
        }
    }

    private void printHelp() {
        System.out.println("Overview" + System.lineSeparator());
        System.out.println("loc: prints locations available");
        System.out.println("iPos: input and set your Position");
        System.out.println("pos: view your current Position");
        System.out.println("go: get location of the nearest transportation point");
        System.out.println("help: print this dialog");
        System.out.println("quit: quit" + System.lineSeparator());
    }
}
