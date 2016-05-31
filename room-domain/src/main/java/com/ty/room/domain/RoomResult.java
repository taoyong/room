package com.ty.room.domain;

/**
 * Created with IntelliJ IDEA.
 * User: taoyong1
 * Date: 2016/5/27
 * Time: 13:11
 * Description:
 */
public class RoomResult implements java.io.Serializable{
    private String msg;
    private boolean state;

    public  RoomResult(boolean state){
        this.state = state;
    }

    public RoomResult(String msg, boolean state) {
        this.msg = msg;
        this.state = state;
    }

    public RoomResult(){}

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isState() {
        return state;
    }

    public void setState(boolean state) {
        this.state = state;
    }
}
