package com.hckj.springboot.controller;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;
import com.github.pagehelper.PageInfo;
import com.hckj.springboot.common.Result;
import com.hckj.springboot.entity.Admin;
import com.hckj.springboot.entity.Book;
import com.hckj.springboot.entity.Params;
import com.hckj.springboot.service.AdminService;
import com.hckj.springboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/book")
public class BookController {
    @Autowired
    private BookService bookService;
    @GetMapping("/search")
    public Result findBySearch(Params params){
        PageInfo<Book> list = bookService.findBySearch(params);
        return Result.success(list);
    }
    @PostMapping("/addedit")
    public Result save(@RequestBody Book book){
        if (book.getId()==null){//新增
            bookService.add(book);
        }else{//编辑
            bookService.update(book);
        }
        return Result.success();
    }
    @DeleteMapping("/del/{id}")
    public Result delete(@PathVariable Integer id){
        bookService.delete(id);
        return Result.success();
    }
    @GetMapping("/echarts/bie")
    public Result bie() {
        // 查询出所有图书
        List<Book> list = bookService.findAll();
        Map<String, Long> collect = list.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getTypeName()))
                .collect(Collectors.groupingBy(Book::getTypeName, Collectors.counting()));
        // 最后返回给前端的数据结构
        List<Map<String, Object>> mapList = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(collect)) {
            for (String key : collect.keySet()) {
                Map<String, Object> map = new HashMap<>();
                map.put("name", key);
                map.put("value", collect.get(key));
                mapList.add(map);
            }
        }
        return Result.success(mapList);
    }

    @GetMapping("/echarts/bar")
    public Result bar() {
        // 查询出所有图书
        List<Book> list = bookService.findAll();
        Map<String, Long> collect = list.stream()
                .filter(x -> ObjectUtil.isNotEmpty(x.getTypeName()))
                .collect(Collectors.groupingBy(Book::getTypeName, Collectors.counting()));

        List<String> xAxis = new ArrayList<>();
        List<Long> yAxis = new ArrayList<>();
        if (CollectionUtil.isNotEmpty(collect)) {
            for (String key : collect.keySet()) {
                xAxis.add(key);
                yAxis.add(collect.get(key));
            }
        }
        Map<String, Object> map = new HashMap<>();
        map.put("xAxis", xAxis);
        map.put("yAxis", yAxis);
        return Result.success(map);
    }

}