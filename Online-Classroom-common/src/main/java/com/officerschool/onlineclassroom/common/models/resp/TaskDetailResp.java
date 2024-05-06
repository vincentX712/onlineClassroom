package com.officerschool.onlineclassroom.common.models.resp;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

/**
 * @author : create by anyuxin
 * @version : v1.0
 * @date : 4/19/24
 */
@Data
public class TaskDetailResp {

    private String taskId;

    private String taskName;

    private String taskDescription;

    private Integer stepNumber;

    private String answer;

    private JSONObject completionStatus; // 学生完成情况
}
