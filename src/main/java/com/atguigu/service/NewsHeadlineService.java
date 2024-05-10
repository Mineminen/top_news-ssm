package com.atguigu.service;

import com.atguigu.pojo.HeadlineDetailVo;
import com.atguigu.pojo.HeadlineQueryVo;
import com.atguigu.pojo.NewsHeadline;

import java.util.Map;

public interface NewsHeadlineService {
    Map findPage(HeadlineQueryVo headlineQueryVo);

    HeadlineDetailVo findByHid(int hid);

    int insert(NewsHeadline newsHeadline);

    NewsHeadline findHeadlineByHid(int hid);

    int update(NewsHeadline newsHeadline);

    void removeByHid(int hid);
}
