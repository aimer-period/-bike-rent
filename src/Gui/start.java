package Gui;
import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 开始界面
 * 选择用户的身份：manager和user
 */
class createGui {
    private static JFrame startframe;
    private final JPanel
            contentPanel = new JPanel();
    private JLabel
            label = new JLabel(),
            label2 = new  JLabel();
    //this.setContentPane(contentPanel);
    //contentPanel.setBackground(Color.white);
    //contentPanel.setOpaque(true);
    createGui(){
        startframe = new JFrame("自行车租赁");
        //界面图标
        startframe.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        startframe.setSize(400,300);
        // 主窗体设置位置
        startframe.setLocation(200,200);

        // 主窗体中的组件设置为绝对定位
        startframe.setLayout(null);

        // 按钮组件
        JButton manager = new JButton("管理员");
        JButton user = new JButton("用户");

        // 同时设置组件的大小和位置
        manager.setBounds(50,50,280,30);
        user.setBounds(50,100,280,30);
        //添加背景
        ImageIcon imageBackGround = new ImageIcon("img/blueBird.png");
        JLabel backLabel = new JLabel();

        backLabel.setIcon(imageBackGround);
        backLabel.setBounds(0,0,500, 350);


        // 把按钮加入到主窗体中
        startframe.add(manager);
        startframe.add(user);
        startframe.add(backLabel);
        //设置两个按钮变成透明化
        manager.setContentAreaFilled(false);
        user.setContentAreaFilled(false);
        //建立监听器
        manager.addActionListener(new ActionListener() {

            // 当按钮被点击时，就会触发 ActionEvent事件
            // actionPerformed 方法就会被执行
            public void actionPerformed(ActionEvent e) {
                startframe.setVisible(false);
                new managerLogin();
            }
        });
        user.addActionListener(new ActionListener() {

            // 当按钮被点击时，就会触发 ActionEvent事件
            // actionPerformed 方法就会被执行
            public void actionPerformed(ActionEvent e) {
                startframe.setVisible(false);
                new userLogin();
            }
        });
        //((JPanel)this.getContentPane()).setOpaque(false);
        // 关闭窗体的时候，退出程序
        startframe.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // 让窗体变得可见
        startframe.setVisible(true);
    }

    public static void main(String [] args){
        try
        {
            //设置本属性将改变窗口边框样式定义
            System.setProperty("sun.java2d.noddraw", "true");
            BeautyEyeLNFHelper.frameBorderStyle = BeautyEyeLNFHelper.FrameBorderStyle.osLookAndFeelDecorated;
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
        new createGui();
    }


}