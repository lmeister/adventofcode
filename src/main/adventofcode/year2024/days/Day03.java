package main.adventofcode.year2024.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day03 implements Day {
    private String inputFilePath;

    public Day03(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }


    @Override
    public String solvePart1() {
        try {
            String input = InputReader.readStringFromFile(inputFilePath);
            List<Instruction> instructions = extractInstructions(input);

            int result = 0;
            for (Instruction instruction : instructions) {
                result += instruction.compute();
            }

            return String.valueOf(result);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    @Override
    public String solvePart2() {
        try {
            String input = InputReader.readStringFromFile(inputFilePath);
            StringBuilder stringBuilder = new StringBuilder();

            boolean isRelevant = true;
            // Alternatively work with splits - Here: iteratively extract relevant parts
            for (int i = 0; i < input.length(); i++) {
                if (input.startsWith("do()", i)) {
                    isRelevant = true;
                    i += 3; // Skip "do()"
                } else if (input.startsWith("don't()", i)) {
                    isRelevant = false;
                    i += 6; // Skip "don't()"
                } else if (isRelevant) {
                    stringBuilder.append(input.charAt(i));
                }
            }
            List<Instruction> instructions = extractInstructions(stringBuilder.toString());
            int result = instructions.stream()
                    .mapToInt(Instruction::compute)
                    .sum();

            return String.valueOf(result);

        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    private List<Instruction> extractInstructions(String input) {
        Pattern mulPattern = Pattern.compile("mul\\((?<a>\\d+),(?<b>\\d+)\\)");
        List<Instruction> instructions = new ArrayList<>();
        Matcher matcher = mulPattern.matcher(input);
        while (matcher.find()) {
            int a = Integer.parseInt(matcher.group("a"));
            int b = Integer.parseInt(matcher.group("b"));
            instructions.add(new Instruction("mul", a, b));
        }
        return instructions;
    }

    public static class Instruction {
        private final String operator;
        private final int a;
        private final int b;

        public Instruction(String operator, int a, int b) {
            this.operator = operator;
            this.a = a;
            this.b = b;
        }

        public int compute() {
            return switch (operator) {
                case "mul" -> a * b;
                default -> throw new IllegalArgumentException("Unsupported operator: " + operator);
            };
        }
    }
}
