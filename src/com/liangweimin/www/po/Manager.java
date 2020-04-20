package com.liangweimin.www.po;

/**
 * 管理员的 javabean
 * @author 梁伟民
 */
public class Manager {
    /**管理员id*/
    private int managerId;
    /**密码*/
    private String password;

    public Manager() {
    }

    public Manager(int managerId, String password) {
        this.managerId = managerId;
        this.password = password;
    }

    public int getManagerId() {
        return managerId;
    }

    public void setManagerId(int managerId) {
        this.managerId = managerId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
