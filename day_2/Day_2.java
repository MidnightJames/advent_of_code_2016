package _2016.day_2;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_2
{
    static final int INVALID_FILENAME = -1;
    static final int FILE_NOT_FOUND = -2;

    public static String getFilename() {
        Scanner keyboardScanner = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String filename = keyboardScanner.nextLine();

        if (filename.isEmpty() || filename.isBlank()) {
            System.out.println("Invalid filename entered.");
            System.exit(INVALID_FILENAME);;
        }

        keyboardScanner.close();
        return filename;
    }

    public static void main(String[] args)
    {
        String filename = getFilename();
        File file = new File(filename);

        // Keypad init
        final int[][] keypadPartOne = { {1, 2, 3}, {4, 5, 6}, {7, 8, 9} };
        int rowPosPartOne = 1;
        int colPosPartOne = 1;

        final char[][] keypadPartTwo = { {'0', '0', '1', '0', '0'},
                                         {'0', '2', '3', '4', '0'},
                                         {'5', '6', '7', '8', '9'},
                                         {'0', 'A', 'B', 'C', '0'},
                                         {'0', '0', 'D', '0', '0'} };
        int rowPosPartTwo = 2;
        int colPosPartTwo = 0;

        StringBuilder bathroomCodePartOne = new StringBuilder();
        StringBuilder bathroomCodePartTwo = new StringBuilder();

        long startTime = System.nanoTime();

        ArrayList<String> commands = new ArrayList<>();
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                commands.add(fileScanner.nextLine());
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        for (String command : commands) {
            char[] commandCharList = command.toCharArray();
            for (char singleCommand : commandCharList) {
                // Part One
                if (singleCommand == 'U' && rowPosPartOne != 0) {
                    --rowPosPartOne;
                } else if (singleCommand == 'D' && rowPosPartOne != 2) {
                    ++rowPosPartOne;
                } else if (singleCommand == 'L' && colPosPartOne != 0) {
                    --colPosPartOne;
                } else if (singleCommand == 'R' && colPosPartOne != 2) {
                    ++colPosPartOne;
                } else {
//                    System.out.println("Invalid command, skipping");
                }

                // Part Two
                if (singleCommand == 'U' && rowPosPartTwo != 0 && keypadPartTwo[rowPosPartTwo - 1][colPosPartTwo] != '0') {
                    --rowPosPartTwo;
                } else if (singleCommand == 'D' && rowPosPartTwo != 4 && keypadPartTwo[rowPosPartTwo + 1][colPosPartTwo] != '0') {
                    ++rowPosPartTwo;
                } else if (singleCommand == 'L' && colPosPartTwo != 0 && keypadPartTwo[rowPosPartTwo][colPosPartTwo - 1] != '0') {
                    --colPosPartTwo;
                } else if (singleCommand == 'R' && colPosPartTwo != 4 && keypadPartTwo[rowPosPartTwo][colPosPartTwo + 1] != '0') {
                    ++colPosPartTwo;
                } else {
//                    System.out.println("Invalid command, skipping");
                }
            }

            bathroomCodePartOne.append(keypadPartOne[rowPosPartOne][colPosPartOne]);
            bathroomCodePartTwo.append(keypadPartTwo[rowPosPartTwo][colPosPartTwo]);
        }

        System.out.println("Part 1 - Bathroom Code: " + bathroomCodePartOne);
        System.out.println("Part 2 - Bathroom Code: " + bathroomCodePartTwo);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
