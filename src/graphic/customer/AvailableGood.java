package graphic.customer;

import com.google.gson.JsonSyntaxException;
import controller.Controller;
import model.Goods;
import view.Algorithms;
import view.FileOperating;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;

public class AvailableGood extends JFrame implements ActionListener {
    int height = 680, width = 1000;
    int page=0;
    Controller controller;
    Container container;
    JButton buy1=new JButton("BUY");
    JButton buy2=new JButton("BUY");
    JButton buy3=new JButton("BUY");
    JButton buy4=new JButton("BUY");
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
    JTextField cod1=new JTextField("good1");
    JTextField cod2=new JTextField("good1");
    JTextField cod3=new JTextField("good1");
    JTextField cod4=new JTextField("good1");
    JTextField amount1=new JTextField("good1");
    JTextField amount2=new JTextField("good1");
    JTextField amount3=new JTextField("good1");
    JTextField amount4=new JTextField("good1");
    JTextField buyAmount1=new JTextField("good1");
    JTextField buyAmount2=new JTextField("good1");
    JTextField buyAmount3=new JTextField("good1");
    JTextField buyAmount4=new JTextField("good1");
    JTextField sell1=new JTextField("good1");
    JTextField sell2=new JTextField("good1");
    JTextField sell3=new JTextField("good1");
    JTextField sell4=new JTextField("good1");
    JTextField user=new JTextField("good1");
    JTextField money=new JTextField("good1");
    JTextField pageText=new JTextField("page : ".toUpperCase()+Integer.toString(page+1));
    FileOperating fileOperating =new FileOperating();

    AvailableGood(Controller controller){
        this.controller=controller;
        try{
            this.controller= fileOperating.readFile("goodlist.txt",this.controller);
        }
        catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        if (this.controller==null){
            this.controller=new Controller(-1);
        }

        container=this.getContentPane();
        this.addJText();
        this.user.setText("id : ".toUpperCase()+Long.toString(this.controller.getCurentCoustomerId()));
        this.money.setText("money : ".toUpperCase()+Integer.toString(this.controller.current().money));
        user.setBackground(new Color(0xEBFFFB));
        money.setBackground(new Color(0xF5F1F1));
        this.renewTexts(this.controller.availibaleGoodsList.size());
        this.addPanels();
        this.addButtons();
        this.makeFrame();
    }

    public void addPanels(){
        LayoutManager mgr;
        mgr = new GroupLayout(container);
        this.setLayout(mgr);
        p1.setBounds(20,50,400,250);
        p2.setBounds(550,50,400,250);
        p3.setBounds(20,305,400,250);
        p4.setBounds(550,305,400,250);

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
        this.addJTextFrame(user,20,10,250,30, this,true);
        this.addJTextFrame(money,700,10,250,30,this,true);
        this.addJTextFrame(pageText,435,600,100,30,this,true);
        pageText.setBackground(new Color(0x89EABF));
        this.addJTextPanel(name1,20,20,250,30,p1,true);
        this.addJTextPanel(name2,20,20,250,30,p2,true);
        this.addJTextPanel(name3,20,20,250,30,p3,true);
        this.addJTextPanel(name4,20,20,250,30,p4,true);
        this.addJTextPanel(cod1,20,55,250,30,p1,true);
        this.addJTextPanel(cod2,20,55,250,30,p2,true);
        this.addJTextPanel(cod3,20,55,250,30,p3,true);
        this.addJTextPanel(cod4,20,55,250,30,p4,true);
        this.addJTextPanel(amount1,20,90,250,30,p1,true);
        this.addJTextPanel(amount2,20,90,250,30,p2,true);
        this.addJTextPanel(amount3,20,90,250,30,p3,true);
        this.addJTextPanel(amount4,20,90,250,30,p4,true);
        this.addJTextPanel(sell1,20,130,250,30,p1,true);
        this.addJTextPanel(sell2,20,130,250,30,p2,true);
        this.addJTextPanel(sell3,20,130,250,30,p3,true);
        this.addJTextPanel(sell4,20,130,250,30,p4,true);
        this.addJTextPanel(buyAmount1,20,190,250,50,p1,false);
        this.addJTextPanel(buyAmount2,20,190,250,50,p2,false);
        this.addJTextPanel(buyAmount3,20,190,250,50,p3,false);
        this.addJTextPanel(buyAmount4,20,190,250,50,p4,false);
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
        addButton(this.buy1,310,180,70,50,p1);
        addButton(this.buy2,310,180,70,50,p2);
        addButton(this.buy3,310,180,70,50,p3);
        addButton(this.buy4,310,180,70,50,p4);
        addButton(this.next,850,580,100,50);
        addButton(this.previous,20,580,100,50);
        addButton(this.home,435,550,100,50);
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
        this.setTitle("AVAILABLE GOODS");
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
    if(e.getSource()==buy1) this.buyKey(this.cod1,this.buyAmount1);
    if(e.getSource()==buy2) this.buyKey(this.cod2,this.buyAmount2);
    if(e.getSource()==buy3) this.buyKey(this.cod3,this.buyAmount3);
    if(e.getSource()==buy4) this.buyKey(this.cod4,this.buyAmount4);
    if(e.getSource()==next) nextKey();
    if(e.getSource()==previous) this.previousKey();
    if(e.getSource()==home){
        this.dispose();
        MYMenuBar myMenuBar=new MYMenuBar(this.controller);
    }
    this.fileOperating.jasonWriter(this.controller);
    this.renewTexts(this.controller.availibaleGoodsList.size());
    }

