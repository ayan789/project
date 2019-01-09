package cn.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import org.apache.spark.broadcast.Broadcast;


import java.util.Arrays;
import java.util.List;

/*
广播变量
 */
public class BroadcastVariable {
    public static void main(String[] args) {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("BroadcastVariable")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        final int factor = 2;
        //定义广播变量
        final Broadcast<Integer> factorBroadcast = sc.broadcast(factor);
        //模拟集合
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //并行化集合，创建初始RDD
        JavaRDD<Integer> numbersRDD = sc.parallelize(numbers);
        JavaRDD<Integer> doubleNumbersRDD = numbersRDD.map(new Function<Integer, Integer>() {
            public Integer call(Integer integer) {
                return integer * factorBroadcast.getValue(); //使用广播变量
            }
        });
        doubleNumbersRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
        sc.close();
    }
}
