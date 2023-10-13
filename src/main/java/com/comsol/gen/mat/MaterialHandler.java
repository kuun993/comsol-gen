package com.comsol.gen.mat;

import com.comsol.model.Material;
import com.comsol.model.ModelNode;
import com.comsol.gen.util.TagUtil;

/**
 * @author waani
 * @date 2023/10/13 15:44
 * @description TODO
 */
public class MaterialHandler {

    /**
     * 创建材料
     * @param comp
     * @return
     */
    public static Material createMaterial(ModelNode comp) {
        String matTag = TagUtil.matTag();
        return comp.material().create(matTag, "Common");
    }


    /**
     * 将材料设置到几何
     * @param material
     * @param namedTag
     */
    public static void set2GeomByNamed(Material material, String namedTag) {
        material.selection().named(namedTag);
    }


}
