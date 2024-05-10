package com.atguigu.service.impl;

import com.atguigu.mapper.NewsUserMapper;
import com.atguigu.pojo.NewsUser;
import com.atguigu.service.NewsUserService;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsUserServiceImpl implements NewsUserService {

    @Resource
    private NewsUserMapper newsUserMapper;
    @Override
    public NewsUser findByUserName(String username) {

        return newsUserMapper.findByUserName(username);

    }

    @Override
    public NewsUser findByUid(Integer userId) {
        return newsUserMapper.findByUid(userId);
    }

    @Override
    public int registUser(NewsUser newsUser) {
        return newsUserMapper.registUser(newsUser);
    }
}
