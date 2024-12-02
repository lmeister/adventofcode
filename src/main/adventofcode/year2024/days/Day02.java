package main.adventofcode.year2024.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.io.IOException;
import java.util.ArrayList;
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
            return String.valueOf(countLevels(input, levels -> isSteady(levels) && diffChecker(levels)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    @Override
    public String solvePart2() {
        try {
            List<String> input = InputReader.readStringsFromFile(inputFilePath);
            return String.valueOf(countLevels(input, levels ->
                    (isSteady(levels) && diffChecker(levels)) || safeWithProblemDampener(levels)));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    // For now, we assume a level has at least 2 entries
    private boolean isSteady(List<Integer> levels) {
        boolean isIncreasing = levels.get(0) <= levels.get(1);
        for (int i = 0; i < levels.size() - 1; i++) {
            if ((isIncreasing && levels.get(i) > levels.get(i + 1)) ||
                    (!isIncreasing && levels.get(i) < levels.get(i + 1))) {
                return false;
            }
        }
        return true;
    }

    private boolean diffChecker(List<Integer> levels) {
        for (int i = 0; i < levels.size() - 1; i++) {
            int diff = Math.abs(levels.get(i) - levels.get(i + 1));
            if (diff < 1 || diff > 3) {
                return false;
            }
        }
        return true;
    }

    private List<Integer> parseLevelLine(String line) {
        return Arrays.stream(line.split(" "))
                .mapToInt(Integer::parseInt)
                .boxed()
                .toList();
    }

    public boolean safeWithProblemDampener(List<Integer> levels) {
        for (int i = 0; i < levels.size(); i++) {
            List<Integer> dampenedLevels = removeAtIndex(levels, i);
            if (isSteady(dampenedLevels) && diffChecker(dampenedLevels)) {
                return true;
            }
        }
        return false;
    }

    private List<Integer> removeAtIndex(List<Integer> levels, int index) {
        List<Integer> resized = new ArrayList<>(levels);
        resized.remove(index);
        return resized;
    }

    private int countLevels(List<String> input, LevelProcessor processor) {
        int amountOfSafeReports = 0;
        for (String line : input) {
            List<Integer> levels = parseLevelLine(line);
            if (processor.isSafe(levels)) {
                amountOfSafeReports++;
            }
        }
        return amountOfSafeReports;
    }

    // Functional interface
    private interface LevelProcessor {
        boolean isSafe(List<Integer> levels);
    }
}
