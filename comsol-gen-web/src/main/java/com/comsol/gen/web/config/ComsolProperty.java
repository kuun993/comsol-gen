package com.comsol.gen.web.config;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @author chenbingkun
 * @date 2023/12/8 9:06
 * @description TODO
 */
@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "comsol")
public class ComsolProperty {

    private String bat;

    private String dbProperties;

    private String jar;

    private String inputFile;

}
