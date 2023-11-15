package com.comsol.gen.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author waani
 * @date 2023/10/9 13:40
 * @email kuun993@163.com
 * @description 可选对象 objects, domains, boundaries, edges and points
 */
@AllArgsConstructor
@Getter
public enum EntityDimEnum {

    /**
     * 0 点
     */
    POINT("pnt"),

    /**
     * 1 边
     */
    EDGE("edg"),

    /**
     * 2 边界
     */
    BOUNDARY("bnd"),

    /**
     * 3 域
     */
    DOMAIN("dom"),

    ;


    private final String abbr;


}
