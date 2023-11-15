package com.hckj.springboot.controller;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.Result;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Log;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.service.AdminService;
import com.hckj.springboot.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/log")
public class LogController {
    @Resource
    private LogService logService;
    @PostMapping
    public Result save(@RequestBody Log log) {
        logService.add(log);
        return Result.success();
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Log> info = logService.findBySearch(params);
        return Result.success(info);
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        logService.delete(id);
        return Result.success();
    }

}