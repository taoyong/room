package com.ty.room.action;

import com.ty.room.domain.RoomResult;
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
@RequestMapping("/")
public class TalkIndexAction {
    @RequestMapping(value = "content.htm",method = RequestMethod.GET)
    public String  getArticles(@RequestParam Map<String,Object> params , Model model,HttpServletRequest request ){

        RoomResult roomResult = new RoomResult(false);
        Long id = MapUtils.getLongValue(params,"id");
        if(id != 0 ){
            roomResult.setState(true);
            roomResult.setMsg("今日头条");
        }else{
            roomResult.setMsg("请求参数错误!");
        }
        model.addAttribute("result",roomResult);
        return "articles";
    }
}
