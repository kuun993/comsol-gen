package com.comsol.gen.common.enums;

/**
 * @author waani
 * @date 2023/10/13 15:27
 * @email kuun993@163.com
 * @description 初始化
 */
public enum InitEnum {

    /**
     * 固体力学
     */
    RandomVibration,

    /**
     * 固体传热 瞬态
     */
    HeatTransferInSolidsTransient,

    /**
     * 固体传热 固态
     */
    HeatTransferInSolidsStationary,

    /**
     * 热-结构相互作用 瞬态
     */
    ThermalStructureInteractionTransient,

    /**
     * 热-结构相互作用 固态
     */
    ThermalStructureInteractionStationary,

    /**
     * 固体和流体传热 瞬态
     */
    HeatTransferInSolidsAndFluidsTransient,

    /**
     * 固体和流体传热 固态
     */
    HeatTransferInSolidsAndFluidsStationary,

    ;

}
