package com.comsol.gen.comp;

import com.comsol.model.Model;
import com.comsol.model.ModelNode;
import com.comsol.gen.util.TagUtil;


/**
 * @author waani
 * @date 2023/9/27 17:26
 * @description component 组件处理器
 */
public class CompHandler {


    /**
     * 几何
     * 默认三维
     */
    private static final int GeometryDimension = 3;


    /**
     * 创建组件、几何和网格
     * @param model
     * @return
     */
    public static ModelNode create(Model model) {
        String compTag = TagUtil.compTag();
        return create(model, compTag);
    }

    /**
     * 创建组件、几何和网格
     * @param model
     * @param compTag
     * @return
     */
    public static ModelNode create(Model model, String compTag) {
        String geomTag = TagUtil.geomTag();
        String meshTag = TagUtil.meshTag();
        return create(model, compTag, geomTag, meshTag);
    }


    /**
     * 创建组件、几何和网格
     * @param model
     * @param compTag
     * @param geomTag
     * @param meshTag
     * @return
     */
    public static ModelNode create(Model model, String compTag, String geomTag, String meshTag) {
        // 创建模型组件
        ModelNode modelNode = model.component().create(compTag);
        // 创建组件几何
        modelNode.geom().create(geomTag, GeometryDimension);
        // 创建组件网格
        modelNode.mesh().create(meshTag);
        return modelNode;
    }





}
