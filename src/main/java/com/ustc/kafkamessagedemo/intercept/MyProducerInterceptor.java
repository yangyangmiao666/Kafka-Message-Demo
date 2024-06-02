package com.ustc.kafkamessagedemo.intercept;

import org.apache.kafka.clients.producer.ProducerInterceptor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;

import java.util.Map;

/**
 * 自定义数据拦截器
 * 1. 实现Kafka提供的生产者接口ProducerInterceptor
 * 2. 定义数据泛型 <K, V>
 * 3. 重写方法
 *    onSend
 *    onAcknowledgement
 *    close
 *    configure
 */
public class MyProducerInterceptor implements ProducerInterceptor<String, String> {
    // 数据发送前，会执行此方法，进行数据发送前的预处理
    @Override
    public ProducerRecord<String, String> onSend(ProducerRecord<String, String> record) {
        // 发送之前对value加上---interceptor
        return new ProducerRecord<>(record.topic(), record.partition(), record.key(), record.value() + "---interceptor");
    }

    // 数据发送后，获取应答时，会执行此方法
    @Override
    public void onAcknowledgement(RecordMetadata metadata, Exception exception) {
    }

    // 生产者关闭时，会执行此方法，完成一些资源回收和释放的操作
    @Override
    public void close() {
    }

    // 创建生产者对象的时候，会执行此方法，可以根据场景对生产者对象
    @Override
    public void configure(Map<String, ?> configs) {
    }
}

