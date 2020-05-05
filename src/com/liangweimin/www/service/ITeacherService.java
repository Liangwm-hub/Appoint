package com.liangweimin.www.service;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.po.*;

import java.util.List;

/**
 * @author 梁伟民
 */
public interface ITeacherService {
    /**
     * 导师登录
     *
     * @param teacher
     * @return
     */
    boolean login(Teacher teacher);

    /**
     * 通过id查找导师
     *
     * @param id
     * @return Teacher对象
     */
    Teacher queryTeacherById(int id);

    /**
     * 导师新发布一条预约信息
     *
     * @param release
     * @return
     */
    boolean setAppointment(Release release);

    /**
     * 导师删除预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    boolean deleteRelease(int id, String appointTime, String place);

    /**
     * 导师修改预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @param release
     * @return
     */
    boolean updateRelease(int id, String appointTime, String place, Release release);

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
     * 分页查询导师发布的预约
     *
     * @param id
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<Release> findUserByPage(int id, String _currentPage, String _rows);

    /**
     * 返回装有 所有等待导师审核的请求的 PageBean
     *
     * @param id
     * @param _currentPage
     * @param _rows
     * @return
     */
    PageBean<Appoint> findRequestByPage(int id, String _currentPage, String _rows);

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
     * 修改预约的范围
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
     * 学生创建聊天室
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
     * 学生发送消息
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
     * 删除对应的聊天室和聊天信息
     * @param chatId
     * @return 布尔值
     */
    boolean deleteChat(int chatId);
}
