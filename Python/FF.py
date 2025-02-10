# FF.py
"""
This module implements the First Fit (FF) algorithm for the bin packing problem.
It processes a list of item sizes and returns the number of bins used.
"""

def first_fit(numbers, bin_capacity):
    """
    Executes the First Fit algorithm.
    
    Parameters:
        numbers (list of int): The item sizes.
        bin_capacity (int): The capacity of each bin.
        
    Returns:
        int: The number of bins used.
    """
    bins = []
    for num in numbers:
        placed = False
        for i in range(len(bins)):
            if bins[i] + num <= bin_capacity:
                bins[i] += num
                placed = True
                break
        if not placed:
            bins.append(num)
    return len(bins)
