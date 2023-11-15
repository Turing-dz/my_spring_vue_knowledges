package com.hckj.springboot.dao;

import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Book;
import com.hckj.springboot.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface BookDao extends Mapper<Book> {

    @Select("select book.*, type.name as typeName from book left join type on book.typeId = type.id")
    List<Book> findAll();
    List<Book> findBySearch(@Param("params") Params params);
}
