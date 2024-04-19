package com.officerschool.onlineclassroom.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.officerschool.onlineclassroom.common.models.dto.TaskDTO;
import com.officerschool.onlineclassroom.common.models.req.NewTaskReq;
import com.officerschool.onlineclassroom.common.models.req.TaskPageReq;
import com.officerschool.onlineclassroom.common.models.resp.TaskDetailResp;
import com.officerschool.onlineclassroom.dao.dataobject.TaskDO;
import com.officerschool.onlineclassroom.dao.mapper.TaskMapper;
import com.officerschool.onlineclassroom.service.utils.FileUtil;
import com.officerschool.onlineclassroom.service.utils.PageUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.*;

/**
 * @author : create by xiangwenchao@zhejianglab.com
 * @version : v1.0
 * @date : 4/16/24
 */
@Service
public class TaskService {

    @Resource
    private TaskMapper taskMapper;

    public String newTask(NewTaskReq req) {
        TaskDO taskDO = new TaskDO();
        String id = UUID.randomUUID().toString().replace("-", "");
        taskDO.setId(id);
        taskDO.setName(req.getTaskName());
        taskDO.setDescription(req.getDescription());
        taskDO.setStepNumber(req.getStepNumber());
        taskDO.setStepConfig(req.getStep_config());
        taskDO.setState(0);
        Timestamp now = new Timestamp(System.currentTimeMillis());
        taskDO.setCreateTime(now);
        taskDO.setUpdateTime(now);
        taskMapper.insert(taskDO);
        return id;
    }

    public PageInfo<TaskDTO> taskList(TaskPageReq req) {
        int pageNum = req.getPageNum() == null ? 1 : req.getPageNum();
        int pageSize = req.getPageSize() == null ? 10 : req.getPageSize();

        PageHelper.startPage(pageNum, pageSize);
        QueryWrapper<TaskDO> queryWrapper = new QueryWrapper<>();

        List<TaskDO> taskDOList = taskMapper.selectList(queryWrapper);
        PageInfo<TaskDO> pageInfo = new PageInfo<>(taskDOList);
        PageInfo<TaskDTO> resPage = PageUtil.convertPageInfo2PageInfoVo(pageInfo);

        List<TaskDTO> resList = new ArrayList<>();
        for (TaskDO taskDO : taskDOList) {
            TaskDTO taskDTO = new TaskDTO();
            taskDTO.setId(taskDO.getId());
            taskDTO.setName(taskDO.getName());
            taskDTO.setDescription(taskDO.getDescription());
            taskDTO.setStepNumber(taskDO.getStepNumber());
            taskDTO.setStepConfig(taskDO.getStepConfig());
            taskDTO.setCreateTime(taskDO.getCreateTime());
            resList.add(taskDTO);
        }
        resPage.setList(resList);
        return resPage;
    }

    public boolean deleteTask(String taskId) {
        if (StringUtils.isBlank(taskId))
            return false;
        UpdateWrapper<TaskDO> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", taskId);
        updateWrapper.ne("state", 1);
        TaskDO taskDO = new TaskDO();
        taskDO.setState(1);
        taskDO.setUpdateTime(new Timestamp(System.currentTimeMillis()));
        return taskMapper.update(taskDO, updateWrapper) > 0;
    }
    public TaskDO taskInfo(String taskId) {
        QueryWrapper<TaskDO> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", taskId);
        queryWrapper.eq("state", 0);
        return taskMapper.selectOne(queryWrapper);
    }

    public TaskDetailResp taskDetail(String taskId) {
        // 1. 查询任务信息
        TaskDO taskDO = taskInfo(taskId);
        if (taskDO == null)
            return null;

        TaskDetailResp taskDetailResp = new TaskDetailResp();
        taskDetailResp.setTaskId(taskDO.getId());
        taskDetailResp.setTaskName(taskDO.getName());
        taskDetailResp.setTaskDescription(taskDO.getDescription());
        taskDetailResp.setStepNumber(taskDO.getStepNumber());
        taskDetailResp.setStepConfig(taskDO.getStepConfig());

//        JSONArray answerArray = new JSONArray();
//        String step1Aaswer = "#\n" +
//                "diffserv domain default\n" +
//                "#\n" +
//                "drop-profile default\n" +
//                "#\n" +
//                " authentication-scheme default\n" +
//                " authorization-scheme default\n" +
//                " accounting-scheme default\n" +
//                " domain default \n" +
//                " domain default_admin \n" +
//                " local-user admin password simple admin\n" +
//                " local-user admin service-type http\n" +
//                "#";
//        JSONObject answerObject1 = new JSONObject();
//        answerObject1.put("num", "step1");
//        answerObject1.put("cfg", step1Aaswer);
//        answerArray.add(answerObject1);
//
//        JSONObject answerObject2 = new JSONObject();
//        answerObject2.put("num", "step2");
//        answerObject2.put("cfg", step1Aaswer);
//        answerArray.add(answerObject2);
//
//        JSONObject answerObject3 = new JSONObject();
//        answerObject3.put("num", "step3");
//        answerObject3.put("cfg", step1Aaswer);
//        answerArray.add(answerObject3);
//
//        System.out.println(answerArray);
        String answer = taskDO.getAnswer();
        Map<String, String> answerMap = parseJsonToMap(answer);


        // 2. 获取学生提交文件内容，并与标准答案进行对比
        String path = "/Users/wenchaoxiang/workspace/onlineClassroom/submit/";
        List<String> studentFiles = FileUtil.listAllFile(path, false);
        if (studentFiles == null || studentFiles.isEmpty())
            return taskDetailResp;

        JSONObject completionStatus = new JSONObject();
        for (String studentFile : studentFiles) { //  Todo:计算完成后将文件的更新时间存入mysql或缓存，避免重复计算
            if (!studentFile.endsWith(".cfg")) // 只识别cfg文件
                continue;
            String studentAnswer = FileUtil.readFileToString(path + studentFile);
            if (StringUtils.isBlank(studentAnswer))
                continue;

            studentAnswer = null;
//            System.gc();

            JSONObject completionItem = new JSONObject();
            completionItem.put("total_percent", "25%");
            Map<String, String> stepPercent = new HashMap<>();
            stepPercent.put("step1", "10%");
            stepPercent.put("step2", "50%");
            stepPercent.put("step3", "0%");
            completionItem.put("step_percent", stepPercent);

            String studentNumber = studentFile.substring(0, studentFile.indexOf("."));
            completionStatus.put(studentNumber, completionItem);
        }
        taskDetailResp.setCompletionStatus(completionStatus);
        return taskDetailResp;
    }

    private Map<String, String> parseJsonToMap(String jsonStr) {
        if (StringUtils.isBlank(jsonStr))
            return null;
        Map<String, String> map = new HashMap<>();
        JSONArray jsonArray = JSONArray.parseArray(jsonStr);
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject object = jsonArray.getJSONObject(i);
            map.put(object.getString("num"), object.getString("cfg"));
        }
        return map;
    }
}
