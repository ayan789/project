package scala.spark.study

import org.apache.spark.{SparkConf, SparkContext}

object ActionOperation {
  def main(args: Array[String]): Unit = {
    //reduce()
    //collect()
    //count()
   // take()
    // saveAsTextFile()
    countByKey()
  }

  def reduce(): Unit = {
    val conf = new SparkConf()
      .setAppName("reduce")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers =  sc.parallelize(numberList,1)
    val sum = numbers.reduce(_+_)
    println(sum)
  }

  def collect(): Unit = {
    val conf = new SparkConf()
      .setAppName("collect")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers =  sc.parallelize(numberList,1)
    val doubleNumbers =  numbers.map(number => number * 2)
    val doubleNumberList = doubleNumbers.collect()
    for(num <- doubleNumberList){
      println(num)
    }
  }

  def count(): Unit = {
    val conf = new SparkConf()
      .setAppName("count")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers =  sc.parallelize(numberList,1)
    val count = numbers.count()
    println(count)
  }

  def take(): Unit = {
    val conf = new SparkConf()
      .setAppName("take")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers =  sc.parallelize(numberList,1)
    val top3Number = numbers.take(3)
    for(num <- top3Number){
      println(num)
    }
  }

  def saveAsTextFile(): Unit = {
    val conf = new SparkConf()
      .setAppName("saveAsTextFile")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val numberList = Array(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
    val numbers =  sc.parallelize(numberList,1)
    numbers.saveAsTextFile("hdfs://localhost:8020/double_number1.txt")
  }

  def countByKey(): Unit ={
    val conf = new SparkConf()
      .setAppName("groupByKey")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2("class1","jack"),Tuple2("class2","peter"),
      Tuple2("class1","tom"),Tuple2("class2","marry"),Tuple2("class2","lorry"))
    val scores = sc.parallelize(scoreList,1)
    val groupedScores = scores.countByKey()
    groupedScores.foreach(groupedScore => {
      println(groupedScore._1)
      println(groupedScore._2)
      println("========================")
    })
  }
}
