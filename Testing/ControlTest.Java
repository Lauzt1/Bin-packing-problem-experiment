import java.util.ArrayList;
import java.util.Arrays;

/**
 * BinPacking.java
 *
 * This class implements both the First Fit (FF) and First Fit Decreasing (FFD)
 * bin packing algorithms. It uses a fixed array of 10 items (values between 1 and 10)
 * and a bin capacity of 10. The program prints to the terminal the contents of each bin.
 */
public class ControlTest {

    /**
     * Implements the First Fit algorithm.
     *
     * @param items       an array of integers representing item sizes.
     * @param binCapacity the capacity of each bin.
     * @return an ArrayList where each element is an ArrayList representing a bin (list of items).
     */
    public static ArrayList<ArrayList<Integer>> firstFit(int[] items, int binCapacity) {
        ArrayList<ArrayList<Integer>> bins = new ArrayList<>();
        for (int item : items) {
            boolean placed = false;
            for (ArrayList<Integer> bin : bins) {
                int sum = 0;
                for (int x : bin) {
                    sum += x;
                }
                if (sum + item <= binCapacity) {
                    bin.add(item);
                    placed = true;
                    break;
                }
            }
            if (!placed) {
                ArrayList<Integer> newBin = new ArrayList<>();
                newBin.add(item);
                bins.add(newBin);
            }
        }
        return bins;
    }
    
    /**
     * Implements the First Fit Decreasing algorithm.
     *
     * @param items       an array of integers representing item sizes.
     * @param binCapacity the capacity of each bin.
     * @return an ArrayList where each element is an ArrayList representing a bin.
     */
    public static ArrayList<ArrayList<Integer>> firstFitDecreasing(int[] items, int binCapacity) {
        // Clone and sort items in descending order.
        int[] sortedItems = items.clone();
        Arrays.sort(sortedItems);
        // Reverse the sorted array.
        for (int i = 0; i < sortedItems.length / 2; i++) {
            int temp = sortedItems[i];
            sortedItems[i] = sortedItems[sortedItems.length - 1 - i];
            sortedItems[sortedItems.length - 1 - i] = temp;
        }
        return firstFit(sortedItems, binCapacity);
    }
    
    /**
     * Prints the bins with their items and total sum.
     *
     * @param bins        the packing solution (list of bins).
     * @param binCapacity the capacity of each bin.
     */
    public static void printBins(ArrayList<ArrayList<Integer>> bins, int binCapacity) {
        for (int i = 0; i < bins.size(); i++) {
            ArrayList<Integer> bin = bins.get(i);
            int sum = 0;
            for (int item : bin) {
                sum += item;
            }
            System.out.println("Bin " + (i + 1) + ": " + bin + " (total = " + sum + ")");
        }
    }
    
    public static void main(String[] args) {
        // Sample input: 10 items with sizes in the range 1-10.
        int[] items = {3, 8, 2, 5, 7, 1, 9, 4, 6, 2};
        int binCapacity = 10;
        
        System.out.println("=== First Fit (FF) Packing ===");
        ArrayList<ArrayList<Integer>> ffBins = firstFit(items, binCapacity);
        printBins(ffBins, binCapacity);
        
        System.out.println("\n=== First Fit Decreasing (FFD) Packing ===");
        ArrayList<ArrayList<Integer>> ffdBins = firstFitDecreasing(items, binCapacity);
        printBins(ffdBins, binCapacity);
    }
}
