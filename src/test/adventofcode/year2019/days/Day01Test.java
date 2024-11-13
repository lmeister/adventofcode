package test.adventofcode.year2019.days;

import main.adventofcode.year2019.days.Day01;
import org.junit.jupiter.api.Test;      // Importing @Test annotation from JUnit 5

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*; // Importing assertion methods like assertEquals

public class Day01Test {

    @Test
    public void testCalculateFuel() {
        // Test individual fuel calculations
        Day01 day01 = new Day01("src/main/adventofcode/year2019/input/Day01.txt");
        assertEquals(2, day01.calculateFuelForSingleModule(12));     // (12 / 3) - 2 = 4 - 2 = 2
        assertEquals(2, day01.calculateFuelForSingleModule(14));     // (14 / 3) - 2 = 4 - 2 = 2
        assertEquals(654, day01.calculateFuelForSingleModule(1969)); // (1969 / 3) - 2 = 656 - 2 = 654
        assertEquals(33583, day01.calculateFuelForSingleModule(100756)); // (100756 / 3) - 2 = 33585 - 2 = 33583
    }

    @Test
    public void testTotalFuel() {
        // Test total fuel calculation for multiple modules
        Day01 day01 = new Day01("src/main/adventofcode/year2019/input/Day01.txt");
        List<Integer> masses = Arrays.asList(12, 14, 1969, 100756);
        assertEquals(34241, day01.calculateFuelForModuleList(masses)); // 2 + 2 + 654 + 33583 = 34241
    }

    @Test
    public void testZeroAndNegativeMass() {
        // If the mass is less than or equal to 0, fuel requirement should be 0 or negative
        Day01 day01 = new Day01("src/main/adventofcode/year2019/input/Day01.txt");
        assertEquals(0, day01.calculateFuelForSingleModule(0));  // (0 / 3) - 2 = -2 (but fuel cannot be negative)
        assertEquals(0, day01.calculateFuelForSingleModule(1));  // (1 / 3) - 2 = -1 (but fuel cannot be negative)
        assertEquals(0, day01.calculateFuelForSingleModule(2));  // (2 / 3) - 2 = -1 (but fuel cannot be negative)
    }

    @Test
    public void testFuelForFuel() {
        // Fuel is additional mass, thus requires more fuel
        Day01 day01 = new Day01("src/main/adventofcode/year2019/input/Day01.txt");
        assertEquals(2, day01.calculateFuelForSingleModuleWithFuelForFuel(14));  // (0 / 3) - 2 = -2 (but fuel cannot be negative)
        assertEquals(966, day01.calculateFuelForSingleModuleWithFuelForFuel(1969));  // (1 / 3) - 2 = -1 (but fuel cannot be negative)
        assertEquals(50346, day01.calculateFuelForSingleModuleWithFuelForFuel(100756));  // (2 / 3) - 2 = -1 (but fuel cannot be negative)

    }
}
