package com.comsol.gen.common;

import com.comsol.gen.util.CollectionUtil;
import com.comsol.gen.vo.BaseVo;
import com.comsol.model.GeomSequence;
import com.comsol.model.ModelNode;

import java.util.List;

/**
 * @author waani
 * @date 2023/10/30 14:24
 * @email kuun993@163.com
 * @description TODO
 */
public interface HandlerInterface<T extends BaseVo> {

    /**
     * create
     * @param comp
     * @param geom
     * @param list
     */
    default void create(ModelNode comp, GeomSequence geom, List<T> list) {
        if (CollectionUtil.isEmpty(list)) {
            return;
        }
        for (T t : list) {
            create(comp, geom, t);
        }
    }


    /**
     * create
     * @param comp
     * @param geom
     * @param t
     */
    void create(ModelNode comp, GeomSequence geom, T t);

}
