package com.comsol.gen.enums;


/**
 * @author waani
 * @date 2023/10/7 9:39
 * @email kuun993@163.com
 * @description 物理场接口类型
 *
 */
public enum PhysicsInterfaceEnum {

    /**
     * 固体力学
     */
    SolidMechanics("solid", "SolidMechanics"),

    /**
     * 固体传热
     */
    HeatTransfer("ht", "HeatTransfer"),

    /**
     * 固体和流通传热
     */
    HeatTransferInSolidsAndFluids("ht", "HeatTransferInSolidsAndFluids"),


    // ANY

    ;


    /**
     * Physics interface tag
     * 物理接口标签
     *
     */
    private final String tag;

    /**
     * Physics interface identifier
     * 物理接口标识符
     *
     */
    private final String physIntId;

    PhysicsInterfaceEnum(String tag, String physIntId) {
        this.tag = tag;
        this.physIntId = physIntId;
    }

    public String getTag() {
        return tag;
    }

    public String getPhysIntId() {
        return physIntId;
    }
}
