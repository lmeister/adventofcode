package main.adventofcode.framework;

public interface Day {
    String solvePart1();
    String solvePart2();

    default void printSolutions(String filePath) {
        System.out.println("Part 1: " + solvePart1());
        System.out.println("Part 2: " + solvePart2());
    }
}