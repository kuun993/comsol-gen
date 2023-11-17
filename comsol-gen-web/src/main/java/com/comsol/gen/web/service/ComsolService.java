package com.comsol.gen.web.service;

import com.comsol.gen.vo.ComsolVo;

/**
 * comsol service
 * @author waani
 * @date 2023/11/9 11:27
 * @email kuun993@163.com
 * @description 
 */
public interface ComsolService {


    /**
     * 开始任务
     * @param comsolVo
     * @return
     */
    String startTask(ComsolVo comsolVo);


    /**
     * 持久化任务
     * @param comsolVo
     * @return
     */
    String transientTask(ComsolVo comsolVo);


    /**
     * 运行任务
     * @param taskId
     */
    void runTask(String taskId);



    ComsolVo select(String taskId);


}
