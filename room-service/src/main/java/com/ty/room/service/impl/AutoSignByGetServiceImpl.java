package com.ty.room.service.impl;

import com.ty.room.domain.util.HttpClientHelper;
import com.ty.room.service.AutoSignService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/6/22
 * Time: 10:05
 * Description:
 */
public class AutoSignByGetServiceImpl implements AutoSignService {
    private  static Logger log = LoggerFactory.getLogger(AutoSignByGetServiceImpl.class);
    @Autowired
    private String url;
    public boolean sign() {
        try {
            HttpClientHelper.requestPost(url,null);
        } catch (IOException e) {
            log.error("sign--> fail !",e);
            return false;
        }
        return true;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
