package com.yis.dateStream.transformation.single;

import com.yis.InitEnv;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author milu
 * @Description keyby操作
 * @createTime 2019年10月28日 19:22:00
 */
public class KeyByTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<Tuple2<String, Integer>> dataSource = env.fromElements(
                new Tuple2<>("a", 3),
                new Tuple2<>("b", 4),
                new Tuple2<>("c", 5)
        );
        KeyedStream keyedStream = dataSource.keyBy(tuple2 -> tuple2.f0);
    }

}
