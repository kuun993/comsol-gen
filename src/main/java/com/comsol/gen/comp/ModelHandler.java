package com.comsol.gen.comp;

import com.comsol.model.Model;
import com.comsol.model.ModelNode;
import com.comsol.model.util.ModelUtil;
import com.comsol.gen.common.ComsolConstants;

/**
 * @author waani
 * @date 2023/10/13 14:45
 * @description TODO
 */
public class ModelHandler {


    public Model build() {
        return build(ComsolConstants.MODEL_TAG);
    }


    public Model build(String modelTag) {
        return build(modelTag, ComsolConstants.MODEL_PATH);
    }


    /**
     * 构建模型
     * @param modelTag
     * @param modelPath
     * @return
     */
    public Model build(String modelTag, String modelPath) {
        Model model = ModelUtil.create(modelTag);
        model.modelPath(modelPath);
        return model;
    }


    /**
     * 创建组件
     * @param model
     * @param compTag
     * @return
     */
    public ModelNode createComponent(Model model, String compTag) {
        // 创建模型组件
        return model.component().create(compTag);
    }


}
