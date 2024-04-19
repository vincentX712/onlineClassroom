package com.officerschool.onlineclassroom.common.models.req;

import lombok.Data;

/**
 * @author : create by xiangwenchao@zhejianglab.com
 * @version : v1.0
 * @date : 4/16/24
 */
@Data
public class NewTaskReq {

    private String taskName;

    private String description;

    private Integer stepNumber;

    private String step_config; // 所有步骤配置的json string
}
