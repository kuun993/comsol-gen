import com.comsol.gen.common.enums.PhysicsEnum;
import com.comsol.gen.comp.ModelHandler;
import com.comsol.gen.geom.GeomHandler;
import com.comsol.gen.mat.MaterialHandler;
import com.comsol.gen.mesh.MeshHandler;
import com.comsol.gen.physics.PhysicsHandler;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.*;
import com.comsol.model.GeomSequence;
import com.comsol.model.Model;
import com.comsol.model.ModelNode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author waani
 * @date 2023/10/13 16:57
 * @email kuun993@163.com
 * @description TODO
 */
public class HtTest {


    public static Model run() {
        // 模型组件
        ModelHandler modelHandler = new ModelHandler();
        Model model = modelHandler.build("Model", "D:/opt/comsol/stp/PCB-ASSEM.stp");
        String compTag = TagUtil.compTag();
        ModelNode modelNode = modelHandler.createComponent(model, compTag);

        // 几何
        GeomHandler geomHandler = new GeomHandler();
        GeomVo geomVo = GeomVo.build("d:/opt/comsol/stp/block.stp");
        GeomSequence geom = geomHandler.geomImport(modelNode, geomVo);


        // 物理场
        PhysicsHandler physicsHandler = new PhysicsHandler();
        PhysicsVo physicsVo = new PhysicsVo();
        // 传热
        physicsVo.setPhysics(PhysicsEnum.HeatTransfer);
        List<PhysicsFeatureVo> physicsFeatureVos = new ArrayList<>(2);
        physicsVo.setFeatures(physicsFeatureVos);
        // 热源
        physicsFeatureVos.add(PhysicsFeatureVo.buildHeatSource());
        // 热通量
        physicsFeatureVos.add(PhysicsFeatureVo.buildHeatFluxBoundary());
        physicsHandler.create(modelNode, geom, physicsVo);

        // 材料
        MaterialHandler materialHandler = new MaterialHandler();
        MaterialVo materialVo = MaterialVo.buildHeatTransfer(true);
        materialHandler.create(modelNode, geom, materialVo);

        // 网格
        MeshHandler meshHandler = new MeshHandler();
        MeshVo meshVo = MeshVo.build();
        meshHandler.create(modelNode, geom, meshVo);


        return model;
    }


    public static void main(String[] args) {
        run();
    }

}
