package com.comsol.gen;

import com.alibaba.fastjson.JSON;
import com.comsol.gen.vo.ComsolVo;
import com.comsol.gen.vo.TaskInfo;
import com.comsol.model.Model;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.SneakyThrows;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

/**
 * @author waani
 * @date 2023/11/17 10:33
 * @email kuun993@163.com
 * @description TODO
 */
public class Exec {


    private static JdbcTemplate jdbcTemplate;


    private static final String SELECT_SQL = "SELECT id, task_id, content, create_time FROM task_info WHERE task_id=?;";


    private static JdbcTemplate getJdbcTemplate(Properties properties) {
        if (jdbcTemplate != null) {
            return jdbcTemplate;
        }
        HikariConfig config = new HikariConfig();
        config.setDriverClassName(properties.getProperty("ds.driver"));
        config.setJdbcUrl(properties.getProperty("ds.url"));
        config.setUsername(properties.getProperty("ds.username"));
        config.setPassword(properties.getProperty("ds.password"));
        DataSource dataSource = new HikariDataSource(config);
        jdbcTemplate = new JdbcTemplate(dataSource);
        return jdbcTemplate;
    }







    private static ComsolVo selectParam(String taskId, Properties properties) {
        TaskInfo taskInfo = getJdbcTemplate(properties).queryForObject(SELECT_SQL, new BeanPropertyRowMapper<>(TaskInfo.class), taskId);
        String content;
        if (taskInfo == null || (content = taskInfo.getContent()) == null) {
            return null;
        }
        return JSON.parseObject(content, ComsolVo.class);
    }



    @SneakyThrows
    private static Properties properties(String path) {
        Properties properties = new Properties();
        try (InputStream inputStream = Files.newInputStream(Paths.get(path))) {
            properties.load(inputStream);
        }
        return properties;
    }



    public static Model start(String taskId, String configPath) {
        Properties properties = properties(configPath);
        ComsolVo comsolVo = selectParam(taskId, properties);
        if (comsolVo == null) {
            return null;
        }
        return Run.run(comsolVo);
    }




}
