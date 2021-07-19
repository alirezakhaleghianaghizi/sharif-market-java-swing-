package graphic.customer;

import com.google.gson.JsonSyntaxException;
import controller.Controller;
import model.Bill;
import model.Customer;
import model.Goods;
import view.Algorithms;
import view.FileOperating;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;

public class Orders extends JFrame implements ActionListener {
    int height = 680, width = 1000;
    int page=0;
    Controller controller;
    Container container;
    JButton delete1 =new JButton("DELETE");
    JButton delete2 =new JButton("DELETE");
    JButton delete3 =new JButton("DELETE");
    JButton delete4 =new JButton("DELETE");
    JButton next=new JButton("next".toUpperCase());
    JButton previous=new JButton("previous".toUpperCase());
    JButton home=new JButton("home".toUpperCase());
    JPanel p1 = new JPanel();
    JPanel p2 = new JPanel();
    JPanel p3 = new JPanel();
    JPanel p4 = new JPanel();
    JTextField id1 =new JTextField("good1");
    JTextField id2 =new JTextField("good1");
    JTextField id3 =new JTextField("good1");
    JTextField id4 =new JTextField("good1");
    JTextField cod1=new JTextField("good1");
    JTextField cod2=new JTextField("good1");
    JTextField cod3=new JTextField("good1");
    JTextField cod4=new JTextField("good1");
    JTextField amount1=new JTextField("good1");
    JTextField amount2=new JTextField("good1");
    JTextField amount3=new JTextField("good1");
    JTextField amount4=new JTextField("good1");
    JTextField date1 =new JTextField("good1");
    JTextField date2 =new JTextField("good1");
    JTextField date3 =new JTextField("good1");
    JTextField date4 =new JTextField("good1");
    JTextField user=new JTextField("good1");
    JTextField money=new JTextField("good1");
    JTextField pageText=new JTextField("page : ".toUpperCase()+Integer.toString(page+1));
    FileOperating fileOperating =new FileOperating();

