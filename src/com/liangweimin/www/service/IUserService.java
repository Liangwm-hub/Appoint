package com.liangweimin.www.service;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.po.*;

import java.util.List;

/**
 * @author 梁伟民
 */
public interface IUserService {

    /**
     * 用户登录
     * @param user
     * @return
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
     * 用户修改信息
     * @param sno
     * @param user
     * @return
     */
    boolean updateUser(int sno, User user);

    /**
     * 根据学号返回学生信息 （User对象）
     * @param sno
     * @return
     */
    User queryUserBySno(int sno);

    /**
     * 学生 预约导师
     * @param sno
     * @param userName
     * @param fileName
     * @param release
     * @return
     */
    boolean appoint(int sno, String userName, String fileName, Release release);

    /**
     * 分页显示所有导师发起的预约
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<Release> findReleaseByPage(String _currentPage, String _rows);

    /**
     * 分页查找预约
     * @param sno
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<Appoint> findAppointmentByPage(int sno, String _currentPage, String _rows);

    /**
     * 学生取消预约
     *
     * @param num
     * @return
     */
    boolean deleteAppointment(int num);

    /**
     * 通过关键字 搜索到请求
     *
     * @param keywords
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<Release> searchReleaseByPage(String keywords, String _currentPage, String _rows);

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
     * 删除对应的聊天室和聊天信息
     * @param chatId
     * @return 布尔值
     */
    boolean deleteChat(int chatId);
}
