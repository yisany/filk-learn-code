package com.yis.dateStream.transformation.multi;

import com.yis.InitEnv;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author milu
 * @Description union
 * @createTime 2019年11月12日 20:03:00
 */
public class UnionTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<Tuple2<String, Integer>> dataSource1 = env.fromElements(
                new Tuple2<>("a", 3), new Tuple2<>("b", 4), new Tuple2<>("c", 5)
        );
        DataStream<Tuple2<String, Integer>> dataSource2 = env.fromElements(
                new Tuple2<>("d", 3), new Tuple2<>("e", 4), new Tuple2<>("f", 5)
        );
        DataStream<Tuple2<String, Integer>> dataSource3 = env.fromElements(
                new Tuple2<>("g", 3), new Tuple2<>("h", 4), new Tuple2<>("i", 5)
        );

        DataStream<Tuple2<String, Integer>> union = dataSource1.union(dataSource2).union(dataSource3);
    }

}
