package com.atguigu.controller;

import com.atguigu.pojo.NewsHeadline;
import com.atguigu.pojo.NewsUser;
import com.atguigu.service.NewsHeadlineService;
import com.atguigu.service.NewsUserService;
import com.atguigu.utils.JwtHelper;
import com.atguigu.utils.Result;
import com.atguigu.utils.ResultCodeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("headline")
public class NewsHeadlineController {

    @Autowired
    private NewsHeadlineService headlineService;

    @Autowired
    private NewsUserService newsUserService;

    /**
     * + 用户在客户端输入发布的新闻信息完毕后
     * + 发布前先请求后端的登录校验接口验证登录
     * + 登录通过则提交新闻信息
     * + 后端将新闻信息存入数据库
     * @param newsHeadline
     * @param token
     * @return
     */
    @PostMapping("publish")
    public Result publish(@RequestBody NewsHeadline newsHeadline,@RequestHeader String token){
        Integer userId = JwtHelper.getUserId(token).intValue();
        newsHeadline.setPublisher(userId);
       int rows =  headlineService.insert(newsHeadline);
       Result r = Result.build(null, ResultCodeEnum.SUCCESS);
        if (rows == 0) {
            r = Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        return r;
    }

    /**
     * + 前端先调用登录校验接口,校验登录是否过期
     * + 登录校验通过后 ,则根据新闻id查询新闻的完整信息并响应给前端
     * @param hid
     * @return
     */
    @PostMapping("findHeadlineByHid")
    public  Result findHeadlineByHid(int hid){
        NewsHeadline newsHeadline =  headlineService.findHeadlineByHid(hid);
        Map data = new HashMap<>();
        data.put("headline",newsHeadline);
        Result r = Result.ok(data);
        return  r;
    }


    /**
     * + 客户端将新闻信息修改后,提交前先请求登录校验接口校验登录状态
     * + 登录校验通过则提交修改后的新闻信息,后端接收并更新进入数据库
     * @param newsHeadline
     * @return
     */
    @PostMapping("update")
    public Result update(@RequestBody NewsHeadline newsHeadline,@RequestHeader String token){
        Result r = null;

        if (!(JwtHelper.isExpiration(token))) {
            headlineService.update(newsHeadline);
            r = Result.ok(null);
        }else {
            r = Result.build(null,ResultCodeEnum.NOTLOGIN);
        }

        return r;
    }

    @PostMapping("removeByHid")
    public Result removeByHid(@RequestParam int hid,@RequestHeader String token){
        Result r = null;
        if(!(JwtHelper.isExpiration(token))){
            headlineService.removeByHid(hid);
            r = Result.ok(null);
        }else {
            r = Result.build(null,ResultCodeEnum.NOTLOGIN);
        }
        return  r;
    }

}
