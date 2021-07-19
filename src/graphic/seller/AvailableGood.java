package graphic.seller;

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
import java.util.ArrayList;
import java.util.regex.Matcher;

public class AvailableGood extends JFrame implements ActionListener {
    int height = 680, width = 1000;
    int page=0;
    Controller controller;
    Container container;
    JButton edit1 =new JButton("edit");
    JButton edit2 =new JButton("edit");
    JButton edit3 =new JButton("edit");
    JButton edit4 =new JButton("edit");
    JButton delete1 =new JButton("delete");
    JButton delete2 =new JButton("delete");
    JButton delete3 =new JButton("delete");
    JButton delete4=new JButton("delete");
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
    JTextField buy1 =new JTextField("good1");
    JTextField buy2 =new JTextField("good1");
    JTextField buy3 =new JTextField("good1");
    JTextField buy4 =new JTextField("good1");
    JTextField sell1=new JTextField("good1");
    JTextField sell2=new JTextField("good1");
    JTextField sell3=new JTextField("good1");
    JTextField sell4=new JTextField("good1");
    JTextField pageText=new JTextField("page : ".toUpperCase()+Integer.toString(page+1));
    FileOperating fileOperating =new FileOperating();
    ArrayList<Goods> list;
    JMenuBar mb=new JMenuBar();
    JMenu goods=new JMenu("ADD GOOD");
    JMenuItem available=new JMenuItem("NEW GOOD");
    String title;

    AvailableGood(Controller controller, ArrayList<Goods> list,String title){
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
        available.addActionListener(this);
        goods.add(available);
        mb.add(goods);
        this.setJMenuBar(mb);
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
        this.addJTextPanel(buy1,20,180,250,30,p1,true);
        this.addJTextPanel(buy2,20,180,250,30,p2,true);
        this.addJTextPanel(buy3,20,180,250,30,p3,true);
        this.addJTextPanel(buy4,20,180,250,30,p4,true);
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
        addButton(this.edit1,310,180,70,50,p1);
        addButton(this.delete1,310,120,70,50,p1);
        addButton(this.edit2,310,180,70,50,p2);
        addButton(this.delete2,310,120,70,50,p2);
        addButton(this.edit3,310,180,70,50,p3);
        addButton(this.delete3,310,120,70,50,p3);
        addButton(this.edit4,310,180,70,50,p4);
        addButton(this.delete4,310,120,70,50,p4);
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
    if(e.getSource()== edit1) this.editKey(cod1);
    if(e.getSource()== edit2)this.editKey(cod2);
    if(e.getSource()==edit3) this.editKey(cod3);
    if(e.getSource()== edit4) this.editKey(cod4);
    if(e.getSource()== delete1) this.deleteKey(cod1);
    if(e.getSource()== delete2)this.deleteKey(cod2);
    if(e.getSource()==delete3) this.deleteKey(cod3);
    if(e.getSource()== delete4) this.deleteKey(cod4);
    if(e.getSource()==next) nextKey();
    if(e.getSource()==previous) this.previousKey();
    if(e.getSource()==home){
        this.dispose();
        Menu menu=new Menu(this.controller);
    }
    if(e.getSource()==this.available){
        this.dispose();
        Goods goods[] ={new Goods("new name",0,0,0,"new kind")};
        Edit edit =new Edit(this.controller,"add new good".toUpperCase(),goods);
    }
    this.fileOperating.jasonWriter(this.controller);
    this.renewTexts(this.list.size());
    }

