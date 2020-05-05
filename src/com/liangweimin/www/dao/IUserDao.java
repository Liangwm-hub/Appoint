package com.liangweimin.www.dao;

import com.liangweimin.www.po.*;

import java.util.List;

/**
 * @author 梁伟民
 */
public interface IUserDao {
    /**
     * 用户（学生）登录
     *
     * @param user
     * @return 布尔值（登录是否成功）
     */
    boolean login(User user);

    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    boolean userRegister(User user);

    /**
     * 用户修改密码
     * @param user
     * @return
     */
    boolean setPassword(User user);

    /**
     * 通过学号判断用户是否已存在
     *
     * @param sno
     * @return 一个布尔值（用户是否存在）
     */
    boolean isExit(int sno);

    /**
     * 通过学号查询用户
     *
     * @param sno
     * @return 一个 User 对象
     */
    User queryUserBySno(int sno);

    /**
     * 修改用户的个人信息
     *
     * @param sno
     * @param user
     * @return 布尔值（修改是否成功）
     */
    boolean updateUser(int sno, User user);


    /**
     * 学生预约导师
     * @param sno
     * @param userName
     * @param fileName
     * @param release
     * @return
     */
    boolean appoint(int sno, String userName, String fileName, Release release);

    /**
     * 查找release表记录总数量
     *
     * @return
     */
    int findReleaseCount();

    /**
     * 查询release表 所有老师发布的预约 返回当前页的数据集合
     *
     * @param start
     * @param rows
     * @return
     */
    List<Release> findReleaseByPage(int start, int rows);

    /**
     * 查找appoint 表里 自己预约 记录总数量
     *
     * @return
     */
    int findTotalCount(int sno);

    /**
     * 返回学生 在appoint表 当前页的预约数据集合
     *
     * @param sno
     * @param start
     * @param rows
     * @return
     */
    List<Appoint> findAppointmentByPage(int sno, int start, int rows);

    /**
     * 学生取消预约
     *
     * @param num
     * @return
     */
    boolean deleteAppointment(int num);

    /**
     * 搜索得到的记录数
     *
     * @param likeKeywords
     * @return
     */
    int findSearchCount(String likeKeywords);

    /**
     * 通过关键字 搜索到请求 获得一个List
     *
     * @param likeKeywords
     * @param start
     * @param rows
     * @return
     */
    List<Release> searchRelease(String likeKeywords, int start, int rows);

    /**
     * 上传图片名字到appoint表
     *
     * @param appoint
     * @param picture
     * @return
     */
    boolean uploadPicture(Appoint appoint, String picture);

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
     * 学生创建聊天室
     * @param chatRoom
     * @return 布尔值
     */
    boolean createChatRoom(ChatRoom chatRoom);

    /**
     * 返回所有聊天
     * @param userSno
     * @return 含有所有聊天的List集合
     */
    List<ChatRoom> findAllChat(int userSno);


    /**
     * 学生发送消息
     * @param chatMessage
     * @return 布尔值
     */
    boolean sendMessage(ChatMessage chatMessage);

    /**
     * 查询所有 id>finalMessageId 的聊天消息,即新的聊天消息
     *
     * @param finalMessageId
     * @return 新的聊天消息的List集合
     */
    List<ChatMessage> getNewMessage(int finalMessageId, int chatId);

    /**
     * 根据浏览器传入的本地最新消息ID查询是否存在新的聊天记录
     *
     * @param finalMessageId
     * @return 新的聊天记录的数量
     */
    boolean hasNew(String finalMessageId, String chatId);

    /**
     * 判断聊天室是否已存在
     *
     * @param chatRoom
     * @return 布尔值
     */
    boolean chatRoomExist(ChatRoom chatRoom);

    /**
     * 删除对应的聊天室和聊天信息
     *
     * @param chatId
     * @return 布尔值
     */
    boolean deleteChat(int chatId);
}
