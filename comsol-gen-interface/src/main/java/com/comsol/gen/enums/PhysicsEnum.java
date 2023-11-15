package com.comsol.gen.enums;

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
    HeatTransfer("HeatTransfer", "ht", "Heat_physics", new String[]{"/physics/ht"}),

    /**
     * 固体力学
     */
    SolidMechanics("SolidMechanics", "solid", "Solid_physics", new String[]{"/physics/solid"}),


    /**
     * 固体和流体传热
     */
    HeatTransferInSolidsAndFluids("HeatTransferInSolidsAndFluids", "ht", "Solid_physics", new String[]{"/physics/ht", "/physics/solid"}),

    ;


    private final String physics;

    private final String tag;

    private final String multiTag;

    private final String[] solveFor;


    public static String getTag(String physics){
        return PhysicsEnum.valueOf(physics).tag;
    }


}
