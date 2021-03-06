package com.liangweimin.www.dao;

import com.liangweimin.www.po.*;

import java.util.List;

/**
 * @author 梁伟民
 */
public interface ITeacherDao {
    /**
     * 通过导师 id 判断用户是否已存在
     *
     * @param id
     * @return 一个布尔值（用户是否存在）
     */
    boolean isExit(int id);

    /**
     * 判断预约是否已存在
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    boolean releaseIsExit(int id, String appointTime, String place);

    /**
     * 通过id查询导师
     *
     * @param id
     * @return 一个 Teacher 对象
     */
    Teacher queryTeacherById(int id);

    /**
     * 导师 登录
     *
     * @param teacher
     * @return 布尔值（登录是否成功）
     */
    boolean login(Teacher teacher);

    /**
     * 查询导师发布的预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    Release queryRelease(int id, String appointTime, String place);

    /**
     * 导师新增发布一条预约信息
     *
     * @param release
     * @return 布尔值（发布是否成功）
     */
    boolean setAppointment(Release release);

    /**
     * 删除导师发布的预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    boolean deleteRelease(int id, String appointTime, String place);

    /**
     * 修改导师发布的预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @param release
     * @return
     */
    boolean updateRelease(int id, String appointTime, String place, Release release);

    /**
     * 查找release表里 导师发布预约 记录总数量
     *
     * @return
     */
    int findTotalCount(int id);

    /**
     * 当前页的数据集合
     *
     * @param id
     * @param start
     * @param rows
     * @return
     */
    List<Release> findUserByPage(int id, int start, int rows);

    /**
     * 查找 等待导师审核的请求 的总数量
     *
     * @param id
     * @return
     */
    int findRequestTotalCount(int id);

    /**
     * 查找该导师所有等待审核的预约请求
     *
     * @param id
     * @param start
     * @param rows
     * @return
     */
    List<Appoint> findRequestByPage(int id, int start, int rows);

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
     * 修改预约的范围
     * 同时修改teacher和release两张表的scope
     *
     * @param id
     * @param scope
     * @return
     */
    boolean updateScope(int id, int scope);

    /**
     * 通过num,找到appoint表对应图片的名字
     *
     * @param num
     * @return
     */
    String downloadPicture(int num);

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
     * 导师创建聊天室
     *
     * @param chatRoom
     * @return 布尔值
     */
    boolean createChatRoom(ChatRoom chatRoom);

    /**
     * 返回所有聊天
     *
     * @param teacherId
     * @return 含有所有聊天的List集合
     */
    List<ChatRoom> findAllChat(int teacherId);

    /**
     * 导师发送消息
     *
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
