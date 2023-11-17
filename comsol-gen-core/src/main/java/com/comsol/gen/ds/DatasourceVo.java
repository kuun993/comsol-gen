package com.comsol.gen.ds;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author waani
 * @date 2023/11/17 10:41
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class DatasourceVo {

    private String driver;

    private String url;

    private String username;

    private String password;

}
