package com.hckj.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.JwtTokenUtils;
import com.hckj.springboot.dao.AdminDao;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {
    @Autowired
    private AdminDao adminDao;
//    public List<Admin> findAll(){
//        return adminDao.findAll();
//    }
    public PageInfo<Admin>  findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Admin> list = adminDao.findBySearch(params);
        return PageInfo.of(list);
    }
    public void add(Admin admin){
        if (admin.getPassword() == null) {
            admin.setPassword("123456");
        }
        adminDao.insertSelective(admin);//通过掉包实现插入数据，不用再去操作dao层
    }
    public void update(Admin admin) {
        adminDao.updateByPrimaryKeySelective(admin);//同上
    }
    public void delete(Integer id) {
        adminDao.deleteByPrimaryKey(id);
    }
    public Admin login(Admin admin) {
        // 1. 进行一些非空判断
        if (admin.getName() == null || "".equals(admin.getName())) {
            throw new CustomException("用户名不能为空");
        }
        if (admin.getPassword() == null || "".equals(admin.getPassword())) {
            throw new CustomException("密码不能为空");
        }
        // 2. 从数据库里面根据这个用户名和密码去查询对应的管理员信息，
        Admin user = adminDao.findByNameAndPassword(admin.getName(), admin.getPassword());
        if (user == null) {
            // 如果查出来没有，那说明输入的用户名或者密码有误，提示用户，不允许登录
            throw new CustomException("用户名或密码输入错误");
        }
        // 如果查出来了有，那说明确实有这个管理员，而且输入的用户名和密码都对；
        // 生成jwt token给前端
        String token = JwtTokenUtils.genToken(user.getId().toString(), user.getPassword());
        user.setToken(token);//这里给admin实体添加一个token
        return user;
    }
    public Admin findById(Integer id) {
        return adminDao.selectByPrimaryKey(id);
    }
}
