package com.yis.dateStream.transformation.single;

import com.yis.InitEnv;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.Arrays;

/**
 * @author milu
 * @Description flatmap转换
 * @createTime 2019年10月21日 19:40:00
 */
public class FlatMapTransform {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStream<String> source = env.fromElements("abc_bcd_cde_def_efg");
        DataStream<Object> flatMapStream = source.flatMap((s, c) -> c.collect(s.split("_")));
        flatMapStream.print();
    }

}
