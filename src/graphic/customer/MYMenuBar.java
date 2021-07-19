package graphic.customer;

import controller.Controller;
import model.Customer;
import view.FileOperating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MYMenuBar implements ActionListener {
    FileOperating fileOperating =new FileOperating();
    Controller controller;
    String title;
    JFrame frame ;
    JButton start=new JButton("START");
    JButton EXITButton=new JButton("EXIT");
    Customer currentCustomer;
    JLabel nameLabel;
    JLabel money;
    JMenuBar mb=new JMenuBar();
    JMenu goods=new JMenu("goods");
    JMenuItem available=new JMenuItem("AVAILABLE GOOD");
    JMenuItem nonAvailable=new JMenuItem("NON AVAILABLE GOOD");
    JMenuItem orders=new JMenuItem("ORDERS");
    public  MYMenuBar( Controller controller) {
        this.controller=controller;
        this.currentCustomer=this.controller.current();
        this.title="MARKET";
        this.frame=new JFrame(title);
        this.addLabels();
        this.addButtons();
        this.makeFrame();
        available.addActionListener(this);
        nonAvailable.addActionListener(this);
        orders.addActionListener(this);
        goods.add(available);
        goods.add(nonAvailable);
        goods.add(orders);
        mb.add(goods);
        frame.setJMenuBar(mb);
    }

    public  void addButtons(){
        addButton(this.EXITButton,175,280,100,50);
        addButton(this.start,65,280,100,50);
    }

    public  void addLabels(){
        nameLabel=new JLabel("id :"+this.controller.getCurentCoustomerId());
         nameLabel.setBounds(20, 0, 50, 50);
        nameLabel.setFont(new Font(null, Font.PLAIN, 15));
        frame.add(nameLabel);
        money=new JLabel("your money : "+this.currentCustomer.money+" remains");
        money.setBounds(90, 00, 250, 50);
        money.setFont(new Font(null, Font.PLAIN, 15));
        frame.add(money);
    }

    public void addButton(JButton myButton,int x ,int y,int w ,int h){
        myButton.setBounds(x,y,w,h);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        frame.add(myButton);
    }

    public void makeFrame(){
        Container container=frame.getContentPane();
        container.setBackground(new Color(0x5DE0D6));

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==start) {
            frame.dispose();
            LaunchPage launchPage = new LaunchPage();
        }
        if(e.getSource()==EXITButton) {
           int response= JOptionPane.showConfirmDialog(frame,"are you sure to exit !","EXIT",JOptionPane.CANCEL_OPTION);
            System.out.println(response);
           if(response==0) {
               System.exit(1);
               frame.dispose();
           }
        }
        if(e.getSource()==available) {
            this.fileOperating.jasonWriter(this.controller);
            frame.dispose();
            AvailableGood availableGood=new AvailableGood(this.controller);
        } if(e.getSource()==nonAvailable) {
            this.fileOperating.jasonWriter(this.controller);
            frame.dispose();
            NonAvailableGood nonAvailableGood=new NonAvailableGood(this.controller);
        }if(e.getSource()==orders) {
            this.fileOperating.jasonWriter(this.controller);
            frame.dispose();
            Orders orders=new Orders(this.controller);
        }

    }



}
