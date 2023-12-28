package _2016.day_1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day_2
{
    public enum Direction {
        NORTH,
        EAST,
        SOUTH,
        WEST
    }

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

    public static Direction changeDirection(Direction direction, char directionChange) {

        // North
        if ((direction == Direction.EAST && directionChange == 'L') ||
                (direction == Direction.WEST && directionChange == 'R')) {
            return Direction.NORTH;
        }

        // East
        if ((direction == Direction.SOUTH && directionChange == 'L') ||
                (direction == Direction.NORTH && directionChange == 'R')) {
            return Direction.EAST;
        }

        // South
        if ((direction == Direction.WEST && directionChange == 'L') ||
                (direction == Direction.EAST && directionChange == 'R')) {
            return Direction.SOUTH;
        }

        // West
        if ((direction == Direction.NORTH && directionChange == 'L') ||
                (direction == Direction.SOUTH && directionChange == 'R')) {
            return Direction.WEST;
        }

        // Returning invalid
        System.out.println("Invalid command, returning given direction");
        return direction;
    }

    public static void main(String[] args)
    {
        String filename = getFilename();
        File file = new File(filename);

        long startTime = System.nanoTime();
        String[] commands = new String[0];

        // File comes in a single line with commands separated by ", "
        try {
            Scanner fileScanner = new Scanner(file);
            commands = fileScanner.nextLine().split(", ");
            fileScanner.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        // Test print
//        for (String command : commands) {
//            System.out.println(command);
//        }

        // Initial states
        int[] coordinates = new int[2];
        int actualLocationDistance = 0;
        Direction direction = Direction.NORTH;
        Set<String> coordinatesVisited = new HashSet<>();
        boolean actualLocationFound = false;

        // First index is direction change, everything else is number of steps in that direction
        for (String command : commands) {
            char directionChange = command.charAt(0);
            int numberOfSteps = Integer.parseInt(command.substring(1));

            direction = changeDirection(direction, directionChange);
            for (int i = 0; i < numberOfSteps; ++i) {
                switch (direction) {
                    case Direction.NORTH:
                        --coordinates[1];
                        break;
                    case Direction.EAST:
                        ++coordinates[0];
                        break;
                    case Direction.SOUTH:
                        ++coordinates[1];
                        break;
                    case Direction.WEST:
                        --coordinates[0];
                        break;
                    default:
                        System.out.println("Invalid Direction, skipping");
                }

                if (!actualLocationFound && coordinatesVisited.contains(coordinates[0] + "," + coordinates[1])) {
                    actualLocationFound = true;
                    actualLocationDistance = Math.abs(coordinates[0]) + Math.abs((coordinates[1]));
                } else {
                    coordinatesVisited.add(coordinates[0] + "," + coordinates[1]);
                }
            }

            // Debug prints
//            System.out.println("Direction: " + direction);
//            System.out.println("X: " + xPos + " Y: " + yPos);
        }

        System.out.println("Part 1:\nEnding X: " + coordinates[0] + ", Ending Y: " + coordinates[1] + ", Distance: " + (Math.abs(coordinates[0]) + Math.abs(coordinates[1])));
        System.out.println("Part 2:\n" + "Actual location distance: " + actualLocationDistance);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
