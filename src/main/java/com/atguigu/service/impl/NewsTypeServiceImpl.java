package com.atguigu.service.impl;

import com.atguigu.mapper.NewsTypeMapper;
import com.atguigu.pojo.NewsType;
import com.atguigu.service.NewsTypeService;
import jakarta.annotation.Resource;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NewsTypeServiceImpl implements NewsTypeService {

    @Resource
    private NewsTypeMapper newsTypeMapper;
    @Override
    public List<NewsType> findAllTypes() {
        return newsTypeMapper.findAllTypes();
    }
}
