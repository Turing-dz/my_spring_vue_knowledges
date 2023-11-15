package com.hckj.springboot.controller;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.Result;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Audit;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.service.AdminService;
import com.hckj.springboot.service.AuditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@CrossOrigin
@RestController
@RequestMapping("/audit")
public class AuditController {
    @Resource
    private AuditService auditService;
    @GetMapping("/search")
    public Result findBySearch(Params params) {
        PageInfo<Audit> info = auditService.findBySearch(params);
        return Result.success(info);
    }
    @PostMapping
    public Result save(@RequestBody Audit audit) {
        if (audit.getId() == null) {
            auditService.add(audit);
        } else {
            auditService.update(audit);
        }
        return Result.success();
    }
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        auditService.delete(id);
        return Result.success();
    }

}