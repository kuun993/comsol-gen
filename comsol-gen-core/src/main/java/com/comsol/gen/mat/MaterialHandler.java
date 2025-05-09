package com.comsol.gen.mat;

import com.comsol.gen.common.AbstractHandler;
import com.comsol.gen.common.HandlerInterface;
import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;
import com.comsol.gen.vo.MaterialVo;
import com.comsol.model.*;
import com.comsol.gen.util.TagUtil;

/**
 * @author waani
 * @date 2023/10/13 15:44
 * @email kuun993@163.com
 * @description TODO
 */
public class MaterialHandler extends AbstractHandler implements HandlerInterface<MaterialVo> {


    /**
     * 创建材料
     * @param comp
     * @param geom
     * @param materialVo
     */
    @Override
    public void create(ModelNode comp, GeomSequence geom, MaterialVo materialVo) {
        String matTag = TagUtil.matTag();
        // 默认 Common
        Material material = comp.material().create(matTag, "Common");

        // 设置材料参数
        MaterialModel materialModel = material.propertyGroup("def");
        setProperties(materialModel, materialVo.getProperties());

        // 选择对象
        AbstractSelect abstractSelect = new BallSelect();
        selectDim(abstractSelect, geom, material.selection(), materialVo.getSelect());
    }





}
