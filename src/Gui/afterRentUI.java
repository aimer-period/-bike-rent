package Gui;

import dataBase.getDB;
import dataBase.insertDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 租借单车之后的界面
 * 退返之后计算费用，退回租借选择界面
 */
public class afterRentUI extends JFrame {
    JButton comeBack = new JButton("退返");
    JPanel  panelSouth =new JPanel(),
            panelCenter = new JPanel();
    SimpleDateFormat sdf = new SimpleDateFormat();
    Date now = new Date();
    public afterRentUI( String name ){
        //获取用户租赁的单车编号
        String id = null;
        try {
            id = getDB.getID(name);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //System.out.println(myFmt.toString());
        JFrame frame = new JFrame(name);
        //设置窗体
        frame.setSize(1000,500);
        frame.setLocation(100,200);
        frame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        //中间布局
        frame.add(panelCenter,BorderLayout.CENTER);
        SimpleDateFormat rentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar c = Calendar.getInstance();
        JLabel varTime = new JLabel();
        String finalId = id;
        Timer timeAction = new Timer(1000, new ActionListener() {
            long timemillis2;
            {
                try {
                    String beforeTime  = getDB.getReturnTime(finalId);
                    SimpleDateFormat rentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    c.setTime(rentTime.parse(beforeTime));
                    timemillis2 = c.getTimeInMillis()+1000*8*60*60;

                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }catch (ParseException e) {
                    e.printStackTrace();
                }
            }

            public void actionPerformed(ActionEvent e) {

                long timemillis1 = System.currentTimeMillis();

                long timemillis = timemillis1-timemillis2;

                // 转换日期显示格式
                SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                varTime.setText(df.format(new Date(timemillis)));
            }
        });
        timeAction.start();
        panelCenter.add(varTime);
        //南部布局
        frame.add(panelSouth, BorderLayout.SOUTH);
        panelSouth.add(comeBack);
        //退返的事件处理
        String finalId1 = id;
        comeBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String time = sdf.format(now);
                try {
                    insertDB.returnRent(finalId1,time);
                    frame.setVisible(false);
                    new userUI(name);
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        //可视化
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
