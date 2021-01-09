package Gui;

import dataBase.checkDB;
import dataBase.getDB;
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

import static dataBase.getDB.bikeData;

public class managerUI extends JFrame {
    JFrame frame;
    JLabel number;//编号
    JLabel bikeName;//品牌
    JLabel sort;//归属
    JLabel price;//价格
    JLabel quantity;//状态
    JLabel operate;//操作标签
    JTextField Txtnumber;//编号文本框
    JTextField TxtBikeName;//品牌文本框
    JTextField Txtsort;//归属别文本框
    JTextField Txtprice;//价格文本框
    JTextField Txtquantity;//状态文本框
    JButton add;//增加按钮
    JButton change;//修改按钮
    JButton delete;//删除按钮
    JButton ret;//返回按钮
    JButton resert;//重置按钮
    Container container;//内容窗格
    JTable table;//表格
    Object title[]={"编号","品牌","归属","价格（小时/元）","描述"};//表格标题
    JPanel panelsouth;//南部面板
    public managerUI(){}
    public managerUI(String name) throws ClassNotFoundException, SQLException {
        frame=new JFrame(name+"----单车租赁系统");
        //设置图标
        frame.setIconImage(new ImageIcon("img/blueBird.png").getImage());
        container=frame.getContentPane();//获得内容窗格
        //获取数据
        dataBase.getDB.getUserArray();
        DefaultTableModel tableModel = new DefaultTableModel(bikeData,title);
        table=new JTable(tableModel);//建立表格

        //南部面板
        panelsouth=new JPanel();
        number=new JLabel("编号：");
        bikeName =new JLabel("品牌：");
        sort=new JLabel("归属：");
        price=new JLabel("价格（小时/元）：");
        quantity=new JLabel("描述");
        operate=new JLabel("操作: ");
        Txtnumber=new JTextField();
        TxtBikeName=new JTextField();
        Txtsort=new JTextField();
        Txtprice=new JTextField();
        Txtquantity=new JTextField();
        add=new JButton("增加");
        change=new JButton("修改");
        delete=new JButton("删除");
        ret=new JButton("返回");
        resert=new JButton("重置");
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
        panelsouth.setLayout(new GridLayout(4,4,10,20));
        //向顶层容器加入嵌入式容器，向嵌入式容器加入组件
        frame.add(panelsouth,BorderLayout.SOUTH);

        //南部
        panelsouth.add(number);
        panelsouth.add(Txtnumber);
        panelsouth.add(bikeName);
        panelsouth.add(TxtBikeName);
        panelsouth.add(sort);
        panelsouth.add(Txtsort);
        panelsouth.add(price);
        panelsouth.add(Txtprice);
        panelsouth.add(quantity);
        panelsouth.add(Txtquantity);
        panelsouth.add(operate);
        panelsouth.add(add);
        panelsouth.add(change);
        panelsouth.add(delete);
        panelsouth.add(resert);
        panelsouth.add(ret);

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
                Txtnumber.setText(number.toString());
                TxtBikeName.setText((bikeName.toString()));
                Txtsort.setText(sort.toString());
                Txtprice.setText(price.toString());
                Txtquantity.setText(quantity.toString());
            }
        });

        //添加按钮触发事件
        add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e)   {
                //获取各个文本框内容
                int count=0;

                String number = Txtnumber.getText();
                String bikeName = TxtBikeName.getText();
                String sort = Txtsort.getText();
                String price = Txtprice.getText();
                String quantity = Txtquantity.getText();
                if (Txtnumber.getText().length() != 0 && bikeName.length() != 0 && sort.length() != 0 && price.length()!= 0 && quantity.length() != 0) {
                    try {
                        count = checkDB.checkSameId(number);
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                    //count获得数据库查询的id个数，若已经存在id则失败
                    if (count !=0) {
                        JOptionPane.showMessageDialog(frame, "添加商品编号已存在", "警告", JOptionPane.INFORMATION_MESSAGE);
                    }
                    else{
                        try {
                            //添加单车
                            if(insertDB.insertBike(number,bikeName,sort,price,quantity)){
                                JOptionPane.showMessageDialog(frame, "添加商品成功", "提醒", JOptionPane.INFORMATION_MESSAGE);
                                frame.setVisible(false);
                                new managerUI(name);
                            }
                        } catch (SQLException throwables) {
                            throwables.printStackTrace();
                        } catch (ClassNotFoundException classNotFoundException) {
                            classNotFoundException.printStackTrace();
                        }

                    }


                }
                else {
                    JOptionPane.showMessageDialog(frame,"请补全完整添加商品信息","警告",JOptionPane.INFORMATION_MESSAGE);

                }
            }



        });

        //修改按钮触发事件
        change.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String number = Txtnumber.getText();
                String bikeName = TxtBikeName.getText();
                String sort = Txtsort.getText();
                String price = Txtprice.getText();
                String content = Txtquantity.getText();

                try {
                    if(number.length()!=0) {
                        //number！=0代表编号有数字，进行数据库查找是否存在
                        if (getDB.getOneBike(number) == 0) {
                            JOptionPane.showMessageDialog(frame, "此编号单车不存在", "/(ㄒoㄒ)/~~", JOptionPane.PLAIN_MESSAGE);
                        }
                        else
                            new changeBike(number,bikeName,sort,price,content);
                    }
                    else{
                        JOptionPane.showMessageDialog(frame, "请输入编号进行修改", "/(ㄒoㄒ)/~~", JOptionPane.PLAIN_MESSAGE);

                    }

                } catch (ClassNotFoundException classNotFoundException) {
                    classNotFoundException.printStackTrace();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        });

        //删除按钮触发事件
        delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = Txtnumber.getText();
                //判断编号是否正确
                if(id.length()!=0) {
                    try {
                        if(insertDB.deletedBike(id)) {
                            JOptionPane.showMessageDialog(frame, "已删除编号为" + id + "的单车信息", "/(ㄒoㄒ)/~~", JOptionPane.PLAIN_MESSAGE);
                            frame.setVisible(false);
                            new managerUI(name);
                        }
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    } catch (ClassNotFoundException classNotFoundException) {
                        classNotFoundException.printStackTrace();
                    }
                }
                else
                    JOptionPane.showMessageDialog(frame, "请输入编号进行删除", "/(ㄒoㄒ)/~~", JOptionPane.PLAIN_MESSAGE);
            }
        });

        //重置按钮触发事件
        resert.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //设置各个文本框的内容为空
                Txtnumber.setText("");
                TxtBikeName.setText("");
                Txtsort.setText("");
                Txtprice.setText("");
                Txtquantity.setText("");

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
            new managerUI("安南");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
