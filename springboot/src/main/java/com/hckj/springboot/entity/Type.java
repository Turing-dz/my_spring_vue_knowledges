package com.hckj.springboot.entity;

import cn.hutool.core.annotation.Alias;

import javax.persistence.*;

@Table(name="type")//这里必须是双引号
public class Type {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    @Alias("图书类别名称")
    private String name;
    @Column(name = "description")
    @Alias("图书类别描述")
    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
