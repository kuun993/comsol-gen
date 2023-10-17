package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/16 10:51
 * @description TODO
 */
@Getter
@Setter
@ToString
public class MaterialVo implements Serializable {


    /**
     * 0-3 分别表示点、边、边界和域，默认为域
     */
    private SelectVo select;


    /**
     * 材料属性
     */
    private Map<String, String> properties;



}
