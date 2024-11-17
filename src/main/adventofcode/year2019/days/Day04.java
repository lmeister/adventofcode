package main.adventofcode.year2019.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.io.IOException;

public class Day04 implements Day {

    private String inputFilePath;

    public Day04(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    @Override
    public String solvePart1() {
        try {
            String input = InputReader.readStringFromFile(this.inputFilePath);
            int lowerBound = Integer.parseInt(input.substring(0, input.indexOf('-')));
            int upperBound = Integer.parseInt(input.substring(input.indexOf('-') + 1));

            int amountOfPasswordsMatchingCriteria = 0;
            for (int i = lowerBound; i <= upperBound; i++) {
                if (hasTwoAdjacentDigits(i) && digitsNeverDecrease(i)) {
                    amountOfPasswordsMatchingCriteria++;
                }
            }
            return String.valueOf(amountOfPasswordsMatchingCriteria);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    @Override
    public String solvePart2() {
        try {
            String input = InputReader.readStringFromFile(this.inputFilePath);
            int lowerBound = Integer.parseInt(input.substring(0, input.indexOf('-')));
            int upperBound = Integer.parseInt(input.substring(input.indexOf('-') + 1));

            int amountOfPasswordsMatchingCriteria = 0;
            for (int i = lowerBound; i <= upperBound; i++) {
                if (hasExactlyTwoAdjacentDigits(i) && digitsNeverDecrease(i)) {
                    amountOfPasswordsMatchingCriteria++;
                }
            }
            return String.valueOf(amountOfPasswordsMatchingCriteria);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    public static boolean hasTwoAdjacentDigits(int number) {
        // Alternatively, we could use a String and iterate through char array
        // However, we can avoid the String conversion etc by simply using math
        int previousDigit = -1;
        while (number > 0) {
            int currentDigit = number % 10;
            if (currentDigit == previousDigit) {
                return true;
            }
            previousDigit = currentDigit;
            number = number / 10;
        }
        return false;
    }

    public static boolean hasExactlyTwoAdjacentDigits(int number) {
        int previousDigit = -1;
        int currentGroupSize = 1; // Keep Track of group size

        while (number > 0) {
            int currentDigit = number % 10;
            if (currentDigit == previousDigit) {
                currentGroupSize++;
            } else {
                if (currentGroupSize == 2) {
                    return true; // if digits change and currentGroupSize == 2, we have exactly 2
                }
                currentGroupSize = 1; // otherwise decrease
            }
            previousDigit = currentDigit;
            number = number / 10;
        }
        return currentGroupSize == 2;
    }

    public static boolean digitsNeverDecrease(int number) {
        int prevDigit = 10;
        while (number > 0) {
            int currentDigit = number % 10;
            if (currentDigit > prevDigit) {
                return false;
            }
            prevDigit = currentDigit;
            number /= 10;
        }
        return true;
    }
}
