package com.chen.drools.bean;

import lombok.Data;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
    private Set<String> classNameSet;
    private Map<String,Integer> classNameMap;
}
