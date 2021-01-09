package dataBase;

import java.sql.*;

public class getDB {
    private static final String URL = "jdbc:mysql://localhost:3306/test?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
    private static final String mySQL = "com.mysql.cj.jdbc.Driver";
    private static final String NAME = "root";
    private static final String PASSWORD = "106606";
    public static Object [][] bikeData;
    public static Object [] bikeOne;
    //返回单车的信息
    public static Object[][] getUserArray() throws  ClassNotFoundException, SQLException{

        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "select * from bike ";
        PreparedStatement ps = conn.prepareStatement(sql);
        //检查
        System.out.println("查询成功！！");
        ResultSet set=ps.executeQuery();
        int row=0;
        while(set.next()){
            row++;
        }

       bikeData =new Object[row][5];
        set=ps.executeQuery();

        //将数据放入数组中
        int count=0;
        while(set.next()){
            bikeData[count][0]=set.getString(1);//id
            bikeData[count][1]=set.getString(2);//品牌
            bikeData[count][2]=set.getString(3);//归属
            bikeData[count][3]=set.getString(4);//价格
            bikeData[count][4]=set.getString(5);//描述
            //System.out.println("成功获取数据！！");
            count++;
        }
        //关闭数据库连接
        conn.close();
        return bikeData;

    }
    //返回一辆单车的信息,并返回是否找到
    public static int getOneBike(String id)throws  ClassNotFoundException, SQLException{
        System.out.println("连接数据库...");
        Class.forName("com.mysql.cj.jdbc.Driver");
        //2.获得数据库的连接
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        System.out.println("连接成功！！");
        String sql = "select * from bike where id=?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setString(1,id);
        //检查
        System.out.println("查询成功！！");
        ResultSet set=ps.executeQuery();
        int row=0;
        while(set.next()){
            row++;
        }
        bikeOne = new Object[5];
        set=ps.executeQuery();
        set.next();
        if(row>0){
            bikeOne[0]=set.getString(1);//id
            bikeOne[1]=set.getString(2);//品牌
            bikeOne[2]=set.getString(3);//归属
            bikeOne[3]=set.getString(4);//价格
            bikeOne[4]=set.getString(5);//描述
        }
        conn.close();
        return row;
    }
    //返回用户的余额
    public static String  getUserMoney(String name)throws  ClassNotFoundException, SQLException{
        Class.forName(mySQL);
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        String sql = "select money from user where admin=?";
        PreparedStatement ps = conn .prepareStatement(sql);
        ps.setString(1,name);
        ResultSet s = ps.executeQuery();
        String money = null;
        if(s.next()){
            money = s.getString(1);
        }
        conn.close();
        return money;
    }
    //返回一个租赁信息的时间
    public static String  getReturnTime(String id)throws  ClassNotFoundException, SQLException{
        Class.forName(mySQL);
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        String sql = "select time from rent where id = ? and content = ?";
        PreparedStatement ps = conn .prepareStatement(sql);
        ps.setString(1,id);
        ps.setString(2,"未归还");
        ResultSet s = ps.executeQuery();
        String time = null;
        if(s.next()){
            time = s.getString(1);
        }
        conn.close();
        System.out.println(time);
        return time;
    }
    //返回用户租赁的单车编号
    public static String  getID(String name)throws  ClassNotFoundException, SQLException{
        Class.forName(mySQL);
        Connection conn = DriverManager.getConnection(URL, NAME, PASSWORD);
        String sql = "select id from rent where name = ? and content = ?";
        PreparedStatement ps = conn .prepareStatement(sql);
        ps.setString(1,name);
        ps.setString(2,"未归还");
        ResultSet s = ps.executeQuery();
        String id = null;
        if(s.next()){
            id = s.getString(1);
        }
        conn.close();
        System.out.println(id);
        return id;
    }
}
