<%@ page import="java.awt.*" %>
<%@ page import="java.util.Random" %>
<%@ page import="java.awt.image.BufferedImage" %>
<%@ page import="javax.imageio.ImageIO" %>

<%--
    jsp也可以是一张图片
--%>

<%@ page contentType="image/jpeg;charset=UTF-8" language="java" %>
<%!
    //随机产生颜色
    public Color getColor() {
        Random ran = new Random();
        int r = ran.nextInt(256); //产生0~255的随机数
        int g = ran.nextInt(256); //产生0~255的随机数
        int b = ran.nextInt(256); //产生0~255的随机数

        return new Color(r, g, b); //三原色（0~255）red green blue 搭配产生各种颜色
    }

    //产生验证码值
    public String getNum() {
        Random ran = new Random();
        //1000~9999
        int number = ran.nextInt(9000) + 1000;
        return String.valueOf(number);
    }

%>

<%
    //禁止缓存，防止验证码过期
    response.setHeader("Pragma", "no-cache");//不要在头信息里缓存
    response.setHeader("Cache-Control", "no-cache");//指示请求或响应消息不能缓存
    response.setHeader("Expires", "0");//过期时间

    //绘制验证码背景
    BufferedImage image = new BufferedImage(80, 20, BufferedImage.TYPE_INT_RGB);//宽、高、类
    //画笔
    Graphics graphics = image.getGraphics();
    //填充背景
    graphics.fillRect(0, 0, 80, 20);//从0,0填充到80,20
    //绘制干扰线条
    for (int i = 0; i < 60; i++) {
        Random ran = new Random();

        //线条的始末位置
        int xBegin = ran.nextInt(80);
        int yBegin = ran.nextInt(30);
        int xEnd = ran.nextInt(xBegin + 10);
        int yEnd = ran.nextInt(yBegin + 10);

        //线条颜色
        graphics.setColor(getColor());//随机

        //画线条
        graphics.drawLine(xBegin, yBegin, xEnd, yEnd);
    }

    //绘制验证码内容
    graphics.setFont(new Font("self", Font.BOLD, 20));
    graphics.setColor(Color.black);//颜色
    String checkCode = getNum();//4个数字

    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < checkCode.length(); i++) {
        sb.append(checkCode.charAt(i) + " "); //charAt()返回指定索引的字符，append()追加字符串
    }

    //正式画验证码
    graphics.drawString(sb.toString(), 15, 20);//位置

    //将验证码值 保存到session 用来比较是否相同
    session.setAttribute("checkCode", checkCode);

    //真实的产生图片
    ImageIO.write(image, "jpeg", response.getOutputStream());//BufferedImage、图片类型、流

    //关闭
    out.clear();
    out = pageContext.pushBody();//放进src里 <input type="image" src="xxx" />
%>