package com.example.remote_help;

public class Person {

    //使用懒汉单例模式，即在类加载时创建好对象
    public static final Person person = new Person();

    public Person(){}

    public static Person getInstance() {
        return person;
    }

    long AppID;

    String RoomID;

    String UserID;

    String StreamID;

    String AppSign;

    public String getAppSign() {
        return AppSign;
    }

    public void setAppSign(String appSign) {
        AppSign = appSign;
    }

    public long getAppID() {
        return AppID;
    }

    public void setAppID(long appID) {
        AppID = appID;
    }

    public String getRoomID() {
        return RoomID;
    }

    public void setRoomID(String roomID) {
        RoomID = roomID;
    }

    public String getUserID() {
        return UserID;
    }

    public void setUserID(String userID) {
        UserID = userID;
    }

    public String getStreamID() {
        return StreamID;
    }

    public void setStreamID(String streamID) {
        StreamID = streamID;
    }
}
