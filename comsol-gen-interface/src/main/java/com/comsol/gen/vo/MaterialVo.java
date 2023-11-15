package com.comsol.gen.vo;

import com.comsol.gen.enums.EntityDimEnum;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author waani
 * @date 2023/10/16 10:51
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class MaterialVo extends BaseVo implements Serializable {


    /**
     * 0-3 分别表示点、边、边界和域，默认为域
     */
    private SelectVo select;


    /**
     * 材料属性
     */
    private Map<String, String> properties;


    /**
     * 材料组
     */
    private String group;


    public String getGroup() {
        if (group == null || group.isEmpty()) {
            return "def";
        }
        return group;
    }


    public static MaterialVo build(int entityDim, double x, double y, double z) {
        MaterialVo obj = new MaterialVo();
        SelectVo selectVo = new SelectVo(x, y, z);
        selectVo.setEntityDim(entityDim);
        obj.select = selectVo;
        return obj;
    }


    /**
     * HeatTransfer 材料
     * @param all
     * @return
     */
    public static MaterialVo buildHeatTransfer(boolean all) {
        MaterialVo materialVo = buildHeatTransfer(0, 0, 0);
        if (!all) {
            return materialVo;
        }
        materialVo.setSelect(new SelectVo(true));
        return materialVo;
    }





    /**
     * HeatTransfer 材料
     * @param x
     * @param y
     * @param z
     * @return
     */
    public static MaterialVo buildHeatTransfer(double x, double y, double z) {
        Map<String, String> properties = new HashMap<>(3);
        properties.put("thermalconductivity", "40");
        properties.put("density", "6000");
        properties.put("heatcapacity", "600");
        MaterialVo obj = build(EntityDimEnum.DOMAIN.ordinal(), x, y, z);
        obj.properties = properties;
        return obj;
    }



}
