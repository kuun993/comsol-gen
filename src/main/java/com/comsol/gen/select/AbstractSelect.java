package com.comsol.gen.select;

import com.comsol.model.GeomSequence;
import com.comsol.gen.common.enums.EntityDimEnum;

/**
 * @author waani
 * @date 2023/10/9 15:04
 * @description 选择对象
 */
public abstract class AbstractSelect {


    /**
     *  选择域
     * @param geom
     * @param selectTag
     * @param cumulativeTag
     * @param x
     * @param y
     * @param z
     */
    public String createDomainSelect(GeomSequence geom, String selectTag, String cumulativeTag, double x, double y, double z) {
        return this.createSelect(EntityDimEnum.DOMAIN.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }

    /**
     * 选择边界
     * @param geom
     * @param selectTag
     * @param cumulativeTag
     * @param x
     * @param y
     * @param z
     */
    public String createBoundarySelect(GeomSequence geom, String selectTag, String cumulativeTag, double x, double y, double z) {
        return this.createSelect(EntityDimEnum.BOUNDARY.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }

    /**
     * 选择边
     * @param geom
     * @param selectTag
     * @param cumulativeTag
     * @param x
     * @param y
     * @param z
     */
    public String createEdgeSelect(GeomSequence geom, String selectTag, String cumulativeTag, double x, double y, double z) {
        return this.createSelect(EntityDimEnum.EDGE.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }

    /**
     * 选择点
     * @param geom
     * @param selectTag
     * @param cumulativeTag
     * @param x
     * @param y
     * @param z
     */
    public String createPointSelect(GeomSequence geom, String selectTag, String cumulativeTag, double x, double y, double z) {
        return this.createSelect(EntityDimEnum.POINT.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }

    /**
     * 根据坐标选择对象
     * @param entityDim         对象类型
     * @param geom              几何对象
     * @param selectTag         选择标签
     * @param cumulativeTag     累积选择标签
     * @param x
     * @param y
     * @param z
     */
    public abstract String createSelect(int entityDim, GeomSequence geom, String selectTag, String cumulativeTag, double x, double y, double z);


    /**
     * 生成选择标签
     * @return
     */
    public abstract String selectTag();
    
}
