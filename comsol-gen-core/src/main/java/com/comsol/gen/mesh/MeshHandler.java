package com.comsol.gen.mesh;

import com.comsol.gen.common.AbstractHandler;
import com.comsol.gen.common.HandlerInterface;
import com.comsol.gen.util.CollectionUtil;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.BaseVo;
import com.comsol.gen.vo.MeshVo;
import com.comsol.gen.vo.SizeVo;
import com.comsol.model.GeomSequence;
import com.comsol.model.MeshFeature;
import com.comsol.model.MeshSequence;
import com.comsol.model.ModelNode;

import java.util.List;

/**
 * @author waani
 * @date 2023/10/30 14:01
 * @email kuun993@163.com
 * @description TODO
 */
public class MeshHandler extends AbstractHandler implements HandlerInterface<MeshVo> {


    /**
     * 创建网格
     * @param comp
     * @param geom
     * @param meshVo
     */
    @Override
    public void create(ModelNode comp, GeomSequence geom, MeshVo meshVo) {
        String meshTag = TagUtil.meshTag();
        MeshSequence meshSequence = comp.mesh().create(meshTag);

        // 网格类型
        String ftetTag = TagUtil.uniqueTag("ftet");
        MeshFeature meshFeature = meshSequence.create(ftetTag, meshVo.getType());

        // 全局网格大小
        MeshFeature globalSize = meshSequence.feature("size");
        globalSize.set("hmax", meshVo.getMax());
        globalSize.set("hmin", meshVo.getMin());


        // 网格大小
        List<SizeVo> sizes = meshVo.getSizes();
        if (CollectionUtil.isEmpty(sizes)) {
            return;
        }
        for (SizeVo size : sizes) {
            String sizeTag = TagUtil.uniqueTag("size");
            MeshFeature sizeMeshFeature = meshFeature.create(sizeTag, "Size");
            // 默认定制 最大单元大小 最小单元大小
            sizeMeshFeature.set("custom", true);
            sizeMeshFeature.set("hmaxactive", true);
            sizeMeshFeature.set("hminactive", true);
            sizeMeshFeature.set("hmax", size.getMax());
            sizeMeshFeature.set("hmin", size.getMin());
        }
    }

}
