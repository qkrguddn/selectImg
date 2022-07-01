
package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImgMsg {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String msg;

    protected ImgMsg() {
    }

    public ImgMsg(String msg) {
        this.msg = msg;
    }

    public Long getId() {
        return id;
    }

    public String getMsg() {
        return msg;
    }

    public String idData() {
        return getMsg();
    }
    @Override
    public String toString() {
        return id + "";
    }
}

