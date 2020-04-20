package com.liangweimin.www.dao;

import com.liangweimin.www.dao.impl.IManagerDao;
import com.liangweimin.www.po.*;
import com.liangweimin.www.util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 梁伟民
 */
public class ManagerDao implements IManagerDao {


    /**
     * 管理员登录
     *
     * @param manager
     * @return
     */
    @Override
    public boolean login(Manager manager) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from manager where manager_id=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, manager.getManagerId());
            ps.setString(2, manager.getPassword());

            //返回一个结果集
            rs = ps.executeQuery();

            if (rs.next()) {
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
        return false;
    }


    /**
     * 查询 appoint表 中等待审核的记录数
     *
     * @return
     */
    @Override
    public int findAppointCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from appoint where status='审核中'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }

    }


    /**
     * 分页查询所有学生的预约请求
     *
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Appoint> findAppointsByPage(int start, int rows) {
        List<Appoint> users = new ArrayList<>();
        Appoint appoint = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = " select * from appoint where status='审核中' limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, rows);
            rs = ps.executeQuery();

            while (rs.next()) {
                int num = rs.getInt(1);
                int id = rs.getInt(2);
                String teacherName = rs.getString(3);
                int sno = rs.getInt(4);
                String userName = rs.getString(5);
                String appointTime = rs.getString(6);
                String place = rs.getString(7);
                String requestTime = rs.getString(8);
                String teacherCollege = rs.getString(9);
                String teacherPhone = rs.getString(10);
                String status = rs.getString(11);

                appoint = new Appoint(num, id, teacherName, sno, userName, appointTime, place, requestTime, teacherCollege, teacherPhone, status);
                users.add(appoint);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 批准学生的请求
     *
     * @param num
     */
    @Override
    public void processSelected(int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `appoint` set status='预约成功' where num=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps);
        }

    }


    /**
     * 拒绝学生的请求
     *
     * @param num
     */
    @Override
    public void refuseRequest(int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `appoint` set status='预约失败' where num=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps);
        }

    }


    /**
     * 查询注册审核中的用户数量
     *
     * @return
     */
    @Override
    public int findUserRegisterCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from user where status='审核中'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }

    /**
     * 分页查询所有注册审核中的用户
     *
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<User> findRegisterByPage(int start, int rows) {
        List<User> users = new ArrayList<>();
        User user = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = " select * from `user` where status='审核中' limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, rows);
            rs = ps.executeQuery();

            //用循环获得rows条数据
            while (rs.next()) {
                int sno = rs.getInt(1);
                String name = rs.getString(2);
                String status = rs.getString(7);

                //把数据都放到List中
                user = new User(sno, name, status);
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 批准用户注册，即改变 字段status 为注册成功
     *
     * @param sno
     * @return
     */
    @Override
    public void processUserRegister(int sno) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `user` set status='注册成功' where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sno);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps);
        }
    }


    /**
     * 驳回用户注册，即改变 字段status 为注册失败
     *
     * @param sno
     * @return
     */
    @Override
    public void refuseUserRegister(int sno) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `user` set status='注册失败' where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sno);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps);
        }
    }


    /**
     * 注册导师账号
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean teacherRegister(Teacher teacher) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into teacher(id,name,password,sex,college,phone,scope) values (?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teacher.getId());
            ps.setString(2, teacher.getName());
            ps.setString(3, teacher.getPassword());
            ps.setString(4, teacher.getSex());
            ps.setString(5, teacher.getCollege());
            ps.setString(6, teacher.getPhone());
            //预约范围默认是30天
            ps.setInt(7, 30);

            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 冻结用户的账号
     *
     * @param sno
     */
    @Override
    public void freezeUser(int sno) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `user` set status='冻结中' where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sno);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps);
        }
    }


    /**
     * 查询注册冻结中的用户数量
     *
     * @return
     */
    @Override
    public int findFreezeUserCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from user where status='冻结中'";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
            }
            return count;
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }

    /**
     * 分页查询所有冻结中中的用户
     *
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<User> findFreezeUserByPage(int start, int rows) {
        List<User> users = new ArrayList<>();
        User user = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = " select * from `user` where status='冻结中' limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, rows);
            rs = ps.executeQuery();

            //用循环获得rows条数据
            while (rs.next()) {
                int sno = rs.getInt(1);
                String name = rs.getString(2);
                String status = rs.getString(7);

                //把数据都放到List中
                user = new User(sno, name, status);
                users.add(user);
            }
            return users;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 解封用户的账号
     *
     * @param sno
     */
    @Override
    public void unsealUser(int sno) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `user` set status='注册成功' where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sno);

            ps.execute();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps);
        }
    }


    /**
     * 管理员发布新的通知
     *
     * @param notice
     * @return
     */
    @Override
    public boolean issueNotice(Notice notice) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //连接数据库
            conn = JDBCUtil.getConnection();
            //预编译
            String sql = "insert into `notice`(notice_title,notice_content,notice_file) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            //填充
            ps.setString(1, notice.getNoticeTitle());
            ps.setString(2, notice.getNoticeContent());
            ps.setString(3, notice.getFileName());

            //执行
            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            //关闭资源
            JDBCUtil.closeSource(conn, ps);
        }
    }


    /**
     * 返回所有的通知
     *
     * @return
     */
    @Override
    public List<Notice> findAllNotices() {

        Notice notice = null;
        List<Notice> notices = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from notice order by notice_id desc";
            ps = conn.prepareStatement(sql);

            rs = ps.executeQuery();

            while (rs.next()) {
                int noticeId = rs.getInt(1);
                String noticeTitle = rs.getString(2);
                String noticeContent = rs.getString(3);
                String fileName = rs.getString(4);

                notice = new Notice(noticeId, noticeTitle, noticeContent, fileName);
                notices.add(notice);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
        return notices;
    }


    /**
     * 通过id找到通知
     *
     * @param noticeId
     * @return
     */
    @Override
    public Notice queryNoticeById(int noticeId) {
        Notice notice = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = JDBCUtil.getConnection();

            //利用学号获得数据
            String sql = "select * from notice where notice_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, noticeId);
            rs = ps.executeQuery();

            if (rs.next()) {
                String noticeTitle = rs.getString(2);
                String noticeContent = rs.getString(3);
                String fileName = rs.getString(4);

                //封装数据
                notice = new Notice(noticeId, noticeTitle, noticeContent, fileName);
            }
            //返回一个user对象
            return notice;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 通过id删除发布的通知
     * @param noticeId
     * @return
     */
    @Override
    public boolean deleteNoticeById(int noticeId){
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from `notice` where notice_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, noticeId);

            int count = ps.executeUpdate();
            if (count > 0) {
                return true;
            } else {
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            JDBCUtil.closeSource(conn, ps);
        }
    }
}
