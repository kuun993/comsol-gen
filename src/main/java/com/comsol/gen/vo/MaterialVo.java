package com.comsol.gen.vo;

import com.comsol.gen.util.StringUtil;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
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
        if (StringUtil.isEmpty(group)) {
            return "def";
        }
        return group;
    }
}
