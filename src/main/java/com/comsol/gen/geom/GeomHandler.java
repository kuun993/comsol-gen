package com.comsol.gen.geom;


import com.comsol.model.GeomFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.util.TagUtil;


/**
 * @author waani
 * @date 2023/9/27 17:26
 * @description 几何处理器
 */
public class GeomHandler {



    /**
     * 创建几何导入
     * 组件 -> 几何 -> 导入
     * @param geom
     * @return
     */
    public static GeomFeature geomImport(GeomSequence geom) {
        String impTag = TagUtil.impTag();
        return geomImport(geom, impTag);
    }


    /**
     * 创建几何导入
     * 组件 -> 几何 -> 导入
     * @param geom
     * @param impTag
     * @return
     */
    public static GeomFeature geomImport(GeomSequence geom, String impTag) {
        return geom.create(impTag, "Import");
    }


    /**
     * 几何导入stp文件
     * @param geomFeature
     * @param filename
     */
    public static void geomImportStp(GeomFeature geomFeature, String filename) {
        geomFeature.set("type", "cad");
        geomFeature.set("filename", filename);
    }


    /**
     * 构建导入的几何对象
     * @param geom
     * @param impTag    不传构建所有对象
     */
    public static void run(GeomSequence geom, String impTag) {
        if(impTag == null || impTag.isEmpty()) {
            geom.runPre("fin");
            return;
        }
        geom.run(impTag);
    }

}
