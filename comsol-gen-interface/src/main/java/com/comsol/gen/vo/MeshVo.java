package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author waani
 * @date 2023/10/16 12:00
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class MeshVo extends BaseVo implements Serializable {

    private static final long serialVersionUID = -8563354063864808983L;


    /**
     * Mesh feature type
     */
    private String type;

    private double max;

    private double min;

    List<SizeVo> sizes;

    public String getType() {
        return "FreeTet";
    }



    public static MeshVo build() {
        MeshVo meshVo = new MeshVo();
        meshVo.type = "FreeTet";
        meshVo.max = 0.005;
        meshVo.min = 0.001;

        List<SizeVo> sizeVos = new ArrayList<>(1);
        sizeVos.add(SizeVo.build());
        meshVo.sizes = sizeVos;

        return meshVo;
    }


}
