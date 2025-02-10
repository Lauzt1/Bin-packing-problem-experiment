import time
import csv
from datetime import datetime
from prettytable import PrettyTable
from FF import first_fit
from FFD import first_fit_decreasing

# Constants
BIN_CAPACITY = 10
SIZES = [1000, 5000, 10000, 30000]
CASES = ["average", "best", "worst"]
CSV_FILE = "python_results.csv"

def read_numbers_from_file(filename):
    """
    Reads numbers from a file where each line contains a single integer.
    
    Parameters:
        filename (str): The name of the file to read.
        
    Returns:
        list of int: The list of numbers read from the file.
    """
    numbers = []
    try:
        with open(filename, 'r') as file:
            for line in file:
                line = line.strip()
                if line:
                    numbers.append(int(line))
    except FileNotFoundError:
        print(f"File not found: {filename}")
    return numbers

def process_file(filename, bin_capacity):
    """
    Processes a single file by reading its numbers and running both
    the FF and FFD algorithms while timing them in seconds.
    
    Parameters:
        filename (str): The input file name.
        bin_capacity (int): The capacity of each bin.
        
    Returns:
        list: A list containing the filename, FF time (s), FFD time (s), 
              FF bins count, and FFD bins count.
    """
    numbers = read_numbers_from_file(filename)
    if not numbers:
        return [filename, "N/A", "N/A", "N/A", "N/A"]
    
    # Measure FF execution time in seconds.
    start_time = time.time()
    bins_ff = first_fit(numbers, bin_capacity)
    time_ff = time.time() - start_time  # seconds

    # Measure FFD execution time in seconds.
    start_time = time.time()
    bins_ffd = first_fit_decreasing(numbers, bin_capacity)
    time_ffd = time.time() - start_time  # seconds

    return [filename, f"{time_ff:.3f}", f"{time_ffd:.3f}", str(bins_ff), str(bins_ffd)]

def append_csv_block(csv_block, csv_file):
    """
    Appends a CSV-formatted text block to the given CSV file.
    The file is opened in append mode so that previous results remain.
    
    Parameters:
        csv_block (str): The multiline CSV data.
        csv_file (str): The output CSV file name.
    """
    with open(csv_file, mode='a', newline='') as file:
        file.write(csv_block)

def display_table(results):
    """
    Displays results in a formatted table.
    
    Parameters:
        results (list): A list of result rows.
    """
    table = PrettyTable()
    table.field_names = ["File", "FF (time)", "FFD (time)", "FF (bins)", "FFD (bins)"]
    for result in results:
        table.add_row(result)
    print(table)

def main():
    # Build the CSV block as a multiline string.
    csv_block = ""
    # Get current timestamp formatted as "M/d/yyyy hh:mm:ss a"
    timestamp = datetime.now().strftime("%m/%d/%Y %I:%M:%S %p")
    
    all_results = []  # For displaying results in a table.
    
    # Process each case type.
    for case in CASES:
        # Append header for the case block.
        csv_block += f"{timestamp} --- {case.upper()} CASE\n"
        csv_block += ",FF (time),FFD (time),FF (bins),FFD (bins)\n"
        
        # Process each input size for the current case.
        for size in SIZES:
            filename = f"{size}_{case}.txt"
            result = process_file(filename, BIN_CAPACITY)
            # We use the size (as a string) as the first column instead of the full filename.
            result_row = [str(size), result[1], result[2], result[3], result[4]]
            csv_block += ",".join(result_row) + "\n"
            all_results.append(result_row)
        
        # Append a blank line between case blocks.
        csv_block += "\n"
    
    # Append the CSV block to the CSV file in append mode.
    append_csv_block(csv_block, CSV_FILE)
    
    # Terminal output indicating completion.
    print("Algorithms tested and recorded in python_results.csv")
    # Display the results in a table.
    display_table(all_results)

if __name__ == "__main__":
    main()
