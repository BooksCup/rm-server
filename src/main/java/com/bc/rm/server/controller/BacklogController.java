package com.bc.rm.server.controller;

import com.bc.rm.server.cons.Constant;
import com.bc.rm.server.entity.Backlog;
import com.bc.rm.server.entity.User;
import com.bc.rm.server.enums.ResponseMsg;
import com.bc.rm.server.service.BacklogService;
import com.bc.rm.server.service.UserService;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * 产品待办事项控制器
 *
 * @author zhou
 */
@RestController
@RequestMapping("/backlogs")
public class BacklogController {

    private static final Logger logger = LoggerFactory.getLogger(BacklogController.class);

    @Resource
    private BacklogService backlogService;

    @Resource
    private UserService userService;

    /**
     * 新增待办事项
     *
     * @param type          类型 "0":story "1":bug
     * @param title         标题
     * @param statusId      状态ID
     * @param currentUserId 当前处理人ID
     * @param moduleId      模块ID
     * @param sprintId      迭代ID
     * @param isLinkSprint  是否关联迭代的开始和结束时间 "0":否 "1":是
     * @param beginDate     预计开始时间
     * @param endDate       预计结束时间
     * @param priorityOrder 优先级顺序
     * @param priority      优先级
     * @param importance    重要程度
     * @return ResponseEntity<Backlog>
     */
    @ApiOperation(value = "新增待办事项", notes = "新增待办事项")
    @PostMapping(value = "")
    public ResponseEntity<Backlog> addBacklog(
            @RequestParam String type,
            @RequestParam String title,
            @RequestParam String statusId,
            @RequestParam String currentUserId,
            @RequestParam(required = false) String moduleId,
            @RequestParam(required = false) String sprintId,
            @RequestParam(required = false, defaultValue = Constant.IS_LINK_SPRINT_NO) String isLinkSprint,
            @RequestParam(required = false) String beginDate,
            @RequestParam(required = false) String endDate,
            @RequestParam(required = false, defaultValue = "1") String priorityOrder,
            @RequestParam(defaultValue = Constant.PRIORITY_MEDIUM) String priority,
            @RequestParam(defaultValue = Constant.IMPORTANCE_COMMON) String importance) {
        ResponseEntity<Backlog> responseEntity;
        try {
            Backlog backlog = new Backlog(type, title, statusId, currentUserId, moduleId,
                    sprintId, isLinkSprint, beginDate, endDate, priorityOrder, priority, importance);
            User currentUser = userService.getUserByUserId(currentUserId);
            backlog.setCurrentUserName(currentUser.getName());
            logger.info("[addBacklog], data: " + backlog);
            backlogService.addBacklog(backlog);

            backlog = backlogService.getBacklogById(backlog.getId());
            responseEntity = new ResponseEntity<>(backlog, HttpStatus.OK);
        } catch (Exception e) {
            logger.error("addBacklog error: " + e.getMessage());
            responseEntity = new ResponseEntity<>(new Backlog(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    /**
     * 获取待办事项列表
     *
     * @param page  当前分页
     * @param limit 每个分页大小
     * @return ResponseEntity
     */
    @ApiOperation(value = "获取待办事项列表", notes = "获取待办事项列表")
    @GetMapping(value = "")
    public ResponseEntity<PageInfo<Backlog>> getBacklogList(
            @RequestParam Integer page,
            @RequestParam Integer limit) {
        logger.info("[getBacklogList] page: " + page + ", limit: " + limit);
        PageInfo<Backlog> pageInfo = backlogService.getBacklogListByPageInfo(page, limit);
        return new ResponseEntity<>(pageInfo, HttpStatus.OK);
    }

    /**
     * 删除待办事项列表
     *
     * @param backlogId 待办事项ID
     * @return ResponseEntity
     */
    @ApiOperation(value = "删除待办事项列表", notes = "删除待办事项列表")
    @DeleteMapping(value = "/{backlogId}")
    public ResponseEntity<String> deleteBacklog(@PathVariable String backlogId) {

        logger.info("[deleteBacklog] backlogId: " + backlogId);
        ResponseEntity<String> responseEntity;
        try {
            backlogService.deleteBacklog(backlogId);
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_BACKLOG_SUCCESS.getResponseCode(),
                    HttpStatus.OK);
        } catch (Exception e) {
            logger.error("deleteBacklog error. errorMsg: " + e.getMessage());
            responseEntity = new ResponseEntity<>(ResponseMsg.DELETE_BACKLOG_ERROR.getResponseCode(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }
}
