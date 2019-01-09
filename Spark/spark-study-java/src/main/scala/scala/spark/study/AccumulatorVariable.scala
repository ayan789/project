package scala.spark.study

import org.apache.spark.{SparkConf, SparkContext}

object AccumulatorVariable {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("AccumulatorVariable")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val sum = sc.accumulator(0)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers =  sc.parallelize(numberList,1)
    numbers.foreach(num => sum.add(num))
    println(sum)
  }
}
