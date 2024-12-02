package main.adventofcode.year2024.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day02 implements Day {

    private String inputFilePath;

    public Day02(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }


    @Override
    public String solvePart1() {
        try {
            List<String> input = InputReader.readStringsFromFile(inputFilePath);
            int amountOfSafeReports = 0;
            for (String line : input) {
                int[] levels = parseLevelLine(line);
                if (isSteady(levels) && diffChecker(levels)) {
                    amountOfSafeReports++;
                }
            }
            return String.valueOf(amountOfSafeReports);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    @Override
    public String solvePart2() {
        try {
            List<String> input = InputReader.readStringsFromFile(inputFilePath);
            int amountOfSafeReports = 0;
            for (String line : input) {
                int[] levels = parseLevelLine(line);
                if (isSteady(levels) && diffChecker(levels)) {
                    amountOfSafeReports++;
                } else {
                    if (safeWithProblemDampener(levels)) {
                        amountOfSafeReports++;
                    }
                }
            }
            return String.valueOf(amountOfSafeReports);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    // For now, we assume a level has at least 2 entries
    private boolean isSteady(int[] levels) {
        boolean checkIncrease = levels[0] > levels[1];
        // This way we only iterate through the levels once
        if (checkIncrease) {
            for (int i = 1; i < levels.length - 1; i++) {
                if (levels[i] < levels[i + 1]) {
                    return false;
                }
            }
        } else {
            for (int i = 1; i < levels.length - 1; i++) {
                if (levels[i] > levels[i + 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // Inclusive bounds
    private boolean diffChecker(int[] levels) {
        for (int i = 0; i < levels.length - 1; i++) {
            int diff = Math.abs(levels[i] - levels[i + 1]);
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    private int[] parseLevelLine(String line) {
        return Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public boolean safeWithProblemDampener(int[] levels) {
        for (int i = 0; i < levels.length; i++) {
            int[] dampenedLevels = removeAtIndex(levels, i);
            if (isSteady(dampenedLevels) && diffChecker(dampenedLevels)) {
                return true;
            }
        }
        return false;
    }

    private int[] removeAtIndex(int[] levels, int index) {
        int[] resized = new int[levels.length - 1];
        for (int i = 0, j = 0; i < levels.length; i++) {
            if (i != index) {
                resized[j++] = levels[i];
            }
        }
        return resized;
    }
}
