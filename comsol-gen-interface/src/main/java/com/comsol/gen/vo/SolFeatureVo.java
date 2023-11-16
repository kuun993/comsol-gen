package com.comsol.gen.vo;

import com.comsol.gen.util.TagUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Map;

/**
 * @author waani
 * @date 2023/11/6 15:38
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class SolFeatureVo {

    /**
     * solverFeatureTag
     */
    private String solverFeatureTag;

    /**
     * solverFeature
     */
    private String solverFeature;

    /**
     * solverLabel
     */
    private String solverLabel;

    /**
     * properties
     */
    private Map<String, Object> properties;

    /**
     * solverFeatureTags
     */
    private String[] solverFeatureTags;

    /**
     * exist
     */
    private boolean exist;

    public SolFeatureVo() {}

    public SolFeatureVo(String solverFeature) {
        this.solverFeature = solverFeature;
        this.solverFeatureTag = TagUtil.uniqueTag(TagUtil.abbr(solverFeature));
    }

    public SolFeatureVo(String solverFeature, String solverFeatureTag) {
        this.solverFeature = solverFeature;
        this.solverFeatureTag = solverFeatureTag;
    }

    public SolFeatureVo(String solverFeature, String solverFeatureTag, boolean exist) {
        this.solverFeature = solverFeature;
        this.solverFeatureTag = solverFeatureTag;
        this.exist = exist;
    }

}
