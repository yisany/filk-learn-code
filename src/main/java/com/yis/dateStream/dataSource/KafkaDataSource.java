package com.yis.dateStream;

import com.yis.InitEnv;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.streaming.api.datastream.DataStream;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;

import java.util.Properties;

/**
 * @author milu
 * @Description kafka数据源
 * @createTime 2019年10月17日 19:25:00
 */
public class KafkaDataSource {

    private final static String BOOTSTRAP_SERVERS = "127.0.0.1:9092";
    private final static String GROUP_ID = "flink";
    private final static String CONSUMER_TOPIC = "flink_log";

    public static void main(String[] args) {
        Properties kafkaProps = new Properties();
        kafkaProps.setProperty("bootstrap.servers", BOOTSTRAP_SERVERS);
        kafkaProps.setProperty("group.id", GROUP_ID);

        StreamExecutionEnvironment env = InitEnv.getEnv();
        /**
         * 创建一个消费者
         */
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer(
                CONSUMER_TOPIC,
                new SimpleStringSchema(),
                kafkaProps
        );
        /**
         * 设置一些参数
         */
        // 从该topic的第一条开始消费
        consumer.setStartFromEarliest();
        // 从该groupId的offset开始消费
        consumer.setStartFromGroupOffsets();
        // 自动提交offset
        consumer.setCommitOffsetsOnCheckpoints(true);
        // 添加自定义的 TimestampAndWatermarks 使用EventTime时可以使用
//        consumer.assignTimestampsAndWatermarks(new CustomWaterMarks());
        /**
         * 设置接收数据源
         */
        DataStream<String> kafkaSource = env.addSource(consumer);
    }

}
