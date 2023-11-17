package com.comsol.gen.web.service.impl;

import com.comsol.gen.vo.ComsolVo;
import com.comsol.gen.web.service.ComsolService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

@Log4j2
@SpringBootTest
class ComsolServiceImplTest {

    @Autowired
    private ComsolService comsolService;

    @Test
    public void transientTask() {
        ComsolVo comsolVo = new ComsolVo();
        comsolVo.setModelName("ModelName");
        String taskId = comsolService.transientTask(comsolVo);
        log.info("transientTask taskId={}", taskId);
        Assert.isTrue(taskId != null, "taskId is null.");
    }


    @Test
    public void select() {
        ComsolVo comsolVo = comsolService.select("0c4b33a9-96af-4977-82aa-ba740ed1f743");
        Assert.isTrue(comsolVo != null, "comsolVo is null.");
    }

}