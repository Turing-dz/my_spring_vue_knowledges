package com.hckj.springboot.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.JwtTokenUtils;
import com.hckj.springboot.dao.AdminDao;
import com.hckj.springboot.dao.HotelDao;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Hotel;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HotelService {
    @Autowired
    private HotelDao hotelDao;
    public PageInfo<Hotel>  findBySearch(Params params) {
        // 开启分页查询
        PageHelper.startPage(params.getPageNum(), params.getPageSize());
        // 接下来的查询会自动按照当前开启的分页设置来查询
        List<Hotel> list = hotelDao.findBySearch(params);
        return PageInfo.of(list);
    }
    public void add(Hotel hotel) {
        hotelDao.insertSelective(hotel);
    }

    public void update(Hotel hotel) {
        hotelDao.updateByPrimaryKeySelective(hotel);
    }

    public void delete(Integer id) {
        hotelDao.deleteByPrimaryKey(id);
    }
}
