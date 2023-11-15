package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author waani
 * @date 2023/10/16 16:13
 * @email kuun993@163.com
 * @description comsol模型参数
 */
@Getter
@Setter
@ToString
public class ComsolVo implements Serializable {

    private static final long serialVersionUID = 835359979650319006L;

    /**
     * 模型名称
     */
    private String modelName;


    /**
     * 模型目录，工作空间
     */
    private String modelPath;


    /**
     * 几何
     */
    private GeomVo geom;

    /**
     * 材料列表
     */
    private List<MaterialVo> materials;

    /**
     * 载荷列表
     */
    private List<PhysicsVo> physics;

    /**
     * 网格列表
     */
    private List<MeshVo> meshes;


    /**
     * 结果
     */
    private ResultVo result;

}
