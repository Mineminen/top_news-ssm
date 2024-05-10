package com.atguigu.service.impl;

import com.atguigu.mapper.NewsHeadlineMapper;
import com.atguigu.pojo.HeadlineDetailVo;
import com.atguigu.pojo.HeadlinePageVo;
import com.atguigu.pojo.HeadlineQueryVo;
import com.atguigu.pojo.NewsHeadline;
import com.atguigu.service.NewsHeadlineService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class NewsHeadlineServiceImpl implements NewsHeadlineService {

    @Resource
    private NewsHeadlineMapper headlineMapper;
    @Override
    public Map findPage(HeadlineQueryVo headlineQueryVo) {
        PageHelper.startPage(headlineQueryVo.getPageNum(),headlineQueryVo.getPageSize());
        List<HeadlinePageVo> list = headlineMapper.queryPage(headlineQueryVo);
        PageInfo<HeadlinePageVo> pageBean = new PageInfo<>(list);
        Map pageInfo = new HashMap();
        pageInfo.put("pageData",pageBean.getList());
        pageInfo.put("pageNum",pageBean.getPageNum());
        pageInfo.put("pageSize",pageBean.getPageSize());
        pageInfo.put("totalPage",pageBean.getPages());
        pageInfo.put("totalSize",pageBean.getTotal());
        return pageInfo;
    }

    @Override
    public HeadlineDetailVo findByHid(int hid) {
        return headlineMapper.findByHid(hid);
    }

    @Override
    public int insert(NewsHeadline newsHeadline) {

        return headlineMapper.insert(newsHeadline);
    }

    @Override
    public NewsHeadline findHeadlineByHid(int hid) {
        return headlineMapper.findHeadlineByHid(hid);
    }

    @Override
    public int update(NewsHeadline newsHeadline) {
        return headlineMapper.update(newsHeadline);
    }

    @Override
    public void removeByHid(int hid) {
        headlineMapper.removeByHid(hid);
    }

}
