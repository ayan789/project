package cn.spark.study;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaSparkContext;

import org.apache.spark.sql.SparkSession;
import org.apache.spark.sql.hive.HiveContext;

public class HiveDataSource {
    public static void main(String[] args) {
        Hdfs2Hive();
    }

   private static void Hdfs2Hive(){
       //创建SparkConf
       SparkConf conf = new SparkConf()
               .setAppName("HiveDataSource")
               .setMaster("local");
       //创建SparkSession
       SparkSession sparkSession = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate();
//       sparkSession.sql("CREATE TABLE IF NOT EXISTS students_info2 (name STRING, age INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY '&'");
//       sparkSession.sql("LOAD DATA INPATH 'hdfs://localhost:8020/students_info.txt' INTO TABLE students_info2");
//
//       sparkSession.sql("CREATE TABLE IF NOT EXISTS students_score2 (name STRING, score INT) ROW FORMAT DELIMITED FIELDS TERMINATED BY '&'");
//       sparkSession.sql("LOAD DATA INPATH 'hdfs://localhost:8020/students_score.txt' INTO TABLE students_score2");

       sparkSession.sql("insert overwrite  directory 'hdfs://localhost:8020/good_student_x/' select si.name,si.age,ss.score from students_info2 si left join students_score2 ss on si.name=ss.name where ss.score>70 FIELDS TERMINATED BY '---'");


   }

    private static void loadHiveTable(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("HiveDataSource")
                .setMaster("local");
        //创建JavaSparkContext
        JavaSparkContext sc = new JavaSparkContext(conf);
        //创建HiveContext
        //不要使用SQLContext,部署异常找不到数据库和表
        HiveContext hiveContext = new HiveContext(sc);
        //查询表前10条数据
        hiveContext.sql("select * from hive_test.student").show();
        sc.stop();
    }



    private static void showHiveTable2(){
        //创建SparkConf
        SparkConf conf = new SparkConf()
                .setAppName("HiveDataSource")
                .setMaster("local");


        SparkSession sparkSession = SparkSession.builder().config(conf).enableHiveSupport().getOrCreate();

//        sparkSession.sql("use abc_hive_db");

//         sparkSession.sql("select * from good_student").write().mode(SaveMode.Append).save("hdfs://localhost:8020/goodStu2.txt");
                 //.saveAsTable("good_stu");

        sparkSession.sql("insert overwrite  directory 'hdfs://localhost:8020/good_student2/' select * from good_student");

    }





}
