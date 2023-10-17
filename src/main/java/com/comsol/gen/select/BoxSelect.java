package com.comsol.gen.select;

import com.comsol.gen.common.ComsolConstants;
import com.comsol.model.GeomFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.util.TagUtil;

/**
 * @author waani
 * @date 2023/10/9 15:03
 * @description 框选择
 */
public class BoxSelect extends AbstractSelect {

    /**
     * 最小差值
     */
    private static final double MIN = 0.00001;


    /**
     * SELECT_TAG
     */
    private static final String SELECT_TAG = "boxsel";

    @Override
    public GeomFeature createSelect(int entityDim, GeomSequence geom, String boxSelectTag, double x, double y, double z) {
        // 创建框选择
        GeomFeature geomFeature = geom.create(boxSelectTag, ComsolConstants.BoxSelection);
        // 设置几何实体层
        geomFeature.set("entitydim", entityDim);
        // 设置框区域
        geomFeature.set("xmin", x - MIN);
        geomFeature.set("xmax", x + MIN);
        geomFeature.set("ymin", y - MIN);
        geomFeature.set("ymax", y + MIN);
        geomFeature.set("zmin", z - MIN);
        geomFeature.set("zmax", z + MIN);
       return geomFeature;
    }


    @Override
    public String selectTag() {
        return TagUtil.uniqueTag(SELECT_TAG);
    }
}
