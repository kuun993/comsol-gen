package com.comsol.gen.select;

import com.comsol.model.GeomObjectSelectionFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.common.ComsolConstants;
import com.comsol.gen.util.TagUtil;

/**
 * @author waani
 * @date 2023/10/9 10:59
 * @description 累计选择
 */
public class CumulativeSelect {



    /**
     * geomTag_selectTag
     */
    private static final String NAMED_FORMAT = "%s_%s";


    /**
     * 创建累计选择
     * @param geom
     * @return
     */
    public static String createSelect(GeomSequence geom) {
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


    /**
     * 选择 累计选择的标签
     * @param geomTag
     * @param cSelTag
     * @return
     */
    public static String namedTag(String geomTag, String cSelTag) {
        return String.format(NAMED_FORMAT, geomTag, cSelTag);
    }

}
