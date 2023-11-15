package com.hckj.springboot.entity;

import javax.persistence.*;
import java.util.Objects;

@Table(name="admin")//这里必须是双引号
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
    @Column(name = "sex")
    private String sex;
    @Column(name = "age")
    private Integer age;
    @Column(name = "phone")
    private String phone;
    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    @Transient//不需要被持久化或序列化的临时数据或敏感数据
    private String token;
    @Transient
    private String verCode;

    public String getVerCode() {
        return verCode;
    }

    public void setVerCode(String verCode) {
        this.verCode = verCode;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }


    @Override
    public String toString() {
        return "Admin{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Admin admin)) return false;
        return Objects.equals(getId(), admin.getId()) && Objects.equals(getName(), admin.getName()) && Objects.equals(getPassword(), admin.getPassword()) && Objects.equals(getSex(), admin.getSex()) && Objects.equals(getAge(), admin.getAge()) && Objects.equals(getPhone(), admin.getPhone());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getPassword(), getSex(), getAge(), getPhone());
    }

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
