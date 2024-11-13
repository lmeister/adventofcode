package main.adventofcode.year2019;

import main.adventofcode.framework.Day;
import main.adventofcode.year2019.days.Day01;

public class AdventOfCode2019 {

    public void runDay(int day, String input) {
        Day dayInstance = getDayInstance(day);
        if (dayInstance != null) {
            dayInstance.printSolutions(input);
        } else {
            System.out.println("Day " + day + " not implemented yet.");
        }
    }

    // Factory-like method to get instance
    public Day getDayInstance(int day) {
        return switch (day) {
            case 1 -> new Day01();
            default -> null;
        };
    }
}
