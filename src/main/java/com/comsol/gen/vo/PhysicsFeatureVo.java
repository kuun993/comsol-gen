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

    private String feature;

    private SelectVo select;




    /**
     * 属性
     */
    private Map<String, String> properties;

}
