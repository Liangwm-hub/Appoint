package com.liangweimin.www.service;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.dao.TeacherDao;
import com.liangweimin.www.po.*;

import java.util.List;

/**
 * 业务逻辑层:
 * 逻辑性的增删查改,对dao的组装
 *
 * @author 梁伟民
 */
public class TeacherService implements ITeacherService {

    TeacherDao teacherDao = new TeacherDao();


    /**
     * 导师登录
     *
     * @param teacher
     * @return
     */
    @Override
    public boolean login(Teacher teacher) {
        //登录是否成功？
        boolean success = teacherDao.login(teacher);

        if (success) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 通过id查找导师
     *
     * @param id
     * @return Teacher对象
     */
    @Override
    public Teacher queryTeacherById(int id) {
        Teacher teacher = teacherDao.queryTeacherById(id);
        return teacher;
    }


    /**
     * 导师新发布一条预约信息
     *
     * @param release
     * @return
     */
    @Override
    public boolean setAppointment(Release release) {
        //发布预约信息是否成功
        return teacherDao.setAppointment(release);
    }


    /**
     * 导师删除预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @return
     */
    @Override
    public boolean deleteRelease(int id, String appointTime, String place) {
        //判断预约是否存在
        boolean exit = teacherDao.releaseIsExit(id, appointTime, place);
        //存在则删除
        if (exit) {
            return teacherDao.deleteRelease(id, appointTime, place);
        }

        return false;
    }


    /**
     * 导师修改预约
     *
     * @param id
     * @param appointTime
     * @param place
     * @param release
     * @return
     */
    @Override
    public boolean updateRelease(int id, String appointTime, String place, Release release) {
        //判断预约是否存在
        boolean exit = teacherDao.releaseIsExit(id, appointTime, place);
        //存在则修改
        if (exit) {
            return teacherDao.updateRelease(id, appointTime, place, release);
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
        return teacherDao.queryRelease(id, appointTime, place);
    }


    /**
     * 分页查询导师发布的预约
     *
     * @param id
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<Release> findUserByPage(int id, String _currentPage, String _rows) {

        int currentPage = Integer.parseInt(_currentPage);
        int rows = Integer.parseInt(_rows);

        //1.创建空的PageBean对象
        PageBean<Release> pb = new PageBean<>();

        //2.设置页码，当前页不能小于1，不能大于总页数；设置条数的参数
        if (currentPage <= 0) {
            currentPage = 1;
        }
        pb.setCurrentPage(currentPage);
        pb.setRows(rows);

        //3.调用dao查询总记录数，设置总记录数的参数
        int totalCount = teacherDao.findTotalCount(id);
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
        // 开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Release> lists = null;

        if (start >= 0) {
            lists = teacherDao.findUserByPage(id, start, rows);
        }
        pb.setList(lists);

        return pb;
    }


    /**
     * 返回装有 所有等待导师审核的请求的 PageBean
     *
     * @param id
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<Appoint> findRequestByPage(int id, String _currentPage, String _rows) {

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
        int totalCount = teacherDao.findRequestTotalCount(id);
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
        // 开始的记录索引
        int start = (currentPage - 1) * rows;
        List<Appoint> lists = null;

        if (start >= 0) {
            lists = teacherDao.findRequestByPage(id, start, rows);
        }
        pb.setList(lists);

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
            teacherDao.processSelected(Integer.parseInt(num));
        }
    }


    /**
     * 拒绝学生的请求
     *
     * @param num
     */
    @Override
    public void refuseRequest(int num) {
        teacherDao.refuseRequest(num);
    }


    /**
     * 修改预约的范围
     *
     * @param id
     * @param scope
     * @return
     */
    @Override
    public boolean updateScope(int id, int scope) {
        return teacherDao.updateScope(id, scope);
    }


    /**
     * 通过num,找到appoint表对应图片的名字
     *
     * @param num
     * @return
     */
    @Override
    public String downloadPicture(int num) {
        return teacherDao.downloadPicture(num);
    }


    /**
     * 返回所有的通知
     *
     * @return
     */
    @Override
    public List<Notice> findAllNotices() {
        return teacherDao.findAllNotices();
    }

    /**
     * 通过id找到通知
     *
     * @param noticeId
     * @return
     */
    @Override
    public Notice queryNoticeById(int noticeId) {
        return teacherDao.queryNoticeById(noticeId);
    }


    /**
     * 学生创建聊天室
     *
     * @param chatRoom
     * @return 布尔值
     */
    public boolean createChatRoom(ChatRoom chatRoom) {
        if (!teacherDao.chatRoomExist(chatRoom)) {
            return teacherDao.createChatRoom(chatRoom);
        }
        return false;
    }

    /**
     * 返回所有聊天
     *
     * @param teacherId
     * @return 含有所有聊天的List集合
     */
    public List<ChatRoom> findAllChat(int teacherId) {
        return teacherDao.findAllChat(teacherId);
    }


    /**
     * 学生发送消息
     *
     * @param chatMessage
     * @return 布尔值
     */
    public boolean sendMessage(ChatMessage chatMessage) {
        return teacherDao.sendMessage(chatMessage);
    }

    /**
     * 查询所有 id>finalMessageId 的聊天消息,即新的聊天消息
     *
     * @param finalMessageId
     * @return 新的聊天消息的List集合
     */
    public List<ChatMessage> getNewMessage(int finalMessageId, int chatId) {
        List<ChatMessage> chatMessages = teacherDao.getNewMessage(finalMessageId, chatId);
        return chatMessages;
    }

    /**
     * 根据浏览器传入的本地最新消息ID查询是否存在新的聊天记录
     *
     * @param finalMessageId
     * @return 新的聊天记录的数量
     */
    public boolean hasNew(String finalMessageId, String chatId) {
        return teacherDao.hasNew(finalMessageId, chatId);
    }

    /**
     * 删除对应的聊天室和聊天信息
     * @param chatId
     * @return 布尔值
     */
    public boolean deleteChat(int chatId){
        return teacherDao.deleteChat(chatId);
    }
}
