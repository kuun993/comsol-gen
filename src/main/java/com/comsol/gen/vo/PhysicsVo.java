package com.comsol.gen.vo;

import com.comsol.gen.common.enums.PhysicsInterfaceEnum;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author waani
 * @date 2023/10/16 13:35
 * @description TODO
 */
@ToString
public class PhysicsVo implements Serializable {

    private static final long serialVersionUID = 7621710160691874411L;

    /**
     * 物理场
     */
    private PhysicsInterfaceEnum physicsInterface;


    /**
     * 载荷设置
     */
    private List<PhysicsFeatureVo> features;


    public PhysicsInterfaceEnum getPhysicsInterface() {
        return physicsInterface;
    }

    public void setPhysicsInterface(PhysicsInterfaceEnum physicsInterface) {
        this.physicsInterface = physicsInterface;
    }

    public List<PhysicsFeatureVo> getFeatures() {
        return features;
    }

    public void setFeatures(List<PhysicsFeatureVo> features) {
        this.features = features;
    }
}
