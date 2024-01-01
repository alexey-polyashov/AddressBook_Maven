package com.example.adrbook.dto;



public class MyErrorInfo {
    private String info;
    private String reason;

    public MyErrorInfo(String info, String reason) {
        this.info = info;
        this.reason = reason;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

}
