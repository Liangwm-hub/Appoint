package com.liangweimin.www.po;

/**
 * 导师发布预约的 javabean
 * @author 梁伟民
 */
public class Release {

    /**职工号*/
    private int id;
    /**名字*/
    private String name;
    /**性别*/
    private String sex;
    /**学院*/
    private String college;
    /**电话*/
    private String phone;
    /**见面时间*/
    private String appointTime;
    /**见面地点*/
    private String place;
    /**预约范围*/
    private int scope;


    public Release() {
    }

    public Release(String appointTime, String place) {
        this.appointTime = appointTime;
        this.place = place;
    }

    public Release(int id, String appointTime, String place) {
        this.id = id;
        this.appointTime = appointTime;
        this.place = place;
    }

    public Release(int id, String name, String sex, String college, String phone, String appointTime, String place) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.phone = phone;
        this.appointTime = appointTime;
        this.place = place;
    }

    public Release(int id, String name, String sex, String college, String phone, String appointTime, String place, int scope) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.phone = phone;
        this.appointTime = appointTime;
        this.place = place;
        this.scope = scope;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getCollege() {
        return college;
    }

    public void setCollege(String college) {
        this.college = college;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "id:"+id+" name:"+name+" sex:"+sex+" college:"+college+" phone:"+phone+" appointTime:"+appointTime+" place"+place;

    }
}
