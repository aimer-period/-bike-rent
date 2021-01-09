package Gui;

import dataBase.getDB;
import dataBase.insertDB;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 左右两个布局对照，左边原始数据，右边是修改后的数据
 * 下方放置修改按钮
 */
public class changeBike extends JFrame {
    private JLabel
                idLabel = new JLabel("编号："),
                nameLabel= new JLabel("品牌"),
                AttributionLabel = new JLabel("归属"),
                priceLabel = new JLabel("价格"),
                contentLabel = new JLabel("状态");
    JTextField txtId = new JTextField(),//编号文本框
            txtName = new JTextField(),//品牌文本框
            txtAttribution = new JTextField(),//归属别文本框
            txtPrice = new JTextField(),//价格文本框
            txtContent = new JTextField();//状态文本框
    JButton register = new JButton("修改");

    JFrame frame = new JFrame();
    //建立三个面板存放三部分数据
    JPanel panelCenter= new JPanel();
    JPanel panelEast =new JPanel();
    JPanel panelSouth = new JPanel();

        public  changeBike(String id,String name,String Attribution, String price, String content)throws ClassNotFoundException, SQLException{
            //数组用与存放单车信息

            getDB.getUserArray();
            frame.setSize(500,500);
            frame.setLocation(400,200);
            frame.add(panelCenter, BorderLayout.CENTER);
            panelCenter.setLayout(new GridLayout(5,2,10,20));
            //赋值进入文本框

            txtId.setText((String) getDB.bikeOne[0]);
            txtName.setText((String) getDB.bikeOne[1]);
            txtAttribution.setText((String) getDB.bikeOne[2]);
            txtPrice.setText((String) getDB.bikeOne[3]);
            txtContent.setText((String) getDB.bikeOne[4]);
            //加入中间容器
            panelCenter.add(idLabel);
            panelCenter.add(txtId);
            panelCenter.add(nameLabel);
            panelCenter.add(txtName);
            panelCenter.add(AttributionLabel);
            panelCenter.add(txtAttribution);
            panelCenter.add(priceLabel);
            panelCenter.add(txtPrice);
            panelCenter.add(contentLabel);
            panelCenter.add(txtContent);

            //南部容器
            frame.add(panelSouth,BorderLayout.SOUTH);
            panelSouth.add(register);
            //注册监听器
            register.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String id = txtId.getText();
                    String name = txtName.getText();
                    String Attribution = txtAttribution.getText();
                    String price = txtPrice.getText();
                    String content = txtContent.getText();
                    try {
                        if(insertDB.updataBike(id,name,Attribution,price,content, (String) getDB.bikeOne[0])){
                            JOptionPane.showMessageDialog(panelCenter, "修改成功", "/(ㄒoㄒ)/~~", JOptionPane.PLAIN_MESSAGE);
                            frame.setVisible(false);
                            JFrame.isDefaultLookAndFeelDecorated();
                        }
                        else
                            JOptionPane.showMessageDialog(panelCenter, "修改失败", "/(ㄒoㄒ)/~~", JOptionPane.PLAIN_MESSAGE);
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
            });
            //this.pack();
            //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
            frame.setVisible(true);

        }

    //测试
    public static void main(String [] args){
        try {
            new changeBike("1","小黄车","吉首大学","1","空闲");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
