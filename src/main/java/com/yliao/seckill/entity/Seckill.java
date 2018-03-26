package com.yliao.seckill.entity;

import java.util.Date;

public class Seckill {
    private long seckillId;
    private String name;
    private Integer number;
    private Date satrtTime;
    private Date endTime;
    private Date createTime;

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Date getSatrtTime() {
        return satrtTime;
    }

    public void setSatrtTime(Date satrtTime) {
        this.satrtTime = satrtTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Seckill{" +
                "seckillId=" + seckillId +
                ", name='" + name + '\'' +
                ", number=" + number +
                ", satrtTime=" + satrtTime +
                ", endTime=" + endTime +
                ", createTime=" + createTime +
                '}';
    }
}
