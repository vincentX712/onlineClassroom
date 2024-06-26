package com.officerschool.onlineclassroom.common.models.dto;

import lombok.Data;

import java.sql.Timestamp;

/**
 * @author : create by anyuxin
 * @version : v1.0
 * @date : 4/18/24
 */
@Data
public class TaskDTO {
    private String id;

    private String name;

    private String description;

    private Integer stepNumber;

    private Integer state; // 所有步骤配置的json字符串

    private Timestamp createTime;

    private Timestamp updateTime;
}
