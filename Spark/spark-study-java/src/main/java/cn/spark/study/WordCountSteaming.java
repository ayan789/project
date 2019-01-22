package cn.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;
import scala.Tuple2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class WordCountSteaming {
    public static void main(String[] args) {
       //SocketStream();
        HdfsStream();
    }

    private static void SocketStream(){
        SparkConf conf = new SparkConf();
        conf.setAppName("WordCountLocal");
        conf.setMaster("local[2]");
        JavaStreamingContext jssc = new JavaStreamingContext(conf,Durations.seconds(1));

        final JavaReceiverInputDStream<String> lines = jssc.socketTextStream("localhost",9999);
        JavaDStream<String> words =  lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String line) {
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(line.split(" ")));
                return list.iterator();
            }
        });

        JavaPairDStream<String,Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word,1);
            }
        });

        JavaPairDStream<String,Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        wordCounts.print();

        jssc.start();
        try {
            jssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jssc.close();
    }

    private static void HdfsStream(){
        SparkConf conf = new SparkConf();
        conf.setAppName("WordCountLocal");
        conf.setMaster("local[2]");
        JavaStreamingContext jssc = new JavaStreamingContext(conf,Durations.seconds(1));

        final JavaDStream<String> lines = jssc.textFileStream("hdfs://localhost:8020/wordcount_dir");
        JavaDStream<String> words =  lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String line) {
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(line.split(" ")));
                return list.iterator();
            }
        });

        JavaPairDStream<String,Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
            public Tuple2<String, Integer> call(String word) throws Exception {
                return new Tuple2<String, Integer>(word,1);
            }
        });

        JavaPairDStream<String,Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer v1, Integer v2) throws Exception {
                return v1 + v2;
            }
        });

        wordCounts.print();

        jssc.start();
        try {
            jssc.awaitTermination();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        jssc.close();
    }
}
