import sys
from pyspark.sql import SparkSession
import pyspark.sql.functions as sparkFun


if __name__ == "__main__":

    #check the number of arguments
    if len(sys.argv) != 2:
        print("Usage: dataframe_example.py <input folder> ")
        exit(-1)

    #Set a name for the application
    appName = "DataFrame Avg Humidity"

    #Set the input folder location to the first argument of the application
    #NB! sys.argv[0] is the path/name of the script file
    input_folder = sys.argv[1]

    #create a new Spark application and get the Spark session object
    spark = SparkSession.builder.appName(appName).getOrCreate()

    #read in the CSV dataset as a DataFrame
    #inferSchema option forces Spark to automatically specify data column types
    #header option forces Spark to automatically fetch column names from the first line in the dataset files
    dataset = spark.read \
                  .option("inferSchema", True) \
                  .option("header", True) \
                  .csv(input_folder)



    dataset = dataset.filter('`Air Temperature` > 20')
    dataset = dataset.groupBy("Station Name").agg(sparkFun.avg("Humidity"))
    dataset = dataset.select("Station Name", "avg(Humidity)")

    dataset.show(10, False)

    dataset.coalesce(1).write.format("csv") \
        .option("header", True) \
        .option("compression", "gzip") \
        .save("output")