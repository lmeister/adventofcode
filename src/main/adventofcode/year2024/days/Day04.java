package main.adventofcode.year2024.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.io.IOException;

public class Day04 implements Day {
    private String inputFilePath;

    private static final int[][] DIRECTIONS = {
            {-1, 0}, {1, 0}, {0, -1}, {0, 1},    // Up, Down, Left, Right
            {-1, -1}, {-1, 1}, {1, -1}, {1, 1}   // Diagonals
    };

    public Day04(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }


    @Override
    public String solvePart1() {
        try {
            char[][] grid = InputReader.readCharGridFromFile(inputFilePath);
            int count = 0;

            // Wenn X gefunden, prüfe alle Seiten ob M, wenn M, dann ob A, dann S?
            for (int x = 0; x < grid.length; x++) {
                for (int y = 0; y < grid[0].length; y++) {
                    if (getGridElement(grid, x, y) == 'X') {
                        count += getAmountOfAdjacentXmas(grid, x, y);
                    }
                }
            }
            return String.valueOf(count);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    @Override
    public String solvePart2() {
        return null;
    }

    public int getAmountOfAdjacentXmas(char[][] grid, int startX, int startY) {
        // Check each direction from the starting point
        int count = 0;
        for (int[] direction : DIRECTIONS) {
            if (isXmasPresentInDirection(grid, startX, startY, direction[0], direction[1])) {
                count++;
            }
        }

        return count;
    }

    private boolean isXmasPresentInDirection(char[][] grid, int startX, int startY, int dx, int dy) {
        for (int i = 0; i < "XMAS".length(); i++) {
            int x = startX + i * dx;
            int y = startY + i * dy;

            // Use a utility function to check bounds safely
            if (getGridElement(grid, x, y) != "XMAS".charAt(i)) {
                return false;
            }
        }

        return true;
    }

    private char getGridElement(char[][] grid, int x, int y) {
        if (x < 0 || x >= grid.length || y < 0 || y >= grid[0].length) {
            return '.'; // placeholder if specified point is out of bounds
        }
        return grid[x][y];
    }
}
