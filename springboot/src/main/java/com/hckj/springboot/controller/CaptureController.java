package com.hckj.springboot.controller;
import com.hckj.springboot.common.CaptureConfig;
import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wf.captcha.utils.CaptchaUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@CrossOrigin
@RestController
@RequestMapping
public class CaptureController {

    @RequestMapping("/captcha")
    public void captcha(@RequestParam String key, HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 指定验证码的长宽以及字符的个数
        SpecCaptcha captcha = new SpecCaptcha(135, 33, 5);
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        // 首先把验证码在后台保存一份，但是不能保存在session，可以存在redis，也可以存在后台的某个Map里面
        CaptureConfig.CAPTURE_MAP.put(key, captcha.text().toLowerCase());
        CaptchaUtil.out(captcha, request, response);

        // 算术类型
//        ArithmeticCaptcha captcha = new ArithmeticCaptcha(135, 33);
//        captcha.setLen(4);  // 几位数运算，默认是两位
//        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
//        captcha.text();  // 获取运算的结果：5
//        CaptureConfig.CAPTURE_MAP.put(key, captcha.text().toLowerCase());
//        CaptchaUtil.out(captcha, request, response);
    }
}