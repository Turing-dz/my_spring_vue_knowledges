package com.hckj.springboot.dao;

import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Audit;
import com.hckj.springboot.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface AuditDao extends Mapper<Audit> {

//    @Select("select * from admin")
//    List<Admin> findAll();
    List<Audit> findBySearch(@Param("params") Params params);

}
