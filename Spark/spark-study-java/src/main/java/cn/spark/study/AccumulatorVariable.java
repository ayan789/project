package cn.spark.study;

import org.apache.spark.Accumulator;
import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.VoidFunction;
import java.util.Arrays;
import java.util.List;

public class AccumulatorVariable {
    public static void main(String[] args) {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("BroadcastVariable")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //定义递增变量
        final Accumulator<Integer> sum = sc.accumulator(0);
        //模拟集合
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //并行化集合，创建初始RDD
        JavaRDD<Integer> numbersRDD = sc.parallelize(numbers);
        numbersRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                sum.add(integer);//使用递增变量
            }
        });
        System.out.println(sum);
        sc.close();
    }
}
