package com.officerschool.onlineclassroom.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author : create by anyuxin
 * @version : v1.0
 * @date : 4/16/24
 */
@Data
@TableName("t_task_step")
public class TaskStepDO {

    private String id;

    private String taskId;

    private String name;

    private String description;

    private String device_name;

    private String config;

    private Timestamp createTime;

    private Timestamp updateTime;
}
