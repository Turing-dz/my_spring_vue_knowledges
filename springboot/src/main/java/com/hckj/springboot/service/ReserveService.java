package com.hckj.springboot.service;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.JwtTokenUtils;
import com.hckj.springboot.dao.AdminDao;
import com.hckj.springboot.dao.HotelDao;
import com.hckj.springboot.dao.ReserveDao;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Hotel;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.entity.Reserve;
import com.hckj.springboot.exception.CustomException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReserveService {
    @Autowired
    private ReserveDao reserveDao;
    @Autowired
    private HotelDao hotelDao;
    @Autowired
    private AdminDao adminDao;
    public PageInfo<Reserve>  findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Reserve> list = reserveDao.findBySearch(params);
        if (CollectionUtil.isEmpty(list)) {
            return PageInfo.of(new ArrayList<>());
        }
        for (Reserve reserve : list) {
            if (ObjectUtil.isNotEmpty(reserve.getHotelId())) {
                Hotel hotel = hotelDao.selectByPrimaryKey(reserve.getHotelId());
                if (ObjectUtil.isNotEmpty(hotel)) {
                    reserve.setHotelName(hotel.getName());
                }
            }
            if (ObjectUtil.isNotEmpty(reserve.getUserId())) {
                Admin admin = adminDao.selectByPrimaryKey(reserve.getUserId());
                if (ObjectUtil.isNotEmpty(admin)) {
                    reserve.setUserName(admin.getName());
                }
            }
        }
        return PageInfo.of(list);
    }
    public void add(Reserve reserve) {
        reserveDao.insertSelective(reserve);
    }

    public void update(Reserve reserve) {
        reserveDao.updateByPrimaryKeySelective(reserve);
    }

    public void delete(Integer id) {
        reserveDao.deleteByPrimaryKey(id);
    }
}
