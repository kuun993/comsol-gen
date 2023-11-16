package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/23 11:05
 * @description TODO
 */
@Getter
@Setter
@ToString
public class FunctionVo {

    /**
     * Property name
     */
    private String type;

    /**
     * arg
     */
    private String arg;

    /**
     * argunit
     */
    private String argUnit;

    /**
     * fununit
     */
    private String funUnit;


    /**
     * 列表
     */
    private List<Map<String, String>> table;

}
