package main.adventofcode.year2024.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day07 implements Day {

    private String inputFilePath;

    public Day07(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    @Override
    public String solvePart1() {
        // Example usage: replace with actual input reading logic
        try {
            List<String> input = InputReader.readStringsFromFile(inputFilePath);
            List<Operand> availableOperands = List.of(Operand.ADD, Operand.MULTIPLY);

            long sum = 0L;
            for (String line : input) {
                Equation equation = findMatchingEquation(line, availableOperands);
                if (equation != null) {
                    sum += Long.parseLong(line.split(":")[0].trim());
                }
            }
            return String.valueOf(sum);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    @Override
    public String solvePart2() {
        try {
            List<String> input = InputReader.readStringsFromFile(inputFilePath);
            List<Operand> availableOperands = List.of(Operand.ADD, Operand.MULTIPLY, Operand.CONCATENATE);

            long sum = 0L;
            for (String line : input) {
                Equation equation = findMatchingEquation(line, availableOperands);
                if (equation != null) {
                    sum += Long.parseLong(line.split(":")[0].trim());
                }
            }
            return String.valueOf(sum);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    private Equation findMatchingEquation(String line, List<Operand> availableOperands) {
        String[] parts = line.split(":");
        long targetResult = Long.parseLong(parts[0].trim());
        List<Long> numbers = Arrays.stream(parts[1].trim().split(" "))
                .map(Long::parseLong)
                .toList();

        // Generate all possible operand combinations
        List<List<Operand>> operandCombinations = generateOperandCombinations(numbers.size() - 1, availableOperands);

        // Evaluate each combination
        for (List<Operand> operands : operandCombinations) {
            Equation equation = new Equation(numbers, operands);
            if (equation.compute() == targetResult) {
                return equation;
            }
        }

        return null; // No matching equation found
    }

    private List<List<Operand>> generateOperandCombinations(int length, List<Operand> availableOperands) {
        List<List<Operand>> combinations = new ArrayList<>();
        generateOperandCombinationsRecursive(length, availableOperands, new ArrayList<>(), combinations);
        return combinations;
    }

    private void generateOperandCombinationsRecursive(int length, List<Operand> availableOperands,
                                                      List<Operand> current, List<List<Operand>> combinations) {
        if (current.size() == length) {
            combinations.add(new ArrayList<>(current));
            return;
        }

        for (Operand operand : availableOperands) {
            current.add(operand);
            generateOperandCombinationsRecursive(length, availableOperands, current, combinations);
            current.remove(current.size() - 1); // Backtrack
        }
    }

    private static record Equation(List<Long> numbers, List<Operand> operands) {
        public Equation {
            if (operands.size() != numbers.size() - 1) {
                throw new IllegalArgumentException("Invalid equation: number of operands must be exactly " +
                        "one less than numbers.");
            }
        }

        public double compute() {
            long result = numbers.get(0); // Start with the first number
            for (int i = 0; i < operands.size(); i++) {
                Operand operand = operands.get(i);
                long nextNumber = numbers.get(i + 1);
                result = operand.apply(result, nextNumber);
            }
            return result;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(numbers.get(0));
            for (int i = 0; i < operands.size(); i++) {
                sb.append(" ").append(operands.get(i)).append(" ").append(numbers.get(i + 1));
            }
            return sb.toString();
        }
    }

    private enum Operand {
        ADD {
            @Override
            public long apply(long a, long b) {
                return a + b;
            }

            @Override
            public String toString() {
                return "+";
            }
        },
        MULTIPLY {
            @Override
            public long apply(long a, long b) {
                return a * b;
            }

            @Override
            public String toString() {
                return "*";
            }
        },
        CONCATENATE {
            @Override
            public long apply(long a, long b) {
                String concatenated = String.valueOf(a) + String.valueOf(b);
                return Long.parseLong(concatenated);
            }

            @Override
            public String toString() {
                return "||";
            }
        };

        public abstract long apply(long a, long b);
    }
}
