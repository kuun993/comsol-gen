package com.comsol.gen.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.comsol.gen.vo.ComsolVo;
import com.comsol.gen.vo.TaskInfo;
import com.comsol.gen.web.service.ComsolService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author waani
 * @date 2023/11/16 17:27
 * @email kuun993@163.com
 * @description TODO
 */
@Log4j2
@RequiredArgsConstructor
@Service
public class ComsolServiceImpl implements ComsolService {


    private final JdbcTemplate jdbcTemplate;


    private static final String SELECT_SQL = "SELECT id, task_id, content, create_time FROM task_info WHERE task_id=?;";


    private static final String INSERT_SQL = "INSERT INTO task_info\n" +
            "(task_id, content)\n" +
            "VALUES(?, ?);";


    @Override
    public String startTask(ComsolVo comsolVo) {
        return null;
    }

    @Override
    public String transientTask(ComsolVo comsolVo) {
        String taskId = UUID.randomUUID().toString();
        String content = JSON.toJSONString(comsolVo);
        jdbcTemplate.update(INSERT_SQL, taskId, content);
        return taskId;
    }

    @Override
    public void runTask(String taskId) {

        JdbcTemplate template = new JdbcTemplate();
    }


    @Override
    public ComsolVo select(String taskId) {
        RowMapper<TaskInfo> mapper = new BeanPropertyRowMapper<>(TaskInfo.class);
        TaskInfo taskInfo = jdbcTemplate.queryForObject(SELECT_SQL, new BeanPropertyRowMapper<TaskInfo>(TaskInfo.class), taskId);
        String content = taskInfo.getContent();
        return JSON.parseObject(content, ComsolVo.class);
    }




}
