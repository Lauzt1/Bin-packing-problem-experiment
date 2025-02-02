package Java;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

public class DataGenerator {

    // Constant for the range of item sizes (1 to 10)
    public static final int MIN_ITEM = 1;
    public static final int MAX_ITEM = 10;

    // Main method for data generation.
    public static void main(String[] args) {
        // Array of input sizes to generate files for.
        int[] sizes = {100, 1000, 5000, 10000};

        // Loop over each size and generate three cases: average, best, and worst.
        for (int n : sizes) {
            // Generate average-case file: random order.
            String avgFilename = n + "_average.txt";
            generateAverageCase(n, avgFilename);
            
            // Generate best-case file: repeating pattern that sums exactly to 10.
            // We use the pattern {4, 6}. (You can change this pattern if desired.)
            String bestFilename = n + "_best.txt";
            generateBestCase(n, bestFilename);
            
            // Generate worst-case file: random numbers sorted in ascending order.
            String worstFilename = n + "_worst.txt";
            generateWorstCase(n, worstFilename);
            
            System.out.println("Generated files for n = " + n);
        }
        
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
        Random rand = new Random(); // Default seed; for reproducibility, you can set a specific seed.
        int[] items = new int[n];
        
        // Fill array with random numbers in the range [MIN_ITEM, MAX_ITEM]
        for (int i = 0; i < n; i++) {
            items[i] = rand.nextInt(MAX_ITEM - MIN_ITEM + 1) + MIN_ITEM;
        }
        
        // Write the items to the file (one number per line)
        writeArrayToFile(items, filename);
    }

    /**
     * Generates a file with n items for the best-case.
     * In best-case we use a repeating pattern that packs perfectly.
     * Here, we use the pattern {4, 6} which sums to 10 (bin capacity).
     *
     * @param n         number of items to generate
     * @param filename  name of the output file
     */
    public static void generateBestCase(int n, String filename) {
        int[] items = new int[n];
        int pattern[] = {4, 6}; // The pattern that sums to 10
        
        // Fill the array by repeating the pattern.
        for (int i = 0; i < n; i++) {
            items[i] = pattern[i % pattern.length];
        }
        
        writeArrayToFile(items, filename);
    }

    /**
     * Generates a file with n items for the worst-case.
     * For worst-case (for FF) we generate random items then sort them in ascending order.
     *
     * @param n         number of items to generate
     * @param filename  name of the output file
     */
    public static void generateWorstCase(int n, String filename) {
        Random rand = new Random(); // Use same range as average-case
        int[] items = new int[n];
        
        // Generate random numbers as before.
        for (int i = 0; i < n; i++) {
            items[i] = rand.nextInt(MAX_ITEM - MIN_ITEM + 1) + MIN_ITEM;
        }
        
        // Sort the array in ascending order (which is known to be a worst-case for First Fit)
        Arrays.sort(items);
        
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
            // Write each item on a separate line
            for (int item : items) {
                writer.println(item);
            }
        } catch (IOException e) {
            System.err.println("Error writing to file " + filename + ": " + e.getMessage());
        }
    }
}
