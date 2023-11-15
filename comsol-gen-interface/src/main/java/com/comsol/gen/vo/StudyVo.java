package com.comsol.gen.vo;

import com.comsol.gen.enums.StudyEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author waani
 * @date 2023/10/30 14:58
 * @email kuun993@163.com
 * @description 研究
 */
@Getter
@Setter
@ToString
public class StudyVo implements Serializable {

    private static final long serialVersionUID = 5161359373585104404L;

    private static final String T_LIST = "range(%d,%d,%d)";


    private StudyEnum studyEnum;

    /**
     * 开始
     */
    private double start;

    /**
     * 步长
     */
    private double step;

    /**
     * 结束
     */
    private double stop;


    /**
     * 生成默认绘图
     */
    private boolean genPlots;

    /**
     * 生成收敛图
     */
    private boolean genConv;

    public String getTList() {
        return String.format(T_LIST, start, step, stop);
    }

}
