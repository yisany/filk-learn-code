package com.yis;

import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

/**
 * Hello world!
 */
public class InitEnv {
    public static StreamExecutionEnvironment getEnv() {
        StreamExecutionEnvironment env = StreamExecutionEnvironment.getExecutionEnvironment();
        return env;
    }
}
