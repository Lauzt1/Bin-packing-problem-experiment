#!/usr/bin/env python3
"""
This Python script implements:
  - First Fit (FF)
  - First Fit Decreasing (FFD)

It uses a fixed input of 10 items (values between 1 and 10) and bin capacity 10.
The output prints the items placed in each bin and the bin's total.
"""

def first_fit(numbers, bin_capacity):
    """
    Executes the First Fit algorithm.
    
    Parameters:
        numbers (list of int): The item sizes.
        bin_capacity (int): The capacity of each bin.
        
    Returns:
        list of lists: A list where each sublist is a bin containing items.
    """
    bins = []  # Each bin is represented as a list of items.
    for num in numbers:
        placed = False
        for bin in bins:
            # Check if the item fits in the current bin.
            if sum(bin) + num <= bin_capacity:
                bin.append(num)
                placed = True
                break
        if not placed:
            # Open a new bin if the item doesn't fit anywhere.
            bins.append([num])
    return bins

def first_fit_decreasing(numbers, bin_capacity):
    """
    Executes the First Fit Decreasing algorithm.
    
    Parameters:
        numbers (list of int): The item sizes.
        bin_capacity (int): The capacity of each bin.
        
    Returns:
        list of lists: A list where each sublist is a bin containing items.
    """
    # Sort items in descending order.
    sorted_numbers = sorted(numbers, reverse=True)
    return first_fit(sorted_numbers, bin_capacity)

def print_bins(bins, bin_capacity):
    """
    Prints each bin's contents and the sum of its items.
    
    Parameters:
        bins (list of lists): The packing solution.
        bin_capacity (int): The capacity of each bin.
    """
    for i, bin in enumerate(bins, start=1):
        total = sum(bin)
        print(f"Bin {i}: {bin} (total = {total})")

if __name__ == "__main__":
    # Sample input: 10 items with sizes in [1, 10].
    items = [3, 8, 2, 5, 7, 1, 9, 4, 6, 2]
    bin_capacity = 10

    print("=== First Fit (FF) Packing ===")
    ff_bins = first_fit(items, bin_capacity)
    print_bins(ff_bins, bin_capacity)

    print("\n=== First Fit Decreasing (FFD) Packing ===")
    ffd_bins = first_fit_decreasing(items, bin_capacity)
    print_bins(ffd_bins, bin_capacity)
