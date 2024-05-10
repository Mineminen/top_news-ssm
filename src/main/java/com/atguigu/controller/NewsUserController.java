package com.atguigu.controller;


import com.alibaba.druid.util.StringUtils;
import com.atguigu.pojo.NewsUser;
import com.atguigu.service.NewsUserService;
import com.atguigu.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin
@RestController
@RequestMapping("user")
public class NewsUserController {

    @Autowired
    private NewsUserService newsUserService;


    /**
     * 处理登录表单提交的业务接口的实现
     * @param newsUser
     * @param result
     * @return
     */
    @PostMapping("login")
    public Result login(@Validated  @RequestBody NewsUser newsUser, BindingResult result){
        Result r = null;
        NewsUser loginNewsUser = newsUserService.findByUserName(newsUser.getUsername());
        if (loginNewsUser == null) {
            r = Result.build(null,ResultCodeEnum.USERNAME_ERROR);
        }else if(!(loginNewsUser.getUserPwd().equalsIgnoreCase(MD5Util.encrypt(newsUser.getUserPwd())))){
            r = Result.build(null,ResultCodeEnum.PASSWORD_ERROR);
        }else{
            // 密码正确
            Map<String,Object> data =new HashMap<>();
            // 生成token口令
            String token = JwtHelper.createToken(loginNewsUser.getUid().longValue());
            // 封装数据map
            data.put("token",token);
            // 封装结果
            r=Result.ok(data);
        }
        return r;
    }

    /**
     * 客户端发送请求,提交token请求头,后端根据token请求头获取登录用户的详细信息并响应给客户端进行存储
     * @param token
     * @return
     */
    @GetMapping("getUserInfo")
    public  Result getUserInfo( @RequestHeader String token){
        Result r = null;
        r=Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (token != null&&(!("".equals(token)))) {
            if (!(JwtHelper.isExpiration(token)))  {
                Integer userId = JwtHelper.getUserId(token).intValue();
                NewsUser loginUser = newsUserService.findByUid(userId);
                Map<String,Object> data =new HashMap<>();
                loginUser.setUserPwd("");
                data.put("loginUser",loginUser);
                r = Result.ok(data);
            }
        }
        return r;
    }

    /**
     *用户在注册时输入用户名时,立刻将用户名发送给后端,后端根据用户名查询用户名是否可用并做出响应
     * @param username
     * @return
     */

    @PostMapping("checkUserName")
    public Result checkUserName(@RequestParam String username){
        Result r = null;
        NewsUser loginNewsUser = newsUserService.findByUserName(username);
        if (loginNewsUser == null) {
            r = Result.build(null,ResultCodeEnum.SUCCESS);
        } else  {
            r = Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        return r;
    }

    /**
     * 客户端将新用户信息发送给服务端,服务端将新用户存入数据库,存入之前做用户名是否被占用校验,校验通过响应成功提示,否则响应失败提示
     * @param newsUser
     * @return
     */
    @PostMapping("regist")
    public  Result regist(@RequestBody  NewsUser newsUser){
        Result r = null;
        newsUser.setUserPwd(MD5Util.encrypt(newsUser.getUserPwd()));
        r = Result.build(null,ResultCodeEnum.SUCCESS);
        int rows = newsUserService.registUser(newsUser);
        if (rows == 0) {
          r =  Result.build(null,ResultCodeEnum.USERNAME_USED);
        }
        return  r;
    }

    /**
     * + 客户端在进入发布页前、发布新闻前、进入修改页前、修改前、删除新闻前先向服务端发送请求携带token请求头
     * + 后端接收token请求头后,校验用户登录是否过期并做响应
     * + 前端根据响应信息提示用户进入登录页还是进入正常业务页面
     * @param token
     * @return
     */
    @GetMapping("checkLogin")
    public Result checkLogin(@RequestHeader String token){

        Result r = null;
        r = Result.build(null,ResultCodeEnum.NOTLOGIN);
        if (token != null && (!("".equals(token))) ) {
            if(!(JwtHelper.isExpiration(token)))
           r = Result.build(null,ResultCodeEnum.SUCCESS);
        }
        return r;

    }






}





