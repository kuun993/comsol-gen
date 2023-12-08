package com.comsol.gen.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.comsol.gen.vo.ComsolVo;
import com.comsol.gen.web.config.ComsolProperty;
import com.comsol.gen.web.service.ComsolService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Service;

import java.io.*;
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


    private final ComsolProperty comsolProperty;

    private static final String INSERT_SQL = "INSERT INTO task_info\n" +
            "(task_id, content)\n" +
            "VALUES(?, ?);";


    @Override
    public String startTask(ComsolVo comsolVo) {
        String taskId = transientTask(comsolVo);

        runTask(taskId);

        return taskId;
    }

    @Override
    public String transientTask(ComsolVo comsolVo) {
        String taskId = UUID.randomUUID().toString();
        String content = JSON.toJSONString(comsolVo);
        jdbcTemplate.update(INSERT_SQL, taskId, content);
        return taskId;
    }

    @SneakyThrows
    @Override
    public void runTask(String taskId) {

        Process process = exec(comsolProperty.getBat(), taskId, comsolProperty.getDbProperties(), comsolProperty.getJar(), comsolProperty.getInputFile());

        InputStream inputStream = process.getInputStream();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));

        String line;
        while ((line = bufferedReader.readLine()) != null) {
            log.debug(line);
        }
    }




    private Process exec(String cmd, String ... envp) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        return runtime.exec(cmd, envp);
    }




}
