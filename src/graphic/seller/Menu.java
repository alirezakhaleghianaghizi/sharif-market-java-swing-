package graphic.seller;

import com.google.gson.JsonSyntaxException;
import controller.Controller;
import view.FileOperating;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

 public class Menu  extends JFrame implements ActionListener {
        int height = 680, width = 1000;
        Controller controller;
        Container container;
        JButton allGoods=new JButton("ALL GOODS");
        JButton availableGoods =new JButton("AVAILABLE GOODS");
        JButton nonAvailableGood=new JButton("NON AVAILABLE GOOD");
        JButton history =new JButton("history".toUpperCase());
        JButton finance =new JButton("finance".toUpperCase());
        JButton exit =new JButton("exit".toUpperCase());
        JPanel p1 = new JPanel();
        JPanel p2 = new JPanel();
        FileOperating fileOperating =new FileOperating();
     public Menu (Controller controller){
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
            this.addPanels();
            this.addButtons();
            this.makeFrame();
        }

        public void addPanels(){
            LayoutManager mgr;
            mgr = new GroupLayout(container);
            this.setLayout(mgr);
            p1.setBounds(150,0,1000,150);
            p2.setBounds(0,0,150,643);


            Border border1, border2;
            border1 = BorderFactory.createLineBorder(new Color(0x2C2C6A),4,true);
            border2 = BorderFactory.createLineBorder(new Color(0x435251),4,true);

            p1.setBorder(border1);
            p2.setBorder(border2);

            p2.setBackground(new Color(0x2A3432));
            p1.setBackground(new Color(0x2F2F49));
            p1.setLayout(null);
            p2.setLayout(null);

            container.add(p1);
            container.add(p2);


        }

        public  void addButtons(){
            addButton(this.allGoods,10,200,130,50,p2,new Color(0x435251));
            addButton(this.availableGoods,10,380,130,50,p2,new Color(0x435251));
            addButton(this.nonAvailableGood,10,550,130,50,p2,new Color(0x435251));
            addButton(this.history,200,50,100,50,p1,new Color(0x2C2C6A));
            addButton(this.finance,600,50,100,50,p1,new Color(0x2C2C6A));
            addButton(this.exit,800,550,100,50);
        }

        public void addButton(JButton myButton,int x ,int y,int w ,int h,JPanel panel,Color color){
            myButton.setBounds(x,y,w,h);
            myButton.setBackground(color);
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
           if(e.getSource()==allGoods) {
               this.dispose();
               AvailableGood availableGood=new AvailableGood(this.controller,this.controller.allListedGoods(this.controller.availibaleGoodsList,this.controller.nonAvailibaleGoodsList),"ALL GOODS");
           }
            if(e.getSource()== availableGoods) {
                this.dispose();
                AvailableGood availableGood=new AvailableGood(this.controller,this.controller.availibaleGoodsList,"AVAILABLE GOODS");
            }
            if(e.getSource()==nonAvailableGood) {
                this.dispose();
                AvailableGood availableGood=new AvailableGood(this.controller,this.controller.nonAvailibaleGoodsList,"NON AVAILABLE GOODS");
            }
            if(e.getSource()== history) {
                this.dispose();
                History history=new History(this.controller,this.controller.billsList,"HISTORY");
            }
            if(e.getSource()== finance){
                this.dispose();
                Finance finance=new Finance(this.controller,this.controller.allListedGoods(this.controller.availibaleGoodsList,this.controller.nonAvailibaleGoodsList),"Finane".toUpperCase());
            }
            if(e.getSource()== exit){
                this.dispose();
                System.exit(1);
            }
            this.fileOperating.jasonWriter(this.controller);      }

    }


