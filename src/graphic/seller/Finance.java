package graphic.seller;

import com.google.gson.JsonSyntaxException;
import controller.Controller;
import model.Goods;

import view.FileOperating;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Finance extends JFrame implements ActionListener {
    int height = 680, width = 1000;
    int page=0;
    Controller controller;
    Container container;
    JButton next=new JButton("next".toUpperCase());
    JButton previous=new JButton("previous".toUpperCase());
    JButton home=new JButton("home".toUpperCase());
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JTextField name1 =new JTextField("good1");
    JTextField name2 =new JTextField("good1");
    JTextField name3 =new JTextField("good1");
    JTextField name4 =new JTextField("good1");
    JTextField allProfit =new JTextField("good1");
    JTextField allSell=new JTextField("good1");
    JTextField profit1 =new JTextField("good1");
    JTextField profit2 =new JTextField("good1");
    JTextField profit3 =new JTextField("good1");
    JTextField profit4 =new JTextField("good1");
    JTextField sell1=new JTextField("good1");
    JTextField sell2=new JTextField("good1");
    JTextField sell3=new JTextField("good1");
    JTextField sell4=new JTextField("good1");
    JTextField pageText=new JTextField("page : ".toUpperCase()+Integer.toString(page+1));
    FileOperating fileOperating =new FileOperating();
    ArrayList<Goods> list;
    String title;
    Finance(Controller controller, ArrayList<Goods> list,String title){
        this.controller=controller;
        this.list=list;
        try{
            this.controller= fileOperating.readFile("goodlist.txt",this.controller);
        }
        catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        if (this.controller==null){
            this.controller=new Controller(-1);
        }
        this.title=title;
        container=this.getContentPane();
        this.addJText();
        this.renewTexts(this.list.size());
        this.addPanels();
        this.addButtons();
        this.makeFrame();
    }

    public void addPanels(){
        LayoutManager mgr;
        mgr = new GroupLayout(container);
        this.setLayout(mgr);
        p1.setBounds(20,35,400,250);
        p2.setBounds(550,35,400,250);
        p3.setBounds(20,290,400,250);
        p4.setBounds(550,290,400,250);

        Border border1, border2, border3, border4;
        border1 = BorderFactory.createLineBorder(Color.MAGENTA,3,true);
        border2 = BorderFactory.createLineBorder(Color.BLUE,2,true);
        border3 = BorderFactory.createLineBorder(Color.CYAN,2,false);
        border4 = BorderFactory.createLineBorder(Color.RED,3,false);

        p1.setBorder(border1);
        p2.setBorder(border2);
        p3.setBorder(border3);
        p4.setBorder(border4);

        p1.setLayout(null);
        p2.setLayout(null);
        p3.setLayout(null);
        p4.setLayout(null);

        container.add(p1);
        container.add(p2);
        container.add(p3);
        container.add(p4);


    }

    public void addJText(){
        LayoutManager mgr1,mgr2,mgr3,mgr4;
        mgr1 = new GroupLayout(container);
        mgr2 = new GroupLayout(container);
        mgr3 = new GroupLayout(container);
        mgr4 = new GroupLayout(container);
        p1.setLayout(mgr1);
        p2.setLayout(mgr2);
        p3.setLayout(mgr3);
        p4.setLayout(mgr4);
        this.addJTextFrame(pageText,435,585,100,30,this,true);
        pageText.setBackground(new Color(0x89EABF));
        this.addJTextPanel(name1,20,20,250,30,p1,true);
        this.addJTextPanel(name2,20,20,250,30,p2,true);
        this.addJTextPanel(name3,20,20,250,30,p3,true);
        this.addJTextPanel(name4,20,20,250,30,p4,true);
        this.addJTextPanel(sell1,20,100,250,30,p1,true);
        this.addJTextPanel(sell2,20,100,250,30,p2,true);
        this.addJTextPanel(sell3,20,100,250,30,p3,true);
        this.addJTextPanel(sell4,20,100,250,30,p4,true);
        this.addJTextPanel(profit1,20,180,250,30,p1,true);
        this.addJTextPanel(profit2,20,180,250,30,p2,true);
        this.addJTextPanel(profit3,20,180,250,30,p3,true);
        this.addJTextPanel(profit4,20,180,250,30,p4,true);
        this.addJTextFrame(allProfit,700,5,250,25,this,true);
        this.addJTextFrame(allSell,20,5,250,25,this,true);
    }

    public void addJTextPanel(JTextField textField,int x ,int y,int w ,int h,JPanel panel,boolean editable){
        textField.setBounds(x,y,w,h);
        if(editable)textField.setEditable(false);
        textField.setBackground(new Color(0xFFE3D801, true));
        panel.add(textField);
    }

    public void addJTextFrame(JTextField textField,int x ,int y,int w ,int h,JFrame panel,boolean editable){
        textField.setBounds(x,y,w,h);
        if(editable)textField.setEditable(false);
        textField.setBackground(new Color(0xFFE3D801, true));
        panel.add(textField);
    }

    public  void addButtons(){
        addButton(this.next,850,565,100,50);
        addButton(this.previous,20,565,100,50);
        addButton(this.home,435,535,100,50);
    }

    public void addButton(JButton myButton,int x ,int y,int w ,int h,JPanel panel){
        myButton.setBounds(x,y,w,h);
        myButton.setBackground(new Color(0xEA0000));
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        panel.add(myButton);
    }

    public void addButton(JButton myButton,int x ,int y,int w ,int h){
        myButton.setBounds(x,y,w,h);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        this.add(myButton);
    }

    public void makeFrame(){
        container.setBackground(new Color(0xFFB3B3C8, true));
        setSize(width,height);
        setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        this.setTitle(this.title);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==next) this.nextKey();
        if(e.getSource()==previous) this.previousKey();
        if(e.getSource()==home){
            this.dispose();
            Menu menu=new Menu(this.controller);
        }
        this.fileOperating.jasonWriter(this.controller);
        this.renewTexts(this.list.size());
    }

    public void nextKey(){
        int goodNumbers=this.list.size();
        if(goodNumbers/4>page){
            this.page++;
            pageText.setText("page : ".toUpperCase()+Integer.toString(page+1));
            this.renewTexts(goodNumbers);
        }
    }

    public void previousKey(){
        int goodNumbers=this.list.size();
        if(0<page){
            this.page--;
            pageText.setText("page : ".toUpperCase()+Integer.toString(page+1));
            this.renewTexts(goodNumbers);
        }
    }

    public void renewSingle(int goodNumber,JTextField name,JTextField buyAmount,JTextField sell,int number){
        if(goodNumber>number&&list.contains(list.get(number))){
            Border border= BorderFactory.createLineBorder(new Color(0xA401C9),5,true);
            name.setBorder(border);
            sell.setBorder(border);
            buyAmount.setBorder(border);

            name.setText("GOOD NAME :"+this.list.get(number).getName());
            sell.setText("GOOD SELL PRICE :"+Double.toString(this.list.get(number).getSellOfOrders())+" $");
            buyAmount.setText("GOOD PROFIT PRICE :"+Double.toString(this.list.get(number).getProfitOfOrders())+" $");
        }else {
            name.setText("");
            sell.setText("");
            buyAmount.setText("");
        }
    }

    public void renewTexts(int goodNumber){
        this.renewSingle(goodNumber,name1, profit1,sell1,page*4);
        this.renewSingle(goodNumber,name2, profit2,sell2,page*4+1);
        this.renewSingle(goodNumber,name3, profit3,sell3,page*4+2);
        this.renewSingle(goodNumber,name4, profit4,sell4,page*4+3);
        this.allProfit.setText("ALL SELLS PROFIT :"+Double.toString(this.controller.allProfit()));
        this.allSell.setText("ALL SELLS :"+Double.toString(this.controller.allSell()));
    }

}
