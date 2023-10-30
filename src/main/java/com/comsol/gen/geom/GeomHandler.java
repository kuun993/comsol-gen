package com.comsol.gen.geom;


import com.comsol.gen.vo.GeomVo;
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
     * 导入几何
     *  1. 导入stp文件
     *  2. 形成 联合体 or 自动装配
     *  3. 构建导入的几何对象
     * @param geom
     * @param geomVo
     */
    public void geomImport(GeomSequence geom, GeomVo geomVo) {
        String impTag = TagUtil.impTag();
        // 创建几何导入: 组件 -> 几何 -> 导入
        GeomFeature geomFeature = geom.create(impTag, "Import");
        // 几何导入stp文件
        geomImportStp(geomFeature, geomVo.getStpFilePath());
        // fin
        fin(geom, geomVo);
        // 构建导入的几何对象
        run(geom, impTag);
    }






    /**
     * 几何导入stp文件
     * @param geomFeature
     * @param stpFilePath
     */
    private void geomImportStp(GeomFeature geomFeature, String stpFilePath) {
        geomFeature.set("type", "cad");
        geomFeature.set("filename", stpFilePath);
    }


    /**
     * 形成 联合体 or 自动装配
     * @param geom
     * @param geomVo
     */
    private void fin(GeomSequence geom, GeomVo geomVo) {
        GeomFeature fin = geom.feature("fin");
        fin.set("action", geomVo.getAction());
        fin.set("repairtoltype", geomVo.getRepairTolType());

        // when repairtoltype=relative, repairTol active.
        if ("relative".equals(geomVo.getRepairTolType())) {
            fin.set("repairtol", geomVo.getRepairTol());
        }
    }



    /**
     * 构建导入的几何对象
     * @param geom
     * @param impTag    不传构建所有对象
     */
    private void run(GeomSequence geom, String impTag) {
        if(impTag == null || impTag.isEmpty()) {
            geom.runPre("fin");
            return;
        }
        geom.run();
    }






}
