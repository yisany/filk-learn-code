package com.yis.dateStream.transformation.single;

import com.yis.InitEnv;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * @author milu
 * @Description filter操作
 * @createTime 2019年10月28日 19:11:00
 */
public class FilterTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<Integer> dataStream = env.fromCollection(Arrays.asList(1, 2, 3, 4, 5, 6));
        DataStream<Integer> filter = dataStream.filter(a -> a > 2);
        filter.print();
    }

}
