package Java;

import java.util.ArrayList;

/**
 * FF.java
 * 
 * This class implements the First Fit (FF) algorithm for the bin packing problem.
 * The algorithm processes items in the order given:
 * - For each item, it scans the list of open bins (each bin stores its remaining capacity).
 * - If the item fits in the first bin that has enough space, it is placed there.
 * - Otherwise, a new bin is opened.
 * 
 * The method returns the total number of bins used.
 */
public class FF {

    /**
     * Executes the First Fit algorithm.
     *
     * @param items       An array of integers representing the sizes of the items.
     * @param binCapacity The capacity of each bin.
     * @return The number of bins used.
     */
    public static int firstFit(int[] items, int binCapacity) {
        // Create a list to keep track of remaining capacity in each bin.
        ArrayList<Integer> bins = new ArrayList<>();
        
        // Process each item in the original order.
        for (int item : items) {
            boolean placed = false;
            // Try to fit the item into an existing bin.
            for (int i = 0; i < bins.size(); i++) {
                if (item <= bins.get(i)) {
                    // Item fits; update the remaining capacity of the bin.
                    bins.set(i, bins.get(i) - item);
                    placed = true;
                    break;
                }
            }
            // If the item doesn't fit in any bin, open a new bin.
            if (!placed) {
                bins.add(binCapacity - item);
            }
        }
        return bins.size();
    }
}
