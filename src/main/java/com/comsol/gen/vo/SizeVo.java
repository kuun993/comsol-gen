package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author waani
 * @date 2023/10/16 12:00
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class SizeVo implements Serializable {

    private static final long serialVersionUID = -1054535207569584135L;

    private double max;

    private double min;

    private SelectVo select;

}
