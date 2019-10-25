package com.yis.dateStream.dataSource;

import com.yis.entity.CsvPOJO;
import com.yis.InitEnv;
import org.apache.flink.api.java.ExecutionEnvironment;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;

import javax.activation.DataSource;

/**
 * @author milu
 * @Description 文件数据源
 * @createTime 2019年10月17日 19:09:00
 */
public class FileDataSource {

    private final static String TEXT_PATH = "/usr/local/data.log";
    private final static String CSV_PATH = "/usr/local/csv_data.log";

    public static void main(String[] args) {
        // 读取文本文件
        StreamExecutionEnvironment env = InitEnv.getEnv();
        DataStreamSource<String> source = env.readTextFile(TEXT_PATH);
        // 读取csv格式文件 -> 注意这里是Set, 不是Stream
        DataSource csvSource = (DataSource) ExecutionEnvironment.getExecutionEnvironment()
                .readCsvFile("/usr/local/csv_data.csv")
                .pojoType(CsvPOJO.class, "id", "name", "message");
    }

}
