package Gui;

import dataBase.verifyLogin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * managerLogin管理员登入界面
 * 功能暂且就登入和重置
 */
class managerLogin extends JFrame {
    private JLabel
                label = null;

    public managerLogin(){
        JFrame managerFrame = new JFrame("管理员登录");
        //布局设置为null，之后用绝对布局
        managerFrame.setLayout(null);
        //设置按钮组件
        JButton
                login = new JButton("登录"),
                registered = new JButton("注册"),
                comeBack = new JButton("返回");
        JTextField
                admin = new JTextField(20),
                password = new JTextField(20);

        //设置背景图片
        ImageIcon image1 = new ImageIcon("img/ride.png");
        ImageIcon image2 = new ImageIcon("img/bike.png");

        JLabel acountLabel = new JLabel("账号：");
        JLabel passwordLabel = new JLabel("密码：");
        JLabel backLabel = new JLabel();
        JLabel backLabel2 = new JLabel();
        backLabel.setIcon(image1);
        backLabel2.setIcon(image2);

        label=new JLabel(image1);
        JLabel label2 = new JLabel(image2);
        //设置标签大小与位置
        label.setBounds(0, 0,500,350);
        label2.setBounds(0, 0, 501, 350);

        managerFrame.add(acountLabel);
        managerFrame.add(admin);
        managerFrame.add(passwordLabel);
        managerFrame.add(password);
        managerFrame.add(login);
        managerFrame.add(registered);
        managerFrame.add(comeBack);
        managerFrame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        //窗口大小
        managerFrame.setSize(400,300);
        // 主窗体设置位置
        managerFrame.setLocation(200,200);
        //设置组件的位置
        acountLabel.setBounds(50,50,50,30);
        passwordLabel.setBounds(50,90,50,30);
        admin.setBounds(100,50,150,30);
        password.setBounds(100,90,150,30);
        //rememberAdmin.setBounds(100,130,100,30);
        //rememberPassword.setBounds(200,130,100,30);
        login.setBounds(60,170,100,30);
        registered.setBounds(180,170,100,30);
        comeBack.setBounds(60,220,100,30);

        //监听事件
        //管理员登入
        login.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String account = admin.getText();
                String passWord = password.getText();
                try {
                    Boolean flag =  verifyLogin.managerVerifyLogin(account,passWord);
                    //将账号密码输入数据库验证，若正确弹出对话框进行下一步
                    if(flag){
                        //设置对话框的位置
                        JOptionPane.showMessageDialog(login,"登陆成功","提醒",JOptionPane.INFORMATION_MESSAGE);
                        managerFrame.setVisible(false);
                        new managerUI(account);
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
                managerFrame.setVisible(false);
                try {
                    new managerRegister();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });
        //返回监听
        comeBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerFrame.setVisible(false);
                new createGui();
            }
        });
        // 关闭窗体的时候，退出程序
        managerFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        managerFrame.setVisible(true);

    }

    public static void main(String [] args){
        new managerLogin();

    }
}
