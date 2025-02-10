# FFD.py
"""
This module implements the First Fit Decreasing (FFD) algorithm.
It sorts the items in descending order and then applies the First Fit algorithm.
"""

from FF import first_fit

def first_fit_decreasing(numbers, bin_capacity):
    """
    Executes the First Fit Decreasing algorithm.
    
    Parameters:
        numbers (list of int): The item sizes.
        bin_capacity (int): The capacity of each bin.
        
    Returns:
        int: The number of bins used.
    """
    sorted_numbers = sorted(numbers, reverse=True)
    return first_fit(sorted_numbers, bin_capacity)
