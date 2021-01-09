package Gui;
import dataBase.checkDB;
import dataBase.verifyLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

class userLogin extends JFrame {
    public userLogin(){
        JFrame userFrame = new JFrame("用户登录");
        //布局设置为null，之后用绝对布局
        userFrame.setLayout(null);
        //设置按钮组件
        JButton
            login = new JButton("登录"),
            registered = new JButton("注册"),
            comeBack = new JButton("返回");
        //设置账号密码文本框
        JTextField
                admin = new JTextField(20),
                password = new JTextField(20);
        //设置复选框组件
        JCheckBox
                rememberAdmin = new JCheckBox("记住账号"),
                rememberPassword = new JCheckBox("记住密码");
        ImageIcon image1 = new ImageIcon("img/ride.png");
        ImageIcon image2 = new ImageIcon("img/bike.png");

        JLabel acountLabel = new JLabel("账号：");
        JLabel passwordLabel = new JLabel("密码：");
        //向面板加入组件
        userFrame.add(acountLabel);
        userFrame.add(admin);
        userFrame.add(passwordLabel);
        userFrame.add(password);
        userFrame.add(login);
        userFrame.add(registered);
        userFrame.add(comeBack);
        //设置图标
        userFrame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        //窗口大小
        userFrame.setSize(400,300);
        // 主窗体设置位置
        userFrame.setLocation(200,200);
        //设置组件的位置
        acountLabel.setBounds(50,50,50,30);
        passwordLabel.setBounds(50,90,50,30);
        admin.setBounds(100,50,150,30);
        password.setBounds(100,90,150,30);
        login.setBounds(60,170,70,30);
        registered.setBounds(180,170,70,30);
        comeBack.setBounds(100,210,70,30);

        //监听事件
        //登入监听
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = admin.getText();
                String passWord = password.getText();
                try {
                    Boolean flag =  verifyLogin.userVerifyLogin(account,passWord);
                    //将账号密码输入数据库验证，若正确弹出对话框进行下一步
                    if(flag){
                        //设置对话框的位置
                        JOptionPane.showMessageDialog(login,"登陆成功","提醒",JOptionPane.INFORMATION_MESSAGE);
                        userFrame.setVisible(false);
                        if(checkDB.checkRent(account))
                            new afterRentUI(account);
                        else
                        new userUI(account);
                    }
                    else
                    {
                        JOptionPane.showMessageDialog(login,"账号或密码错误","提醒",JOptionPane.INFORMATION_MESSAGE);
                        admin.setText("");
                        password.setText("");
                    }

                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }
            }
        });
        //注册监听
        registered.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            userFrame.setVisible(false);
            new userRegister();
            }
        });

        //返回监听
        comeBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                userFrame.setVisible(false);
                new createGui();
            }
        });
        // 关闭窗体的时候，退出程序
        userFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        userFrame.setVisible(true);
    }

    //测试
    public static void main(String [] args){
        new userLogin();
    }
}
