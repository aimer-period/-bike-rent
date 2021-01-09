package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class userRegister {
    userRegister(){
        JFrame userRigersterFrame = new JFrame("用户注册");
        //布局设置为null，之后用绝对布局
        userRigersterFrame.setLayout(null);
        //设置按钮组件
        JButton
                registered = new JButton("注册"),
                comeBack = new JButton("返回");
        //设置账号密码文本框
        JTextField
                admin = new JTextField(20),
                password = new JTextField(20),
                passwordDetermine = new JTextField(20);
        //背景
        ImageIcon image1 = new ImageIcon("img/ride.png");
        ImageIcon image2 = new ImageIcon("img/bike.png");

        JLabel acountLabel = new JLabel("账    号：");
        JLabel passwordLabel = new JLabel("密    码：");
        JLabel passwordLabelDetermine = new JLabel("确定密码:");
        //向面板加入组件
        userRigersterFrame.add(acountLabel);
        userRigersterFrame.add(admin);
        userRigersterFrame.add(passwordLabel);
        userRigersterFrame.add(password);
        userRigersterFrame.add(passwordDetermine);
        userRigersterFrame.add(passwordLabelDetermine);
        userRigersterFrame.add(registered);
        userRigersterFrame.add(comeBack);
        //设置图标
        userRigersterFrame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        //窗口大小
        userRigersterFrame.setSize(400,300);
        // 主窗体设置位置
        userRigersterFrame.setLocation(200,200);
        //设置组件的位置
        acountLabel.setBounds(50,50,70,30);
        passwordLabel.setBounds(50,90,70,30);
        passwordLabelDetermine.setBounds(50,140,70,30);

        admin.setBounds(120,50,150,30);
        password.setBounds(120,90,150,30);
        passwordDetermine.setBounds(120,140,150,30);

        registered.setBounds(60,210,100,30);
        comeBack.setBounds(180,210,100,30);
        //用户注册监听器
        registered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = admin.getText();
                String firstPassWord = password.getText();
                String seconedPassWord = passwordDetermine.getText();
                try {
                    if(dataBase.verifyLogin.verifyUserSameName(name)){
                        //账号已存在，清空文本框
                        JOptionPane.showMessageDialog(userRigersterFrame,"该账号已存在","/(ㄒoㄒ)/~~",JOptionPane.INFORMATION_MESSAGE);
                        admin.setText("");
                        password.setText("");
                        passwordDetermine.setText("");

                    }
                    else if(firstPassWord.equals(seconedPassWord)==false){
                        //密码不同，清空密码
                        JOptionPane.showMessageDialog(userRigersterFrame,"两次密码不相同","/(ㄒoㄒ)/~~",JOptionPane.INFORMATION_MESSAGE);
                        password.setText("");
                        passwordDetermine.setText("");
                    }
                    else{
                        //显示注册成功，跳回登入界面
                        JOptionPane.showMessageDialog(userRigersterFrame,"注册成功","/(ㄒoㄒ)/~~",JOptionPane.PLAIN_MESSAGE);
                        userRigersterFrame.setVisible(false);
                        dataBase.insertDB.insertAccount(name,firstPassWord);
                        new userLogin();
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        //
        comeBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userRigersterFrame.setVisible(false);
                new userLogin();
            }
        });

        //可视化
        userRigersterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        userRigersterFrame.setVisible(true);
    }
    //测试
    public static void main(String [] args){
        new userRegister();
    }
}
