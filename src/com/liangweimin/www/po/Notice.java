package com.liangweimin.www.po;

/**
 * notice表对应的 javabean
 * @author 梁伟民
 */
public class Notice {
    /**通知编号*/
    private int noticeId;
    /**通知标题*/
    private String noticeTitle;
    /**通知内容*/
    private String noticeContent;
    /**通知附属文件*/
    private String fileName;

    public Notice() {
    }

    public Notice(String noticeTitle, String noticeContent, String fileName) {
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.fileName = fileName;
    }

    public Notice(int noticeId, String noticeTitle, String noticeContent, String fileName) {
        this.noticeId = noticeId;
        this.noticeTitle = noticeTitle;
        this.noticeContent = noticeContent;
        this.fileName = fileName;
    }

    public int getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(int noticeId) {
        this.noticeId = noticeId;
    }

    public String getNoticeTitle() {
        return noticeTitle;
    }

    public void setNoticeTitle(String noticeTitle) {
        this.noticeTitle = noticeTitle;
    }

    public String getNoticeContent() {
        return noticeContent;
    }

    public void setNoticeContent(String noticeContent) {
        this.noticeContent = noticeContent;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
