package com.comsol.gen.util;

import com.comsol.gen.common.ComsolConstants;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author waani
 * @date 2023/10/13 14:23
 * @email kuun993@163.com
 * @description TagUtil 标签工具
 */
public class TagUtil {

    private static final Map<String, AtomicInteger> _TAG = new HashMap<>();


    /**
     * Type of feature.
     * Feature tag.
     */
    private static final Map<String, String> _ABBR = new HashMap<>();


    static {
        _ABBR.put("SOR", "so");
        _ABBR.put("StudyStep", "st");
        _ABBR.put("Multigrid", "mg");
    }




    /**
     * 生成标签
     * @param prefix
     * @return
     */
    public static String uniqueTag(String prefix) {
        int index = 1;
        if (!_TAG.containsKey(prefix)) {
            synchronized (TagUtil.class) {
                if (!_TAG.containsKey(prefix)) {
                    _TAG.put(prefix, new AtomicInteger(1));
                }
            }
        }
        return prefix + _TAG.get(prefix).getAndIncrement();
    }

    public static String modelTag() {
        return uniqueTag(ComsolConstants.MODEL_TAG);
    }

    public static String compTag() {
        return uniqueTag(ComsolConstants.COMP_TAG);
    }

    public static String geomTag() {
        return uniqueTag(ComsolConstants.GEOM_TAG);
    }

    public static String impTag() {
        return uniqueTag(ComsolConstants.GEOM_IMPORT_TAG);
    }

    public static String stdTag() {
        return uniqueTag(ComsolConstants.STD_TAG);
    }

    public static String matTag() {
        return uniqueTag(ComsolConstants.MAT_TAG);
    }

    public static String meshTag() {
        return uniqueTag(ComsolConstants.MESH_TAG);
    }

    public static String cselTag() {
        return uniqueTag(ComsolConstants.CSEL_TAG);
    }

    public static String solTag() {
        return uniqueTag(ComsolConstants.SOL_TAG);
    }

    /**
     * 缩写
     * @param str
     * @return
     */
    public static String abbr(String str){
        if (_ABBR.containsKey(str)) {
            return _ABBR.get(str);
        }
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(Character.toLowerCase(str.charAt(0)));
        for (int i = 1; i < str.length(); i++) {
            char c = str.charAt(i);
            if (Character.isUpperCase(c)) {
                stringBuilder.append(Character.toLowerCase(c));
            }
        }
        String abbr;
        _ABBR.put(str, (abbr = stringBuilder.toString()));
        return abbr;
    }


    public static void main(String[] args) {
        System.out.println(abbr("Multigrid"));
        System.out.println(abbr("Multigrid"));
        System.out.println(abbr("SOR"));
        System.out.println(abbr("Direct"));
        System.out.println(abbr("Iterative"));
        System.out.println(abbr("FullyCoupled"));
        System.out.println(abbr("StudyStep"));
    }


}
