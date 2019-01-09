package scala.spark.study

import org.apache.spark.{SparkConf, SparkContext}

object TransformationOperation {

  def main(args: Array[String]): Unit = {
    map()
    //filter()
    //flatMap()
    //groupByKey()
    //reduceByKey()
    //sortByKey()
    //join()
    //cogroup()
  }

  def map(): Unit ={
    val conf = new SparkConf()
      .setAppName("map")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val numbers = Array(1,2,3,4,5,6)
    val numberRDD = sc.parallelize(numbers,1)
    val multipleNumberRDD = numberRDD.map(num => num*2)
    multipleNumberRDD.foreach(num => println(num))
  }

  def filter(): Unit ={
    val conf = new SparkConf()
      .setAppName("filter")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val numbers = Array(1,2,3,4,5,6,7,8,9,10)
    val numberRDD = sc.parallelize(numbers,1)
    val evenNumberRDD = numberRDD.filter(num => num%2 == 0)
    evenNumberRDD.foreach(num => println(num))
  }

  def flatMap(): Unit ={
    val conf = new SparkConf()
      .setAppName("flatMap")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val lineArray = Array("hello you","hello me","hello world")
    val lines = sc.parallelize(lineArray,1)
    val words = lines.flatMap(line => line.split(" "))
    words.foreach(word => println(word))
  }

  def groupByKey(): Unit ={
    val conf = new SparkConf()
      .setAppName("groupByKey")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2("class1",80),Tuple2("class2",75),
      Tuple2("class1",90),Tuple2("class2",60))
    val scores = sc.parallelize(scoreList,1)
    val groupedScores = scores.groupByKey()
    groupedScores.foreach(score => {
      println(score._1)
      score._2.foreach(singleScore => println(singleScore))
      println("========================")
    })
  }

  def reduceByKey(): Unit ={
    val conf = new SparkConf()
      .setAppName("reduceByKey")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2("class1",80),Tuple2("class2",75),
      Tuple2("class1",90),Tuple2("class2",60))
    val scores = sc.parallelize(scoreList,1)
    val totalScore = scores.reduceByKey(_+_)
    totalScore.foreach(classScore => println(classScore._1 + ":" + classScore._2))
  }

  def sortByKey(): Unit ={
    val conf = new SparkConf()
      .setAppName("sortByKey")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val scoreList = Array(Tuple2(80,"tom"),Tuple2(75,"marry"),
      Tuple2(90,"peter"),Tuple2(60,"jack"))
    val scores = sc.parallelize(scoreList,1)
    val totalScore = scores.sortByKey()
    totalScore.foreach(classScore => println(classScore._1 + ":" + classScore._2))
  }

  def join(): Unit ={
    val conf = new SparkConf()
      .setAppName("sortByKey")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val studentList = Array(Tuple2(1, "tom"), Tuple2(2, "marry"),
       Tuple2(3, "peter"),Tuple2(4, "jack"))
    val scoreList = Array(Tuple2(1, 100), Tuple2(2, 90),
      Tuple2(3, 80),Tuple2(4, 70))
    val students = sc.parallelize(studentList,1)
    val scores = sc.parallelize(scoreList,1)
    val studentsScores = students.join(scores)
    studentsScores.foreach(studentScore => {
        println(studentScore._1 + ":" + studentScore._2._1 + "," + studentScore._2._2)
        println("=========")
      }
    )
  }

  def cogroup(): Unit ={
    val conf = new SparkConf()
      .setAppName("sortByKey")
      .setMaster("local")
    val sc = new SparkContext(conf)
    val rdd0 = sc.parallelize(Array((1,1), (1,2) , (1,3) , (2,1) , (2,2) , (2,3)), 3)
    val rdd2 = rdd0.cogroup(rdd0)
    rdd2.foreach(rdd2_ =>  println(rdd2_))
  }



}
