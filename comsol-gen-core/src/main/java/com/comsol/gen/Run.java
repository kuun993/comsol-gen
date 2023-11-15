package com.comsol.gen;

import com.comsol.gen.comp.ModelHandler;
import com.comsol.gen.enums.PhysicsEnum;
import com.comsol.gen.enums.StudyEnum;
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
 * @author chenbingkun
 * @date 2023/11/9 17:26
 * @email kuun993@163.com
 * @description TODO
 */
public class Run {




    public static Model run(ComsolVo vo) {

        // 模型组件
        ModelHandler modelHandler = new ModelHandler();
        Model model = modelHandler.build(vo.getModelName(), vo.getModelPath());
        // 组件tag
        String compTag = TagUtil.compTag();
        ModelNode modelNode = modelHandler.createComponent(model, compTag);

        // 几何
        GeomHandler geomHandler = new GeomHandler();
        GeomSequence geom = geomHandler.geomImport(modelNode, vo.getGeom());


        // 物理场
        PhysicsHandler physicsHandler = new PhysicsHandler();
        physicsHandler.create(modelNode, geom, vo.getPhysics());

        // 材料
        MaterialHandler materialHandler = new MaterialHandler();
        materialHandler.create(modelNode, geom, vo.getMaterials());

        // 网格
        MeshHandler meshHandler = new MeshHandler();
        meshHandler.create(modelNode, geom, vo.getMeshes());

        // 研究
        StudyHandler studyHandler = new StudyHandler();
        Study study = studyHandler.createStudy(model, StudyEnum.Transient, PhysicsEnum.HeatTransfer);

        // 求解
        SolverHandler solverHandler = new SolverHandler();
        solverHandler.createSolver(model, study.tag(), StudyEnum.Transient, PhysicsEnum.HeatTransfer);

        // 结果
        ResultHandler resultHandler = new ResultHandler();
        resultHandler.export(model, vo.getResult());

        

        return null;
    }




}
