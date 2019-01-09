package cn.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import java.util.Arrays;
import java.util.Iterator;

public class SortWordCount {
    public static void main(String[] args) {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("SortWordCount")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //创造初始RDD
        JavaRDD<String> lines = sc.textFile("hdfs://localhost:8020/words.txt");
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) {
                return Arrays.asList(s.split(" ")).iterator();
            }
        });
        JavaPairRDD<String, Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String s) {
                return new Tuple2<String, Integer>(s,1);
            }
        });
        JavaPairRDD<String, Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer+integer2;
            }
        });
        JavaPairRDD<Integer,String> countWords = wordCounts.mapToPair(new PairFunction<Tuple2<String,Integer>, Integer, String>() {
            public Tuple2<Integer, String> call(Tuple2<String, Integer> stringIntegerTuple2) {
                return new Tuple2<Integer, String>(stringIntegerTuple2._2,stringIntegerTuple2._1);
            }
        });
        JavaPairRDD<Integer,String> sortCountWords = countWords.sortByKey(false);
        JavaPairRDD<String,Integer> sortWordCounts = sortCountWords.mapToPair(new PairFunction<Tuple2<Integer,String>, String,Integer>() {
            public Tuple2<String,Integer> call(Tuple2<Integer,String> stringIntegerTuple2) {
                return new Tuple2<String,Integer>(stringIntegerTuple2._2,stringIntegerTuple2._1);
            }
        });
        sortWordCounts.foreach(new VoidFunction<Tuple2<String, Integer>>() {
            public void call(Tuple2<String, Integer> stringIntegerTuple2) throws Exception {
                System.out.println(stringIntegerTuple2._1 + ":" + stringIntegerTuple2._2);
            }
        });

    }
}
