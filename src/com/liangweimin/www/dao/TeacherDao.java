package com.liangweimin.www.dao;

import com.liangweimin.www.po.*;
import com.liangweimin.www.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 * 导师的
 * 数据访问层
 * 直接操作数据库（单纯的增删改查等）
 *
 * @author 梁伟民
 */
public class TeacherDao implements ITeacherDao {

    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    /**
     * 通过导师 id 判断用户是否已存在
     *
     * @param id
     * @return 一个布尔值（用户是否存在）
     */
    @Override
    public boolean isExit(int id) {
        return queryTeacherById(id) == null ? false : true;
    }


    /**
     * 判断预约是否已存在
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    @Override
    public boolean releaseIsExit(int id, String appointTime, String place) {
        return queryRelease(id, appointTime, place) != null;
    }


    /**
     * 通过id查询导师
     *
     * @param id
     * @return 一个 Teacher 对象
     */
    @Override
    public Teacher queryTeacherById(int id) {
        Teacher teacher = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //利用id获得数据
            String sql = "select * from teacher where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();

            if (rs.next()) {
                int no = rs.getInt(1);
                String name = rs.getString(2);
                String sex = rs.getString(4);
                String college = rs.getString(5);
                String phone = rs.getString(6);
                int scope = rs.getInt(7);

                //封装数据到Teacher
                teacher = new Teacher(no, name, sex, college, phone, scope);
            }
            //返回一个teacher对象
            return teacher;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 导师 登录
     *
     * @param teacher
     * @return 布尔值（登录是否成功）
     */
    @Override
    public boolean login(Teacher teacher) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from teacher where id=? and password=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teacher.getId());
            ps.setString(2, teacher.getPassword());

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
     * 查询导师发布的预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    @Override
    public Release queryRelease(int id, String appointTime, String place) {
        Release release = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //利用id获得数据
            String sql = "select * from `release` where id=? and appoint_time=? and place=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, appointTime);
            ps.setString(3, place);
            rs = ps.executeQuery();

            if (rs.next()) {
                int tId = rs.getInt(1);
                String name = rs.getString(2);
                String sex = rs.getString(3);
                String college = rs.getString(4);
                String phone = rs.getString(5);
                String tAppointTime = rs.getString(6);
                String tPlace = rs.getString(7);

                //封装数据到Release
                release = new Release(tId, name, sex, college, phone, tAppointTime, tPlace);
            }
            //返回一个release对象
            return release;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }
    }


    /**
     * 导师新增发布一条预约信息
     *
     * @param release
     * @return 布尔值（发布是否成功）
     */
    @Override
    public boolean setAppointment(Release release) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            //连接数据库
            conn = JDBCUtil.getConnection();
            //预编译
            String sql = "insert into `release`(id,`name`,sex,college,phone,appoint_time,place,scope) values(?,?,?,?,?,?,?,?)";
            ps = conn.prepareStatement(sql);
            //填充
            ps.setInt(1, release.getId());
            ps.setString(2, release.getName());
            ps.setString(3, release.getSex());
            ps.setString(4, release.getCollege());
            ps.setString(5, release.getPhone());
            ps.setString(6, release.getAppointTime());
            ps.setString(7, release.getPlace());
            ps.setInt(8, release.getScope());

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
     * 删除导师发布的预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    @Override
    public boolean deleteRelease(int id, String appointTime, String place) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "delete from `release` where id=? and appoint_time=? and place=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, appointTime);
            ps.setString(3, place);

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
     * 修改导师发布的预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @param release
     * @return
     */
    @Override
    public boolean updateRelease(int id, String appointTime, String place, Release release) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update `release` set appoint_Time =?,place=? where id=? and appoint_Time=? and place=?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, release.getAppointTime());
            ps.setString(2, release.getPlace());
            ps.setInt(3, id);
            ps.setString(4, appointTime);
            ps.setString(5, place);

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
     * 查找release表里 导师发布预约 记录总数量
     *
     * @return
     */
    @Override
    public int findTotalCount(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from `release` where id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);

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
     * 当前页的数据集合
     *
     * @param id
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Release> findUserByPage(int id, int start, int rows) {
        List<Release> releases = new ArrayList<>();
        Release release1 = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = " select * from `release` where id=? limit ?,? ";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setInt(2, start);
            ps.setInt(3, rows);
            rs = ps.executeQuery();

            //用循环获得rows条数据
            while (rs.next()) {
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
     * 查找 等待导师审核的请求 的总数量
     *
     * @param id
     * @return
     */
    @Override
    public int findRequestTotalCount(int id) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select count(*) from `appoint` where teacher_id=? and status=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.setString(2, "审核中");

            rs = ps.executeQuery();
            if (rs.next()) {
                count = rs.getInt(1);
                System.out.println(count);
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
     * 查找该导师所有等待审核的预约请求
     *
     * @param id
     * @param start
     * @param rows
     * @return
     */
    @Override
    public List<Appoint> findRequestByPage(int id, int start, int rows) {
        List<Appoint> appoints = new ArrayList<>();
        Appoint appoint = null;

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();

            System.out.println(id + start + rows);
            //用limit完成分页,start从哪里开始查，rows查多少条
            String sql = "select * from appoint where teacher_id=? and status=? limit ?,?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, id);
            ps.setString(2, "审核中");
            ps.setInt(3, start);
            ps.setInt(4, rows);
            rs = ps.executeQuery();

            //用循环获得rows条数据
            while (rs.next()) {
                int num = rs.getInt(1);
                int userSno = rs.getInt(4);
                String userName = rs.getString(5);
                String appointTime = rs.getString(6);
                String place = rs.getString(7);

                //封装,存进List
                appoint = new Appoint(num, id, userSno, userName, appointTime, place);
                appoints.add(appoint);
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
     * 修改预约的范围
     * 同时修改teacher和release两张表的scope
     *
     * @param id
     * @param scope
     * @return
     */
    @Override
    public boolean updateScope(int id, int scope) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "update teacher t1, `release` t2 set t1.scope=?,t2.scope=? where t1.id=? and t2.id=? ;";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, scope);
            ps.setInt(2, scope);
            ps.setInt(3, id);
            ps.setInt(4, id);

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
     * 通过num,找到appoint表对应图片的名字
     *
     * @param num
     * @return
     */
    @Override
    public String downloadPicture(int num) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String picture = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select picture from appoint where num=?";
            ps = conn.prepareStatement(sql);

            ps.setInt(1, num);

            rs = ps.executeQuery();

            if (rs.next()) {
                picture = rs.getString(1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JDBCUtil.closeSource(conn, ps, rs);
        }

        return picture;
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
     * 导师创建聊天室
     *
     * @param chatRoom
     * @return 布尔值
     */
    public boolean createChatRoom(ChatRoom chatRoom) {
        String sql = "insert into `chat_room`(teacher_id,teacher_name,user_sno,user_name) values(?,?,?,?)";
        int count = template.update(sql, chatRoom.getTeacherId(), chatRoom.getTeacherName(), chatRoom.getUserSno(), chatRoom.getUserName());
        return count > 0;
    }


    /**
     * 返回所有聊天
     *
     * @param teacherId
     * @return 含有所有聊天的List集合
     */
    public List<ChatRoom> findAllChat(int teacherId) {

        ChatRoom chatRoom = null;
        List<ChatRoom> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from chat_room where teacher_id=? order by chat_id desc";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, teacherId);

            rs = ps.executeQuery();

            while (rs.next()) {
                int chatId = rs.getInt("chat_id");
                int userSno = rs.getInt("user_sno");
                String teacherName = rs.getString("teacher_name");
                String userName = rs.getString("user_name");

                chatRoom = new ChatRoom(chatId, teacherId, teacherName, userSno, userName);
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
     * 导师发送消息
     *
     * @param chatMessage
     * @return 布尔值
     */
    public boolean sendMessage(ChatMessage chatMessage) {
        String sql = "insert into chat_message(chat_id,message_content,teacher_name,user_name,sender_identity,create_time) values(?,?,?,?,?,?)";
        int count = template.update(sql, chatMessage.getChatId(), chatMessage.getMessageContent(), chatMessage.getTeacherName(), chatMessage.getUserName(), chatMessage.getSenderIdentity(), chatMessage.getCreateTime());
        return count > 0;
    }


    /**
     * 查询所有 id>finalMessageId 的聊天消息,即新的聊天消息
     *
     * @param finalMessageId
     * @return 新的聊天消息的List集合
     */
    public List<ChatMessage> getNewMessage(int finalMessageId, int chatId) {

        ChatMessage chatMessage = null;
        List<ChatMessage> list = new ArrayList<>();

        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = JDBCUtil.getConnection();
            String sql = "select * from chat_message where message_id>? AND chat_id=?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1, finalMessageId);
            ps.setInt(2, chatId);

            rs = ps.executeQuery();

            while (rs.next()) {
                int messageId = rs.getInt("message_id");
                String messageContent = rs.getString("message_content");
                String teacherName = rs.getString("teacher_name");
                String userName = rs.getString("user_name");
                String senderIdentity = rs.getString("sender_identity");
                String createTime = rs.getString("create_time");

                chatMessage = new ChatMessage(messageId, chatId, messageContent, teacherName, userName, senderIdentity, createTime);
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
    public boolean deleteChat(int chatId) {

        String sql1 = "delete from chat_room where chat_id=?";
        String sql2 = "delete from chat_message where chat_id=?";

        int count1 = template.update(sql1, chatId);
        int count2 = template.update(sql2, chatId);

        return count1 > 0;
    }
}
