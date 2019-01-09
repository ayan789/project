package cn.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.Function2;
import scala.Tuple2;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class ActionOperation {
    public static void main(String[] args) {
        //reduce();
        //collect();
       // count();
       // take();
        // saveAsTextFile();
        countByKey();
    }

    private static void reduce(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("reduce")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //构造集合(对这个集合进行累加)
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //构建初始RDD
        JavaRDD<Integer> numbers = sc.parallelize(numberList,1);
        int sum = numbers.reduce(new Function2<Integer, Integer, Integer>() {
            public Integer call(Integer integer, Integer integer2) throws Exception {
                return integer + integer2;
            }
        });
        System.out.println(sum);
        sc.close();
    }

    //collect,不建议使用，远程集群的数据拉取到本地执行
    //建议使用foreach，在远程集群上遍历RDD中的元素
    private static void collect() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("collect")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //构造集合
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //构建初始RDD
        JavaRDD<Integer> numbers = sc.parallelize(numberList,1);
        JavaRDD<String> multipleNumberRDD = numbers.map(new Function<Integer, String>() {
            public String call(Integer integer) throws Exception {
                return (integer*2)+"";
            }
        });
        List<String> doubleNumberList = multipleNumberRDD.collect();
        for(String num : doubleNumberList){
            System.out.println(num);
        }
        sc.close();
    }

    private static void count() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("count")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //构造集合
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //构建初始RDD
        JavaRDD<Integer> numbers = sc.parallelize(numberList,1);
        long num = numbers.count();
        System.out.println(num);
        sc.close();
    }

    private static void take() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("take")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //构造集合
        List<Integer> numberList = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //构建初始RDD
        JavaRDD<Integer> numbers = sc.parallelize(numberList,1);
        List<Integer> top3Numbers = numbers.take(3);
        for(Integer num : top3Numbers){
            System.out.println(num);
        }
        sc.close();
    }

    private static void saveAsTextFile() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("saveAsTextFile")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //构造集合
        List<Integer> numberList = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //构建初始RDD
        JavaRDD<Integer> numbers = sc.parallelize(numberList,1);
        JavaRDD<String> multipleNumberRDD = numbers.map(new Function<Integer, String>() {
            public String call(Integer integer) throws Exception {
                return (integer*2)+"";
            }
        });
        //直接将本地RDD保存到远程HDFS文件中
        multipleNumberRDD.saveAsTextFile("hdfs://localhost:8020/double_number.txt");
        sc.close();
    }

    private static void countByKey() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("countByKey")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //模拟集合
        List<Tuple2<String, String>> studentList = Arrays.asList(
                new Tuple2<String, String>("class1", "jack"),
                new Tuple2<String, String>("class2", "marry"),
                new Tuple2<String, String>("class1", "peter"),
                new Tuple2<String, String>("class2", "tom"),
                new Tuple2<String, String>("class2", "lorry"));
        //并行化集合，创造JavaPairRDD
        JavaPairRDD<String,String> students = sc.parallelizePairs(studentList);
        Map<String, Long> studentCounts = students.countByKey();
        for(Map.Entry studentCount : studentCounts.entrySet()){
            System.out.println(studentCount.getKey() + ":" + studentCount.getValue());
        }

    }
}
