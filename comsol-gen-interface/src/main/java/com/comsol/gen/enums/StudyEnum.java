package com.comsol.gen.enums;



/**
 * @author waani
 * @date 2023/10/7 14:52
 * @email kuun993@163.com
 * @description TODO
 */

public enum StudyEnum {


    /**
     * 瞬态
     */
    Transient("time", "Transient"),

    /**
     * 稳态
     */
    Stationary("stat", "Stationary"),

    /**
     * 特征频率
     */
    EigenFrequency("eig", "Eigenfrequency"),

    /**
     * 模型降阶
     */
    ModelReduction("mr", "ModelReduction"),

    ;


    private final String tag;

    private final String type;


    StudyEnum(String tag, String type) {
        this.tag = tag;
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public String getType() {
        return type;
    }
}
