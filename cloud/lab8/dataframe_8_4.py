import sys
from pyspark.sql import SparkSession
import pyspark.sql.functions as sparkFun


if __name__ == "__main__":

    #check the number of arguments
    if len(sys.argv) != 2:
        print("Usage: dataframe_example.py <input folder> ")
        exit(-1)

    #Set a name for the application
    appName = "DataFrame Solar"

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


    dataset = dataset.select("Station Name", "Solar Radiation", sparkFun.date_format(sparkFun.to_date("Measurement Timestamp", "dd/MM/yyyy hh:mm:ss"), "dd/MM/yyyy").alias("date"))
    dataset = dataset.groupBy("date", "Station Name")\
        .agg(sparkFun.avg("Solar Radiation"),
             sparkFun.min("Solar Radiation"),
             sparkFun.max("Solar Radiation"))
    dataset = dataset.withColumnRenamed('avg(Solar Radiation)', "Average Solar")
    dataset = dataset.withColumnRenamed('min(Solar Radiation)', "Minimum Solar")
    dataset = dataset.withColumnRenamed('max(Solar Radiation)', "Maximum Solar")

    dataset = dataset.orderBy("Average Solar", ascending=False)

    dataset.show(10, False)

    dataset.coalesce(1).write.format("csv") \
        .option("header", True) \
        .option("compression", "gzip") \
        .save("output2")

    # Answer to the question: The Highest average solar value was in the Foster Weather Station on 08/11/2020. The value was ~504.15384.