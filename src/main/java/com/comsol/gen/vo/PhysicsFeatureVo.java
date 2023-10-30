package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/16 13:35
 * @description TODO
 */
@Getter
@Setter
@ToString
public class PhysicsFeatureVo implements Serializable {

    private static final long serialVersionUID = 910986978893973473L;

    /**
     * 物理场特征
     * 如：固体传热的热源、热通量等
     */
    private String feature;


    /**
     * 物理场特征
     * 选择对象
     */
    private SelectVo select;


    /**
     * 物理场特征属性
     */
    private Map<String, String> properties;


    /**
     * 父物理场特征
     * 如 "固体" 创建平移运动等
     */
    private String parentTag;

}
