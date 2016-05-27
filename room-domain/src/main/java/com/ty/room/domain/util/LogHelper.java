package com.ty.room.domain.util;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/27
 * Time: 15:30
 * Description:重要日志输出工具类
 */
public class LogHelper {

    /**
     * 所有日志
     */
    private static final String ROOM_ALL = "room_all_log";
    public static final Logger roomAllLog = LogManager.getLogger(ROOM_ALL);

    /**
     * 错误日志
     */
    private static final String ROOM_ERROR = "room_error_log";
    public static final Logger roomErrorLog = LogManager.getLogger(ROOM_ERROR);



}
