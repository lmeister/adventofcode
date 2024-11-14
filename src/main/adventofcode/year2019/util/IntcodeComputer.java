package main.adventofcode.year2019.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class IntcodeComputer {
    private List<Integer> program;


    public IntcodeComputer(List<Integer> program) {
        this.program = new ArrayList<>(program); // copy in order to maintain original list just in case
    }

    public IntcodeComputer(String program) {
        this.program = Arrays.stream(program.split(","))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
    }

    public List<Integer> runProgram() {
        // Return computer to state of crashed computer
        this.program.set(1, 12);
        this.program.set(2, 2);
        int pointer = 0;

        while (pointer < this.program.size()) {
            int opcode = this.program.get(pointer);

            // Halt program execution on opcode 99
            if (opcode == 99) {
                break;
            }

            if (pointer + 3 >= this.program.size()) {
                throw new IndexOutOfBoundsException("Incomplete opcode parameters after pointer " + pointer);
            }

            int address1 = this.program.get(pointer + 1);
            int address2 = this.program.get(pointer + 2);
            int targetAddress = this.program.get(pointer + 3);

            runOpcode(opcode, address1, address2, targetAddress);
            pointer += 4; // Move to next instruction set
        }

        return this.program;
    }

    private void runOpcode(int operation, int address1, int address2, int targetAddress) {
        if (address1 >= this.program.size() || address2 >= this.program.size() || targetAddress >= this.program.size()) {
            String errorMessage = String.format(
                    "Address out of bounds:\nAddress1=[%d], Address2=[%d], TargetAddress=[%d], Programsize=[%d]",
                    address1, address2, targetAddress, this.program.size());
            throw new IndexOutOfBoundsException(errorMessage);
        }

        switch (operation) {
            case 1:
                this.program.set(targetAddress, this.program.get(address1) + this.program.get(address2));
                break;
            case 2:
                this.program.set(targetAddress, this.program.get(address1) * this.program.get(address2));
                break;
            case 99:
                // Do nothing, program should halt (Handled in runProgram)
                break;
            default:
                throw new IllegalArgumentException("Invalid opcode: " + operation);
        }
    }
}
