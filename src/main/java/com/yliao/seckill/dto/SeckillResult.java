package com.yliao.seckill.dto;

/**
 * @Author: yliao
 * @Date: Created in 2018/4/5
 * 封装json结果
 */
public class SeckillResult<T> {
    private boolean success;
    private T data;
    private String info;

    public SeckillResult() {}

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public SeckillResult(boolean success, String info) {
        this.success = success;
        this.info = info;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public String toString() {
        return "SeckillResult{" +
                "success=" + success +
                ", data=" + data +
                ", info='" + info + '\'' +
                '}';
    }
}
