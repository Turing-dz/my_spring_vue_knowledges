package com.hckj.springboot.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.JwtTokenUtils;
import com.hckj.springboot.dao.AdminDao;
import com.hckj.springboot.dao.BookDao;
import com.hckj.springboot.dao.TypeDao;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Book;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.entity.Type;
import com.hckj.springboot.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookDao bookDao;
    public List<Book> findAll(){
        return bookDao.findAll();
    }
//    @Resource
//    private TypeDao typeDao;
//    public PageInfo<Book> findBySearch(Params params) {
//        // 开启分页查询
//        PageHelper.startPage(params.getPageNum(), params.getPageSize());
//        // 接下来的查询会自动按照当前开启的分页设置来查询
//        List<Book> list = bookDao.findBySearch(params);
//        if (CollectionUtil.isEmpty(list)) {
//            return PageInfo.of(new ArrayList<>());
//        }
//        for (Book book : list) {
//            if (ObjectUtil.isNotEmpty(book.getTypeId())) {
//                Type type = typeDao.selectByPrimaryKey(book.getTypeId());
//                if (ObjectUtil.isNotEmpty(type)) {
//                    book.setTypeName(type.getName());
//                }
//            }
//        }
//        return PageInfo.of(list);
//    }

    public PageInfo<Book>  findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Book> list = bookDao.findBySearch(params);
        return PageInfo.of(list);
    }


    public void add(Book book){
        bookDao.insertSelective(book);//通过掉包实现插入数据，不用再去操作dao层
    }
    public void update(Book book) {
        bookDao.updateByPrimaryKeySelective(book);//同上
    }
    public void delete(Integer id) {
        bookDao.deleteByPrimaryKey(id);
    }

}
