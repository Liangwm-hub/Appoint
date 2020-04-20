package com.liangweimin.www.po;

/**
 * @author 梁伟民
 */
public class Teacher {
    private int id;
    private String name;
    private String password;
    private String sex;
    private String college;
    private String phone;
    /**预约范围*/
    private int scope;


    public Teacher(int id, String password) {
        this.id = id;
        this.password = password;
    }

    public Teacher(int id, String name, String sex, String college, String phone) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.phone = phone;
    }

    public Teacher(int id, String name, String password, String sex, String college, String phone) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.sex = sex;
        this.college = college;
        this.phone = phone;
    }

    public Teacher() {
    }

    public Teacher(int id, String name, String sex, String college, String phone, int scope) {
        this.id = id;
        this.name = name;
        this.sex = sex;
        this.college = college;
        this.phone = phone;
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

    public int getScope() {
        return scope;
    }

    public void setScope(int scope) {
        this.scope = scope;
    }

    @Override
    public String toString() {
        return "id:"+id+" name:"+name+" sex:"+sex+" college:"+college+" phone:"+phone;
    }
}
