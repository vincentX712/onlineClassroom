package com.officerschool.onlineclassroom.dao.dataobject;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.sql.Timestamp;

/**
 * @author : create by xiangwenchao@zhejianglab.com
 * @version : v1.0
 * @date : 4/16/24
 */
@Data
@TableName("t_task")
public class TaskDO {

    private String id;

    private String name;

    private String description;

    private Integer stepNumber;

    private String stepConfig; // 所有步骤配置的json字符串

    private String answer; // 答案

    private Integer state;

    private Timestamp createTime;

    private Timestamp updateTime;
}
