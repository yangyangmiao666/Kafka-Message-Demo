package com.ustc.kafkamessagedemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author :Yangyang Miao
 * @date :2023-07-30 17:25:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Person {

    private Long id;

    private String name;

    private String email;
}
