import java.util.Scanner;

public class main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int rows = 0;
        int cols = 0;
        System.out.println("LIVE CELLS ARE PRINTED AS A, which means alive. DEAD CELLS WILL BE PRINTED AS D, which mean dead");

        while (true) {
            System.out.println("Enter number of rows: ");
            if (scanner.hasNextInt()) {
                rows = scanner.nextInt();
                break;
            } else {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
        }

        while (true) {
            System.out.println("Enter number of columns: ");
            if (scanner.hasNextInt()) {
                cols = scanner.nextInt();
                break;
            } else {
                System.out.println("Please enter an integer.");
                scanner.next();
            }
        }

        GameOfLife game = new GameOfLife(rows, cols);

        while (true) {
            System.out.println("Randomize the grid? (r/m)");
            String input = scanner.next();
            if (input.equals("r")) {
                game.randomizeGrid();
                break;
            } else if (input.equals("m")) {
                game.setGrid();
                break;
            } else {
                System.out.println("Invalid input. Please enter 'r' for random or 'm' for manual.");
            }
        }


        while (true) {
            System.out.println("Current Generation:");
            game.printGrid();

            System.out.print("Press any key then enter to go to next generation, or 'q' to quit...");
            String input = scanner.next();

            if (input.equals("q")) {
                break;
            }

            game.nextGeneration();
        }

        System.out.println("Thanks for playing!");
    }
}
