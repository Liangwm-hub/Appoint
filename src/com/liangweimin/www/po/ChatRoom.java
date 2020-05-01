package com.liangweimin.www.po;

/**
 * 聊天室
 *
 * @author 梁伟民
 */
public class ChatRoom {
    /**聊天编号*/
    private int chatId;
    /**导师的编号*/
    private int teacherId;
    /**导师名字*/
    private String teacherName;
    /**学生的学号*/
    private int userSno;
    /**学生名字*/
    private String userName;

    public ChatRoom(int teacherId, String teacherName, int userSno, String userName) {
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.userSno = userSno;
        this.userName = userName;
    }

    public ChatRoom(int chatId, int teacherId, String teacherName, int userSno, String userName) {
        this.chatId = chatId;
        this.teacherId = teacherId;
        this.teacherName = teacherName;
        this.userSno = userSno;
        this.userName = userName;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public int getUserSno() {
        return userSno;
    }

    public void setUserSno(int userSno) {
        this.userSno = userSno;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "ChatRoom{" +
                "chatId=" + chatId +
                ", teacherId=" + teacherId +
                ", teacherName='" + teacherName + '\'' +
                ", userSno=" + userSno +
                ", userName='" + userName + '\'' +
                '}';
    }
}
