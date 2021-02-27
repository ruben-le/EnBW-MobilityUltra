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
        while(!executionState.isFinished()) {
            printHelp();
            String input = scanner.nextLine().toLowerCase();
            if(input.equals("printlocations")) {
                executionState.printLocations();
            }
            if(input.equals("inputpos")) {
                executionState.getPositionData();
            }
            if(input.equals("pos")) {
                executionState.outputPos();
            }
            if(input.equals("quit")) {
                executionState.setFinished();
            }
        }
    }

    private void printHelp() {
        System.out.println("Commands overview:" + System.lineSeparator());
        System.out.println("printLocations: prints locations available");
        System.out.println("inputPos: set your Position");
        System.out.println("pos: view your current Position");
        System.out.println("quit: quit" + System.lineSeparator());
    }
}
