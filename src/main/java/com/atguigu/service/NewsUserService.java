package com.atguigu.service;

import com.atguigu.pojo.NewsUser;

public interface NewsUserService {
    NewsUser findByUserName(String username);

    NewsUser findByUid(Integer userId);

    int registUser(NewsUser newsUser);
}
