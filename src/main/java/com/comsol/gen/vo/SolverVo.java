package com.comsol.gen.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;
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
public class SolverVo {

    private String studyTag;

    private String solverTag;


    private List<SolFeatureVo> solFeatureVos;

}
