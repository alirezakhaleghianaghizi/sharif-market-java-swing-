package graphic.seller;

import com.google.gson.JsonSyntaxException;
import controller.Controller;
import model.Bill;
import view.FileOperating;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class History extends JFrame implements ActionListener {
    int height = 680, width = 1000;
    int page=0;
    Controller controller;
    Container container;
    JButton next=new JButton("next".toUpperCase());
    JButton previous=new JButton("previous".toUpperCase());
    JButton home=new JButton("home".toUpperCase());
    JTextField bill1 =new JTextField("good1");
    JTextField bill2 =new JTextField("good1");
    JTextField bill3 =new JTextField("good1");
    JTextField bill4 =new JTextField("good1");
    JTextField bill5 =new JTextField("good1");
    JTextField bill6 =new JTextField("good1");
    JTextField bill7 =new JTextField("good1");
    JTextField bill8 =new JTextField("good1");
    JTextField bill9 =new JTextField("good1");
    JTextField bill10 =new JTextField("good1");
    JTextField pageText=new JTextField("page : ".toUpperCase()+Integer.toString(page+1));
    FileOperating fileOperating =new FileOperating();
    ArrayList<Bill> list;
    String title;
    History(Controller controller, ArrayList<Bill> list, String title){
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
        this.addButtons();
        this.makeFrame();
    }

    public void addJText(){
        this.addJTextFrame(pageText,435,605,100,30,true);
        pageText.setBackground(new Color(0x89EABF));
        this.addJTextFrame(bill1,60,10,800,45,true);
        this.addJTextFrame(bill2,60,65,800,45,true);
        this.addJTextFrame(bill3,60,120,800,45,true);
        this.addJTextFrame(bill4,60,175,800,45,true);
        this.addJTextFrame(bill5,60,230,800,45,true);
        this.addJTextFrame(bill6,60,285,800,45,true);
        this.addJTextFrame(bill7,60,340,800,45,true);
        this.addJTextFrame(bill8,60,395,800,45,true);
        this.addJTextFrame(bill9,60,450,800,45,true);
        this.addJTextFrame(bill10,60,505,800,45,true);
    }

    public void addJTextFrame(JTextField textField, int x , int y, int w , int h, boolean editable){
        textField.setBounds(x,y,w,h);
        if(editable)textField.setEditable(false);
        textField.setBackground(new Color(0xFFE3D801, true));
        this.add(textField);
    }

    public  void addButtons(){
        this.setLayout(null);
        addButton(this.next,850,565,100,50);
        addButton(this.previous,20,565,100,50);
        addButton(this.home,435,555,100,50);
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
        if(goodNumbers/10>page){
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

    public void renewSingle(int goodNumber,JTextField bill,int number){
        if(number>=0){
            Border border= BorderFactory.createLineBorder(new Color(0xA401C9),5,true);
            bill.setBorder(border);

            bill.setText("CUSTOMER ID :"+this.list.get(number).getCustomerCod()+" ||| BILL DATE : "+this.list.get(number).getDate().toString()+" ||| BILL GOOD COD : "+this.list.get(number).getGoodCod()+" ||| BILL AMOUNT :"+this.list.get(number).getAmount());
        }else {
            bill.setText("");
        }
    }

    public void renewTexts(int goodNumber){
        this.renewSingle(goodNumber, bill1,goodNumber-page*10-1);
        this.renewSingle(goodNumber, bill2,goodNumber-page*10-2);
        this.renewSingle(goodNumber, bill3,goodNumber-page*10-3);
        this.renewSingle(goodNumber, bill4,goodNumber-page*10-4);
        this.renewSingle(goodNumber, bill5,goodNumber-page*10-5);
        this.renewSingle(goodNumber, bill6,goodNumber-page*10-6);
        this.renewSingle(goodNumber, bill7,goodNumber-page*10-7);
        this.renewSingle(goodNumber, bill8,goodNumber-page*10-8);
        this.renewSingle(goodNumber, bill9,goodNumber-page*10-9);
        this.renewSingle(goodNumber, bill10,goodNumber-page*10-10);
    }

}
