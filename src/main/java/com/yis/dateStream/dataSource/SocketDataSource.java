package com.yis.dateStream.dataSource;

import com.yis.InitEnv;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * @author milu
 * @Description socket数据源
 * @createTime 2019年10月17日 19:14:00
 */
public class SocketDataSource {

    public static void main(String[] args) {
        /**
         * 可以额外传入两个参数:
         * delimiter: 将数据切割成Records数据格式
         * maxRetry: 端口异常情况下, 指定重连次数
         */
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStreamSource<String> source = env.socketTextStream("localhost", 9999);
    }

}
