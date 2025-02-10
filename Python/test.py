import time
import csv
from prettytable import PrettyTable

def read_numbers_from_file(filename):
    with open(filename, 'r') as file:
        numbers = list(map(int, file.read().split()))
    return numbers

def first_fit(numbers, bin_capacity):
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

def first_fit_decreasing(numbers, bin_capacity):
    numbers.sort(reverse=True)
    return first_fit(numbers, bin_capacity)

def process_file(filename, bin_capacity):
    numbers = read_numbers_from_file(filename)

    start_time = time.time()
    bins_ff = first_fit(numbers, bin_capacity)
    time_ff = time.time() - start_time

    start_time = time.time()
    bins_ffd = first_fit_decreasing(numbers, bin_capacity)
    time_ffd = time.time() - start_time

    return [filename, time_ff, time_ffd, bins_ff, bins_ffd]

def save_results_to_csv(results, output_file):
    with open(output_file, mode='w', newline='') as file:
        writer = csv.writer(file)
        writer.writerow(["File", "FF (time)", "FFD (time)", "FF (bins)", "FFD (bins)"])
        for result in results:
            writer.writerow(result)

def display_table(results):
    table = PrettyTable()
    table.field_names = ["File", "FF (time)", "FFD (time)", "FF (bins)", "FFD (bins)"]
    for result in results:
        table.add_row(result)
    print(table)

# Example usage
files = ["1000_average.txt", "1000_best.txt", "1000_worst.txt", 
         "5000_average.txt", "5000_best.txt", "5000_worst.txt", 
         "10000_average.txt", "10000_best.txt", "10000_worst.txt",
         "30000_average.txt", "30000_best.txt", "30000_worst.txt"]

bin_capacity = 10  # Assuming bin capacity is 10

results = []
for file in files:
    results.append(process_file(file, bin_capacity))

save_results_to_csv(results, "bin_packing_results.csv")
display_table(results)
