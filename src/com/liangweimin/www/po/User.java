package com.liangweimin.www.po;

/**
 * @author 梁伟民
 */
public class User {
    /**学号*/
    private int sno;
    /**名字*/
    private String name;
    /**密码*/
    private String password;
    /**性别*/
    private String sex;
    /**专业班级*/
    private String majorClass;
    /**电话*/
    private String phone;
    /**账号状态*/
    private String status;

    public User() {
    }

    public User(int sno, String password) {
        this.sno = sno;
        this.password = password;
    }

    public User(int sno, String name, String password) {
        this.sno = sno;
        this.name = name;
        this.password = password;
    }

    public User(int sno, String name, String password, String sex, String majorClass, String phone) {
        this.sno = sno;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.majorClass = majorClass;
        this.phone = phone;
    }

    public User(int sno, String name, String sex, String majorClass, String phone) {
        this.sno = sno;
        this.name = name;
        this.sex = sex;
        this.majorClass = majorClass;
        this.phone = phone;
    }

    public int getSno() {
        return sno;
    }

    public void setSno(int sno) {
        this.sno = sno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMajorClass() {
        return majorClass;
    }

    public void setMajorClass(String majorClass) {
        this.majorClass = majorClass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "sno:" + sno + " name:" + name + " sex:" + sex + " majorClass:" + majorClass + " phone:" + phone;
    }
}
