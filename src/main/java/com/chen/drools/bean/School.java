package com.chen.drools.bean;

import lombok.Data;

import java.util.List;

/**
 * School
 * <p>
 *
 * @Author LeifChen
 * @Date 2021-08-31
 */
@Data
public class School {

    private String code;
    private String className;
    private List<String> classNameList;
}
