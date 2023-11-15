package com.hckj.springboot.controller;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.AutoLog;
import com.hckj.springboot.common.Result;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Notice;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.service.AdminService;
import com.hckj.springboot.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/notice")
public class NoticeController {
    @Autowired
    private NoticeService noticeService;

    @AutoLog("更新日志操作")
    @PostMapping
    public Result update(@RequestBody Notice notice) {
        if (ObjectUtil.isEmpty(notice.getId())) {
            noticeService.add(notice);
        } else {
            noticeService.update(notice);
        }
        return Result.success();
    }

    @GetMapping
    public Result findTop() {
        List<Notice> list = noticeService.findTop();
        return Result.success(list);
    }

    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Notice> info = noticeService.findBySearch(params);
        return Result.success(info);
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        noticeService.delete(id);
        return Result.success();
    }

}