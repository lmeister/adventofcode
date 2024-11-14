package main.adventofcode.year2019.util;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IntcodeComputerTest {

    @Test
    public void testAdditionOpcode() {
        // Test case for addition (Opcode 1)
        List<Integer> memory = Arrays.asList(1, 0, 0, 0, 99);
        IntcodeComputer computer = new IntcodeComputer(memory);
        computer.runProgram();  // Running the program
        List<Integer> expectedMemory = Arrays.asList(2, 0, 0, 0, 99);
        assertEquals(expectedMemory, computer.getCurrentState());
    }

    @Test
    public void testMultiplicationOpcode() {
        // Test case for multiplication (Opcode 2)
        List<Integer> memory = Arrays.asList(2, 3, 0, 3, 99);
        IntcodeComputer computer = new IntcodeComputer(memory);
        computer.runProgram();
        List<Integer> expectedMemory = Arrays.asList(2, 3, 0, 6, 99);
        assertEquals(expectedMemory, computer.getCurrentState());
    }

    @Test
    public void testComplexProgram1() {
        // Test case for mixed operations
        List<Integer> memory = Arrays.asList(2, 4, 4, 5, 99, 0);
        IntcodeComputer computer = new IntcodeComputer(memory);
        computer.runProgram();
        List<Integer> expectedMemory = Arrays.asList(2, 4, 4, 5, 99, 9801);
        assertEquals(expectedMemory, computer.getCurrentState());
    }
    @Test
    public void testComplexProgram2() {
        // Test case for mixed operations
        List<Integer> memory = Arrays.asList(1, 1, 1, 4, 99, 5, 6, 0, 99);
        IntcodeComputer computer = new IntcodeComputer(memory);
        computer.runProgram();
        List<Integer> expectedMemory = Arrays.asList(30, 1, 1, 4, 2, 5, 6, 0, 99);
        assertEquals(expectedMemory, computer.getCurrentState());
    }

}