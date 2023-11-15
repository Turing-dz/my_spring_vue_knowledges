package com.hckj.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.dao.BookDao;
import com.hckj.springboot.dao.TypeDao;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Book;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.entity.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeService {
    @Autowired
    private TypeDao typeDao;
    public List<Type> findAll(){
        return typeDao.findAll();
    }
    public PageInfo<Type>  findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Type> list = typeDao.findBySearch(params);
        return PageInfo.of(list);
    }
    public void add(Type type){
        typeDao.insertSelective(type);//通过掉包实现插入数据，不用再去操作dao层
    }
    public void update(Type type) {
        typeDao.updateByPrimaryKeySelective(type);//同上
    }
    public void delete(Integer id) {
        typeDao.deleteByPrimaryKey(id);
    }
}
