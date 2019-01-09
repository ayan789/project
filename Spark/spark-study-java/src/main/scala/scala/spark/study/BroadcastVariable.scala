package scala.spark.study

import org.apache.spark.{SparkConf, SparkContext}

object BroadcastVariable {
  def main(args: Array[String]): Unit = {
    val conf = new SparkConf()
      .setAppName("collect")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val foctor = 2
    val foctorBroadcast = sc.broadcast(foctor)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers =  sc.parallelize(numberList,1)
    val doubleNumbers =  numbers.map(number => number * foctorBroadcast.value)
    val doubleNumberList = doubleNumbers.collect()
    for(num <- doubleNumberList){
      println(num)
    }
  }
}
