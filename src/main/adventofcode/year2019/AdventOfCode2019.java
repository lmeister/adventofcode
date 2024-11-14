package main.adventofcode.year2019;

import main.adventofcode.framework.Day;
import main.adventofcode.year2019.days.Day01;
import main.adventofcode.year2019.days.Day02;

public class AdventOfCode2019 {

    public void runDay(int day) {
        Day dayInstance = getDayInstance(day);
        if (dayInstance != null) {
            dayInstance.printSolutions(createInputFilePathFromDay(day));
        } else {
            System.out.println("Day " + day + " not implemented yet.");
        }
    }

    // Factory-like method to get instance
    public Day getDayInstance(int day) {
        String dayFormatted = String.format("%02d", day);
        String inputFilePath = "src/main/adventofcode/year2019/input/Day" + dayFormatted + ".txt";

        return switch (day) {
            case 1 -> new Day01(inputFilePath);
            case 2 -> new Day02(inputFilePath);
            default -> null;
        };
    }

    private String createInputFilePathFromDay(int day) {
        String dayFormatted = String.format("%02d", day);
        return "src/main/adventofcode/year2019/input/Day" + dayFormatted + ".txt";
    }
}
