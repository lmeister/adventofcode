package main.adventofcode.year2019.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;
import main.adventofcode.year2019.util.IntcodeComputer;

import java.io.IOException;

public class Day02 implements Day {
    private String inputFilePath;

    public Day02(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }
    public String solvePart1() {
        try {
            IntcodeComputer intcodeComputer = new IntcodeComputer(InputReader.readStringFromFile(inputFilePath));
            return String.valueOf(intcodeComputer.runProgram().get(0));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    public String solvePart2() {
        return null;
    }
}
