package com.comsol.gen.model;

import com.comsol.model.Model;
import com.comsol.model.util.ModelUtil;
import com.comsol.gen.common.ComsolConstants;

/**
 * @author waani
 * @date 2023/10/13 14:45
 * @description TODO
 */
public class ModelHandler {


    public static Model build() {
        return build(ComsolConstants.MODEL_TAG);
    }

    public static Model build(String modelTag) {
        return build(modelTag, ComsolConstants.MODEL_PATH);
    }

    public static Model build(String modelTag, String modelPath) {
        Model model = ModelUtil.create(modelTag);
        model.modelPath(modelPath);
        return model;
    }



}
