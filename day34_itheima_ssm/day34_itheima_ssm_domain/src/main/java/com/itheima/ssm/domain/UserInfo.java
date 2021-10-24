package com.itheima.ssm.domain;

import java.util.List;

//与数据库中users对应
public class UserInfo {
    private String id;
    private String username;
    private String email;
    private String password;
    private String phoneNum;
    private Integer status;
    private Boolean statusBoolean;
    private String statusStr;

    public Boolean getStatusBoolean() {
        if (status != null){
            //1表示可用，0表示不可用
            if (status == 1)
                statusBoolean=true;
            if (status == 0)
                statusBoolean=false;
        }
        return statusBoolean;
    }

    public void setStatusBoolean(Boolean statusBoolean) {
        this.statusBoolean = statusBoolean;
    }

    private List<Role> roles;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getStatusStr() {
        if (status != null){
            //1表示可用，0表示不可用
            if (status == 1)
               statusStr="开启";
            if (status == 0)
                statusStr="未开启";
        }
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }
}
