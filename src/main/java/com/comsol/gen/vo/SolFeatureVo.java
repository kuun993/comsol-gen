package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author chenbingkun
 * @date 2023/11/6 15:38
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class SolFeatureVo {

    private String solverFeature;

    private Map<String, Object> param;

    private SolFeatureVo solFeature;

    private boolean exist;


    public SolFeatureVo() {}

    public SolFeatureVo(String solverFeature) {
        this.solverFeature = solverFeature;
    }

    public SolFeatureVo(String solverFeature, boolean exist) {
        this.solverFeature = solverFeature;
        this.exist = exist;
    }

}
