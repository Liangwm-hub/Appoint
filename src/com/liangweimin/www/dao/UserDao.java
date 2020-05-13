package com.liangweimin.www.dao;

import com.liangweimin.www.po.*;
import com.liangweimin.www.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 用户（学生）的
 * 数据访问层
 * 直接操作数据库（单纯的增删改查等）
 *
 * @author 梁伟民
 */
public class UserDao implements IUserDao {

    /**
     * 创建JDBCTemplate对象
     */
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());


    /**
     * 用户（学生）登录
     *
     * @param user
     * @return 布尔值（登录是否成功）
     */
    @Override
    public boolean login(User user) {
        String sql = "select count(*) from user where sno=? and password=? and status=?";
        Integer count = template.queryForObject(sql, int.class, user.getSno(), user.getPassword(), "注册成功");
        System.out.println(count);
        return count > 0;
    }

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public boolean userRegister(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "insert into user(sno,name,password,status) values (?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, user.getSno());
            ps.setString(2, user.getName());
            ps.setString(3, user.getPassword());
            ps.setString(4, "审核中");

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
     * 用户修改密码
     *
     * @param user
     * @return
     */
    @Override
    public boolean setPassword(User user) {
        String sql = "update `user` set `password`=? where `sno`=?";
        int count = template.update(sql, user.getPassword(), user.getSno());
        return count > 0;
    }

    /**
     * 通过学号判断用户是否已存在
     *
     * @param sno
     * @return 一个布尔值（用户是否存在）
     */
    @Override
    public boolean isExit(int sno) {
        return queryUserBySno(sno) == null ? false : true;
    }


    /**
     * 通过学号查询用户
     *
     * @param sno
     * @return 一个 User 对象
     */
    @Override
    public User queryUserBySno(int sno) {
        User user = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //利用学号获得数据
            String sql = "select * from user where sno=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sno);
            rs = ps.executeQuery();

            if (rs.next()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                String sex = rs.getString(4);
                String majorClass = rs.getString(5);
                String phone = rs.getString(6);

                //封装数据到User
                user = new User(no, name, sex, majorClass, phone);
            }
            //返回一个user对象
            return user;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }

    /**
     * 修改用户的个人信息
     *
     * @param sno
     * @param user
     * @return 布尔值（修改是否成功）
     */
    @Override
    public boolean updateUser(int sno, User user) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = JDBCUtil.getConnection();
            String sql = "update user set name =?,sex=?,majorClass=?,phone=? where sno=?";
            ps = conn.prepareStatement(sql);

            ps.setString(1, user.getName());
            ps.setString(2, user.getSex());
            ps.setString(3, user.getMajorClass());
            ps.setString(4, user.getPhone());
            ps.setInt(5, sno);

            //返回执行影响的条数
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


    /**
     * 学生 预约导师
     *
     * @param sno
     * @param release
     * @return
     */
    @Override
    public boolean appoint(int sno, String userName, String fileName, Release release) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //连接数据库
            conn = JDBCUtil.getConnection();
            //预编译
            String sql = "insert into `appoint`(teacher_id,teacher_name,user_sno,user_name,appoint_time,place,request_time,teacher_college,teacher_phone,status,picture) values(?,?,?,?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //填充
            ps.setInt(1, release.getId());
            ps.setString(2, release.getName());
            ps.setInt(3, sno);
            ps.setString(4, userName);
            ps.setString(5, release.getAppointTime());
            ps.setString(6, release.getPlace());
            //当前时间
            Date date = new Date();
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            ps.setString(7, simpleDateFormat.format(date));
            ps.setString(8, release.getCollege());
            ps.setString(9, release.getPhone());
            ps.setString(10, "审核中");
            ps.setString(11, fileName);

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
     * 查找release表记录总数量
     *
     * @return
     */
    @Override
    public int findReleaseCount() {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from `release` where appoint_time between now() and date_add(curdate(),interval scope day)";
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
     * 查询release表 所有老师发布的预约 返回当前页的数据集合
     *
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Release> findReleaseByPage(int start, int rows) {
        List<Release> releases = new ArrayList<>();
        Release release1 = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = " select * from `release` where appoint_time between now() and date_add(curdate(),interval scope day) limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, start);
            ps.setInt(2, rows);
            rs = ps.executeQuery();

            //用循环获得rows条数据
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String sex = rs.getString(3);
                String college = rs.getString(4);
                String phone = rs.getString(5);
                String appointTime = rs.getString(6);
                String place = rs.getString(7);

                //把数据都放到List中
                release1 = new Release(id, name, sex, college, phone, appointTime, place);
                releases.add(release1);
            }
            return releases;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 查找appoint 表里 自己预约 记录总数量
     *
     * @return
     */
    @Override
    public int findTotalCount(int sno) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from `appoint` where user_sno=? and DATE_FORMAT(request_time,'%Y-%m-%d')>=DATE_SUB(curdate(),interval 30 day)";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sno);

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
     * 返回学生 在appoint表 当前页的预约数据集合
     *
     * @param sno
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Appoint> findAppointmentByPage(int sno, int start, int rows) {
        List<Appoint> appoints = new ArrayList<>();
        Appoint appoint1 = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = " select * from `appoint` where user_sno=? and DATE_FORMAT(request_time,'%Y-%m-%d')>=DATE_SUB(curdate(),interval 30 day) order by appoint_time desc limit ?,? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, sno);
            ps.setInt(2, start);
            ps.setInt(3, rows);
            rs = ps.executeQuery();

            //用循环获得rows条数据
            while (rs.next()) {
                int num = rs.getInt(1);
                int id = rs.getInt(2);
                String teacherName = rs.getString(3);
                String appointTime = rs.getString(6);
                String place = rs.getString(7);
                String requestTime = rs.getString(8);
                String teacherCollege = rs.getString(9);
                String requestPhone = rs.getString(10);
                String status = rs.getString(11);

                //把数据都放到List中
                appoint1 = new Appoint(num, id, teacherName, sno, appointTime, place, requestTime, teacherCollege, requestPhone, status);
                appoints.add(appoint1);
            }
            return appoints;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }

    /**
     * 学生取消预约
     *
     * @param num
     * @return
     */
    @Override
    public boolean deleteAppointment(int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from `appoint` where num=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, num);

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


    /**
     * 搜索得到的记录数
     *
     * @param likeKeywords
     * @return
     */
    @Override
    public int findSearchCount(String likeKeywords) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from `release` where (id like ? or name like ? or sex like ? or college like ? or phone like ? or appoint_time like ? or place like ?) and (appoint_time between now() and date_add(curdate(),interval scope day))";
            ps = conn.prepareStatement(sql);
            ps.setString(1, likeKeywords);
            ps.setString(2, likeKeywords);
            ps.setString(3, likeKeywords);
            ps.setString(4, likeKeywords);
            ps.setString(5, likeKeywords);
            ps.setString(6, likeKeywords);
            ps.setString(7, likeKeywords);

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
     * 通过关键字 搜索到请求 获得一个List
     *
     * @param likeKeywords
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Release> searchRelease(String likeKeywords, int start, int rows) {
        List<Release> releases = new ArrayList<>();
        Release release1 = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = " select * from `release` where (id like ? or name like ? or sex like ? or college like ? or phone like ? or appoint_time like ? or place like ?) and (appoint_time between now() and date_add(curdate(),interval scope day)) limit ?,?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, likeKeywords);
            ps.setString(2, likeKeywords);
            ps.setString(3, likeKeywords);
            ps.setString(4, likeKeywords);
            ps.setString(5, likeKeywords);
            ps.setString(6, likeKeywords);
            ps.setString(7, likeKeywords);
            ps.setInt(8, start);
            ps.setInt(9, rows);
            rs = ps.executeQuery();

            //用循环获得rows条数据
            while (rs.next()) {
                int id = rs.getInt(1);
                String name = rs.getString(2);
                String sex = rs.getString(3);
                String college = rs.getString(4);
                String phone = rs.getString(5);
                String appointTime = rs.getString(6);
                String place = rs.getString(7);

                //把数据都放到List中
                release1 = new Release(id, name, sex, college, phone, appointTime, place);
                releases.add(release1);
            }
            return releases;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 上传图片名字到appoint表
     *
     * @param appoint
     * @param picture
     * @return
     */
    @Override
    public boolean uploadPicture(Appoint appoint, String picture) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `appoint` set picture=? where teacher_id=? and user_sno=? and appoint_time=? and place=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, picture);
            ps.setInt(2, appoint.getId());
            ps.setInt(3, appoint.getSno());
            ps.setString(4, appoint.getAppointTime());
            ps.setString(5, appoint.getPlace());

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
     * 学生创建聊天室
     *
     * @param chatRoom
     * @return 布尔值
     */
    @Override
    public boolean createChatRoom(ChatRoom chatRoom) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //连接数据库
            conn = JDBCUtil.getConnection();
            //预编译
            String sql = "insert into `chat_room`(teacher_id,teacher_name,user_sno,user_name) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //填充
            ps.setInt(1, chatRoom.getTeacherId());
            ps.setString(2, chatRoom.getTeacherName());
            ps.setInt(3, chatRoom.getUserSno());
            ps.setString(4, chatRoom.getUserName());

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
     * 返回所有聊天
     *
     * @param userSno
     * @return 含有所有聊天的List集合
     */
    @Override
    public List<ChatRoom> findAllChat(int userSno) {

        ChatRoom chatRoom = null;
        List<ChatRoom> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from chat_room where user_sno=? order by chat_id desc";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, userSno);

            rs = ps.executeQuery();

            while (rs.next()) {
                int chatId = rs.getInt("chat_id");
                int teacherId = rs.getInt("teacher_id");
                String teacherName = rs.getString("teacher_name");
                String userName = rs.getString("user_name");

                chatRoom = new ChatRoom(chatId, teacherId, teacherName, userSno, userName);
                System.out.println(chatRoom);
                list.add(chatRoom);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
        return list;
    }


    /**
     * 学生发送消息
     *
     * @param chatMessage
     * @return 布尔值
     */
    @Override
    public boolean sendMessage(ChatMessage chatMessage) {
        String sql = "insert into chat_message(chat_id,message_content,message_type,teacher_name,user_name,sender_identity,create_time) values(?,?,?,?,?,?,?)";
        int count = template.update(sql, chatMessage.getChatId(), chatMessage.getMessageContent(), chatMessage.getMessageType(), chatMessage.getTeacherName(), chatMessage.getUserName(), chatMessage.getSenderIdentity(), chatMessage.getCreateTime());
        return count > 0;
    }


    /**
     * 查询所有 id>finalMessageId 的聊天消息,即新的聊天消息
     *
     * @param finalMessageId
     * @return 新的聊天消息的List集合
     */
    @Override
    public List<ChatMessage> getNewMessage(int finalMessageId, int chatId) {

        ChatMessage chatMessage = null;
        List<ChatMessage> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from chat_message where message_id>? AND `chat_id`=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, finalMessageId);
            ps.setInt(2, chatId);

            rs = ps.executeQuery();

            while (rs.next()) {
                int messageId = rs.getInt("message_id");
                String messageContent = rs.getString("message_content");
                String messageType = rs.getString("message_type");
                String teacherName = rs.getString("teacher_name");
                String userName = rs.getString("user_name");
                String senderIdentity = rs.getString("sender_identity");
                String createTime = rs.getString("create_time");

                chatMessage = new ChatMessage(messageId, chatId, messageContent, messageType, teacherName, userName, senderIdentity, createTime);
                list.add(chatMessage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
        return list;


    }


    /**
     * 根据浏览器传入的本地最新消息ID查询是否存在新的聊天记录
     *
     * @param finalMessageId
     * @return 新的聊天记录的数量
     */
    @Override
    public boolean hasNew(String finalMessageId, String chatId) {
        String sql = "SELECT COUNT(*) FROM `chat_message` WHERE `message_id`>? AND `chat_id`=?";
        Long count = template.queryForObject(sql, long.class, finalMessageId, chatId);
        return count > 0;
    }


    /**
     * 判断聊天室是否已存在
     *
     * @param chatRoom
     * @return 布尔值
     */
    @Override
    public boolean chatRoomExist(ChatRoom chatRoom) {
        String sql = "select count(*) from chat_room where teacher_id=? and user_sno=?";
        Integer count = template.queryForObject(sql, int.class, chatRoom.getTeacherId(), chatRoom.getUserSno());
        return count > 0;
    }

    /**
     * 删除对应的聊天室和聊天信息
     *
     * @param chatId
     * @return 布尔值
     */
    @Override
    public boolean deleteChat(int chatId) {

        String sql1 = "delete from chat_room where chat_id=?";
        String sql2 = "delete from chat_message where chat_id=?";

        int count1 = template.update(sql1, chatId);
        int count2 = template.update(sql2, chatId);

        return count1 > 0;
    }
}
