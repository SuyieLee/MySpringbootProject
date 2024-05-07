package com.mao.service;

import com.mao.entity.Blog;
import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.transaction.annotation.Transactional;

public interface BlogService extends IService<Blog> {
    @Transactional(rollbackFor = Exception.class)
    int deleteBlog(Long id);
}
