package main.adventofcode.framework;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class InputReader {
    public static List<Integer> readIntegersFromFile(String filepath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filepath));
        return lines.stream().map(Integer::parseInt).toList();
    }

    public static String readStringFromFile(String filepath) throws IOException {
        return Files.readString(Paths.get(filepath));
    }

    public static List<String> readStringsFromFile(String filepath) throws IOException {
        return Files.readAllLines(Paths.get(filepath));
    }
}
