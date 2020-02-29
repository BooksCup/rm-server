package com.bc.rm.server.service;

import com.bc.rm.server.entity.User;
import com.github.pagehelper.PageInfo;

/**
 * 用户
 *
 * @author zhou
 */
public interface UserService {

    /**
     * 新增用户
     *
     * @param user 用户
     */
    void addUser(User user);

    /**
     * 获取用户分页列表
     *
     * @param name     用户名
     * @param pageNum  当前分页
     * @param pageSize 每个分页大小
     * @return 用户分页列表
     */
    PageInfo<User> getUserListByPageInfo(String name, int pageNum, int pageSize);

    /**
     * 修改用户
     *
     * @param user 用户
     */
    void updateUser(User user);

    /**
     * 删除用户
     *
     * @param userId 用户ID
     */
    void deleteUser(String userId);
}
