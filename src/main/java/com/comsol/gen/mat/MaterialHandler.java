package com.comsol.gen.mat;

import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;
import com.comsol.gen.vo.MaterialVo;
import com.comsol.gen.vo.SelectVo;
import com.comsol.model.GeomSequence;
import com.comsol.model.Material;
import com.comsol.model.MaterialModel;
import com.comsol.model.ModelNode;
import com.comsol.gen.util.TagUtil;

import java.util.List;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/13 15:44
 * @description TODO
 */
public class MaterialHandler {



    public void createMaterial(ModelNode comp, GeomSequence geom, List<MaterialVo> materialVos) {
        if (materialVos == null || materialVos.size() == 0) {
            return;
        }
        for (MaterialVo materialVo : materialVos) {
            createMaterial(comp, geom, materialVo);
        }
    }


    /**
     * 创建材料
     * @param comp
     * @param geom
     * @param materialVo
     */
    public void createMaterial(ModelNode comp, GeomSequence geom, MaterialVo materialVo) {
        String matTag = TagUtil.matTag();
        Material material = comp.material().create(matTag, "Common");
        setParam(material, materialVo.getProperties());
        select(geom, material, materialVo.getSelect());
    }


    /**
     * 设置材料参数
     * @param material
     * @param properties
     */
    private void setParam(Material material, Map<String, String> properties) {
        if (properties == null || properties.size() == 0) {
            return;
        }
        // 默认分组
        MaterialModel materialModel = material.propertyGroup("def");
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            materialModel.set(entry.getKey(), entry.getValue());
        }
    }




    /**
     * 选择
     * @param geom
     * @param material
     * @param selectVo
     */
    private void select(GeomSequence geom, Material material, SelectVo selectVo){
        AbstractSelect ballSelect = new BallSelect();
        String selectTag = ballSelect.selectTag();
        String named = ballSelect.create(selectVo.getEntityDim(), geom, selectTag, selectVo.getX(), selectVo.getY(), selectVo.getZ());
        material.selection().named(named);
    }





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
