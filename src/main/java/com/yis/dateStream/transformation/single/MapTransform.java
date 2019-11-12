package com.yis.dateStream.transformation.single;

import com.yis.InitEnv;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author milu
 * @Description map转换
 * @createTime 2019年10月21日 19:31:00
 */
public class MapTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream mapSource = env.fromElements(
                new Tuple2<>("a", 3),
                new Tuple2<>("b", 4),
                new Tuple2<>("c", 5)
        );
        DataStream<Tuple2> mapStream = mapSource.map(
                (MapFunction<Tuple2<String, Integer>, Tuple2<String, Integer>>) t -> new Tuple2<>(t.f0, t.f1 + 1)
        );

        mapStream.print();
    }

}
