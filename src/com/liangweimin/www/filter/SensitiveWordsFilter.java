package com.liangweimin.www.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.List;

/**
 * 敏感词汇过滤器
 *
 * @author 梁伟民
 */
@WebFilter(filterName = "SensitiveWordsFilter", value = {"/user/SendMessageByUserServlet","/teacher/SendMessageByTeacherServlet"} )
public class SensitiveWordsFilter implements Filter {

    /**
     * 敏感词汇集合
     */
    private List<String> list = new ArrayList();

    @Override
    public void destroy() {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //创建代理对象
        ServletRequest proxyReq = (ServletRequest) Proxy.newProxyInstance(req.getClass().getClassLoader(), req.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                //如果是getParameter方法
                if ("getParameter".equals(method.getName())) {
                    //增强getParameter方法

                    //获取返回值
                    String value = (String) method.invoke(req, args);

                    if (value != null) {
                        for (String str : list) {
                            if (value.contains(str)) {
                                value = value.replaceAll(str, "**");
                            }
                        }
                    }
                    //返回修改后的value
                    return value;
                }

                //不是getParameter方法
                return method.invoke(req, args);
            }
        });

        chain.doFilter(proxyReq, resp);
    }

    @Override
    public void init(FilterConfig config) throws ServletException {
        try {
            //1.获取文件真实路径
            ServletContext servletContext = config.getServletContext();
            String realPath = servletContext.getRealPath("/WEB-INF/classes/sensitive_words.txt");

            //2.读取文件(默认是GBK,这里我设置utf-8)
//            BufferedReader br = new BufferedReader(new FileReader(realPath));
            BufferedReader br = new BufferedReader(new InputStreamReader( new FileInputStream(realPath), "UTF-8") );

            //3.将文件的每一行数据添加到list
            String line = null;
            while ((line = br.readLine()) != null) {
                list.add(line);
            }

            br.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
