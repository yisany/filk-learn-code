package com.yis.dateStream.transformation.multi;

import com.yis.InitEnv;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.ConnectedStreams;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.api.functions.co.CoMapFunction;

/**
 * @author milu
 * @Description connect
 * @createTime 2019年11月12日 20:06:00
 */
public class ConnectTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<Tuple2<String, Integer>> dataSource1 = env.fromElements(
                new Tuple2<>("a", 3), new Tuple2<>("b", 4), new Tuple2<>("c", 5)
        );
        DataStream<Tuple2<String, Long>> dataSource2 = env.fromElements(
                new Tuple2<>("d", 3L), new Tuple2<>("e", 4L), new Tuple2<>("f", 5L)
        );
        DataStream<Tuple2<String, Double>> dataSource3 = env.fromElements(
                new Tuple2<>("g", 3.0), new Tuple2<>("h", 4.0), new Tuple2<>("i", 5.0)
        );

        ConnectedStreams<Tuple2<String, Integer>, Tuple2<String, Long>> connect = dataSource1.connect(dataSource2);

        connect.map(new CoMapFunction<Tuple2<String, Integer>, Tuple2<String, Long>, String>() {
            // 定义第一个数据集函数处理逻辑, 输出值为第一个DataStream
            @Override
            public String map1(Tuple2<String, Integer> tuple1) throws Exception {
                return String.join("-", tuple1.f0.toString(), tuple1.f1.toString());
            }
            // 定义第二个数据集函数处理逻辑, 输出值为第二个DataStream
            @Override
            public String map2(Tuple2<String, Long> tuple2) throws Exception {
                return String.join("-", tuple2.f0.toString(), tuple2.f1.toString());
            }
        });

        // TODO 留个坑, 有关广播变量 BroadCast
    }

}
