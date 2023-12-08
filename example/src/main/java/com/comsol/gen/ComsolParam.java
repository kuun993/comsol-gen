package com.comsol.gen;

import com.alibaba.fastjson.JSON;
import com.comsol.gen.enums.PhysicsEnum;
import com.comsol.gen.vo.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenbingkun
 * @date 2023/12/4 10:00
 * @description TODO
 */
public class ComsolParam {


    public static void main(String[] args) {
        ComsolVo comsolVo = new ComsolVo();
        comsolVo.setModelName("Model");

        comsolVo.setGeom(GeomVo.build("D:/opt/comsol/stp/PCB-ASSEM.stp"));


        List<PhysicsVo> physicsVos = new ArrayList<>();
        PhysicsVo physicsVo = new PhysicsVo();
        physicsVos.add(physicsVo);
        // 传热
        physicsVo.setPhysics(PhysicsEnum.HeatTransfer);
        List<PhysicsFeatureVo> physicsFeatureVos = new ArrayList<PhysicsFeatureVo>(2);
        // 热源
        physicsFeatureVos.add(PhysicsFeatureVo.buildHeatSource(0.03, 0.01, 0.01));
        // 热通量
        physicsFeatureVos.add(PhysicsFeatureVo.buildHeatFluxBoundary(0, -0.025, 0.001));
        physicsVo.setFeatures(physicsFeatureVos);
        comsolVo.setPhysics(physicsVos);

        List<MaterialVo> materialVos = new ArrayList<>();
        MaterialVo materialVo = MaterialVo.buildHeatTransfer(true);
        materialVos.add(materialVo);
        comsolVo.setMaterials(materialVos);

        List<MeshVo> meshes = new ArrayList<>();
        meshes.add(MeshVo.build());
        comsolVo.setMeshes(meshes);

        comsolVo.setResult(ResultVo.buildHeatTransfer("d:/opt/comsol/test/"));


        String json = JSON.toJSONString(comsolVo);
        System.out.println(json);

    }



}
