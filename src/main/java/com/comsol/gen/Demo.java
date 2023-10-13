package com.comsol.gen;

import com.comsol.gen.common.enums.InitEnum;
import com.comsol.gen.mat.MaterialHandler;
import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;
import com.comsol.gen.select.CumulativeSelect;
import com.comsol.gen.util.TagUtil;
import com.comsol.model.GeomSequence;
import com.comsol.model.Material;
import com.comsol.model.Model;
import com.comsol.model.ModelNode;

/**
 * @author waani
 * @date 2023/10/9 11:07
 * @description TODO
 */
public class Demo {



    public void run() {
        String compTag = TagUtil.compTag();
        String geomTag = TagUtil.geomTag();
        String meshTag = TagUtil.meshTag();

        // 固体传热 + 稳态
        Model model = ComsolInit.init(InitEnum.HeatTransferInSolidsStationary, compTag, geomTag, meshTag);

        // 球选择
        AbstractSelect select = new BallSelect();

        // 生成球选择标签
        String ballTag = select.selectTag();

        // 获取几何
        GeomSequence geom = model.geom(geomTag);

        // 创建累计选择
        String cSelTag = CumulativeSelect.createSelect(geom);

        // 创建球选择关联累计选择
        String selectNamedTag = select.createDomainSelect(geom, ballTag, cSelTag, 0.01, 0.01, 0.01);

        // 创建材料 选择边界（仅测试）
        ModelNode comp = model.component(compTag);
        Material material = MaterialHandler.createMaterial(comp);
        // 设置一些属性
        material.set("xxx", "xxx");
        // named
        MaterialHandler.set2GeomByNamed(material, selectNamedTag);
    }



}
