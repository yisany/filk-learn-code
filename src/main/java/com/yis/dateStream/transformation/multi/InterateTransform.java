package com.yis.dateStream.transformation.multi;

import com.yis.InitEnv;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.IterativeStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author milu
 * @Description interate
 * @createTime 2019年11月12日 20:33:00
 */
public class InterateTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStreamSource<Integer> dataSource = env.fromElements(3, 1, 2, 1, 5);
        // 开启iterate
        IterativeStream<Integer> iterate = dataSource.iterate();
        DataStream<Integer> iterationBody = iterate.map(i -> i + 1);
        DataStream<Integer> feedback = iterationBody.filter(value -> value > 0);
        // 关闭iterate
        iterate.closeWith(feedback);
        DataStream<Integer> output = iterationBody.filter(value -> value <= 0);

    }

}
