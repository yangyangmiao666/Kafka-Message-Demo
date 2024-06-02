package com.ustc.kafkamessagedemo.kafkaorigintest;

import com.ustc.kafkamessagedemo.intercept.MyProducerInterceptor;
import com.ustc.kafkamessagedemo.partitioner.MyKafkaPartitioner;
import org.apache.kafka.clients.producer.*;

import java.util.HashMap;
import java.util.Map;


/**
 * kafka生产者测试
 *
 * @author YangyangMiao
 * @email yangyangmiao666@outlook.com
 * @since 2024/5/30 11:20
 */

public class KafkaProducerTest {
    public static void main(String[] args) {
        // 属性集合
        Map<String, Object> configMap = new HashMap<>();
        // 配置属性：Kafka服务器集群地址
        configMap.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        // 配置属性：Kafka生产的数据为KV对，所以在生产数据进行传输前需要分别对K,V进行对应的序列化操作
        configMap.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        configMap.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        // 自定义拦截器
        configMap.put(ProducerConfig.INTERCEPTOR_CLASSES_CONFIG, MyProducerInterceptor.class.getName());
        // 自定义分区
        configMap.put( ProducerConfig.PARTITIONER_CLASS_CONFIG, MyKafkaPartitioner.class.getName());

        // 创建Kafka生产者对象，建立Kafka连接
        // 构造对象时，需要传递配置参数
        KafkaProducer<String, String> producer = new KafkaProducer<>(configMap);
        // 准备数据,定义泛型
        // 构造对象时需要传递 【Topic主题名称】，【Key】，【Value】三个参数
        ProducerRecord<String, String> record = new ProducerRecord<>("test3", "key3", "value3");
        // 生产（发送）数据 发送成功后回调函数
        producer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                System.out.println("数据发送成功：" + recordMetadata.timestamp());
            }
        });
        // 关闭生产者连接
        producer.close();
    }
}

