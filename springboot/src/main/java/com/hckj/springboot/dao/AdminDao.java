package com.hckj.springboot.dao;

import com.hckj.springboot.entity.Admin;

import com.hckj.springboot.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;


import java.util.List;
@Repository
public interface AdminDao extends Mapper<Admin> {

//    @Select("select * from admin")
//    List<Admin> findAll();
    List<Admin> findBySearch(@Param("params") Params params);
    @Select("select * from admin where name = #{name} and password = #{password} limit 1")
    Admin findByNameAndPassword(@Param("name") String name, @Param("password") String password);
}
