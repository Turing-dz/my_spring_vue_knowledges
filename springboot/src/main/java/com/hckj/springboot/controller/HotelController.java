package com.hckj.springboot.controller;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.AutoLog;
import com.hckj.springboot.common.Result;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Hotel;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.service.AdminService;
import com.hckj.springboot.service.HotelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/hotel")
public class HotelController {
    @Resource
    private HotelService hotelService;

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Hotel> info = hotelService.findBySearch(params);
        return Result.success(info);
    }

    @PostMapping
    public Result save(@RequestBody Hotel hotel) {
        if (hotel.getId() == null) {
            hotelService.add(hotel);
        } else {
            hotelService.update(hotel);
        }
        return Result.success();
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        hotelService.delete(id);
        return Result.success();
    }

}