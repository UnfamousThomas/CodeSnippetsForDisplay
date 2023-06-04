import sys
import re
from pyspark.sql import SparkSession
from pyspark.sql.functions import input_file_name


# Custom function for computing a sum.
# Inputs: a and b are values from two different RDD records/tuples.
def customSum(a, b):
    sum = a + b
    return sum


def top5(records):
    sorted_records = sorted(records, key=lambda x: -x[1])
    return sorted_records[:5]


if __name__ == "__main__":
    # Check the number of arguments
    if len(sys.argv) != 2:
        print("Usage: wordcount <file>", file=sys.stderr)
        exit(-1)

    # Set a name for the application
    appName = "PythonWordCount"

    # Set the input folder location to the first argument of the application
    # NB! sys.argv[0] is the path/name of the script file
    input_folder = sys.argv[1]

    # Create a new Spark application and get the Spark session object
    spark = SparkSession.builder.appName(appName).getOrCreate()

    # Get the spark context object.
    sc = spark.sparkContext

    # Load input RDD from the data folder
    lines = spark.read.text(input_folder).select(input_file_name(), "value").rdd.map(tuple)

    # Take 5 records from the RDD and print them out
    records = lines.take(5)
    for record in records:
        print(record)

    # Apply RDD operations to compute WordCount
    # lines RDD contains tuples of (file_name, line) from the input files.
    # Let's split the lines into words and use flatMapValues operation to generate an RDD of words.
    pattern = re.compile('[^a-zA-Z0-9]')
    words = lines.flatMapValues(lambda line: pattern.sub(' ', line).lower().split())

    # Transform words into ((file_name, word), 1) Key & Value tuples
    pairs = words.map(lambda pair: ((pair[0], pair[1].lower()), 1))
    counts = pairs.reduceByKey(customSum)

    # Map the RDD from ((file_name, word), count) to (file_name, (word, count)) and group by file_name
    counts_by_file = counts.map(lambda x: (x[0][0], (x[0][1], x[1]))).groupByKey()

    # Map each file to a list of top 5 words and save the output to a text file
    counts_top5 = counts_by_file.mapValues(lambda x: top5(x))
    counts_top5.saveAsTextFile("outputtest.txt")