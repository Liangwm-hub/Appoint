package com.liangweimin.www.test;

import com.liangweimin.www.util.JDBCUtil;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @author 梁伟民
 */
public class TestUser {
    private JdbcTemplate template = new JdbcTemplate(JDBCUtil.getDataSource());

    public static void main(String[] args) {
        TestUser testUser = new TestUser();
        boolean login = testUser.login();
        System.out.println(login);
    }

    public boolean login() {

        String sql = "select count(*) from user where sno=? and password=? and status=?";
        Integer count = template.queryForObject(sql, int.class, 1001, "-126124-5314-22-1181121087652-95104-111-878123", "注册成功");
        System.out.println(count);
        return count>0;
    }
}
