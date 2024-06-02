package com.ustc.kafkamessagedemo.kafkaorigintest;

import org.apache.kafka.clients.admin.Admin;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.CreateTopicsResult;
import org.apache.kafka.clients.admin.NewTopic;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Kafka原生创建topic
 *
 * @author YangyangMiao
 * @email yangyangmiao666@outlook.com
 * @since 2024/6/2 16:18
 */
public class KafkaAdminTest {
    public static void main(String[] args) {

        // 配置对象
        Map<String, Object> configMap = new HashMap<>();
        configMap.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");

        // 创建管理者对象
        final Admin admin = Admin.create(configMap);

        // 创建主题
        // 主题名称
        String topicName1 = "test1";
        // 分区数量
        int partitionCount1 = 1;
        // 副本数量
        short replicationCount1 = 1;

        NewTopic topic1 = new NewTopic(topicName1, partitionCount1, replicationCount1);

        String topicName2 = "test2";
        int partitionCount2 = 2;
        short replicationCount2 = 2;

        NewTopic topic2 = new NewTopic(topicName2, partitionCount2, replicationCount2);

        // 自己分配副本方案(k-v 分区-副本所在的broker id)
        String topicName3 = "test3";
        Map<Integer, List<Integer>> map = new HashMap<>();
        map.put(0, Arrays.asList(3, 1));
        map.put(1, Arrays.asList(2, 3));
        map.put(2, Arrays.asList(1, 2));

        NewTopic topic3 = new NewTopic(topicName3, map);


        final CreateTopicsResult topics = admin.createTopics(Arrays.asList(topic1, topic2, topic3));

        // 关闭管理者对象
        admin.close();

    }
}
