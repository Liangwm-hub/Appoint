package com.liangweimin.www.po;

/**
 * 导师的 javabean
 * @author 梁伟民
 */
public class Appoint {

    /**预约编号*/
    private int num;
    /**导师编号*/
    private int id;
    /**导师名字*/
    private String teacherName;
    /**学生学号*/
    private int sno;
    /**学生名字*/
    private String userName;
    /**见面预定时间*/
    private String appointTime;
    /**见面地点*/
    private String place;
    /**发起请求时间*/
    private String requestTime;
    /**导师所属学院*/
    private String teacherCollege;
    /**导师电话*/
    private String teacherPhone;
    /**预约状态*/
    private String status;
    /**上传的图片*/
    private String picture;


    public Appoint(int id, int sno, String userName, String appointTime, String place) {
        this.id = id;
        this.sno = sno;
        this.userName = userName;
        this.appointTime = appointTime;
        this.place = place;
    }


    public Appoint(int id, int sno, String appointTime, String place) {
        this.id = id;
        this.sno = sno;
        this.appointTime = appointTime;
        this.place = place;
    }

    public Appoint(int id, String teacherName, int sno, String userName, String appointTime, String place, String requestTime, String status) {
        this.id = id;
        this.teacherName = teacherName;
        this.userName = userName;
        this.sno = sno;
        this.appointTime = appointTime;
        this.place = place;
        this.requestTime = requestTime;
        this.status = status;
    }

    public Appoint(int id, String teacherName, String appointTime, String place, String requestTime, String teacherCollege, String teacherPhone, String status) {
        this.id = id;
        this.teacherName = teacherName;
        this.appointTime = appointTime;
        this.place = place;
        this.requestTime = requestTime;
        this.teacherCollege = teacherCollege;
        this.teacherPhone = teacherPhone;
        this.status = status;
    }

    public Appoint(int id, String teacherName, int sno, String appointTime, String place, String requestTime, String teacherCollege, String teacherPhone, String status) {
        this.id = id;
        this.teacherName = teacherName;
        this.sno = sno;
        this.appointTime = appointTime;
        this.place = place;
        this.requestTime = requestTime;
        this.teacherCollege = teacherCollege;
        this.teacherPhone = teacherPhone;
        this.status = status;
    }

    public Appoint(int num, int id, int sno, String userName, String appointTime, String place) {
        this.num = num;
        this.id = id;
        this.sno = sno;
        this.userName = userName;
        this.appointTime = appointTime;
        this.place = place;
    }

    public Appoint(int id, String teacherName, String appointTime, String place, String requestTime, String status) {
        this.id = id;
        this.teacherName = teacherName;
        this.appointTime = appointTime;
        this.place = place;
        this.requestTime = requestTime;
        this.status = status;
    }

    public Appoint() {
    }

    public Appoint(int sno, String appointTime, String place, String requestTime) {
        this.sno = sno;
        this.appointTime = appointTime;
        this.place = place;
        this.requestTime = requestTime;
    }

    public Appoint(int num, int id, String teacherName, int sno, String appointTime, String place, String requestTime, String teacherCollege, String teacherPhone, String status) {
        this.num = num;
        this.id = id;
        this.teacherName = teacherName;
        this.sno = sno;
        this.appointTime = appointTime;
        this.place = place;
        this.requestTime = requestTime;
        this.teacherCollege = teacherCollege;
        this.teacherPhone = teacherPhone;
        this.status = status;
    }

    public Appoint(int num, int id, String teacherName, int sno, String userName, String appointTime, String place, String requestTime, String teacherCollege, String teacherPhone, String status) {
        this.num = num;
        this.id = id;
        this.teacherName = teacherName;
        this.sno = sno;
        this.userName = userName;
        this.appointTime = appointTime;
        this.place = place;
        this.requestTime = requestTime;
        this.teacherCollege = teacherCollege;
        this.teacherPhone = teacherPhone;
        this.status = status;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getAppointTime() {
        return appointTime;
    }

    public void setAppointTime(String appointTime) {
        this.appointTime = appointTime;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(String requestTime) {
        this.requestTime = requestTime;
    }

    public String getTeacherCollege() {
        return teacherCollege;
    }

    public void setTeacherCollege(String teacherCollege) {
        this.teacherCollege = teacherCollege;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
