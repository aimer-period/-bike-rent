package Gui;

import dataBase.insertDB;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static dataBase.getDB.bikeData;
import static dataBase.getDB.getUserMoney;

/**
 * 用户操作界面
 *
 */
public class userUI extends JFrame {
    JFrame frame;
    JLabel number;//编号
    JLabel bikeName;//品牌
    JLabel sort;//归属
    JLabel price;//价格
    JLabel quantity;//状态
    JLabel operate;//操作标签

    JLabel txtNumber;//编号文本框
    JLabel TxtBikeName;//品牌文本框
    JLabel txtAttribution;//归属别文本框
    JLabel txtPrice;//价格文本框
    JLabel txtContent;//状态文本框
    JLabel txtMoney;

    JButton add,        //增加按钮
            change,    //修改按钮
            delete,    //删除按钮
            ret,       //返回按钮
            rent;       //租借

    Container container;//内容窗格
    JTable table;//表格
    Object title[]={"编号","品牌","归属","价格（小时/元）","状态"};//表格标题
    JPanel panelSouth;//南部面板
    public userUI(){}
    public userUI(String name) throws ClassNotFoundException, SQLException {

        frame=new JFrame(name+"----单车租赁");
        //设置图标
        frame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        container=frame.getContentPane();//获得内容窗格
        //获取数据
        dataBase.getDB.getUserArray();
        DefaultTableModel tableModel = new DefaultTableModel(bikeData,title);
        table=new JTable(tableModel);//建立表格

        //南部面板
        panelSouth =new JPanel();
        number=new JLabel("编号：");
        bikeName =new JLabel("品牌：");
        sort=new JLabel("归属：");
        price=new JLabel("价格（小时/元）：");
        quantity=new JLabel("描述");
        operate=new JLabel("余额: ");
        txtNumber = new JLabel();
        TxtBikeName= new JLabel();
        txtAttribution = new JLabel();
        txtPrice = new JLabel();
        txtContent = new JLabel();
        txtMoney = new JLabel();

        add=new JButton("增加");
        change=new JButton("修改");
        delete=new JButton("删除");
        ret=new JButton("返回");
        rent = new JButton("租赁");

        Border border=new LineBorder(Color.blue);

        //设置窗体
        frame.setSize(1000,500);
        frame.setLocation(100,200);
        //准备表格
        table.setRowHeight(20);
        table.setFont(new Font("宋体",Font.BOLD,10));
        table.getTableHeader().setFont(new Font("宋体",Font.BOLD,10));
        container.add(new JScrollPane(table),BorderLayout.CENTER);//设置表格居中，并加入滚动条

        //设置南部面板布局管理器
        panelSouth.setLayout(new GridLayout(4,4,10,20));
        //向顶层容器加入嵌入式容器，向嵌入式容器加入组件
        frame.add(panelSouth,BorderLayout.SOUTH);

        //南部
        panelSouth.add(number);
        panelSouth.add(txtNumber);
        panelSouth.add(bikeName);
        panelSouth.add(TxtBikeName);
        panelSouth.add(sort);
        panelSouth.add(txtAttribution);
        panelSouth.add(price);
        panelSouth.add(txtPrice);
        panelSouth.add(quantity);
        panelSouth.add(txtContent);
        panelSouth.add(operate);
        panelSouth.add(txtMoney);
        panelSouth.add(rent);
        panelSouth.add(ret);

        //获得用户余额
        try {
            txtMoney.setText(getUserMoney(name));
        } catch (ClassNotFoundException classNotFoundException) {
            classNotFoundException.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //设置顶层窗口可见
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        //表格触发鼠标事件
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row=table.getSelectedRow();//获取选中行
                //获取选中行，每列的内容
                Object number=table.getValueAt(row,0);
                Object bikeName=table.getValueAt(row,1);
                Object sort=table.getValueAt(row,2);
                Object price=table.getValueAt(row,3);
                Object quantity=table.getValueAt(row,4);
                //将获取内容，放入对应的文本域
                txtNumber.setText(number.toString());
                TxtBikeName.setText((bikeName.toString()));
                txtAttribution.setText(sort.toString());
                txtPrice.setText(price.toString());
                txtContent.setText(quantity.toString());

            }
        });
        //租赁事件触发
        rent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //将租赁的时间记录
                Date now = new Date();
                SimpleDateFormat myFmt=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String time = myFmt.format(now);
                String content = txtContent.getText();
                //先判断是否已经选中了单车
                if(number.getText().length()!=0)
                    //嵌套判断是否已经被租借
                    if(content.equals("空闲")) {

                        //插入租赁的信息
                        try {

                            if(insertDB.insertRent(txtNumber.getText(), name ,time , txtPrice.getText()))
                                System.out.println("插入了一条租赁信息");
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }
                        JOptionPane.showMessageDialog(frame,"已租借："+TxtBikeName.getText(),"/(ㄒoㄒ)/~~",JOptionPane.PLAIN_MESSAGE);
                        frame.setVisible(false);
                        new afterRentUI(name);
                    }
                    else
                        JOptionPane.showMessageDialog(frame,"此单车已被租借","/(ㄒoㄒ)/~~",JOptionPane.PLAIN_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame,"请选择一辆单车","/(ㄒoㄒ)/~~",JOptionPane.PLAIN_MESSAGE);
            }
        });

        //返回按钮触发事件
        ret.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                //返回初始界面
                new createGui();
            }
        });




    }
    public static void main(String args[]){
        try {
            new userUI("安南");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
