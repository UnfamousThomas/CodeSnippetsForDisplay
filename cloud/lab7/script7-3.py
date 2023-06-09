import sys
import re
from pyspark.sql import SparkSession

# Custom function for computing a sum.
# Inputs: a and b are values from two different RDD records/tuples.
def customSum(a, b):
    sum = a + b

    return sum


if __name__ == "__main__":
    # Check the number of arguments
    if len(sys.argv) != 2:
        print("Usage: wordcount <file>", file=sys.stderr)
        exit(-1)

    # Set a name for the application
    appName = "PythonWordCount"

    # Set the input folder location to the firsta rgument of the application
    # NB! sys.argv[0] is the path/name of the script file
    input_folder = sys.argv[1]

    # Create a new Spark application and get the Spark session object
    spark = SparkSession.builder.appName(appName).getOrCreate()

    # Get the spark context object.
    sc = spark.sparkContext

    # Load input RDD from the data folder
    lines = sc.textFile(input_folder)

    # Take 5 records from the RDD and print them out
    records = lines.take(5)
    for record in records:
        print(record)

    # Apply RDD operations to compute WordCount
    # lines RDD contains lines from the input files.
    # Let's split the lines into words and use flatMap operation to generate an RDD of words.
    pattern = re.compile('[^a-zA-Z0-9]')

    words = lines.flatMap(lambda line: pattern.sub(' ', line).lower().split())

    # Transform words into (word, 1) Key & Value tuples
    pairs = words.map(lambda word: (word, 1))

    # Apply reduceBy key to group pairs by key/word and apply sum operation on the list of values inside each group
    # Apply our of customSum function as the aggregation function,
    # but we could also have used "lambda x,y: x+y" function
    counts = pairs.reduceByKey(customSum)

    # Read the data out of counts RDD
    # Output is a Python list (of (key, value) tuples)
    output = counts.collect()

    # Print each key and value tuple inside output list

    with open("output7-3.txt", "w") as f:
        for (word, count) in output:
            f.write(word + " " + str(count) + "\n")
            print(word, count)

    # Stop Spark session. It is not required when running locally through PyCharm.
    #spark.sparkContext.stop()
    #spark.stop()