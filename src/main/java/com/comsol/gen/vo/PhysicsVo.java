package com.comsol.gen.vo;

import com.comsol.gen.common.enums.PhysicsEnum;
import com.comsol.gen.common.enums.PhysicsInterfaceEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * @author waani
 * @date 2023/10/16 13:35
 * @description TODO
 */
@Getter
@Setter
@ToString
public class PhysicsVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = 7621710160691874411L;


    /**
     * 物理场接口
     * 如: 固体传热
     */
    private PhysicsEnum physics;

    /**
     * 载荷设置
     */
    private List<PhysicsFeatureVo> features;


}
