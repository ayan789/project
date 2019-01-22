//package cn.spark.study;
//
//import org.apache.commons.codec.StringDecoder;
//import org.apache.kafka.clients.consumer.ConsumerRecord;
//import org.apache.kafka.common.serialization.StringDeserializer;
//import org.apache.spark.SparkConf;
//import org.apache.spark.api.java.function.FlatMapFunction;
//import org.apache.spark.api.java.function.Function2;
//import org.apache.spark.api.java.function.PairFunction;
//import org.apache.spark.streaming.Durations;
//import org.apache.spark.streaming.api.java.*;
//
//import org.apache.spark.streaming.kafka010.ConsumerStrategies;
//import org.apache.spark.streaming.kafka010.KafkaUtils;
//import org.apache.spark.streaming.kafka010.LocationStrategies;
//
//import java.util.*;
//
//public class WordCountKafka {
//    public static void main(String[] args) {
//        SparkConf conf = new SparkConf();
//        conf.setAppName("WordCountKafka");
//        conf.setMaster("local[2]");
//        JavaStreamingContext jssc = new JavaStreamingContext(conf,Durations.seconds(5));
//        Map<String, Object> kafkaParams = new HashMap<String, Object>();
//        kafkaParams.put("bootstrap.servers", "localhost:9092");
//        kafkaParams.put("key.deserializer", StringDeserializer.class);
//        kafkaParams.put("value.deserializer", StringDeserializer.class);
//        kafkaParams.put("group.id", "use_a_separate_group_id_for_each_stream");
//        kafkaParams.put("auto.offset.reset", "latest");
//        kafkaParams.put("enable.auto.commit", false);
//
//        Collection<String> topics = Arrays.asList("test", "test2");
//
//
//        final JavaInputDStream<ConsumerRecord<String, String>> lines = KafkaUtils
//                .createDirectStream(jssc,
//                        LocationStrategies.PreferConsistent(),
//                        ConsumerStrategies.<String, String> Subscribe(topics,
//                                kafkaParams));
//
//        Map<String, String> kafkaParameters = new HashMap<String, String>();
//        kafkaParameters.put("metadata.broker.list", "192.168.179.3:9092,192.168.179.4:9092,192.168.179.5:9092");
//
//        HashSet<String> topics = new HashSet<String>();
//        topics.add("kfk");
//        JavaPairInputDStream<String,String> lines = KafkaUtils.createDirectStream(jssc,String.class,String.class,StringDecoder.class,StringDecoder.class,kafkaParameters,topics);
//

//        topicThreadMap.put("wordCount",1);

        //KafkaUtils.createDirectStream[String,String](ssc, PreferConsistent,Subscribe[String,String](topic,kafkaParams))
//        JavaPairReceiverInputDStream<String,String> lines = KafkaUtils.create(
//                jssc,
//                "localhost:2181",
//                "1",
//                topicThreadMap);

//        JavaDStream<String> words =  lines.flatMap(new FlatMapFunction<Tuple2<String, String>, String>() {
//               public Iterator<String> call(Tuple2<String, String> stringStringTuple2) throws Exception {
//                   ArrayList<String> list = new ArrayList<String>(Arrays.asList(stringStringTuple2._2.split(" ")));
//                   return list.iterator();
//               }
//           });
//
//        JavaPairDStream<String,Integer> pairs = words.mapToPair(new PairFunction<String, String, Integer>() {
//            public Tuple2<String, Integer> call(String word) throws Exception {
//                return new Tuple2<String, Integer>(word,1);
//            }
//        });
//
//        JavaPairDStream<String,Integer> wordCounts = pairs.reduceByKey(new Function2<Integer, Integer, Integer>() {
//            public Integer call(Integer v1, Integer v2) throws Exception {
//                return v1 + v2;
//            }
//        });
//
//        wordCounts.print();
//
//        jssc.start();
//        try {
//            jssc.awaitTermination();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        jssc.close();
//    }
//}
