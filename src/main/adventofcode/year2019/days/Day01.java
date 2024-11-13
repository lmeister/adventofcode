package main.adventofcode.year2019.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Day01 implements Day {
    private String inputFilePath;

    public Day01(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String solvePart1() {
        try {
            List<Integer> moduleMasses = InputReader.readIntegersFromFile(this.inputFilePath);
            return String.valueOf(calculateFuelForModuleList(moduleMasses));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    public String solvePart2() {
        try {
            List<Integer> moduleMasses = InputReader.readIntegersFromFile(this.inputFilePath);
            return String.valueOf(calculateFuelForModuleListWithFuelForFuel(moduleMasses));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    // Solves part 1 fuel calculation
    public int calculateFuelForSingleModule(int moduleMass) {
        // Negative fuel does not make sense
        return Math.max(((moduleMass / 3) - 2), 0);
    }

    // Solves part 1 for a list (helper method for testing)
    public int calculateFuelForModuleList(List<Integer> moduleMasses) {
        return moduleMasses.stream().mapToInt(this::calculateFuelForSingleModule).sum();
    }

    // Solves part 2 recursive fuel calculation:
    public int calculateFuelForSingleModuleWithFuelForFuel(int moduleMass) {
        int totalFuel = 0;
        int fuel = calculateFuelForSingleModule(moduleMass);

        while (fuel > 0) {
            totalFuel += fuel;
            fuel = calculateFuelForSingleModule(fuel);
        }

        return totalFuel;
    }

    public int calculateFuelForModuleListWithFuelForFuel (List<Integer> moduleMasses) {
        return moduleMasses.stream().mapToInt(this::calculateFuelForSingleModuleWithFuelForFuel).sum();
    }
}
