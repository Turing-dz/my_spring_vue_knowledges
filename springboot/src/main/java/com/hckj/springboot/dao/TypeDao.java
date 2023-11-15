package com.hckj.springboot.dao;

import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Book;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.entity.Type;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface TypeDao extends Mapper<Type> {

    @Select("select * from type")
    List<Type> findAll();
    List<Type> findBySearch(@Param("params") Params params);
}
