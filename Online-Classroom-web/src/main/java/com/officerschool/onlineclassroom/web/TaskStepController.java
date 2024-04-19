package com.officerschool.onlineclassroom.web;

import com.officerschool.onlineclassroom.common.enums.ErrorCodeEnum;
import com.officerschool.onlineclassroom.common.models.CommonResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : create by xiangwenchao@zhejianglab.com
 * @version : v1.0
 * @date : 4/16/24
 */
@RestController
@RequestMapping(value = "/taskStep")
public class TaskStepController {

//    private final Logger logger = LoggerFactory.getLogger(TaskStepController.class);
//
//    @RequestMapping(value = "/stepList", method = RequestMethod.GET)
//    public CommonResult taskStepList(String taskId)
//    {
//        try {
//            return CommonResult.createOK();
//        } catch (Exception e) {
//            logger.error("TaskStepController#taskStepList error", e);
//            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/newTaskStep", method = RequestMethod.POST)
//    public CommonResult newTaskStep() {
//        try {
//            return CommonResult.createOK();
//        } catch (Exception e) {
//            logger.error("TaskStepController#newTaskStep error", e);
//            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/deleteTaskStep", method = RequestMethod.POST)
//    public CommonResult deleteTaskStep(String taskStepId) {
//        try {
//            return CommonResult.createOK();
//        } catch (Exception e) {
//            logger.error("TaskStepController#deleteTaskStep error", e);
//            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
//        }
//    }
//
//    @RequestMapping(value = "/taskStepDetail", method = RequestMethod.GET)
//    public CommonResult taskStepDetail(String taskStepId) {
//        try {
//            return CommonResult.createOK();
//        } catch (Exception e) {
//            logger.error("TaskStepController#taskStepDetail error", e);
//            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
//        }
//    }
}
