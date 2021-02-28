package prototype;

import java.util.Scanner;

public class Controller {

    private final ExecutionState executionState;
    private final Scanner scanner;

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
                executionState.printOtherLocations();
            }
            if(input.equals("help")) {
                printHelp();
            }
            if(input.equals("exit")) {
                executionState.setFinished();
            }
        }
    }

    private void printHelp() {
        System.out.println("Übersicht" + System.lineSeparator());
        System.out.println("loc: Ausgabe aller verfügbaren Transportmöglichkeiten");
        System.out.println("iPos: Eingabe der eigenen Position");
        System.out.println("pos: Anzeige der aktuellen Position");
        System.out.println("go: Finde die beste Transportmöglichkeit in der Nähe");
        System.out.println("help: Zeigt diesen Dialog an");
        System.out.println("exit: Beenden" + System.lineSeparator());
    }
}
