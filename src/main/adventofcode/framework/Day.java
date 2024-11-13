package main.adventofcode.framework;

public interface Day {
    String solvePart1(String input);
    String solvePart2(String input);

    default void printSolutions(String input) {
        System.out.println("Part 1: " + solvePart1(input));
        System.out.println("Part 2: " + solvePart2(input));
    }
}