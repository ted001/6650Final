package com.educative.ecommerce.model;

import javax.persistence.*;

@Entity
@Table(name = "timestamp")
public class TimeStamp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private int val;
    private long time;
    public TimeStamp(int val) {
        this.val = val;
    }

    public TimeStamp() {
    }

    public Integer getId() {
        return id;
    }

    public Integer getVal() {
        return val;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setVal(int val) {
        this.val = val;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public long getTime(int port) {
        return Long.parseLong(System.currentTimeMillis()+""+port);
    }
}

