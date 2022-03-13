package com.capgemini.NightFox.Exception;

import java.time.LocalDate;

public class ErrorDetails {

    private LocalDate timeStamp;
    private String msg;
    private String details;

    public ErrorDetails(LocalDate timeStamp, String msg, String details) {
        this.timeStamp = timeStamp;
        this.msg = msg;
        this.details = details;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
