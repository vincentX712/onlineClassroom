package com.officerschool.onlineclassroom.web;

import com.alibaba.fastjson.JSONObject;
import com.officerschool.onlineclassroom.common.enums.ErrorCodeEnum;
import com.officerschool.onlineclassroom.common.models.CommonResult;
import com.officerschool.onlineclassroom.common.models.req.NewTaskReq;
import com.officerschool.onlineclassroom.common.models.req.TaskPageReq;
import com.officerschool.onlineclassroom.service.TaskService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author : create by xiangwenchao@zhejianglab.com
 * @version : v1.0
 * @date : 4/16/24
 */
@RestController
@RequestMapping("/task")
public class TaskController {

    private final Logger logger = LoggerFactory.getLogger(TaskController.class);

    @Resource
    private TaskService taskService;

    @RequestMapping(value = "/taskList", method = RequestMethod.GET)
    public CommonResult taskList(TaskPageReq req) {
        try {
            return CommonResult.createOK(taskService.taskList(req));
        } catch (Exception e) {
            logger.error("TaskController#tasks error", e);
            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/newTask", method = RequestMethod.POST)
    public CommonResult newTask(@RequestBody NewTaskReq req) {
        try {
            if (StringUtils.isBlank(req.getTaskName()) || StringUtils.isBlank(req.getStepNumber().toString())) {
                return CommonResult.fail(ErrorCodeEnum.REQUEST_PARAM_NULL);
            }
            return CommonResult.createOK(taskService.newTask(req));
        } catch (Exception e) {
            logger.error("TaskController#newTask error", e);
            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/deleteTask", method = RequestMethod.POST)
    public CommonResult deleteTask(@RequestBody JSONObject req) {
        try {
            String taskId = req.getString("taskId");
            if (StringUtils.isBlank(taskId)) {
                return CommonResult.fail(ErrorCodeEnum.REQUEST_PARAM_NULL);
            }
            return CommonResult.createOK(taskService.deleteTask(taskId));
        } catch (Exception e) {
            logger.error("TaskController#deleteTask error", e);
            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
        }
    }

    @RequestMapping(value = "/taskDetail", method = RequestMethod.GET)
    public CommonResult taskDetail(String taskId) {
        try {
            if (StringUtils.isBlank(taskId)) {
                return CommonResult.fail(ErrorCodeEnum.REQUEST_PARAM_NULL);
            }
            return CommonResult.createOK(taskService.taskDetail(taskId));
        } catch (Exception e) {
            logger.error("TaskController#taskDetail error", e);
            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
        }
    }
}
