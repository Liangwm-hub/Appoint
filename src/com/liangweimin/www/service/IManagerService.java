package com.liangweimin.www.service;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.po.*;

import java.util.List;

/**
 * @author 梁伟民
 */
public interface IManagerService {
    /**
     * 管理员登录
     *
     * @param manager
     * @return
     */
    boolean login(Manager manager);

    /**
     * 分页查找预约
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<Appoint> findAppointsByPage(String _currentPage, String _rows);

    /**
     * 批量批准学生的请求
     *
     * @param nums
     */
    void processSelected(String[] nums);

    /**
     * 拒绝学生的请求
     *
     * @param num
     */
    void refuseRequest(int num);

    /**
     * 分页查询所有注册审核中的用户
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<User> findRegisterByPage(String _currentPage, String _rows);

    /**
     * 批准用户注册
     *
     * @param snos
     */
    void processUserRegister(String[] snos);

    /**
     * 驳回用户的注册请求
     *
     * @param sno
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
     * 分页查询所有注册审核中的用户
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<User> findFreezeUserByPage(String _currentPage, String _rows);

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
     *
     * @param noticeId
     * @return
     */
    boolean deleteNoticeById(int noticeId);
}