    Orders(Controller controller){
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
        this.renewTexts(this.controller.current().bills.size());
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
        this.addJTextPanel(id1,20,20,250,30,p1,true);
        this.addJTextPanel(id2,20,20,250,30,p2,true);
        this.addJTextPanel(id3,20,20,250,30,p3,true);
        this.addJTextPanel(id4,20,20,250,30,p4,true);
        this.addJTextPanel(cod1,20,55,250,30,p1,true);
        this.addJTextPanel(cod2,20,55,250,30,p2,true);
        this.addJTextPanel(cod3,20,55,250,30,p3,true);
        this.addJTextPanel(cod4,20,55,250,30,p4,true);
        this.addJTextPanel(amount1,20,90,250,30,p1,true);
        this.addJTextPanel(amount2,20,90,250,30,p2,true);
        this.addJTextPanel(amount3,20,90,250,30,p3,true);
        this.addJTextPanel(amount4,20,90,250,30,p4,true);
        this.addJTextPanel(date1,20,130,250,30,p1,true);
        this.addJTextPanel(date2,20,130,250,30,p2,true);
        this.addJTextPanel(date3,20,130,250,30,p3,true);
        this.addJTextPanel(date4,20,130,250,30,p4,true);
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
        addButton(this.delete1,310,180,100,50,p1);
        addButton(this.delete2,310,180,100,50,p2);
        addButton(this.delete3,310,180,100,50,p3);
        addButton(this.delete4,310,180,100,50,p4);
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
        this.setTitle("orders".toUpperCase());
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==delete1) this.deleteKey(this.id1,this.amount1);
        if(e.getSource()== delete2) this.deleteKey(this.id2,this.amount2);
        if(e.getSource()== delete3) this.deleteKey(this.id3,this.amount3);
        if(e.getSource()== delete4) this.deleteKey(this.id4,this.amount4);
        if(e.getSource()==next) this.nextKey();
        if(e.getSource()==previous) this.previousKey();
        if(e.getSource()==home){
            this.dispose();
            MYMenuBar myMenuBar=new MYMenuBar(this.controller);
        }
        this.fileOperating.jasonWriter(this.controller);
        this.renewTexts(this.controller.current().bills.size());
    }

    public void deleteKey(JTextField cod,JTextField buyAmount){
        try{
            Matcher matcher2 = Algorithms.BILLID.inputMatcher(cod.getText());
            matcher2.find();
            Matcher matcher = Algorithms.BILLAMOUNT.inputMatcher(buyAmount.getText());
            matcher.find();
            double amount=Double.parseDouble(matcher.group(1));
            System.out.println("\n"+matcher2.group()+"\n");
            Goods goods1=null;
            Bill bill=null;
            for (Bill bill1 : this.controller.current().bills) {
                if(bill1.id==Long.parseLong(matcher2.group(1))) bill=bill1;
            }
            for (Goods goods : this.controller.availibaleGoodsList) {
                if(bill!=null&&goods.getCod()==bill.getGoodCod())goods1=goods;
            }
            if(goods1==null){
                for (Goods goods : this.controller.nonAvailibaleGoodsList) {
                    if(bill!=null&&goods.getCod()==bill.getGoodCod())goods1=goods;
                }
                this.controller.nonAvailibaleGoodsList.remove(goods1);
               this.controller.availibaleGoodsList.add(goods1);
            }
            amount=bill.getAmount();
            goods1.setAmount(goods1.getAmount()+amount);
            goods1.setAmountOfOrders(-amount+goods1.getAmountOfOrders());
            goods1.setNumberOfOrders(-1+goods1.getNumberOfOrders());
            goods1.setProfitOfOrders(-amount*(goods1.getSell()-goods1.getBuy())+goods1.getProfitOfOrders());
            goods1.setSellOfOrders(goods1.getSellOfOrders()-amount*goods1.getSell());
            for (Bill bill2 : this.controller.billsList) {
                if(bill2.id==Long.parseLong(matcher2.group(1))){
                    controller.billsList.remove(bill2);break;
                }
            }
            for (Customer customer : controller.customers) {
                if(customer.getId()==controller.getCurentCoustomerId()){
                    customer.bills.remove(bill);
                    customer.money+=bill.getAmount()*goods1.getSell();
                }
            }
            if(!this.controller.availibaleGoodsList.contains(goods1)) this.controller.availibaleGoodsList.add(goods1);
            JOptionPane.showMessageDialog(this,"deleted the order: "+bill.getGoodCod());
            this.fileOperating.jasonWriter(this.controller);
        }
        catch (IllegalStateException e2){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER CORRECT FORMAT","warning",JOptionPane.WARNING_MESSAGE);
        }
        catch (NumberFormatException e2){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER CORRECT FORMAT","warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void nextKey(){
        int goodNumbers=this.controller.current().bills.size();
        if(goodNumbers/4>page){
            this.page++;
            pageText.setText("page : ".toUpperCase()+Integer.toString(page+1));
            this.renewTexts(goodNumbers);
        }
    }

    public void previousKey(){
        int goodNumbers=this.controller.current().bills.size();
        if(0<page){
            this.page--;
            pageText.setText("page : ".toUpperCase()+Integer.toString(page+1));
            this.renewTexts(goodNumbers);
        }
    }

    public void renewSingle(int orders, JTextField id, JTextField cod, JTextField available, JTextField date, int number){
        if(orders >number){
            Border border= BorderFactory.createLineBorder(new Color(0xA401C9),5,true);
            id.setBorder(border);
            cod.setBorder(border);
            date.setBorder(border);
            available.setBorder(border);
            id.setText("BILL ID :"+Long.toString(controller.current().bills.get(number).id));
            cod.setText("GOOD COD :"+Long.toString(controller.current().bills.get(number).getGoodCod()));
            date.setText("BILL DATE :"+controller.current().bills.get(number).getDate().toString());
            available.setText("BILL AMOUNT :"+Double.toString(controller.current().bills.get(number).getAmount()));
        }else {
            id.setText(null);
            cod.setText(null);
            available.setText(null);
            date.setText(null);
        }
    }

    public void renewTexts(int orders){
        this.renewSingle(orders, id1,cod1,amount1,date1,page*4);
        this.renewSingle(orders, id2,cod2,amount2,date2,page*4+1);
        this.renewSingle(orders, id3,cod3,amount3,date3,page*4+2);
        this.renewSingle(orders, id4,cod4,amount4,date4,page*4+3);
        this.money.setText("MONEY :"+this.controller.current().money);
    }
}
