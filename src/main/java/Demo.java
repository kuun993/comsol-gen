import com.comsol.gen.ComsolInit;
import com.comsol.gen.common.enums.InitEnum;
import com.comsol.gen.geom.GeomHandler;
import com.comsol.gen.mat.MaterialHandler;
import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;
import com.comsol.gen.select.CumulativeSelect;
import com.comsol.gen.util.TagUtil;
import com.comsol.model.*;

/**
 * @author waani
 * @date 2023/10/9 11:07
 * @description TODO
 */
public class Demo {



    public static void run() {
        String modelTag = TagUtil.modelTag();
        String compTag = TagUtil.compTag();
        String geomTag = TagUtil.geomTag();
        String meshTag = TagUtil.meshTag();

        // 固体传热 + 稳态
        Model model = ComsolInit.init(InitEnum.HeatTransferInSolidsStationary, modelTag, "C:/Users/CBK/Desktop/ht-test", compTag, geomTag, meshTag);

        // 获取几何
        GeomSequence geom = model.geom(geomTag);

        // 导入模型
        String impTag = TagUtil.impTag();
        GeomFeature geomFeature = GeomHandler.geomImport(geom, impTag);
        GeomHandler.geomImportStp(geomFeature, "C:/Users/CBK/Desktop/ht-test/stp/PCB-ASSEM.stp");

        GeomHandler.run(geom, impTag);

        // 球选择
        AbstractSelect select = new BallSelect();

        // 生成球选择标签
        String ballTag = select.selectTag();

        // 创建球选择
        String selectNamedTag = select.createDomainSelect(geom, ballTag, 0.01, 0.01, 0.01);

       // 组件
        ModelNode comp = model.component(compTag);

//        comp.physics()

        // 创建材料
        Material material = MaterialHandler.createMaterial(comp);
        // 设置一些属性
        material.propertyGroup("def").set("density", "20");
        // named
        MaterialHandler.set2GeomByNamed(material, selectNamedTag);

    }


    public static void main(String[] args) {
        run();
    }


}