    public void editKey(JTextField cod){

        try{
            Matcher matcher2 = Algorithms.COD.inputMatcher(cod.getText());
            matcher2.find();
            System.out.println("\n"+matcher2.group()+"\n");
            Goods goods1=new Goods(null,0,0,0,null);
            for (Goods goods : this.controller.availibaleGoodsList) {
                if(goods.getCod()==Long.parseLong(matcher2.group(1)))goods1=goods;
            }
            for (Goods goods : this.controller.nonAvailibaleGoodsList) {
                if(goods.getCod()==Long.parseLong(matcher2.group(1)))goods1=goods;
            }
            this.list.remove(goods1);


            if(this.controller.availibaleGoodsList.contains(goods1)) System.out.println("1 : "+goods1.getName());//this.controller.availibaleGoodsList.remove(goods1);
            if(this.controller.nonAvailibaleGoodsList.contains(goods1))System.out.println("2 : "+goods1.getName()); //this.controller.nonAvailibaleGoodsList.remove(goods1);
            this.dispose();
            Goods goods4[]={goods1};
            Edit edit=new Edit(this.controller,"EDIT GOOD",goods4);
            System.out.println("1 : "+goods1.getName());
            if(this.controller.availibaleGoodsList.contains(goods1)) ;//this.controller.availibaleGoodsList.add(goods1);
            if(this.controller.nonAvailibaleGoodsList.contains(goods1)) System.out.println("2 : "+goods1.getName());// this.controller.nonAvailibaleGoodsList.add(goods1);
        }
        catch (IllegalStateException e2){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER CORRECT FORMAT","warning",JOptionPane.WARNING_MESSAGE);
        }
        catch (NumberFormatException e2){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER CORRECT FORMAT","warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void deleteKey(JTextField cod){

        try{
            Matcher matcher2 = Algorithms.COD.inputMatcher(cod.getText());
            matcher2.find();
            System.out.println("\n"+matcher2.group()+"\n");
            Goods goods1=new Goods(null,0,0,0,null);
            for (Goods goods : this.controller.availibaleGoodsList) {
                if(goods.getCod()==Long.parseLong(matcher2.group(1)))goods1=goods;
            }
            for (Goods goods : this.controller.nonAvailibaleGoodsList) {
                if(goods.getCod()==Long.parseLong(matcher2.group(1)))goods1=goods;
            }
            int  situation = JOptionPane.showConfirmDialog(this,"Are you sure to delete :"+this.name2.getText(),"delete",JOptionPane.OK_CANCEL_OPTION);

            if(situation==0){
                this.list.remove(goods1);
                this.renewTexts(this.list.size());
                if(this.controller.availibaleGoodsList.contains(goods1)) this.controller.availibaleGoodsList.remove(goods1);
                if(this.controller.nonAvailibaleGoodsList.contains(goods1))this.controller.nonAvailibaleGoodsList.remove(goods1);
                this.fileOperating.jasonWriter(this.controller);
                if(this.title.equalsIgnoreCase("ALL GOODS")) this.list=this.controller.allListedGoods(this.controller.availibaleGoodsList,this.controller.nonAvailibaleGoodsList);
                if(this.title.equalsIgnoreCase("AVAILABLE GOODS"))this.list=this.controller.availibaleGoodsList;
                if(this.title.equalsIgnoreCase("NON AVAILABLE GOODS"))this.list=this.controller.nonAvailibaleGoodsList;
                this.fileOperating.jasonWriter(this.controller);
                this.renewTexts(list.size());
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

    public void renewSingle(int goodNumber,JTextField name,JTextField cod,JTextField available,JTextField buyAmount,JTextField sell,int number){
        if(goodNumber>number&&list.contains(list.get(number))){
            Border border= BorderFactory.createLineBorder(new Color(0xA401C9),5,true);
            name.setBorder(border);
            cod.setBorder(border);
            sell.setBorder(border);
            available.setBorder(border);
            buyAmount.setBorder(border);

            name.setText("GOOD NAME :"+this.list.get(number).getName());
            cod.setText("GOOD COD :"+Long.toString(this.list.get(number).getCod()));
            sell.setText("GOOD SELL PRICE :"+Double.toString(this.list.get(number).getSell())+" $");
            available.setText("AVAILABEL :"+Double.toString(this.list.get(number).getAmount())+"   "+this.list.get(number).getKindOfAmount());
            buyAmount.setText("GOOD BUY PRICE :"+Double.toString(this.list.get(number).getBuy())+" $");
        }else {
            name.setText("");
            cod.setText("");
            available.setText("");
            sell.setText("");
            buyAmount.setText("");
        }
    }

    public void renewTexts(int goodNumber){
       this.renewSingle(goodNumber,name1,cod1,amount1, buy1,sell1,page*4);
       this.renewSingle(goodNumber,name2,cod2,amount2, buy2,sell2,page*4+1);
       this.renewSingle(goodNumber,name3,cod3,amount3, buy3,sell3,page*4+2);
       this.renewSingle(goodNumber,name4,cod4,amount4, buy4,sell4,page*4+3);
    }

}
