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

public class Edit extends JFrame implements ActionListener {
    int height = 680, width = 1000;
    int page=0;
    Controller controller;
    Container container;
    JButton edit1 =new JButton("edit");
    JButton edit2 =new JButton("edit");
    JButton edit3 =new JButton("edit");
    JButton edit4 =new JButton("edit");
    JButton edit5 =new JButton("edit");
    JButton home=new JButton("home".toUpperCase());
    JPanel p1 = new JPanel();
    JTextField name1 =new JTextField("good1");
    JTextField name2 =new JTextField("good1");
    JTextField cod1=new JTextField("good1");
    JTextField cod2=new JTextField("good1");
    JTextField amount1=new JTextField("good1");
    JTextField amount2=new JTextField("good1");
    JTextField buy1 =new JTextField("good1");
    JTextField buy2 =new JTextField("good1");
    JTextField sell1=new JTextField("good1");
    JTextField sell2=new JTextField("good1");
    FileOperating fileOperating =new FileOperating();
    String title;
    Goods []good=new Goods[1];
    JLabel firstEdition=new JLabel("firstEdition".toUpperCase());
    JLabel secondEdition=new JLabel("secondEdition".toUpperCase());

    Edit(Controller controller,String title,Goods []good){
        this.controller=controller;
        try{
            this.controller= fileOperating.readFile("goodlist.txt",this.controller);
        }
        catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        this.good=good;
        if (this.controller==null){
            this.controller=new Controller(-1);
        }
        this.title=title;
        container=this.getContentPane();
        this.addJText();
        this.addPanels();
        this.addButtons();
        this.renewTexts();
        firstEdition.setBounds(220,30,100,100);
        secondEdition.setBounds(530,30,100,100);
        this.add(firstEdition);
        this.add(secondEdition);
        this.makeFrame();
    }

    public void addPanels(){
        LayoutManager mgr;
        mgr = new GroupLayout(container);
        this.setLayout(mgr);
        p1.setBounds(200,100,600,370);
        Border border1 = BorderFactory.createLineBorder(Color.MAGENTA,3,true);
        p1.setBorder(border1);
        p1.setLayout(null);
        container.add(p1);
    }

    public void addJText(){
        LayoutManager mgr1;
        mgr1 = new GroupLayout(container);
        p1.setLayout(mgr1);
        this.addJTextPanel(name1,20,20,250,30,p1,true);
        this.addJTextPanel(name2,330,20,250,30,p1,false);
        this.addJTextPanel(cod1,20,90,250,30,p1,true);
        this.addJTextPanel(cod2,330,90,250,30,p1,false);
        this.addJTextPanel(amount1,20,160,250,30,p1,true);
        this.addJTextPanel(amount2,330,160,250,30,p1,false);
        this.addJTextPanel(sell1,20,230,250,30,p1,true);
        this.addJTextPanel(sell2,330,230,250,30,p1,false);
        this.addJTextPanel(buy1,20,300,250,30,p1,true);
        this.addJTextPanel(buy2,330,300,250,30,p1,false);
    }

    public void addJTextPanel(JTextField textField,int x ,int y,int w ,int h,JPanel panel,boolean editable){
        textField.setBounds(x,y,w,h);
        if(editable)textField.setEditable(false);
        textField.setBackground(new Color(0xFFE3D801, true));
        panel.add(textField);
    }

    public  void addButtons(){
        addButton(this.edit1,510,55,70,30,p1);
        addButton(this.edit2,510,125,70,30,p1);
        addButton(this.edit3,510,195,70,30,p1);
        addButton(this.edit4,510,265,70, 30,p1);
        addButton(this.edit5,510,335,70, 30,p1);
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
        if(this.controller.availibaleGoodsList.contains(good)) System.out.println("true");
        try {
            if(e.getSource()==home){
                this.dispose();
                Menu menu=new Menu(this.controller);
            }
            if(e.getSource()== edit1){
                int  situation = JOptionPane.showConfirmDialog(this,"new name :"+this.name2.getText(),"editing",JOptionPane.OK_CANCEL_OPTION);
                if(situation==0) good[0].setName(this.name2.getText());
            }
            if(e.getSource()== edit2){
                int  situation = JOptionPane.showConfirmDialog(this,"new kind :"+this.cod2.getText(),"editing",JOptionPane.OK_CANCEL_OPTION);
                if(situation==0) good[0].setKindOfAmount(this.cod2.getText());
            }
            if(e.getSource()==edit3){
                int  situation = JOptionPane.showConfirmDialog(this,"new amount :"+this.amount2.getText(),"editing",JOptionPane.OK_CANCEL_OPTION);
                if(situation==0) {
                    good[0].setAmount(Double.parseDouble(this.amount2.getText()));

                }
            }
            if(e.getSource()== edit4){
                int  situation = JOptionPane.showConfirmDialog(this,"new sell price :"+this.sell2.getText(),"editing",JOptionPane.OK_CANCEL_OPTION);
                if(situation==0) good[0].setSell(Double.parseDouble(this.sell2.getText()));
            }
            if(e.getSource()== edit5){
                int  situation = JOptionPane.showConfirmDialog(this,"new buy price :"+this.buy2.getText(),"editing",JOptionPane.OK_CANCEL_OPTION);
                if(situation==0) good[0].setBuy(Double.parseDouble(this.buy2.getText()));
            }
        }catch (NumberFormatException e1){
            JOptionPane.showMessageDialog(this,"PLEASE ENTER CORRECT NUMBER FORMAT","warning".toUpperCase(),JOptionPane.WARNING_MESSAGE);
        }
        if(this.controller.availibaleGoodsList.contains(good)) System.out.println("true");
        this.fileOperating.jasonWriter(this.controller);
        this.renewTexts();
        edit();
        this.fileOperating.jasonWriter(this.controller);
    }

    public void edit(){
        for (Goods goods : this.controller.availibaleGoodsList) {
            if(goods.getCod()==good[0].getCod()){
                this.controller.availibaleGoodsList.remove(goods);
                break;
            }
        }
        for (Goods goods : this.controller.nonAvailibaleGoodsList) {
            if(goods.getCod()==good[0].getCod()){
                this.controller.nonAvailibaleGoodsList.remove(goods);
                break;
            }
        }
        if((good[0]).getAmount()>0) this.controller.availibaleGoodsList.add(good[0]);
        if((good[0]).getAmount()<=0)  this.controller.nonAvailibaleGoodsList.add(good[0]);
    }

    public Goods[] returnGood(){
        return good;
    }

    public void renewSingle(Goods good,JTextField name,JTextField cod,JTextField available,JTextField buyAmount,JTextField sell,int number){
            Border border= BorderFactory.createLineBorder(new Color(0xA401C9),5,true);
            name.setBorder(border);
            cod.setBorder(border);
            sell.setBorder(border);
            available.setBorder(border);
            buyAmount.setBorder(border);

            name.setText("GOOD NAME :"+good.getName());
            cod.setText("GOOD KIND :"+good.getKindOfAmount());
            sell.setText("GOOD SELL PRICE :"+Double.toString(good.getSell())+" $");
            available.setText("AVAILABEL :"+Double.toString(good.getAmount()));
            buyAmount.setText("GOOD BUY PRICE :"+Double.toString(good.getBuy())+" $");

    }

    public void renewTexts(){
        this.renewSingle(good[0],name1,cod1,amount1, buy1,sell1,page*4);
        this.renewSingle(good[0],name2,cod2,amount2, buy2,sell2,page*4);

    }

}
