package com.ty.room.action;

import org.apache.commons.collections4.MapUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/26
 * Time: 14:46
 * Description:
 */
@Controller
@RequestMapping("/index")
public class TalkIndexAction {
    @RequestMapping(value = "/articles.htm",method = RequestMethod.GET)
    public String  getArticles(@RequestParam Map<String,Object> params , Model model,HttpServletRequest request ){

        Long id = MapUtils.getLongValue(params,"id");
        if(id != 0 ){
            model.addAttribute("msg","今日头条");
        }else{
            model.addAttribute("msg","请求参数错误!");
        }
        return "artiles";
    }
}
