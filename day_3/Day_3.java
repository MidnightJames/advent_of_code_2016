package _2016.day_3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Day_3
{
    static final int INVALID_FILENAME = -1;
    static final int FILE_NOT_FOUND = -2;

    public static String getFilename()
    {
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

        ArrayList<String> triangles = new ArrayList<>();

        long startTime = System.nanoTime();
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                triangles.add(fileScanner.nextLine().strip());
            }
            fileScanner.close();
        }
        catch (FileNotFoundException exception) {
            System.out.println("File not found.");
            System.exit(FILE_NOT_FOUND);
        }

        // Part One
        int possibleTrianglesPartOne = 0;
        for (String triangle : triangles) {

            // '\\s+' splits whitespace '\s' and the '+' means more than one space may exist
            String[] triangleArray = triangle.split("\\s+");
            int sideOne = Integer.parseInt(triangleArray[0]);
            int sideTwo = Integer.parseInt(triangleArray[1]);
            int sideThree = Integer.parseInt(triangleArray[2]);

            if (sideOne + sideTwo > sideThree && sideOne + sideThree > sideTwo && sideTwo + sideThree > sideOne) {
                ++possibleTrianglesPartOne;
            }
        }

        System.out.println("Part 1: " + possibleTrianglesPartOne);

        // Part Two
        int possibleTrianglesPartTwo = 0;
        for (int i = 0; i < triangles.size(); i += 3) {
            String[] rowOne = triangles.get(i).split("\\s+");
            String[] rowTwo = triangles.get(i + 1).split("\\s+");
            String[] rowThree = triangles.get(i + 2).split("\\s+");

            for (int j = 0; j < 3; ++j) {
                int sideOne = Integer.parseInt(rowOne[j]);
                int sideTwo = Integer.parseInt(rowTwo[j]);
                int sideThree = Integer.parseInt(rowThree[j]);

                if (sideOne + sideTwo > sideThree && sideOne + sideThree > sideTwo && sideTwo + sideThree > sideOne) {
                    ++possibleTrianglesPartTwo;
                }
            }
        }

        System.out.println("Part 2: " + possibleTrianglesPartTwo);

        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        System.out.println("Time: " + duration + " ms");
    }
}