    public void buyKey(JTextField cod,JTextField buyAmount){
        try{
            Matcher matcher2 = Algorithms.COD.inputMatcher(cod.getText());
            matcher2.find();
            double amount=Double.parseDouble(buyAmount.getText());
            System.out.println("\n"+matcher2.group()+"\n");
            Goods goods1=new Goods(null,0,0,0,null);
            for (Goods goods : this.controller.availibaleGoodsList) {
                if(goods.getCod()==Long.parseLong(matcher2.group(1)))goods1=goods;
            }
            if(this.controller.current().money<amount*goods1.getSell()) JOptionPane.showMessageDialog(this,"KIND OF AMOUNT WRONG","warning",JOptionPane.WARNING_MESSAGE);
            else {
                long orderingSituation=this.controller.order(Long.parseLong(matcher2.group(1)),amount,goods1.getKindOfAmount(),this.controller.getCurentCoustomerId());
                if(orderingSituation==-1)JOptionPane.showMessageDialog(this,"KIND OF AMOUNT WRONG","warning",JOptionPane.WARNING_MESSAGE);
                else if(orderingSituation==0)JOptionPane.showMessageDialog(this,"NOT ENOUGH GOOD","warning",JOptionPane.WARNING_MESSAGE);
                else{
                    this.controller.current().money-=amount*goods1.getSell();
                    JOptionPane.showMessageDialog(this,"your bill id is : "+orderingSituation);
                }
            }
        }
        catch (IllegalStateException e2){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER CORRECT FORMAT","warning",JOptionPane.WARNING_MESSAGE);
        }
        catch (NumberFormatException e2){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER CORRECT FORMAT","warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void nextKey(){
        int goodNumbers=this.controller.availibaleGoodsList.size();
        if(goodNumbers/4>page){
            this.page++;
            pageText.setText("page : ".toUpperCase()+Integer.toString(page+1));
            this.renewTexts(goodNumbers);
        }
    }

    public void previousKey(){
        int goodNumbers=this.controller.availibaleGoodsList.size();
        if(0<page){
            this.page--;
            pageText.setText("page : ".toUpperCase()+Integer.toString(page+1));
            this.renewTexts(goodNumbers);
        }
    }

    public void renewSingle(int goodNumber,JTextField name,JTextField cod,JTextField available,JTextField buyAmount,JTextField sell,int number){
        if(goodNumber>number){
            Border border= BorderFactory.createLineBorder(new Color(0xA401C9),5,true);
            name.setBorder(border);
            cod.setBorder(border);
            sell.setBorder(border);
            available.setBorder(border);
            buyAmount.setBorder(border);

            name.setText("GOOD NAME :"+controller.availibaleGoodsList.get(number).getName());
            cod.setText("GOOD COD :"+Long.toString(controller.availibaleGoodsList.get(number).getCod()));
            sell.setText("GOOD SELL PRICE :"+Double.toString(controller.availibaleGoodsList.get(number).getSell())+" $");
            available.setText("AVAILABEL :"+Double.toString(controller.availibaleGoodsList.get(number).getAmount())+"   "+controller.availibaleGoodsList.get(number).getKindOfAmount());
            buyAmount.setText("ENTER AMOUNT");
        }else {
            name.setText(null);
            cod.setText(null);
            available.setText(null);
            sell.setText(null);
            buyAmount.setText(null);
        }
    }

    public void renewTexts(int goodNumber){
       this.renewSingle(goodNumber,name1,cod1,amount1,buyAmount1,sell1,page*4);
       this.renewSingle(goodNumber,name2,cod2,amount2,buyAmount2,sell2,page*4+1);
       this.renewSingle(goodNumber,name3,cod3,amount3,buyAmount3,sell3,page*4+2);
       this.renewSingle(goodNumber,name4,cod4,amount4,buyAmount4,sell4,page*4+3);
        this.money.setText("MONEY :"+this.controller.current().money);
    }

}
