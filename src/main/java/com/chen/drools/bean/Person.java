package com.chen.drools.bean;

import lombok.*;

/**
 * Person
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-07-31
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class Person {
    private String name;
    private Integer age;
    private String className;
}
