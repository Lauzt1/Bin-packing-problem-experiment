package Java;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * FFD.java
 * 
 * This class implements the First Fit Decreasing (FFD) algorithm for the bin packing problem.
 * Given an array of item sizes and a bin capacity, it sorts the items in decreasing order
 * and then applies the First Fit strategy.
 */
public class FFD {
    
    /**
     * Runs the First Fit Decreasing algorithm.
     *
     * @param items       an array of integers representing item sizes.
     * @param binCapacity the capacity of each bin.
     * @return the number of bins used.
     */
    public static int firstFitDecreasing(int[] items, int binCapacity) {
        // Sort items in ascending order, then reverse to get descending order.
        Arrays.sort(items);
        reverseArray(items);
        
        // List to store the remaining capacity in each open bin.
        ArrayList<Integer> bins = new ArrayList<>();
        
        // Process each item.
        for (int item : items) {
            boolean placed = false;
            // Try to place the item in the first bin that can accommodate it.
            for (int i = 0; i < bins.size(); i++) {
                int remaining = bins.get(i);
                if (item <= remaining) {
                    bins.set(i, remaining - item);  // Place item and update remaining capacity.
                    placed = true;
                    break;
                }
            }
            // If the item was not placed, open a new bin.
            if (!placed) {
                bins.add(binCapacity - item);
            }
        }
        
        return bins.size();
    }
    
    /**
     * Helper method to reverse an integer array in place.
     *
     * @param array the array to reverse.
     */
    private static void reverseArray(int[] array) {
        int i = 0, j = array.length - 1;
        while (i < j) {
            int temp = array[i];
            array[i] = array[j];
            array[j] = temp;
            i++;
            j--;
        }
    }
}
