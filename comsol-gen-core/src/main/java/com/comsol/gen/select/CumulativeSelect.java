package com.comsol.gen.select;

import com.comsol.model.GeomObjectSelectionFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.common.ComsolConstants;
import com.comsol.gen.util.TagUtil;

/**
 * @author waani
 * @date 2023/10/9 10:59
 * @email kuun993@163.com
 * @description 累计选择
 */
public class CumulativeSelect {



    /**
     * 创建累计选择
     * @param geom
     * @return
     */
    public static String createCumulativeSelect(GeomSequence geom) {
        String name = TagUtil.cselTag();
        createCumulativeSelect(geom, name, name);
        return name;
    }


    /**
     * 创建累计选择
     * @param geom
     * @param cSelTag
     * @param label
     */
    public static void createCumulativeSelect(GeomSequence geom, String cSelTag, String label) {
        GeomObjectSelectionFeature cumulativeSelection = geom.selection().create(cSelTag, ComsolConstants.CumulativeSelection);
        cumulativeSelection.label(label);
    }


}
