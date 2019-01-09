package scala.spark.study

import org.apache.spark.SparkConf
import org.apache.spark.sql.SparkSession

object TestHive {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("map")
      .setMaster("local")
    val spark = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
    spark.sql("show databases").collect().foreach(println)
  }
}
