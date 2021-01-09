package dataBase;

import java.sql.*;

public class insertDB {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private static final String NAME = "root";
    private static final String PASSWORD = "106606";
    //空构造方法
    public insertDB(){
    }

    //将新建立的用户账号插入数据库
    public static void insertAccount(String admin,String password)throws SQLException,ClassNotFoundException{
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "insert into user(admin,passWord) values(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,admin);
        ps.setString(2,password);
        int row = ps.executeUpdate();
        if(row>0)
            System.out.println("增加[---"+admin+"----"+password+"---]");
        conn.close();
    }
    //插入新注册的管理员
    public static Boolean insertManager(String admin,String password)throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "insert into manager(admin,password) values(?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,admin);
        ps.setString(2,password);
        int row = ps.executeUpdate();
        if(row>0){
            System.out.println("增加[---"+admin+"----"+password+"---]");
            flag = true;
        }

        conn.close();
        return flag;
    }
    //插入新增加的单车信息
    public static Boolean insertBike(String id,String name,String sort,String price,String content)throws SQLException,ClassNotFoundException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL,NAME,PASSWORD);
        String sql = "insert into bike(id,name,Attribution,price,content) values(?,?,?,?,?)";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3,sort);
        ps.setString(4,price);
        ps.setString(5,content);
        int row =ps.executeUpdate();
        conn.close();
        if(row>0)
            return true;
        else
            return false;

    }
    //修改单车信息
    public static Boolean updataBike(String id,String name,String sort,String price,String content,String oldID)throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "update  bike set id=?,name=?,Attribution=?,price=?,content=? where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3,sort);
        ps.setString(4,price);
        ps.setString(5,content);
        ps.setString(6,oldID);
        int row = ps.executeUpdate();
        conn.close();
        if(row>0){
            System.out.println("修改成功");
            return true;
        }
        else
        return flag;

    }
    //删除单车信息
    public static Boolean deletedBike(String id)throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "delete from bike where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        int row = ps.executeUpdate();
        conn.close();
        if(row>0){
            System.out.println("删除成功");
            return true;
        }
        else
            return flag;

    }
    //插入租赁信息
    public static Boolean insertRent(String id , String name , String time ,String price )throws SQLException,ClassNotFoundException{
        Boolean flag = false;
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "insert into  rent(id,name,time,price) values(?,?,?,?) ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2,name);
        ps.setString(3,time);
        ps.setString(4,price);
        int row = ps.executeUpdate();
        conn.close();
        if(row>0){
            System.out.println("插入了一条租赁信息");
            afterRent(id);
            return true;
        }
        else
            return flag;
    }
    //租赁之后修改单车状态
    public static void afterRent(String id)throws SQLException,ClassNotFoundException{
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = sql= "UPDATE  bike set content = ? where id = ? ";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"已租借");
        ps.setString(2,id);
        int row = ps.executeUpdate();
        conn.close();
        if(row>0) {
            System.out.println("修改成功");
        }
    }
    //退返单车
    public static void returnRent(String id, String time)throws SQLException,ClassNotFoundException{
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "UPDATE  bike set content = ? where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"空闲");
        ps.setString(2,id);
        int row = ps.executeUpdate();
        conn.close();
        change(time , id);
        if(row>0) {
            System.out.println("bike状态修改成功");
        }
    }
    //归还单车之后把租赁记录加上归还时间
    public static void change(String time ,String id)throws SQLException,ClassNotFoundException{
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "UPDATE  rent set content = ? , returntime = ?  where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,"已归还");
        ps.setString(2,time);
        ps.setString(3,id);
        int row = ps.executeUpdate();
        conn.close();
        if(row>0) {
            System.out.println("归还记录修改成功");
        }
    }
    //测试
    public static void main(String [] args){
        new insertDB();
    }
}
