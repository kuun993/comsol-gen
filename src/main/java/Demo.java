import com.comsol.gen.ComsolInit;
import com.comsol.gen.common.enums.InitEnum;
import com.comsol.gen.geom.GeomHandler;
import com.comsol.gen.mat.MaterialHandler;
import com.comsol.gen.select.AbstractSelect;
import com.comsol.gen.select.BallSelect;
import com.comsol.gen.select.CumulativeSelect;
import com.comsol.gen.util.TagUtil;
import com.comsol.gen.vo.GeomVo;
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
        Model model = ComsolInit.init(InitEnum.HeatTransferInSolidsStationary, modelTag, "C:/Users/CBK/Desktop/ht-test", compTag);

        ModelNode modelNode = model.component("compTag");

        // 获取几何
        GeomSequence geom = model.geom(geomTag);


        // 导入模型
        GeomHandler geomHandler = new GeomHandler();
        GeomVo geomVo = new GeomVo();
        // set geomVo
        geomHandler.geomImport(modelNode, geomVo);


        // 球选择
        AbstractSelect select = new BallSelect();

        // 生成球选择标签
        String ballTag = select.selectTag();

        // 创建球选择
        String selectNamedTag = select.createDomainSelect(geom, ballTag, 0.01, 0.01, 0.01);

       // 组件
        ModelNode comp = model.component(compTag);



    }


    public static void main(String[] args) {
        run();
    }


}
