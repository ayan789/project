package scala.spark.study

import org.apache.spark.{SparkConf, SparkContext}

object SortWordCount {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("SortWordCount")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val lines = sc.textFile("hdfs://localhost:8020/words.txt")
    val words = lines.flatMap(line => line.split(" "))
    val pairs = words.map(word => Tuple2(word,1))
  }
}
