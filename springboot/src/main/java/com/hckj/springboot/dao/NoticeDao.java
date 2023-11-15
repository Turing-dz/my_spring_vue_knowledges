package com.hckj.springboot.dao;

import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Notice;
import com.hckj.springboot.entity.Params;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface NoticeDao extends Mapper<Notice> {

//    @Select("select * from admin")
//    List<Admin> findAll();
    List<Notice> findBySearch(@Param("params") Params params);
    @Select("select * from notice order by time desc limit 3")
    List<Notice> findTop3();
}
