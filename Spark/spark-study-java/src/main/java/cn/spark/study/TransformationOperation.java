package cn.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function;
import org.apache.spark.api.java.function.VoidFunction;
import scala.Tuple2;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class TransformationOperation {
    public static void main(String[] args) {
        // map();
        // filter();
        // flatMap();
        // groupByKey();
        // sortByKey();
        // join();
        cogroup();
    }

    private static void map(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("map")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //构造集合
        List<Integer> numbers = Arrays.asList(1,2,3,4,5);
        //并行化集合，创造初始RDD
        JavaRDD<Integer> numberRDD = sc.parallelize(numbers);
        //使用map算子，将集合中的每个元素乘以2
        //map算子，是对任何类型的RDD都可以调用的
        //在java中，map算子接收的参数是Function对象
        //创建的Function对象，一定会让你设置第二个泛型类型，就是返回的新元素的类型
        JavaRDD<String> multipleNumberRDD = numberRDD.map(new Function<Integer, String>() {
            public String call(Integer integer) throws Exception {
                return (integer*2)+"";
            }
        });
        //遍历打印新的RDD
        multipleNumberRDD.foreach(new VoidFunction<String>() {
            public void call(String str) throws Exception {
                System.out.println(str);
            }
        });
        //关闭JavaSparkContext
        sc.close();
    }
    /*
    filter算子：过滤集合中的偶数
     */
    private static void filter(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("filter")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //模拟集合
        List<Integer> numbers = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        //并行化集合，创建初始RDD
        JavaRDD<Integer> numbersRDD = sc.parallelize(numbers);
        //对初始RDD执行filter算子，过滤出其中的偶数
        //filter算子，传入的也是function，其中的使用注意点，实际上和map是一样的
        //每一个初始RDD中的元素，都会传入call（）方法，此时你可以执行自定义的计算逻辑
        //来判断这个元素是否是你想要的
        //如果你在新的RDD中保留这个元素，那么就返回true，反之返回false
        JavaRDD<Integer> evenNumberRDD =  numbersRDD.filter(new Function<Integer, Boolean>() {
            public Boolean call(Integer integer) throws Exception {
                return integer%2==0;
            }
        });
        //遍历打印新的RDD
        evenNumberRDD.foreach(new VoidFunction<Integer>() {
            public void call(Integer integer) throws Exception {
                System.out.println(integer);
            }
        });
        //关闭JavaSparkContext
        sc.close();
    }

    /*
    flatMap，将文本行拆为多个单词
     */
    private static void flatMap(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("filter")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //构建集合
        List<String> lineList = Arrays.asList("hello hu","hello meng","hello die");
        //并行化集合，创建RDD
        JavaRDD<String> lines = sc.parallelize(lineList);
        /* 对RDD执行flatMap算子，将每行文本拆分为多个单词
        flatMap算子，在java中，接收的参数是FlatMapFunction
        我们需要自定义FlatMapFunction的第二个泛型参数，级：代表了返回的新元素的类型
        call（）方法，返回的类型，不是U，而是Iterable<U>这里的U也与第二个泛型类型相同
        flatMap其实就是，接收原始RDD中的每个元素，并进行各种逻辑计算和处理，返回可以返回多个元素
        多个元素，即封装在Iterable集合中，可以使用ArrayList等集合*/
        JavaRDD<String> words = lines.flatMap(new FlatMapFunction<String, String>() {
            public Iterator<String> call(String s) throws Exception {
                ArrayList<String> list = new ArrayList<String>(Arrays.asList(s.split(" ")));
                return list.iterator();
            }
        });
        //遍历打印新的RDD
        words.foreach(new VoidFunction<String>() {
            public void call(String s) throws Exception {
                System.out.println(s);
            }
        });
        //关闭JavaSparkContext
        sc.close();
    }

    /*
    groupByKey，按照班级进行成绩分组
     */
    private static void groupByKey(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("groupByKey")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //模拟集合
        List<Tuple2<String,Integer>> scoreList = Arrays.asList(
                new Tuple2<String, Integer>("class1",80),
                new Tuple2<String, Integer>("class2",75),
                new Tuple2<String, Integer>("class1",90),
                new Tuple2<String, Integer>("class2",65));
        //并行化集合，创造JavaPairRDD
        JavaPairRDD<String,Integer> scores = sc.parallelizePairs(scoreList);
        //针对scores RDD,执行groupByKey算子，对每个班级的成绩进行分组
        //groupByKey算子，返回的还是JavaPairRDD
        JavaPairRDD<String,Iterable<Integer>> groupedScore =  scores.groupByKey();
        //遍历打印新的RDD
        groupedScore.foreach(new VoidFunction<Tuple2<String, Iterable<Integer>>>() {
            public void call(Tuple2<String, Iterable<Integer>> stringIterableTuple2) throws Exception {
                System.out.println("class:" + stringIterableTuple2._1);
                Iterator<Integer> ite = stringIterableTuple2._2.iterator();
                while(ite.hasNext()){
                    System.out.println(ite.next());
                }
                System.out.println("=======================");
            }
        });
        //关闭JavaSparkContext
        sc.close();
    }

    /*
   sortByKey 按照分数进行排序
    */
    private static void sortByKey() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("sortByKey")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //模拟集合
        List<Tuple2<Integer, String>> scoreList = Arrays.asList(
                new Tuple2<Integer, String>(80, "tom"),
                new Tuple2<Integer, String>(75, "marry"),
                new Tuple2<Integer, String>(90, "peter"),
                new Tuple2<Integer, String>(65, "jack"));
        //并行化集合，创造JavaPairRDD
        JavaPairRDD<Integer,String> scores = sc.parallelizePairs(scoreList);
        //对scores RDD进行sortByKey算子
        JavaPairRDD<Integer,String> sortedScores = scores.sortByKey();
        //打印sortedScores RDD
        sortedScores.foreach(new VoidFunction<Tuple2<Integer, String>>() {
            public void call(Tuple2<Integer, String> integerStringTuple2) throws Exception {
                System.out.println(integerStringTuple2._1 + ":" + integerStringTuple2._2);
            }
        });
    }

    /*
    join,关联RDD
   */
    private static void join() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("sortByKey")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //模拟集合
        List<Tuple2<Integer, String>> studentList = Arrays.asList(
                new Tuple2<Integer, String>(1, "tom"),
                new Tuple2<Integer, String>(2, "marry"),
                new Tuple2<Integer, String>(3, "peter"),
                new Tuple2<Integer, String>(4, "jack"));
        List<Tuple2<Integer, Integer>> scoreList = Arrays.asList(
                new Tuple2<Integer, Integer>(1, 100),
                new Tuple2<Integer, Integer>(2, 90),
                new Tuple2<Integer, Integer>(3, 80),
                new Tuple2<Integer, Integer>(5, 80),
                new Tuple2<Integer, Integer>(6, 80),
                new Tuple2<Integer, Integer>(7, 80),
                new Tuple2<Integer, Integer>(4, 70));

        //并行化集合，创造JavaPairRDD
        JavaPairRDD<Integer,String> students = sc.parallelizePairs(studentList);
        JavaPairRDD<Integer,Integer> scores = sc.parallelizePairs(scoreList);
        //使用join算子关联两个RDD
        JavaPairRDD<Integer,Tuple2<String,Integer>> studentsScores = students.join(scores);
        //打印studentsScores RDD
        studentsScores.foreach(new VoidFunction<Tuple2<Integer, Tuple2<String, Integer>>>() {
            public void call(Tuple2<Integer, Tuple2<String, Integer>> integerTuple2Tuple2) throws Exception {
                System.out.println("id:" + integerTuple2Tuple2._1);
                System.out.println("name:" + integerTuple2Tuple2._2._1);
                System.out.println("name:" + integerTuple2Tuple2._2._2);
                System.out.println("============================");
            }
        });
    }

    private static void cogroup() {
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("sortByKey")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //模拟集合
        List<Tuple2<Integer, Integer>> studentList = Arrays.asList(
                new Tuple2<Integer, Integer>(1, 1),
                new Tuple2<Integer, Integer>(2, 2),
                new Tuple2<Integer, Integer>(3, 3),
                new Tuple2<Integer, Integer>(2, 1),
                new Tuple2<Integer, Integer>(2, 2),
                new Tuple2<Integer, Integer>(2, 3));
        //并行化集合，创造JavaPairRDD
        JavaPairRDD<Integer,Integer> students = sc.parallelizePairs(studentList);
        JavaPairRDD studentsScores = students.cogroup(students);
        studentsScores.foreach(new VoidFunction() {
            public void call(Object o) throws Exception {
                System.out.println("id:" + o.toString());
            }
        });
    }



}
