package com.comsol.gen.select;

import com.comsol.gen.common.ComsolConstants;
import com.comsol.model.GeomFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.util.TagUtil;

/**
 * @author waani
 * @date 2023/10/9 10:26
 * @email kuun993@163.com
 * @description 球选择
 */
public class BallSelect extends AbstractSelect {

    /**
     * 半径
     */
    private static final double R = 1E-8;


    /**
     * SELECT_TAG
     */
    private static final String SELECT_TAG = "ballsel";





    @Override
    public GeomFeature createSelect(int entityDim, GeomSequence geom, String selectTag, double x, double y, double z) {
        // 创建球选择
        GeomFeature geomFeature = geom.create(selectTag, ComsolConstants.BallSelection);
        // 设置几何实体层
        geomFeature.set("entitydim", entityDim);
        // 设置球心坐标
        geomFeature.set("posx", x);
        geomFeature.set("posy", y);
        geomFeature.set("posz", z);
        // 设置半径
        geomFeature.set("r", R);
        return geomFeature;
    }

    @Override
    public String selectTag() {
        return TagUtil.uniqueTag(SELECT_TAG);
    }
}
