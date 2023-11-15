package com.comsol.gen.vo;

import com.comsol.gen.enums.EntityDimEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/16 13:35
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class PhysicsFeatureVo implements Serializable {

    private static final long serialVersionUID = 910986978893973473L;

    /**
     * 物理场特征
     * 如：固体传热的热源、热通量等
     */
    private String feature;


    /**
     * 物理场特征
     * 选择对象
     */
    private SelectVo select;


    /**
     * 物理场特征属性
     */
    private Map<String, String> properties;


    /**
     * 父物理场特征
     * 如 "固体" 创建平移运动等
     */
    private String parentTag;


    public static PhysicsFeatureVo build(String feature, int entityDim, double x, double y, double z) {
        PhysicsFeatureVo obj = new PhysicsFeatureVo();
        obj.feature = feature;
        SelectVo selectVo = new SelectVo(x, y, z);
        selectVo.setEntityDim(entityDim);
        obj.select = selectVo;
        return obj;
    }


    /**
     * 热源
     * @return
     */
    public static PhysicsFeatureVo buildHeatSource() {
        return buildHeatSource(0, 0, 0);
    }


    /**
     * 热源
     * @return
     */
    public static PhysicsFeatureVo buildHeatSource(double x, double y, double z) {
        return buildHeatSource(x, y, z, "2e6");
    }


    /**
     * 热源
     * @param x
     * @param y
     * @param z
     * @param Q0
     * @return
     */
    public static PhysicsFeatureVo buildHeatSource(double x, double y, double z, String Q0) {
        PhysicsFeatureVo obj = build("HeatSource", EntityDimEnum.DOMAIN.ordinal(), x, y, z);
        Map<String, String> properties = new HashMap<>(1);
        properties.put("Q0", Q0);
        obj.properties = properties;
        return obj;
    }


    /**
     * 热通量
     * @return
     */
    public static PhysicsFeatureVo buildHeatFluxBoundary() {
        return buildHeatFluxBoundary(0, 0, 0);
    }

    /**
     * 热通量
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static PhysicsFeatureVo buildHeatFluxBoundary(double x, double y, double z) {
        return buildHeatFluxBoundary(x, y, z, "120");
    }


    /**
     * 热通量
     * @param x
     * @param y
     * @param z
     * @param q0_input
     * @return
     */
    public static PhysicsFeatureVo buildHeatFluxBoundary(double x, double y, double z, String q0_input) {
        PhysicsFeatureVo obj = build("HeatFluxBoundary", EntityDimEnum.BOUNDARY.ordinal(), x, y, z);
        Map<String, String> properties = new HashMap<>(1);
        properties.put("q0_input", q0_input);
        obj.properties = properties;
        return obj;
    }


}
