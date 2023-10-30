package com.comsol.gen.select;

import com.comsol.gen.util.CollectionUtil;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.CoordinateVo;
import com.comsol.model.GeomFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.common.enums.EntityDimEnum;
import com.comsol.model.XDLocalSelection;

import java.util.List;

/**
 * @author waani
 * @date 2023/10/9 15:04
 * @email kuun993@163.com
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
     * @param x
     * @param y
     * @param z
     */
    public String create(int entityDim, GeomSequence geom, String selectTag, double x, double y, double z) {
        this.createSelect(entityDim, geom, selectTag, x, y, z);
        return String.format(SELECT_NAMED_FORMAT, geom.tag(), selectTag);
    }





    /**
     * 累积选择
     * @param cSelTag           累积选择标签
     * @param select            选择对象
     */
    public void cumulativeSelect(String cSelTag, GeomFeature select) {
        select.set("contributeto", cSelTag);
    }


    /**
     * 累积选择Named
     * @param geomTag
     * @param cSelTag
     * @return
     */
    public String cumulativeSelectNamed(String geomTag, String cSelTag) {
        return String.format(SELECT_CUMULATIVE_NAMED_FORMAT, geomTag, cSelTag);
    }


    /**
     * 累积选择 tag
     * @return
     */
    public String cumulativeSelectTag() {
        return TagUtil.uniqueTag("csel");
    }


    /**
     * 根据坐标选择对象
     * @param entityDim
     * @param geom
     * @param selectTag
     * @param vo
     * @return
     */
    public GeomFeature createSelect(int entityDim, GeomSequence geom, String selectTag, CoordinateVo vo) {
        return this.createSelect(entityDim, geom, selectTag, vo.getX(), vo.getY(), vo.getZ());
    }


    /**
     * 创建选择并关联到累积选择
     * 返回 named
     *
     * @param entityDim
     * @param geom
     * @param coordinateVos
     * @return
     */
    public String select(int entityDim, GeomSequence geom, List<CoordinateVo> coordinateVos) {
        // 累积选择
        String cSelTag = this.cumulativeSelectTag();
        // 创建选择并关联到累积选择
        for (CoordinateVo coordinateVo : coordinateVos) {
            String selectTag = this.selectTag();
            GeomFeature select = this.createSelect(entityDim, geom, selectTag, coordinateVo);
            this.cumulativeSelect(cSelTag, select);
        }
        return String.format(SELECT_CUMULATIVE_NAMED_FORMAT, geom.tag(), cSelTag);
    }


    /**
     * 创建选择并关联到累积选择
     * 并给 XDLocalSelection 赋予累积选择
     * @param entityDim
     * @param geom
     * @param coordinateVos
     * @param selection
     */
    public void select(int entityDim, GeomSequence geom, List<CoordinateVo> coordinateVos, XDLocalSelection selection) {
        if (CollectionUtil.isEmpty(coordinateVos)) {
            return;
        }
        String named = this.select(entityDim, geom, coordinateVos);
        selection.named(named);
    }





    // ~ 抽象方法


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
