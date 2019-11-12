package com.yis.dateStream.transformation.single;

import com.yis.InitEnv;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.KeyedStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author milu
 * @Description reduce
 * @createTime 2019年10月28日 19:36:00
 */
public class ReduceTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<Tuple2<String, Integer>> dataSource = env.fromElements(
                new Tuple2<>("a", 3),
                new Tuple2<>("b", 4),
                new Tuple2<>("c", 5)
        );
        KeyedStream keyedStream = dataSource.keyBy(tuple2 -> tuple2.f0);
        DataStream<Tuple2<String, Integer>> data = keyedStream.reduce((t1, t2) -> {
            Tuple2<String, Integer> tt1 = (Tuple2<String, Integer>) t1;
            Tuple2<String, Integer> tt2 = (Tuple2<String, Integer>) t2;
            return new Tuple2<>(tt1.f0, tt1.f1 + tt2.f1);
        });

        data.print();
    }
}
