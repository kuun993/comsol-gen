package com.comsol.gen.geom;


import com.comsol.gen.vo.GeomVo;
import com.comsol.model.GeomFeature;
import com.comsol.model.GeomSequence;
import com.comsol.gen.util.TagUtil;
import com.comsol.model.ModelNode;


/**
 * @author waani
 * @date 2023/9/27 17:26
 * @email kuun993@163.com
 * @description 几何处理器
 */
public class GeomHandler {


    /**
     * 几何
     * 默认三维
     */
    private static final int GeometryDimension = 3;


    /**
     * 导入几何
     *  1. 导入stp文件
     *  2. 形成 联合体 or 自动装配
     *  3. 构建导入的几何对象
     * @param modelNode
     * @param geomVo
     */
    public GeomSequence geomImport(ModelNode modelNode, GeomVo geomVo) {
        // 创建组件几何
        String geomTag = TagUtil.geomTag();
        GeomSequence geomSequence = modelNode.geom().create(geomTag, GeometryDimension);

        // 创建几何导入: 组件 -> 几何 -> 导入
        // 导入stp文件
        String impTag = TagUtil.impTag();
        GeomFeature geomFeature = geomSequence.create(impTag, "Import");
        geomImportStp(geomFeature, geomVo.getStpFilePath());

        // fin
        fin(geomSequence, geomVo);

        // 构建导入的几何对象
        run(geomSequence, impTag);

        return geomSequence;
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
     * @param geomSequence
     * @param geomVo
     */
    private void fin(GeomSequence geomSequence, GeomVo geomVo) {
        GeomFeature fin = geomSequence.feature("fin");
        fin.set("action", geomVo.getAction());
        fin.set("repairtoltype", geomVo.getRepairTolType());

        // when repairtoltype=relative, repairTol active.
        if ("relative".equals(geomVo.getRepairTolType())) {
            fin.set("repairtol", geomVo.getRepairTol());
        }

        fin.set("isCreatePairs", geomVo.isCreatePairs());


    }



    /**
     * 构建导入的几何对象
     * @param geomSequence
     * @param impTag    不传构建所有对象
     */
    private void run(GeomSequence geomSequence, String impTag) {
        if(impTag == null || impTag.isEmpty()) {
            geomSequence.runPre("fin");
            return;
        }
        geomSequence.run();
    }






}
