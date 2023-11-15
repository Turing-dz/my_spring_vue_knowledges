package com.hckj.springboot.controller;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.AutoLog;
import com.hckj.springboot.common.CaptureConfig;
import com.hckj.springboot.common.Result;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.service.AdminService;

import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping
public class AdminController {
    @Autowired
    private AdminService adminService;
//    @GetMapping
//    public Result findAll() {
//        List <Admin> list=adminService.findAll();
//        return Result.success(list);
//    }
    @GetMapping("/search")
    public Result findBySearch(Params params){
        PageInfo<Admin> list = adminService.findBySearch(params);
        return Result.success(list);
    }
    @PostMapping("/addedit")
    public Result save(@RequestBody Admin admin){
        if (admin.getId()==null){//新增
            adminService.add(admin);
        }else{//编辑
            adminService.update(admin);
        }
        return Result.success();
    }
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        adminService.delete(id);
        return Result.success();
    }
    @PostMapping("/login")
    @AutoLog("登录")
    public Result login(@RequestBody Admin admin,@RequestParam String key, HttpServletRequest request){
        if (!admin.getVerCode().toLowerCase().equals(CaptureConfig.CAPTURE_MAP.get(key))) {
            // 如果不相等，说明验证不通过
            CaptchaUtil.clear(request);
            return Result.error("验证码不正确");
        }
        Admin loginUser=adminService.login(admin);
        return Result.success(loginUser);
    }
    @PostMapping("/register")
    @AutoLog("注册")
    public Result register(@RequestBody Admin admin) {
        adminService.add(admin);
        return Result.success();
    }

}