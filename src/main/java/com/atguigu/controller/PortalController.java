package com.atguigu.controller;

import com.atguigu.pojo.HeadlineDetailVo;
import com.atguigu.pojo.HeadlineQueryVo;
import com.atguigu.pojo.NewsType;
import com.atguigu.service.NewsHeadlineService;
import com.atguigu.service.NewsTypeService;
import com.atguigu.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin
@RestController
@RequestMapping("portal")
public class PortalController {

    @Autowired
    private NewsTypeService newsTypeService;

    @Autowired
    private NewsHeadlineService newsHeadlineService;

    /**
     * 进入新闻首页,查询所有分类并动态展示新闻类别栏位
     * @return
     */
    @GetMapping("findAllTypes")
    public Result findAllTypes(){
        Result r =null;
        List<NewsType> newsTypeList =  newsTypeService.findAllTypes();
        r = Result.ok(newsTypeList);
        return  r;
    }


    /**
     * + 客户端向服务端发送查询关键字,新闻类别,页码数,页大小
     * + 服务端根据条件搜索分页信息,返回含页码数,页大小,总页数,总记录数,当前页数据等信息,并根据时间降序,浏览量降序排序
     * @param headlineQueryVo
     * @return
     */
    @PostMapping("findNewsPage")
    public Result findNewsPage(@RequestBody HeadlineQueryVo headlineQueryVo){

        //将参数传递给服务层 进行分页查询
        Map  pageInfo = newsHeadlineService.findPage(headlineQueryVo);
        Map data = new HashMap<>();
        data.put("pageInfo",pageInfo);
        //将分页查询的结果转换成json响应给客户端
        Result r = Result.ok(data);
        return r;
    }

    /**
     * + 用户点击"查看全文"时,向服务端发送新闻id
     * + 后端根据新闻id查询完整新闻文章信息并返回
     * + 后端要同时让新闻的浏览量+1
     * @return
     */
    @PostMapping("showHeadlineDetail")
    public  Result showHeadlineDetail(@RequestParam int hid){
        HeadlineDetailVo headlineDetailVo = newsHeadlineService.findByHid(hid);
        Map data = new HashMap<>();
        data.put("headline",headlineDetailVo);
        Result r = Result.ok(data);
        return r;
    }


}
