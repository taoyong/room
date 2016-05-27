package com.ty.room.domain.util;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/27
 * Time: 15:45
 * Description:
 */
public class WebHelper {

    private static final Logger log = LogHelper.roomErrorLog;
    public static final String getIpAddress(HttpServletRequest request) {
        String ip = "";
        try {
            ip = request.getHeader("x-forwarded-for");
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }
            //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
            if (ip != null && ip.length() > 15) { //"***.***.***.***".length() = 15
                if (ip.indexOf(",") > 0) {
                    ip = ip.substring(0, ip.indexOf(","));
                }
            }
        } catch (Exception e) {
            log.error("获取用户ip地址异常--->" + e.getMessage(), e);
        }
        return StringUtils.isNotEmpty(ip) ? ip.trim() : ip;
    }

}
