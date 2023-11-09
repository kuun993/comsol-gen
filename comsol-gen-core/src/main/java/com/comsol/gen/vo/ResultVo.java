package com.comsol.gen.vo;

import com.comsol.gen.common.ComsolConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author waani
 * @date 2023/10/30 15:31
 * @email kuun993@163.com
 * @description 结果
 */
@Getter
@Setter
@ToString
public class ResultVo implements Serializable {

    private static final long serialVersionUID = -5840561307777489233L;


    /**
     * 数据 导出文件类型
     * txt vtu
     */
    private String exportType;

    /**
     * 数据 结果路径
     */
    private String dataFilePath;


    /**
     * 图像 结果路径
     */
    private String imgFilePath;


    /**
     * 数据集
     */
    private String datasetTag;

    /**
     * 表达式
     */
    private String expression;



    public static ResultVo buildHeatTransfer(String rootPath) {
        UUID uuid = UUID.randomUUID();
        ResultVo resultVo = new ResultVo();
        resultVo.exportType = "vtu";
        resultVo.dataFilePath = rootPath + "/result/" + uuid + ".vtu";
        resultVo.imgFilePath = rootPath + "/result/" + uuid + ".png";
        resultVo.datasetTag = "dset1";
        resultVo.expression = "T";
        return resultVo;
    }


}
