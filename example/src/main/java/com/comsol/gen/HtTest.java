package com.comsol.gen;

import com.comsol.gen.common.enums.PhysicsEnum;
import com.comsol.gen.common.enums.StudyEnum;
import com.comsol.gen.comp.ModelHandler;
import com.comsol.gen.geom.GeomHandler;
import com.comsol.gen.mat.MaterialHandler;
import com.comsol.gen.mesh.MeshHandler;
import com.comsol.gen.physics.PhysicsHandler;
import com.comsol.gen.result.ResultHandler;
import com.comsol.gen.std.SolverHandler;
import com.comsol.gen.std.StudyHandler;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.*;
import com.comsol.model.GeomSequence;
import com.comsol.model.Model;
import com.comsol.model.ModelNode;
import com.comsol.model.Study;

import java.util.ArrayList;
import java.util.List;


/**
 * @author waani
 * @date 2023/10/13 16:57
 * @email kuun993@163.com
 * @description 固体传热
 */
public class HtTest {


    public static Model run() {
        // 模型组件
        ModelHandler modelHandler = new ModelHandler();
        Model model = modelHandler.build("Model", "D:/opt/comsol/");
        String compTag = TagUtil.compTag();
        ModelNode modelNode = modelHandler.createComponent(model, compTag);

        // 几何
        GeomHandler geomHandler = new GeomHandler();
        GeomVo geomVo = GeomVo.build("D:/opt/comsol/stp/PCB-ASSEM.stp");
        GeomSequence geom = geomHandler.geomImport(modelNode, geomVo);


        // 物理场
        PhysicsHandler physicsHandler = new PhysicsHandler();
        PhysicsVo physicsVo = new PhysicsVo();
        // 传热
        physicsVo.setPhysics(PhysicsEnum.HeatTransfer);
        List<PhysicsFeatureVo> physicsFeatureVos = new ArrayList<PhysicsFeatureVo>(2);
        // 热源
        physicsFeatureVos.add(PhysicsFeatureVo.buildHeatSource(0.03, 0.01, 0.01));
        // 热通量
        physicsFeatureVos.add(PhysicsFeatureVo.buildHeatFluxBoundary(0, -0.025, 0.001));
        physicsVo.setFeatures(physicsFeatureVos);
        physicsHandler.create(modelNode, geom, physicsVo);

        // 材料
        MaterialHandler materialHandler = new MaterialHandler();
        MaterialVo materialVo = MaterialVo.buildHeatTransfer(true);
        materialHandler.create(modelNode, geom, materialVo);

        // 网格
        MeshHandler meshHandler = new MeshHandler();
        MeshVo meshVo = MeshVo.build();
        meshHandler.create(modelNode, geom, meshVo);

        // 研究
        StudyHandler studyHandler = new StudyHandler();
        Study study = studyHandler.createStudy(model, StudyEnum.Transient, PhysicsEnum.HeatTransfer);

        // 求解
        SolverHandler solverHandler = new SolverHandler();
        solverHandler.createSolver(model, study.tag(), StudyEnum.Transient, PhysicsEnum.HeatTransfer);

        // 结果
        ResultHandler resultHandler = new ResultHandler();
        ResultVo resultVo = ResultVo.buildHeatTransfer("d:/opt/comsol/test/");

        resultHandler.export(model, resultVo);

        return model;
    }


    public static void main(String[] args) {
        try {
            run();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            throw new RuntimeException(e.getMessage());
        }
    }

}
