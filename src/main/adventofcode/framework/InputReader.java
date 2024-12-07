package main.adventofcode.framework;

import main.adventofcode.framework.util.dataobjects.Grid;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputReader {
    public static List<Integer> readIntegersFromFile(String filepath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));
        return lines.stream().map(Integer::parseInt).toList();
    }

    public static String readStringFromFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath));
    }

    public static List<String> readStringsFromFile(String filepath) throws IOException {
        return Files.readAllLines(Paths.get(filepath));
    }

    // TODO: Remove this, refactor usages to use Grid class
    public static char[][] readCharGridFromFile(String filepath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));
        int rows = lines.size();
        int cols = lines.get(0).length(); // Assumes all rows have the same length.

        char[][] grid = new char[rows][cols];
        for (int i = 0; i < rows; i++) {
            grid[i] = lines.get(i).toCharArray();
        }

        return grid;
    }

    public static Grid<Character> readCharacterGridFromFile(String filepath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));
        int rows = lines.size();
        int cols = lines.get(0).length(); // Assumes all rows have the same length.

        // Create a 2D array of Characters
        Character[][] gridArray = new Character[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                gridArray[i][j] = lines.get(i).charAt(j);
            }
        }

        return new Grid<>(gridArray);
    }
}
