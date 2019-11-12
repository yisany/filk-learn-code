package com.yis.dateStream.transformation.multi;

import com.yis.InitEnv;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.List;

/**
 * @author milu
 * @Description split
 * @createTime 2019年11月12日 20:19:00
 */
public class SplitTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<Tuple2<String, Integer>> dataSource = env.fromElements(
                new Tuple2<>("a", 3), new Tuple2<>("b", 4), new Tuple2<>("c", 5), new Tuple2<>("d", 6), new Tuple2<>("e", 7)
        );
        // 1.9已经过时
        dataSource.split(tuple2 -> {
            List<String> output = new ArrayList<>();
            output.add(tuple2.f1 % 2 == 0 ? "even" : "odd");
            return output;
        });
    }

}
