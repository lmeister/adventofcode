package main.adventofcode.year2019.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntcodeComputer {
    private final List<Integer> memory;
    private List<Integer> currentState;


    public IntcodeComputer(List<Integer> memory) {
        this.memory = List.copyOf(memory);
        this.currentState = new ArrayList<>(this.memory);
    }

    public IntcodeComputer(String memory) {
        this.memory = Arrays.stream(memory.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
        this.currentState = new ArrayList<>(this.memory);
    }

    public void initializeProgramState(int noun, int verb) {
        this.currentState.clear();
        this.currentState.addAll(this.memory); // Reset to initial state/memory
        this.currentState.set(1, noun);
        this.currentState.set(2, verb);
    }

    public List<Integer> runProgram() {
        int pointer = 0;

        while (pointer < this.currentState.size()) {
            int opcode = this.currentState.get(pointer);

            // Halt program execution on opcode 99
            if (opcode == 99) {
                break;
            }

            if (pointer + 3 >= this.currentState.size()) {
                throw new IndexOutOfBoundsException("Incomplete opcode parameters after pointer " + pointer);
            }

            int address1 = this.currentState.get(pointer + 1);
            int address2 = this.currentState.get(pointer + 2);
            int targetAddress = this.currentState.get(pointer + 3);

            runOpcode(opcode, address1, address2, targetAddress);
            pointer += 4; // Move to next instruction set
        }

        return this.currentState;
    }

    private void runOpcode(int operation, int address1, int address2, int targetAddress) {
        validateAddressBounds(address1, address2, targetAddress);

        switch (operation) {
            case 1 -> this.currentState.set(targetAddress, this.currentState.get(address1) + this.currentState.get(address2));
            case 2 -> this.currentState.set(targetAddress, this.currentState.get(address1) * this.currentState.get(address2));
            default -> throw new IllegalArgumentException("Invalid opcode: " + operation);
        }
    }

    public List<Integer> getCurrentState() {
        return this.currentState;
    }

    private void validateAddressBounds(int... addresses) {
        for (int address : addresses) {
            if (address >= this.currentState.size() || address < 0) {
                throw new IndexOutOfBoundsException(
                        String.format("Address out of bounds: [%d], Program size: [%d]", address, this.currentState.size())
                );
            }
        }
    }
}
