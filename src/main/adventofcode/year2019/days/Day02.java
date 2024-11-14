package main.adventofcode.year2019.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;
import main.adventofcode.year2019.util.IntcodeComputer;

import java.io.IOException;
import java.util.List;

public class Day02 implements Day {
    private String inputFilePath;

    public Day02(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }
    public String solvePart1() {
        try {
            IntcodeComputer intcodeComputer = new IntcodeComputer(InputReader.readStringFromFile(inputFilePath));
            intcodeComputer.initializeProgramState(12, 2);
            return String.valueOf(intcodeComputer.runProgram().get(0));
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    public String solvePart2() {

        try {
            IntcodeComputer intcodeComputer = new IntcodeComputer(InputReader.readStringFromFile(inputFilePath));

            for (int noun = 0; noun <= 99; noun++) {
                for (int verb = 0; verb <= 99; verb++) {
                    intcodeComputer.initializeProgramState(noun, verb);
                    List<Integer> result = intcodeComputer.runProgram();

                    if (result.get(0) == 19690720) {
                        return String.valueOf(100 * noun + verb);
                    }
                }
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }

        return "No Solution found.";
    }
}
