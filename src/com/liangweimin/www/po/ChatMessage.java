package com.liangweimin.www.po;

/**
 * 聊天信息
 * @author 梁伟民
 */
public class ChatMessage {
    /**信息编号*/
    private int messageId;
    /**聊天编号*/
    private int chatId;
    /**信息内容*/
    private String messageContent;
    /**导师名字*/
    private String teacherName;
    /**学生名字*/
    private String userName;
    /**发送人的身份*/
    private String senderIdentity;
    /**发送的时间*/
    private String createTime;

    public ChatMessage(int chatId, String messageContent, String teacherName, String userName, String senderIdentity, String createTime) {
        this.chatId = chatId;
        this.messageContent = messageContent;
        this.teacherName = teacherName;
        this.userName = userName;
        this.senderIdentity = senderIdentity;
        this.createTime = createTime;
    }

    public ChatMessage(int messageId, int chatId, String messageContent, String teacherName, String userName, String senderIdentity, String createTime) {
        this.messageId = messageId;
        this.chatId = chatId;
        this.messageContent = messageContent;
        this.teacherName = teacherName;
        this.userName = userName;
        this.senderIdentity = senderIdentity;
        this.createTime = createTime;
    }

    public int getMessageId() {
        return messageId;
    }

    public void setMessageId(int messageId) {
        this.messageId = messageId;
    }

    public int getChatId() {
        return chatId;
    }

    public void setChatId(int chatId) {
        this.chatId = chatId;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSenderIdentity() {
        return senderIdentity;
    }

    public void setSenderIdentity(String senderIdentity) {
        this.senderIdentity = senderIdentity;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }


    @Override
    public String toString() {
        return "ChatMessage{" +
                "messageId=" + messageId +
                ", chatId=" + chatId +
                ", messageContent='" + messageContent + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", userName='" + userName + '\'' +
                ", senderIdentity='" + senderIdentity + '\'' +
                ", createTime='" + createTime + '\'' +
                '}';
    }
}
