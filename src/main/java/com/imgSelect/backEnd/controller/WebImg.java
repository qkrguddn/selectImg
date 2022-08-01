package com.imgSelect.backEnd.controller;
public class WebImg {
    private String content;
    private String mysqlData;
    private String frameName;
    private int pageCount;
    private Long finalId;

    public WebImg(int pageCount,String frameName)
    {
        this.pageCount = pageCount;
        this.frameName = frameName;
    }
    public String getFrameName() {return frameName;}
    public void setFrameName(String frameName) {this.frameName = frameName;}

    public String getContent() {return content;}
    public void setContent(String content) {this.content = content;}

    public String getMysqlData() {return mysqlData;}
    public void setMysqlData(String mysqlData) {this.mysqlData = mysqlData;}

    public  int getPageCount(){return pageCount;}
    public void setPageCount(int pageCount){this.pageCount = pageCount;}

    public  Long getFinalId(){return finalId;}
    public void setFinalId(Long finalId){this.finalId = finalId;}
}

