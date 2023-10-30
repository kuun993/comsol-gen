package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author waani
 * @date 2023/10/17 15:56
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class SelectVo implements Serializable {

    private static final long serialVersionUID = 8395653363505280863L;

    /**
     * 0-3 分别表示点、边、边界和域，默认为域
     */
    private Integer entityDim;


    /**
     * 坐标集合
     */
    private List<CoordinateVo> coordinateVos;


    /**
     * 全选
     */
    private boolean all;


    /**
     * 默认为域
     * @return
     */
    public Integer getEntityDim() {
        return entityDim==null? 3: entityDim;
    }

}
