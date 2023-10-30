package com.comsol.gen.physics;

import com.comsol.gen.common.AbstractHandler;
import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.PhysicsFeatureVo;
import com.comsol.gen.vo.PhysicsVo;
import com.comsol.gen.vo.SelectVo;
import com.comsol.model.GeomSequence;
import com.comsol.model.ModelNode;
import com.comsol.model.physics.Physics;
import com.comsol.model.physics.PhysicsFeature;

import java.util.List;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/17 11:43
 * @description TODO
 */
public class PhysicsHandler extends AbstractHandler {


    /**
     * 给物理场接口添加特征
     * @param comp
     * @param geom
     * @param physicsVos
     */
    public void createPhysics(ModelNode comp, GeomSequence geom, List<PhysicsVo> physicsVos){
        if (physicsVos == null || physicsVos.size() == 0) {
            return;
        }
        for (PhysicsVo physicsVo : physicsVos) {
            createPhysics(comp, geom, physicsVo);
        }
    }


    /**
     * 给物理场接口添加特征
     * @param comp
     * @param geom
     * @param physicsVo
     */
    public void createPhysics(ModelNode comp, GeomSequence geom, PhysicsVo physicsVo){
        // 物理场接口
        Physics physics = comp.physics(physicsVo.getPhysicsInterface().getTag());
        // 添加物理场特征
        addPhysics(geom, physics, physicsVo.getFeatures());
    }


    /**
     * 添加物理场特征
     * @param geom
     * @param physics
     * @param featuresVos
     */
    private void addPhysics(GeomSequence geom, Physics physics, List<PhysicsFeatureVo> featuresVos){
        if (featuresVos == null || featuresVos.size() == 0) {
            return;
        }
        for (PhysicsFeatureVo featuresVo : featuresVos) {
            addPhysics(geom, physics, featuresVo);
        }
    }


    /**
     * 添加物理场特征
     * @param geom
     * @param physics
     * @param featureVo
     */
    private void addPhysics(GeomSequence geom, Physics physics, PhysicsFeatureVo featureVo){
        String featureTag = TagUtil.uniqueTag(featureVo.getFeature());
        PhysicsFeature physicsFeature = physics.create(featureTag, featureVo.getFeature(), featureVo.getSelect().getEntityDim());
        // 设置物理场特征参数
        setProperties(physicsFeature, featureVo.getProperties());
        // 选择对象
        AbstractSelect abstractSelect = new BallSelect();
        selectDim(abstractSelect, geom, physicsFeature.selection(), featureVo.getSelect());
    }




}
