package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author waani
 * @date 2023/10/17 15:56
 * @description TODO
 */
@Getter
@Setter
@ToString
public class SelectVo extends CoordinateVo implements Serializable {

    private static final long serialVersionUID = 8395653363505280863L;

    /**
     * 0-3 分别表示点、边、边界和域，默认为域
     */
    private Integer entityDim;


    /**
     * 默认为域
     * @return
     */
    public Integer getEntityDim() {


        return entityDim==null? 3: entityDim;
    }

}
