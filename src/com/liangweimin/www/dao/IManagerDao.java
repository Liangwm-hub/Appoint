package com.liangweimin.www.dao;

import com.liangweimin.www.po.*;

import java.util.List;

/**
 * @author 梁伟民
 */
public interface IManagerDao {
    /**
     * 管理员登录
     *
     * @param manager
     * @return
     */
    boolean login(Manager manager);

    /**
     * 查询 appoint表 中等待审核的记录数
     *
     * @return
     */
    int findAppointCount();

    /**
     * 分页查询所有学生的预约请求
     *
     * @param start
     * @param rows
     * @return
     */
    List<Appoint> findAppointsByPage(int start, int rows);

    /**
     * 批准学生的请求
     *
     * @param num
     */
    void processSelected(int num);

    /**
     * 拒绝学生的请求
     *
     * @param num
     */
    void refuseRequest(int num);

    /**
     * 查询注册审核中的用户数量
     *
     * @return
     */
    int findUserRegisterCount();

    /**
     * 分页查询所有注册审核中的用户
     *
     * @param start
     * @param rows
     * @return
     */
    List<User> findRegisterByPage(int start, int rows);

    /**
     * 批准用户注册，即改变 字段status 为注册成功
     *
     * @param sno
     * @return
     */
    void processUserRegister(int sno);

    /**
     * 驳回用户注册，即改变 字段status 为注册失败
     *
     * @param sno
     * @return
     */
    void refuseUserRegister(int sno);

    /**
     * 注册导师账号
     *
     * @param teacher
     * @return
     */
    boolean teacherRegister(Teacher teacher);

    /**
     * 冻结用户的账号
     *
     * @param sno
     */
    void freezeUser(int sno);

    /**
     * 查询注册冻结中的用户数量
     *
     * @return
     */
    int findFreezeUserCount();

    /**
     * 分页查询所有冻结中中的用户
     *
     * @param start
     * @param rows
     * @return
     */
    List<User> findFreezeUserByPage(int start, int rows);

    /**
     * 解封用户的账号
     *
     * @param sno
     */
    void unsealUser(int sno);

    /**
     * 管理员发布新的通知
     *
     * @param notice
     * @return
     */
    boolean issueNotice(Notice notice);

    /**
     * 返回所有的通知
     *
     * @return
     */
    List<Notice> findAllNotices();

    /**
     * 通过id找到通知
     *
     * @param noticeId
     * @return
     */
    Notice queryNoticeById(int noticeId);

    /**
     * 通过id删除发布的通知
     * @param noticeId
     * @return
     */
    boolean deleteNoticeById(int noticeId);
}
