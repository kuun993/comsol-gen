package com.comsol.gen.comp;

import com.comsol.model.Model;
import com.comsol.model.ModelNode;
import com.comsol.gen.util.TagUtil;


/**
 * @author waani
 * @date 2023/9/27 17:26
 * @email kuun993@163.com
 * @description component 组件处理器
 */
@Deprecated
public class CompHandler {



    /**
     * 创建组件
     * @param model
     * @param compTag
     * @return
     */
    public static ModelNode create(Model model, String compTag) {
        // 创建模型组件
        return model.component().create(compTag);
    }




}
