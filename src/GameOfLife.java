import java.util.Scanner;
import java.util.Random;

public class GameOfLife {
    private int[][] grid;
    private int rows;
    private int cols;

    public GameOfLife(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
        this.grid = new int[rows][cols];
    }

    public void randomizeGrid() {
        Random random = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                this.grid[i][j] = random.nextInt(2);
            }
        }
    }

    public void setGrid() {
        Scanner scanner = new Scanner(System.in);
        for (int i = 0; i < rows; i++) {
            System.out.println("Enter row " + (i + 1) + " of " + rows + " (0s and 1s): ");
            String input = scanner.nextLine();
            if (input.length() < cols) {
                System.out.println("You did not fill in enough values, so we will randomly assign the rest.");
                randomizeGridFromIndex(i, input.length());
                break;
            } else {
                for (int j = 0; j < cols; j++) {
                    char c = input.charAt(j);
                    if (c == '0' || c == '1') {
                        this.grid[i][j] = Integer.parseInt(String.valueOf(c));
                    } else {
                        System.out.println("Invalid input. Please enter only 0s and 1s.");
                        j--;
                    }
                }
            }
        }
    }
    private void randomizeGridFromIndex(int row, int col) {
        Random random = new Random();
        for (int j = col; j < cols; j++) {
            this.grid[row][j] = random.nextInt(2);
        }
    }

    public void nextGeneration() {
        int[][] newGrid = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int neighbors = countLiveNeighbors(i, j);
                if (this.grid[i][j] == 1) {
                    if (neighbors < 2 || neighbors > 3) {
                        newGrid[i][j] = 0;
                    } else {
                        newGrid[i][j] = 1;
                    }
                } else {
                    if (neighbors == 3) {
                        newGrid[i][j] = 1;
                    } else {
                        newGrid[i][j] = 0;
                    }
                }
            }
        }

        this.grid = newGrid;
    }

    private int countLiveNeighbors(int row, int col) {
        int count = 0;
        for (int i = row - 1; i <= row + 1; i++) {
            for (int j = col - 1; j <= col + 1; j++) {
                if (i >= 0 && i < rows && j >= 0 && j < cols && !(i == row && j == col)) {
                    count += this.grid[i][j];
                }
            }
        }
        return count;
    }

    public void printGrid() {
        System.out.println();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(this.grid[i][j] == 1 ? "A " : "D ");
            }
            System.out.println();
        }
        System.out.println();
    }
}