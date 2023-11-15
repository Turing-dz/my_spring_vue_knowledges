package com.hckj.springboot.service;

import cn.hutool.core.date.DateUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.JwtTokenUtils;
import com.hckj.springboot.dao.AdminDao;
import com.hckj.springboot.dao.NoticeDao;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Notice;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticeService {
    @Autowired
    private NoticeDao noticeDao;
    public PageInfo<Notice> findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Notice> list = noticeDao.findBySearch(params);
        return PageInfo.of(list);
    }
    public void add(Notice notice) {
        notice.setTime(DateUtil.now());
        noticeDao.insertSelective(notice);
    }
    public void update(Notice notice) {
        notice.setTime(DateUtil.now());
        noticeDao.updateByPrimaryKeySelective(notice);
    }

    public void delete(Integer id) {
        noticeDao.deleteByPrimaryKey(id);
    }

    public List<Notice> findTop() {
        return noticeDao.findTop3();
    }
}
