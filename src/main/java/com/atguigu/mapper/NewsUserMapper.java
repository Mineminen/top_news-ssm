package com.atguigu.mapper;

import com.atguigu.pojo.NewsUser;

import java.util.List;

public interface NewsUserMapper {
    NewsUser findByUserName(String username) ;

    NewsUser findByUid(Integer userId);

    int registUser(NewsUser newsUser);
}
