package com.comsol.gen.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author waani
 * @date 2023/10/16 10:52
 * @email kuun993@163.com
 * @description 坐标
 */
@Getter
@Setter
@ToString
public class CoordinateVo implements Serializable {

    private static final long serialVersionUID = -6847767530429385467L;

    private double x;

    private double y;

    private double z;

    public CoordinateVo() {}

    public CoordinateVo(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
}
