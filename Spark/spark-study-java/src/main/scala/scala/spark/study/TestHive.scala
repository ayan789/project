package scala.spark.study

import org.apache.spark.{SparkConf, SparkContext}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.hive.HiveContext

object TestHive {
  def main(args: Array[String]): Unit = {
    //connTest()
   // saveTest()
    loadTest()
  }

  def connTest(): Unit ={
    val conf = new SparkConf()
      .setAppName("connTest")
      .setMaster("local")
    val spark = SparkSession.builder().enableHiveSupport().config(conf).getOrCreate()
    spark.sql("show databases").collect().foreach(println)
  }

  def saveTest(): Unit ={
    val conf = new SparkConf()
      .setAppName("saveTest")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val sqlContext = new HiveContext(sc)
    sqlContext.sql("CREATE TABLE IF NOT EXISTS src3 (key INT, value STRING) ROW FORMAT DELIMITED FIELDS TERMINATED BY '&'")   //这里需要注意数据的间隔符
    sqlContext.sql("LOAD DATA INPATH 'hdfs://localhost:8020/kv3.txt' INTO TABLE src3")
    sc.stop()
  }

  def loadTest(): Unit ={
    val conf = new SparkConf()
      .setAppName("loadTest")
      .setMaster("local")
    val sc=new SparkContext(conf)
    val hiveContext = new HiveContext(sc)

    hiveContext.sql("CREATE TABLE IF NOT EXISTS students_info (name STRING, age INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY '&'")
    hiveContext.sql("LOAD DATA INPATH 'hdfs://localhost:8020/students_info.txt' INTO TABLE students_info")

    hiveContext.sql("CREATE TABLE IF NOT EXISTS students_score (name STRING, score INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY '&'")
    hiveContext.sql("LOAD DATA INPATH 'hdfs://localhost:8020/students_score.txt' INTO TABLE students_score")



  }



}
