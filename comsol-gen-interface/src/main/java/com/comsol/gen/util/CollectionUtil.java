package com.comsol.gen.util;

import java.util.Collection;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/30 10:32
 * @email kuun993@163.com
 * @description TODO
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return null == collection || collection.isEmpty();
    }


    public static boolean isEmpty(Map<?, ?> map) {
        return (map == null || map.isEmpty());
    }

}