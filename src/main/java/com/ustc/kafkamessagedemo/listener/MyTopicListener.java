package com.ustc.kafkamessagedemo.listener;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.PartitionOffset;
import org.springframework.kafka.annotation.TopicPartition;
import org.springframework.stereotype.Component;

/**
 * @author :Yangyang Miao
 * @date :2023-07-30 17:51:00
 */
@Component
public class MyTopicListener {

    // 默认的监听是从消息队列最后一个消息，新消息才能拿到
    @KafkaListener(topics = "news", groupId = "haha")
    public void haha01(ConsumerRecord record) {
        // 1.获取消息的各种详细信息
        // String topic = record.topic();

        Object key = record.key();
        Object value = record.value();

        System.out.println("收到消息 key:" + key + " value:" + value);
    }

    // 拿到以前完整的消息
    @KafkaListener(id = "thing2", topicPartitions =
            {@TopicPartition(topic = "topic1", partitions = {"0", "1"}),
                    @TopicPartition(topic = "topic2", partitions = "0",
                            partitionOffsets = @PartitionOffset(partition = "1", initialOffset = "100"))
            })
    public void haha02(ConsumerRecord record) {
        // 1.获取消息的各种详细信息
        // String topic = record.topic();

        Object key = record.key();
        Object value = record.value();

        System.out.println("收到消息 key:" + key + " value:" + value);
    }
}
