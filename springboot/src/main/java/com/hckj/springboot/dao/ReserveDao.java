package com.hckj.springboot.dao;

import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.entity.Reserve;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Repository
public interface ReserveDao extends Mapper<Reserve> {
    List<Reserve> findBySearch(@Param("params") Params params);

}
