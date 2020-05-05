package com.liangweimin.www.service;

import com.liangweimin.www.bean.PageBean;
import com.liangweimin.www.dao.UserDao;
import com.liangweimin.www.po.*;

import java.util.List;

/**
 * 业务逻辑层:
 * 逻辑性的增删查改,对dao的组装
 *
 * @author 梁伟民
 */
public class UserService implements IUserService {

    UserDao userDao = new UserDao();

    /**
     * 用户登录
     */
    @Override
    public boolean login(User user) {
        //登录是否成功？
        return userDao.login(user);
    }


    /**
     * 用户注册
     *
     * @param user
     * @return
     */
    @Override
    public boolean userRegister(User user) {
        boolean exit = userDao.isExit(user.getSno());
        //不存在这个用户才能注册
        if (!exit) {
            return userDao.userRegister(user);
        }
        return false;
    }

    /**
     * 用户修改密码
     *
     * @param user
     * @return
     */
    @Override
    public boolean setPassword(User user) {
        return userDao.setPassword(user);
    }


    /**
     * 用户修改信息
     *
     * @param sno
     * @param user
     * @return
     */
    @Override
    public boolean updateUser(int sno, User user) {
        //用户是否存在
        boolean exit = userDao.isExit(sno);

        if (exit) {
            //存在则修改
            return userDao.updateUser(sno, user);
        }
        return false;
    }


    /**
     * 根据学号返回学生信息 （User对象）
     *
     * @param sno
     */
    @Override
    public User queryUserBySno(int sno) {
        User user = userDao.queryUserBySno(sno);
        return user;
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
        return userDao.appoint(sno, userName, fileName, release);
    }


    /**
     * 分页显示所有导师发起的预约
     *
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<Release> findReleaseByPage(String _currentPage, String _rows) {

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
        int totalCount = userDao.findReleaseCount();
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
        List<Release> lists = null;

        //start不能小于0
        if (start >= 0) {
            lists = userDao.findReleaseByPage(start, rows);
        }
        pb.setList(lists);

        return pb;
    }


    @Override
    public PageBean<Appoint> findAppointmentByPage(int sno, String _currentPage, String _rows) {

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
        int totalCount = userDao.findTotalCount(sno);
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
        // 开始的记录索引 (当前页码-1)*行数
        int start = (currentPage - 1) * rows;
        List<Appoint> lists = null;

        if (start >= 0) {
            lists = userDao.findAppointmentByPage(sno, start, rows);
        }
        pb.setList(lists);

        return pb;
    }

    /**
     * 学生取消预约
     *
     * @param num
     * @return
     */
    @Override
    public boolean deleteAppointment(int num) {
        return userDao.deleteAppointment(num);
    }


    /**
     * 通过关键字 搜索到请求
     *
     * @param keywords
     * @param _currentPage
     * @param _rows
     * @return
     */
    @Override
    public PageBean<Release> searchReleaseByPage(String keywords, String _currentPage, String _rows) {

        //%表示任意字符串
        String likeKeywords = "%" + keywords + "%";

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
        int totalCount = userDao.findSearchCount(likeKeywords);
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
        List<Release> lists = null;

        if (start >= 0) {
            lists = userDao.searchRelease(likeKeywords, start, rows);
        }

        pb.setList(lists);

        return pb;
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
        return userDao.uploadPicture(appoint, picture);
    }

    /**
     * 返回所有的通知
     *
     * @return
     */
    @Override
    public List<Notice> findAllNotices() {
        return userDao.findAllNotices();
    }

    /**
     * 通过id找到通知
     *
     * @param noticeId
     * @return
     */
    @Override
    public Notice queryNoticeById(int noticeId) {
        return userDao.queryNoticeById(noticeId);
    }

    /**
     * 学生创建聊天室
     *
     * @param chatRoom
     * @return 布尔值
     */
    @Override
    public boolean createChatRoom(ChatRoom chatRoom) {
        if (!userDao.chatRoomExist(chatRoom)) {
            return userDao.createChatRoom(chatRoom);
        }
        return false;
    }

    /**
     * 返回所有聊天
     *
     * @param userSno
     * @return 含有所有聊天的List集合
     */
    @Override
    public List<ChatRoom> findAllChat(int userSno) {
        return userDao.findAllChat(userSno);
    }


    /**
     * 学生发送消息
     *
     * @param chatMessage
     * @return 布尔值
     */
    @Override
    public boolean sendMessage(ChatMessage chatMessage) {
        return userDao.sendMessage(chatMessage);
    }

    /**
     * 查询所有 id>finalMessageId 的聊天消息,即新的聊天消息
     *
     * @param finalMessageId
     * @return 新的聊天消息的List集合
     */
    @Override
    public List<ChatMessage> getNewMessage(int finalMessageId, int chatId) {
        List<ChatMessage> chatMessages = userDao.getNewMessage(finalMessageId, chatId);
        return chatMessages;
    }

    /**
     * 根据浏览器传入的本地最新消息ID查询是否存在新的聊天记录
     *
     * @param finalMessageId
     * @return 新的聊天记录的数量
     */
    @Override
    public boolean hasNew(String finalMessageId, String chatId) {
        return userDao.hasNew(finalMessageId, chatId);
    }

    /**
     * 删除对应的聊天室和聊天信息
     * @param chatId
     * @return 布尔值
     */
    @Override
    public boolean deleteChat(int chatId){
        return userDao.deleteChat(chatId);
    }
}
