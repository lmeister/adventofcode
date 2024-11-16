package main.adventofcode.year2019.days;

import main.adventofcode.framework.Day;
import main.adventofcode.framework.InputReader;

import java.awt.Point;
import java.io.IOException;
import java.util.*;

public class Day03 implements Day {

    private String inputFilePath;

    public Day03(String inputFilePath) {
        this.inputFilePath = inputFilePath;
    }

    public String solvePart1() {
        try {
            List<String> input = InputReader.readStringsFromFile(this.inputFilePath);
            Set<Point> wire1Points = wirePathToPoints(input.get(0));
            Set<Point> wire2Points = wirePathToPoints(input.get(1));
            Set<Point> intersections = getIntersections(wire1Points, wire2Points);
            Point centralPort = new Point(0, 0);

            int minDistance = intersections.stream()
                    .mapToInt(intersection -> computeManhattanDistance(centralPort, intersection))
                    .min()
                    .orElse(-1);
            return String.valueOf(minDistance);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    public String solvePart2() {
        try {
            List<String> input = InputReader.readStringsFromFile(this.inputFilePath);
            Map<Point, Integer> wire1PointStepMap = wirePathToPointStepMap(input.get(0));
            Map<Point, Integer> wire2PointStepMap = wirePathToPointStepMap(input.get(1));
            Set<Point> intersections = getIntersections(wire1PointStepMap.keySet(), wire2PointStepMap.keySet());

            int minDistance = intersections.stream()
                    .mapToInt(intersection -> wire1PointStepMap.get(intersection) + wire2PointStepMap.get(intersection))
                    .min()
                    .orElse(-1);
            return String.valueOf(minDistance);
        } catch (IOException ioException) {
            ioException.printStackTrace();
            return "Error reading file: " + ioException.getMessage();
        }
    }

    public static Set<Point> getIntersections(Set<Point> wire1Points, Set<Point> wire2Points) {
        Set<Point> intersections = new HashSet<>(wire1Points);
        intersections.retainAll(wire2Points);
        return intersections;
    }

    public static int computeManhattanDistance(Point a, Point b) {
        return (int) (Math.abs(a.getX() - b.getX()) + Math.abs(a.getY() - b.getY()));
    }

    public static Set<Point> wirePathToPoints(String wirePath) {
        Set<Point> points = new HashSet<>();
        int x = 0;
        int y = 0;

        for (String move : wirePath.split(",")) {
            char direction = move.charAt(0);
            int distance = Integer.parseInt(move.substring(1));

            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case 'R' -> x++;
                    case 'L' -> x--;
                    case 'U' -> y++;
                    case 'D' -> y--;
                }
                points.add(new Point(x, y));
            }
        }
        return points;
    }

    public static Map<Point, Integer> wirePathToPointStepMap(String wirePath) {
        Map<Point, Integer> points = new HashMap<>();
        int x = 0;
        int y = 0;
        int steps = 0;

        for (String move : wirePath.split(",")) {
            char direction = move.charAt(0);
            int distance = Integer.parseInt(move.substring(1));

            for (int i = 0; i < distance; i++) {
                switch (direction) {
                    case 'R' -> x++;
                    case 'L' -> x--;
                    case 'U' -> y++;
                    case 'D' -> y--;
                }
                steps++;
                points.put(new Point(x, y), steps);
            }
        }
        return points;
    }
}
