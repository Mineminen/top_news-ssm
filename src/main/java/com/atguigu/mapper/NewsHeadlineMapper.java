package com.atguigu.mapper;

import com.atguigu.pojo.HeadlineDetailVo;
import com.atguigu.pojo.HeadlinePageVo;
import com.atguigu.pojo.HeadlineQueryVo;
import com.atguigu.pojo.NewsHeadline;

import java.util.List;

public interface NewsHeadlineMapper {


    HeadlineDetailVo findByHid(int hid) ;

    List<HeadlinePageVo> queryPage(HeadlineQueryVo headlineQueryVo);

    int insert(NewsHeadline newsHeadline);

    NewsHeadline findHeadlineByHid(int hid);

    int update(NewsHeadline newsHeadline);

    void removeByHid(int hid);


}
