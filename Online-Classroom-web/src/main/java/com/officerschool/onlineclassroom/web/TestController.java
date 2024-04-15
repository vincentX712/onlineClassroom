package com.officerschool.onlineclassroom.web;

import com.officerschool.onlineclassroom.common.enums.ErrorCodeEnum;
import com.officerschool.onlineclassroom.common.models.CommonResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author : create by xiangwenchao@zhejianglab.com
 * @version : v1.0
 * @date : 4/15/24
 */
@RestController
@RequestMapping("/test")
public class TestController {
    @RequestMapping(value = "/func1", method = RequestMethod.GET)
    public CommonResult func1() {
        try {
            return CommonResult.createOK("func1 测试成功");
        } catch (Exception e) {
            return CommonResult.fail(ErrorCodeEnum.SERVER_ERROR);
        }
    }
}
