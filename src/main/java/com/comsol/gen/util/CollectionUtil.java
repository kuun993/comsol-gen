package com.comsol.gen.util;

import java.util.Collection;

/**
 * @author waani
 * @date 2023/10/30 10:32
 * @email kuun993@163.com
 * @description TODO
 */
public class CollectionUtil {

    public static boolean isEmpty(Collection<?> collection) {
        if (null == collection || collection.isEmpty()) {
            return false;
        }
        return true;
    }

}
