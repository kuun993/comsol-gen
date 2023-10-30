package com.comsol.gen.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author waani
 * @date 2023/10/20 09:40
 * @email kuun993@163.com
 * @description comsol 物理场接口
 */
@Getter
@AllArgsConstructor
public enum PhysicsEnum {


    /**
     * 固体传热
     */
    HeatTransfer("HeatTransfer", "ht", "Heat_physics"),

    /**
     * 固体力学
     */
    SolidMechanics("SolidMechanics", "solid", "Solid_physics"),


    /**
     * 固体和流体传热
     */
    HeatTransferInSolidsAndFluids("HeatTransferInSolidsAndFluids", "ht", "Solid_physics"),

    ;


    private final String physics;

    private final String tag;

    private final String multiTag;



    public static String getTag(String physics){
        return PhysicsEnum.valueOf(physics).tag;
    }


}
