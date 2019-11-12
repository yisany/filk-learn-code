package com.yis.dateStream.transformation.multi;

import com.yis.InitEnv;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.SplitStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author milu
 * @Description select
 * @createTime 2019年11月12日 20:30:00
 */
public class SelectTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<Tuple2<String, Integer>> dataSource = env.fromElements(
                new Tuple2<>("a", 3), new Tuple2<>("b", 4), new Tuple2<>("c", 5), new Tuple2<>("d", 6), new Tuple2<>("e", 7)
        );
        // 1.9已经过时
        SplitStream<Tuple2<String, Integer>> split = dataSource.split(tuple2 -> {
            List<String> output = new ArrayList<>();
            output.add(tuple2.f1 % 2 == 0 ? "even" : "odd");
            return output;
        });

        DataStream<Tuple2<String, Integer>> even = split.select("even");
        DataStream<Tuple2<String, Integer>> odd = split.select("odd");
        DataStream<Tuple2<String, Integer>> select = split.select("even", "odd");
    }

}
