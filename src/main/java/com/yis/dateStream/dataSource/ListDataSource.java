package com.yis.dateStream.dataSource;

import com.yis.InitEnv;
import org.apache.flink.api.java.tuple.Tuple2;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author milu
 * @Description 集合数据源
 * @createTime 2019年10月17日 19:17:00
 */
public class ListDataSource {

    public static void main(String[] args) {
        StreamExecutionEnvironment env = InitEnv.getEnv();
        // 集合内的数据类型必须保持一致
        // 从元素集合创建
        DataStreamSource<Tuple2> tupleSource = env.fromElements(new Tuple2(1L, 2L), new Tuple2(1L, 5L), new Tuple2(3L, 12L));
        // 从数组创建
        String[] elements = new String[]{"hello", "flink"};
        DataStreamSource<List<String>> arraySource = env.fromElements(Arrays.asList(elements));
        // 从list创建
        List<String> arrayList = new ArrayList() {{
            add("hello, flink");
        }};
        DataStreamSource<List<String>> listSource = env.fromElements(arrayList);
    }

}
