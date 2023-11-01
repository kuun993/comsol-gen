package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author waani
 * @date 2023/10/30 10:01
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class GeomVo {


    /**
     * stp文件路径
     */
    private String stpFilePath;

    /**
     * 绝对导入误差
     */
    private double importTol;


    //
    // fin
    // 几何形成联合体/装配
    // action: union/assembly
    // repairtoltype: auto/relative
    // when repairtoltype=relative, repairTol active.
    //

    /**
     * assembly 装配
     * union    联合体
     */
    private String action;

    /**
     * relative 相对
     * auto     自动
     */
    private String repairTolType;


    /**
     *
     * 相对修复容差
     */
    private double repairTol;


    /**
     * 自动创建对
     */
    private boolean createPairs;


}
