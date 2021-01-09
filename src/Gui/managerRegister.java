package Gui;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * 管理员manager的注册界面
 *
 */
public class managerRegister {
    managerRegister()throws ClassNotFoundException,SQLException{
        JFrame managerRegisterFrame = new JFrame("管理员注册");
        //布局设置为null，之后用绝对布局
        managerRegisterFrame.setLayout(null);
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
        managerRegisterFrame.add(acountLabel);
        managerRegisterFrame.add(admin);
        managerRegisterFrame.add(passwordLabel);
        managerRegisterFrame.add(password);
        managerRegisterFrame.add(passwordDetermine);
        managerRegisterFrame.add(passwordLabelDetermine);
        managerRegisterFrame.add(registered);
        managerRegisterFrame.add(comeBack);
        //设置图标
        managerRegisterFrame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        //窗口大小
        managerRegisterFrame.setSize(400,300);
        // 主窗体设置位置
        managerRegisterFrame.setLocation(200,200);
        //设置组件的位置
        acountLabel.setBounds(50,50,70,30);
        passwordLabel.setBounds(50,90,70,30);
        passwordLabelDetermine.setBounds(50,140,70,30);

        admin.setBounds(120,50,150,30);
        password.setBounds(120,90,150,30);
        passwordDetermine.setBounds(120,140,150,30);

        registered.setBounds(50,210,100,30);
        comeBack.setBounds(220,210,100,30);


        //注册监听器
        registered.addActionListener(new ActionListener()  {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = admin.getText();
                String firstPassWord = password.getText();
                String secondPassWord = passwordDetermine.getText();

                try {
                    if(dataBase.verifyLogin.verifyManagerSameName(name)){
                        JOptionPane.showMessageDialog(managerRegisterFrame,"已存在相同账号","/(ㄒoㄒ)/~~",JOptionPane.PLAIN_MESSAGE);
                        admin.setText("");
                        password.setText("");
                        passwordDetermine.setText("");

                    }
                    else if(firstPassWord.equals(secondPassWord)!=true){
                        JOptionPane.showMessageDialog(managerRegisterFrame,"两次密码不相同","/(ㄒoㄒ)/~~",JOptionPane.PLAIN_MESSAGE);
                        password.setText("");
                        passwordDetermine.setText("");
                    }
                    else{
                        if(dataBase.insertDB.insertManager(name,firstPassWord)) {
                            //注册成功转到管理员登录界面
                            JOptionPane.showMessageDialog(managerRegisterFrame, "注册管理员成功", "/(ㄒoㄒ)/~~", JOptionPane.PLAIN_MESSAGE);
                            managerRegisterFrame.setVisible(false);
                            new managerLogin();
                        }

                    }


                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                }


            }
        });

        //返回监听
        comeBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                managerRegisterFrame.setVisible(false);
                new managerLogin();
            }
        });
        //可视化
        managerRegisterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        managerRegisterFrame.setVisible(true);
    }

    public static void main(String [] args){
        try {
            new managerRegister();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
