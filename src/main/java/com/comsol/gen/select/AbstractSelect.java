package com.comsol.gen.select;

import com.comsol.model.GeomFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.common.enums.EntityDimEnum;

/**
 * @author waani
 * @date 2023/10/9 15:04
 * @description 选择对象
 */
public abstract class AbstractSelect {


    /**
     * geom_select
     */
    String SELECT_NAMED_FORMAT = "%s_%s";


    /**
     * geom_cumulative_dom
     */
    String SELECT_CUMULATIVE_NAMED_FORMAT = "%s_%s_dom";


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
        return this.create(EntityDimEnum.DOMAIN.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }

    /**
     * 选择域
     * @param geom
     * @param selectTag
     * @param x
     * @param y
     * @param z
     * @return
     */
    public String createDomainSelect(GeomSequence geom, String selectTag, double x, double y, double z) {
        return this.create(EntityDimEnum.DOMAIN.ordinal(), geom, selectTag, x, y, z);
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
        return this.create(EntityDimEnum.BOUNDARY.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }


    /**
     * 选择边界
     * @param geom
     * @param selectTag
     * @param x
     * @param y
     * @param z
     */
    public String createBoundarySelect(GeomSequence geom, String selectTag, double x, double y, double z) {
        return this.create(EntityDimEnum.BOUNDARY.ordinal(), geom, selectTag, x, y, z);
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
        return this.create(EntityDimEnum.EDGE.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }

    /**
     * 选择边
     * @param geom
     * @param selectTag
     * @param x
     * @param y
     * @param z
     */
    public String createEdgeSelect(GeomSequence geom, String selectTag, double x, double y, double z) {
        return this.create(EntityDimEnum.EDGE.ordinal(), geom, selectTag, x, y, z);
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
        return this.create(EntityDimEnum.POINT.ordinal(), geom, selectTag, cumulativeTag, x, y, z);
    }

    /**
     * 选择点
     * @param geom
     * @param selectTag
     * @param x
     * @param y
     * @param z
     */
    public String createPointSelect(GeomSequence geom, String selectTag, double x, double y, double z) {
        return this.create(EntityDimEnum.POINT.ordinal(), geom, selectTag, x, y, z);
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
    public String create(int entityDim, GeomSequence geom, String selectTag, String cumulativeTag, double x, double y, double z) {
        GeomFeature select = this.createSelect(entityDim, geom, selectTag, x, y, z);
        select.set("contributeto", cumulativeTag);
        return String.format(SELECT_CUMULATIVE_NAMED_FORMAT, geom.tag(), cumulativeTag);
    }

    /**
     * 根据坐标选择对象
     * @param entityDim         对象类型
     * @param geom              几何对象
     * @param selectTag         选择标签
     * @param x
     * @param y
     * @param z
     */
    public String create(int entityDim, GeomSequence geom, String selectTag, double x, double y, double z) {
        this.createSelect(entityDim, geom, selectTag, x, y, z);
        return String.format(SELECT_NAMED_FORMAT, geom.tag(), selectTag);
    }




    /**
     * 根据坐标选择对象
     * @param entityDim         对象类型
     * @param geom              几何对象
     * @param selectTag         选择标签
     * @param x
     * @param y
     * @param z
     * @return
     */
    public abstract GeomFeature createSelect(int entityDim, GeomSequence geom, String selectTag, double x, double y, double z);


    /**
     * 生成选择标签
     * @return
     */
    public abstract String selectTag();
    
}
