package dataBase;

import java.sql.*;

public class checkDB {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private static final String NAME = "root";
    private static final String PASSWORD = "106606";
    checkDB(){}

    //检查是否已存在该id
    public static int checkSameId(String id)throws ClassNotFoundException, SQLException {
        int count=0;
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL,NAME,PASSWORD);
        String sql = "select * from bike where id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        ResultSet set=ps.executeQuery();
        int row=0;
        while(set.next()){
            row++;
        }
        if(row>0){
            count++;
        }
        conn.close();
        return count;
    }
    //判断该用户是否已经有了未归还的单车租赁记录
    public static Boolean checkRent(String name)throws ClassNotFoundException, SQLException{
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection(URL,NAME,PASSWORD);
        String sql = "select * from rent where name = ? and content = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,"未归还");
        ResultSet set=ps.executeQuery();
        int row=0;
        while(set.next()){
            row++;
        }
        conn.close();
        if(row>0){
            return true;
        }
        else
            return false;
    }


}
