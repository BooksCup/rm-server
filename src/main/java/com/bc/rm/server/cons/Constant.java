package com.bc.rm.server.cons;


/**
 * 常量类
 *
 * @author zhou
 */
public class Constant {
    /**
     * 初始化hashmap容量
     */
    public static final int DEFAULT_HASH_MAP_CAPACITY = 16;

    /**
     * https
     */
    public static final String PROTOCOL_HTTPS_PREFIX = "https://";

    /**
     * 用户状态:激活
     */
    public static final String USER_STATUS_ACTIVATE = "0";

    /**
     * 待办事项类型-STORY
     */
    public static final String BACKLOG_TYPE_STORY = "0";

    /**
     * 待办事项类型-BUG
     */
    public static final String BACKLOG_TYPE_BUG = "1";

    // 是否关联迭代
    /**
     * 否
     */
    public static final String IS_LINK_SPRINT_NO = "0";

    /**
     * 是
     */
    public static final String IS_LINK_SPRINT_YES = "1";

    // 优先级
    /**
     * 优先级-低
     */
    public static final String PRIORITY_LOW = "0";

    /**
     * 优先级-中
     */
    public static final String PRIORITY_MEDIUM = "1";

    /**
     * 优先级-高
     */
    public static final String PRIORITY_HIGH = "2";

    // 重要程度
    /**
     * 重要程度-提示
     */
    public static final String IMPORTANCE_REMIND = "0";

    /**
     * 重要程度-一般
     */
    public static final String IMPORTANCE_COMMON = "1";

    /**
     * 重要程度-重要
     */
    public static final String IMPORTANCE_IMPORTANT = "2";

    /**
     * 重要程度-关键
     */
    public static final String IMPORTANCE_KEY = "3";

    public static final String E_SIGN_BASE_URL = "https://smlopenapi.esign.cn";

    /**
     * 电子合同服务成功返回码
     */
    public static final String E_CONTRACT_SUCCESS_RESULT_CODE = "0";
}
