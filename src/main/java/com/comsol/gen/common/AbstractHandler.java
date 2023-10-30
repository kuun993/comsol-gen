package com.comsol.gen.common;


import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.util.CollectionUtil;
import com.comsol.gen.vo.CoordinateVo;
import com.comsol.gen.vo.SelectVo;
import com.comsol.model.GeomSequence;
import com.comsol.model.ParameterEntity;
import com.comsol.model.XDLocalSelection;

import java.util.List;
import java.util.Map;

/**
 * @author chenbingkun
 * @date 2023/10/30 11:01
 * @email kuun993@163.com
 * @description TODO
 */
public abstract class AbstractHandler {



    /**
     * 设置参数
     * @param parameterEntity
     * @param properties
     */
    protected void setProperties(ParameterEntity parameterEntity, Map<String, String> properties) {
        if (properties == null || properties.size() == 0) {
            return;
        }
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            parameterEntity.set(entry.getKey(), entry.getValue());
        }
    }


    /**
     * 选择对象
     * @param select
     * @param geom
     * @param selection
     * @param selectVo
     */
    protected void selectDim(AbstractSelect select, GeomSequence geom, XDLocalSelection selection, SelectVo selectVo){
        List<CoordinateVo> coordinateVos = selectVo.getCoordinateVos();
        if (CollectionUtil.isEmpty(coordinateVos)) {
            return;
        }
        select.select(selectVo.getEntityDim(), geom, coordinateVos, selection);
    }


}
