package scala.spark.study

import org.apache.spark.{SparkConf}
import org.apache.spark.streaming.{Seconds, StreamingContext}


object WordCountSteaming {
  def main(args: Array[String]): Unit = {
    //SocketStream()
    HdfsStream()
  }

  def SocketStream(): Unit ={
    val conf = new SparkConf()
      .setAppName("WordCountSteaming")
      .setMaster("local[2]")
    //    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(conf,Seconds(1))
    val lines = ssc.socketTextStream("localhost",9999)
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word,1))
    val wordCount = pairs.reduceByKey(_ + _)
    wordCount.print()
    ssc.start()
    ssc.awaitTermination()
  }

  def HdfsStream(): Unit ={
    val conf = new SparkConf()
      .setAppName("WordCountSteaming")
      .setMaster("local[2]")
    //    val sc = new SparkContext(conf)
    val ssc = new StreamingContext(conf,Seconds(1))
    val lines = ssc.textFileStream("hdfs://localhost:8020/wordcount_dir")
    val words = lines.flatMap(_.split(" "))
    val pairs = words.map(word => (word,1))
    val wordCount = pairs.reduceByKey(_ + _)
    wordCount.print()
    ssc.start()
    ssc.awaitTermination()
  }

}
