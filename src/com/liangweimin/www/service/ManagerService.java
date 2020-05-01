package com.liangweimin.www.service;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.dao.ManagerDao;
import com.liangweimin.www.dao.TeacherDao;
import com.liangweimin.www.po.*;

import java.util.List;

/**
 * 管理员的 业务逻辑层
 *
 * @author 梁伟民
 */
public class ManagerService implements IManagerService {

    ManagerDao managerDao = new ManagerDao();


    /**
     * 管理员登录
     *
     * @param manager
     * @return
     */
    @Override
    public boolean login(Manager manager) {
        return managerDao.login(manager);
    }


    /**
     * 分页查找预约
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<Appoint> findAppointsByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //1.创建空的PageBean对象
        PageBean<Appoint> pb = new PageBean<>();

        //2.设置页码，当前页不能小于1，不能大于总页数；设置条数的参数
        if (currentPage <= 0) {
            currentPage = 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数，设置总记录数的参数
        int totalCount = managerDao.findAppointCount();
        pb.setTotalCount(totalCount);

        //4.计算总页码数，设置总页码数的参数
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows + 1);
        pb.setTotalPage(totalPage);

        //页码不能大于总页数
        if (currentPage > totalPage) {
            currentPage -= 1;
        }
        pb.setCurrentPage(currentPage);

        //5.调用dao查询List集合,设置List的参数
        //开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Appoint> appoints = null;

        if (start>=0){
            appoints = managerDao.findAppointsByPage(start, rows);
        }
        pb.setList(appoints);

        return pb;
    }


    /**
     * 批量批准学生的请求
     *
     * @param nums
     */
    @Override
    public void processSelected(String[] nums) {
        //1.遍历数组
        for (String num :
                nums) {
            //2.调用dao批准
            managerDao.processSelected(Integer.parseInt(num));
        }
    }


    /**
     * 拒绝学生的请求
     *
     * @param num
     */
    @Override
    public void refuseRequest(int num) {
        managerDao.refuseRequest(num);
    }


    /**
     * 分页查询所有注册审核中的用户
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<User> findRegisterByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<>();

        //2.设置页码，当前页不能小于1，不能大于总页数；设置条数的参数
        if (currentPage <= 0) {
            currentPage = 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数，设置总记录数的参数
        int totalCount = managerDao.findUserRegisterCount();
        pb.setTotalCount(totalCount);

        //4.计算总页码数，设置总页码数的参数
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows + 1);
        pb.setTotalPage(totalPage);

        //页码不能大于总页数
        if (currentPage > totalPage) {
            currentPage -= 1;
        }
        pb.setCurrentPage(currentPage);

        //5.调用dao查询List集合,设置List的参数
        //开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> users = null;

        if (start>=0){
            users = managerDao.findRegisterByPage(start, rows);
        }
        pb.setList(users);

        return pb;
    }


    /**
     * 批准用户注册
     *
     * @param snos
     */
    @Override
    public void processUserRegister(String[] snos) {
        //1.遍历数组
        for (String sno :
                snos) {
            //2.调用dao批准
            managerDao.processUserRegister(Integer.parseInt(sno));
        }
    }


    /**
     * 驳回用户的注册请求
     *
     * @param sno
     */
    @Override
    public void refuseUserRegister(int sno) {
        managerDao.refuseUserRegister(sno);
    }


    /**
     * 注册导师账号
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean teacherRegister(Teacher teacher) {
        TeacherDao teacherDao = new TeacherDao();
        boolean exit = teacherDao.isExit(teacher.getId());
        //不存在这个账号,才能注册
        if (!exit) {
            return managerDao.teacherRegister(teacher);
        }
        return false;
    }


    /**
     * 冻结用户的账号
     *
     * @param sno
     */
    @Override
    public void freezeUser(int sno) {
        managerDao.freezeUser(sno);
    }


    /**
     * 分页查询所有注册审核中的用户
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<User> findFreezeUserByPage(String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //1.创建空的PageBean对象
        PageBean<User> pb = new PageBean<>();

        //2.设置页码，当前页不能小于1，不能大于总页数；设置条数的参数
        if (currentPage <= 0) {
            currentPage = 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数，设置总记录数的参数
        int totalCount = managerDao.findFreezeUserCount();
        pb.setTotalCount(totalCount);

        //4.计算总页码数，设置总页码数的参数
        int totalPage = (totalCount % rows) == 0 ? (totalCount / rows) : (totalCount / rows + 1);
        pb.setTotalPage(totalPage);

        //页码不能大于总页数
        if (currentPage > totalPage) {
            currentPage -= 1;
        }
        pb.setCurrentPage(currentPage);

        //5.调用dao查询List集合,设置List的参数
        //开始的记录索引
        int start = (currentPage - 1) * rows;
        List<User> users = null;

        if (start>=0){
            users = managerDao.findFreezeUserByPage(start, rows);
        }
        pb.setList(users);

        return pb;
    }


    /**
     * 解封用户的账号
     *
     * @param sno
     */
    @Override
    public void unsealUser(int sno) {
        managerDao.unsealUser(sno);
    }


    /**
     * 管理员发布新的通知
     *
     * @param notice
     * @return
     */
    @Override
    public boolean issueNotice(Notice notice) {
        return managerDao.issueNotice(notice);
    }


    /**
     * 返回所有的通知
     *
     * @return
     */
    @Override
    public List<Notice> findAllNotices() {
        return managerDao.findAllNotices();
    }


    /**
     * 通过id找到通知
     *
     * @param noticeId
     * @return
     */
    @Override
    public Notice queryNoticeById(int noticeId) {
        return managerDao.queryNoticeById(noticeId);
    }


    /**
     * 通过id删除发布的通知
     *
     * @param noticeId
     * @return
     */
    @Override
    public boolean deleteNoticeById(int noticeId) {
        return managerDao.deleteNoticeById(noticeId);
    }
}
