package com.comsol.gen.physics;

import com.comsol.gen.common.AbstractHandler;
import com.comsol.gen.common.HandlerInterface;
import com.comsol.gen.common.enums.PhysicsEnum;
import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;
import com.comsol.gen.util.CollectionUtil;
import com.comsol.gen.util.StringUtil;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.PhysicsFeatureVo;
import com.comsol.gen.vo.PhysicsVo;
import com.comsol.model.GeomSequence;
import com.comsol.model.ModelNode;
import com.comsol.model.physics.Physics;
import com.comsol.model.physics.PhysicsFeature;
import java.util.List;

/**
 * @author waani
 * @date 2023/10/17 11:43
 * @description TODO
 */
public class PhysicsHandler extends AbstractHandler implements HandlerInterface<PhysicsVo> {


    /**
     * 给物理场接口添加特征
     * @param comp
     * @param geom
     * @param physicsVo
     */
    @Override
    public void create(ModelNode comp, GeomSequence geom, PhysicsVo physicsVo){
        // 物理场接口
        PhysicsEnum physicsEnum = physicsVo.getPhysics();
        String physicsTag = TagUtil.uniqueTag(physicsEnum.getTag());
        Physics physics = comp.physics().create(physicsTag, physicsEnum.getPhysics());
        // 添加物理场特征
        addPhysicsFeatures(geom, physics, physicsVo.getFeatures());
    }


    /**
     * 添加物理场特征
     * @param geom
     * @param physics
     * @param featuresVos
     */
    private void addPhysicsFeatures(GeomSequence geom, Physics physics, List<PhysicsFeatureVo> featuresVos){
        if (CollectionUtil.isEmpty(featuresVos)) {
            return;
        }
        for (PhysicsFeatureVo featuresVo : featuresVos) {
            addPhysicsFeature(geom, physics, featuresVo);
        }
    }


    /**
     * 添加物理场特征
     * @param geom
     * @param physics
     * @param featureVo
     */
    private void addPhysicsFeature(GeomSequence geom, Physics physics, PhysicsFeatureVo featureVo){
        String featureTag = TagUtil.uniqueTag(featureVo.getFeature());
        PhysicsFeature physicsFeature;
        if (StringUtil.isEmpty(featureVo.getParentTag())) {
            // 父特征
            physicsFeature = physics.feature(featureVo.getParentTag()).create(featureTag, featureVo.getFeature(), featureVo.getSelect().getEntityDim());
        } else {
            physicsFeature = physics.create(featureTag, featureVo.getFeature(), featureVo.getSelect().getEntityDim());
        }

        // 设置物理场特征参数
        setProperties(physicsFeature, featureVo.getProperties());
        // 选择对象
        AbstractSelect abstractSelect = new BallSelect();
        selectDim(abstractSelect, geom, physicsFeature.selection(), featureVo.getSelect());
    }




}
