package dataBase;

import java.sql.*;

/**
 * 1.连接数据库
 * 2.方法userVerifyLogin验证账号是否正确,返回一个Boolean类型的标记，若为true为正确，反之错误
 * 3.方法verifyUserSameName：用户注册时判断是否已经存在
 */

public class verifyLogin {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private static final String NAME = "root";
    private static final String PASSWORD = "106606";
    //空构造方法
    public verifyLogin(){
    }

    //验证用户登入账号密码是否正确
    public static Boolean userVerifyLogin(String admin, String password) throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        //1.加载驱动程序
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "select * from user where admin=? and password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,admin);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        //判断账号密码是否正确
        if(rs.next()){
           flag= true;
        }
        //关闭数据库连接
        conn.close();
        return flag;
        }

        //用户注册时判断是否已经存在
    public static Boolean verifyUserSameName(String admin)throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        //1.加载驱动程序
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "select * from user where admin=? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,admin);
        ResultSet rs = ps.executeQuery();
        //判断账号密码是否正确
        if(rs.next()){
            flag= true;
        }
        //关闭数据库连接
        conn.close();
        return flag;
     }


     //验证管理员的账号密码
    public static boolean managerVerifyLogin(String admin, String password) throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        //1.加载驱动程序
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "select * from manager where admin=? and password=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,admin);
        ps.setString(2,password);
        ResultSet rs = ps.executeQuery();
        //判断账号密码是否正确
        if(rs.next()){
            flag= true;
        }
        //关闭数据库连接
        conn.close();
        System.out.println("断开连接");
        return flag;
    }
    //验证管理员账号是否已经存在
    public static Boolean verifyManagerSameName(String admin)throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        //1.加载驱动程序
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "select * from manager where admin=? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,admin);
        ResultSet rs = ps.executeQuery();
        //判断是否存在
        if(rs.next()){
            flag= true;
        }
        //关闭数据库连接
        conn.close();
        return flag;
    }

    //测试
    public static void main(String [] args){
        try {
             userVerifyLogin("admin","123456");
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
