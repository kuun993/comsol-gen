package com.comsol.gen.vo;

import lombok.*;

import java.io.Serializable;

/**
 * @author waani
 * @date 2023/11/17 09:34
 * @email kuun993@163.com
 * @description TODO
 */
@Getter
@Setter
@ToString
public class TaskInfo implements Serializable {


    private static final long serialVersionUID = -6916859935250341261L;

    /**
     * id
     */
    private long id;

    /**
     * 任务ID
     */
    private String taskId;

    /**
     * 参数内容
     */
    private String content;

}
