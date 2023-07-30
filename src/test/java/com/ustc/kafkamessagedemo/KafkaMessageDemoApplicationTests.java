package com.ustc.kafkamessagedemo;

import com.ustc.kafkamessagedemo.entity.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.StopWatch;

import java.util.concurrent.CompletableFuture;

@SpringBootTest
class KafkaMessageDemoApplicationTests {

    @Autowired
    private KafkaTemplate kafkaTemplate;

    // 10000个异步任务
    CompletableFuture[] futures = new CompletableFuture[10000];

    StopWatch stopWatch = new StopWatch();

    /**
     * 发送String
     */
    @Test
    void contextLoads() {
        stopWatch.start();
        for (int i = 0; i < 10000; i++) {
            CompletableFuture future = kafkaTemplate.send("news", "hahaha", "哈哈哈");
            futures[i] = future;
        }

        CompletableFuture.allOf(futures).join();
        stopWatch.stop();
        long mills = stopWatch.getTotalTimeMillis();
        System.out.println("10000个消息发布完成用了" + mills + "毫秒");
    }

    /**
     * 发送Json对象
     */
    @Test
    void send() {
        Person person = new Person(1L, "张三", "123@qq.com");
        CompletableFuture future = kafkaTemplate.send("news","newPerson", person);
        future.join();
        System.out.println("消息发送成功");
    }
}
