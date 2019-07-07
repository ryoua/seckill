package com.ryoua.seckill.entity;

import java.util.Date;

/**
 * @Author ryoua Created on 2019-07-06
 */
public class User {
    private Long id;
    private String nickname;
    private String password;
    private String salt;
    private String head;
    private Date createTime;
    private Date changeTime;
    private Integer loginCount;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getNickname() {
        return nickname;
    }
    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getSalt() {
        return salt;
    }
    public void setSalt(String salt) {
        this.salt = salt;
    }
    public String getHead() {
        return head;
    }
    public void setHead(String head) {
        this.head = head;
    }
    public Date getChangeTime() {
        return changeTime;
    }
    public void setChangeTime(Date changeTime) {
        this.changeTime = changeTime;
    }
    public Date getCreateTime() {
        return createTime;
    }
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
    public Integer getLoginCount() {
        return loginCount;
    }
    public void setLoginCount(Integer loginCount) {
        this.loginCount = loginCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", nickname='" + nickname + '\'' +
                ", password='" + password + '\'' +
                ", salt='" + salt + '\'' +
                ", head='" + head + '\'' +
                ", createTime=" + createTime +
                ", changeTime=" + changeTime +
                ", loginCount=" + loginCount +
                '}';
    }
}
