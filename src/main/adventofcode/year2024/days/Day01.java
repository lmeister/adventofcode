package main.adventofcode.year2024.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;
import main.adventofcode.framework.util.dataobjects.Pair;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class Day01 implements Day {
    private String inputFilePath;

    public Day01(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String solvePart1() {
        try {
            Pair<List<Integer>, List<Integer>> inputLists = readInputLocations(inputFilePath);
            List<Integer> firstList = inputLists.first();
            List<Integer> secondList = inputLists.second();

            // Sort both lists
            Collections.sort(firstList);
            Collections.sort(secondList);

            // Sum up diffs
            int totalDistance = 0;
            for (int i = 0; i < firstList.size(); i++) {
                totalDistance += Math.abs(firstList.get(i) - secondList.get(i));
            }

            return String.valueOf(totalDistance);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    public String solvePart2() {
        try {
            Pair<List<Integer>, List<Integer>> inputLists = readInputLocations(inputFilePath);
            List<Integer> firstList = inputLists.first();
            List<Integer> secondList = inputLists.second();

            Map<Integer, Long> frequencyMap = secondList.stream()
                    .collect(Collectors.groupingBy(e -> e, Collectors.counting()));

            int similarityScore = firstList.stream()
                    .mapToInt(element -> element * frequencyMap.getOrDefault(element, 0L).intValue())
                    .sum();

            return String.valueOf(similarityScore);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    private Pair<List<Integer>, List<Integer>> readInputLocations(String filePath) throws IOException {
        List<String> lines = InputReader.readStringsFromFile(filePath);
        List<Integer> firstList = new ArrayList<>();
        List<Integer> secondList = new ArrayList<>();

        for (String line : lines) {
            String[] split = line.split("\\s+");
            firstList.add(Integer.parseInt(split[0]));
            secondList.add(Integer.parseInt(split[1]));
        }

        return new Pair<>(firstList, secondList);
    }

}
