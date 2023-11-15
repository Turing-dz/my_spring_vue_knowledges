package com.hckj.springboot.service;

import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.JwtTokenUtils;
import com.hckj.springboot.dao.AdminDao;
import com.hckj.springboot.dao.AuditDao;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Audit;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuditService {
    @Autowired
    private AuditDao auditDao;
    @Autowired
    private AdminDao adminDao;

    public void add(Audit audit) {
        auditDao.insertSelective(audit);
    }

    public void update(Audit audit) {
        auditDao.updateByPrimaryKeySelective(audit);
    }

    public PageInfo<Audit> findBySearch(Params params) {
        Admin user = JwtTokenUtils.getCurrentUser();
        if (ObjectUtil.isNull(user)) {
            throw new CustomException("从token中未解析到用户信息，请重新登录");
        }
        if ("ROLE_STUDENT".equals(user.getRole())) {
            params.setUserId(user.getId());
        }
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        List<Audit> list = auditDao.findBySearch(params);
        return PageInfo.of(list);
    }
    public void delete(Integer id) {
        auditDao.deleteByPrimaryKey(id);
    }

}
