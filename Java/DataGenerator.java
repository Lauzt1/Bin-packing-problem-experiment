package Java;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class DataGenerator {

    // Constants for the range of item sizes (from 1 to 10)
    public static final int MIN_ITEM = 1;
    public static final int MAX_ITEM = 10;

    // Main method to generate data files
    public static void main(String[] args) {
        // Array of input sizes for which we want to generate files.
        // Changed from {100, 1000, 5000, 10000} to {1000, 5000, 10000, 30000}
        int[] sizes = {1000, 5000, 10000, 30000};

        // Loop over each size and generate three cases: average, best, and worst.
        for (int n : sizes) {
            // Generate the average-case file (random order numbers)
            String avgFilename = n + "_average.txt";
            generateAverageCase(n, avgFilename);

            // Generate the best-case file (repeating pattern {4, 6} that sums to 10)
            String bestFilename = n + "_best.txt";
            generateBestCase(n, bestFilename);

            // Generate the worst-case file (random numbers sorted in ascending order)
            String worstFilename = n + "_worst.txt";
            generateWorstCase(n, worstFilename);

            // Print a message indicating the files for the current size have been generated
            System.out.println("Generated files for n = " + n);
        }

        // Final message once all files have been generated
        System.out.println("Data generation completed.");
    }

    /**
     * Generates a file with n random items (average-case).
     * Each item is a random integer between MIN_ITEM and MAX_ITEM.
     *
     * @param n         number of items to generate
     * @param filename  name of the output file
     */
    public static void generateAverageCase(int n, String filename) {
        Random rand = new Random(); // Create a random number generator
        int[] items = new int[n];

        // Fill the array with random numbers within the range [MIN_ITEM, MAX_ITEM]
        for (int i = 0; i < n; i++) {
            items[i] = rand.nextInt(MAX_ITEM - MIN_ITEM + 1) + MIN_ITEM;
        }

        // Write the items to the file, one number per line
        writeArrayToFile(items, filename);
    }

    /**
     * Generates a file with n items for the best-case.
     * For the best-case, we use a repeating pattern that perfectly fits.
     * Here, the pattern {4, 6} always sums to 10 (the bin capacity).
     *
     * @param n         number of items to generate
     * @param filename  name of the output file
     */
    public static void generateBestCase(int n, String filename) {
        int[] items = new int[n];
        int[] pattern = {4, 6}; // Pattern that sums to 10

        // Fill the array by repeating the pattern
        for (int i = 0; i < n; i++) {
            items[i] = pattern[i % pattern.length];
        }

        // Write the items to the file, one number per line
        writeArrayToFile(items, filename);
    }

    /**
     * Generates a file with n items for the worst-case.
     * For worst-case (for the First Fit algorithm), we generate random items and then sort them in ascending order.
     *
     * @param n         number of items to generate
     * @param filename  name of the output file
     */
    public static void generateWorstCase(int n, String filename) {
        Random rand = new Random(); // Create a random number generator
        int[] items = new int[n];

        // Fill the array with random numbers within the range [MIN_ITEM, MAX_ITEM]
        for (int i = 0; i < n; i++) {
            items[i] = rand.nextInt(MAX_ITEM - MIN_ITEM + 1) + MIN_ITEM;
        }

        // Sort the array in ascending order, which is a worst-case for some algorithms
        Arrays.sort(items);

        // Write the items to the file, one number per line
        writeArrayToFile(items, filename);
    }

    /**
     * Writes an array of integers to a file.
     * Each integer is written on a new line.
     *
     * @param items     array of integers to write
     * @param filename  name of the file to write to
     */
    public static void writeArrayToFile(int[] items, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            // Write each integer to a new line in the file
            for (int item : items) {
                writer.println(item);
            }
        } catch (IOException e) {
            // Print an error message if there is an issue writing to the file
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }
}
