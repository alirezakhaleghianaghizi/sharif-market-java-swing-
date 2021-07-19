package graphic.customer;

import controller.Controller;
import model.Customer;
import view.FileOperating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NewWindow implements ActionListener {
    FileOperating fileOperating =new FileOperating();
    Controller controller;
    String title;
    String nextMenu;
    JFrame frame ;
    JButton start=new JButton("START");
    JButton InButton;
    JButton okButton=new JButton("OK");
    JTextField userName=new JTextField("id");
    JTextField userPassword=new JTextField("password");
    JLabel nameLabel=new JLabel("id");
    JLabel password=new JLabel("password");
    NewWindow(String title,String nextMenu,Controller controller) {
        this.controller=controller;
        this.title=title;
        this.nextMenu=nextMenu;
       this.frame=new JFrame(title);
       this.InButton=new JButton(nextMenu);
       this.addJText();
        this.addLabels();
        this.addButtons();
        this.makeFrame();
    }

    public  void addButtons(){
        addButton(this.InButton,175,280,100,50);
        addButton(this.okButton,370,170,70,50);
        addButton(this.start,65,280,100,50);
    }

    public  void addLabels(){
        nameLabel.setBounds(70, 130, 50, 50);
        nameLabel.setFont(new Font(null, Font.PLAIN, 15));
        frame.add(nameLabel);
        password.setBounds(70, 180, 70, 50);
        password.setFont(new Font(null, Font.PLAIN, 15));
        frame.add(password);
    }

    public  void addJText(){
        this.userName.setBounds(140,130,80,40);
        frame.add(userName);
        this.userPassword.setBounds(140,180,80,40);
        frame.add(userPassword);
    }

    public void addButton(JButton myButton,int x ,int y,int w ,int h){
        myButton.setBounds(x,y,w,h);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        frame.add(myButton);
    }

    public void makeFrame(){
        Container container=frame.getContentPane();
        if(this.title.equalsIgnoreCase("sign in")) container.setBackground(new Color(0xEA6ABF));
        else container.setBackground(new Color(0x4DEF49));

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
        if(e.getSource()==InButton) {
            frame.dispose();
            NewWindow myWindow = new NewWindow(nextMenu,title,this.controller);
        }
        if(e.getSource()==okButton) {
            this.controller.setCurentCoustomerId(-1);
           if(this.title.equalsIgnoreCase("log in")) this.login();
           else this.signin();

        }
    }

    public void login(){
        try{
            int id=Integer.parseInt(this.userName.getText());
            int pass=Integer.parseInt(this.userPassword.getText());
            int situation=this.controller.searchingCustomer(id,pass);
            if(situation==1){
                this.fileOperating.jasonWriter(this.controller);
                JOptionPane.showMessageDialog(frame,title+" with id : "+this.controller.getCurentCoustomerId()+" and pass :"+this.userPassword.getText());
                frame.dispose();
                MYMenuBar menuBar=new MYMenuBar(this.controller);
            }
            else if(situation==0) JOptionPane.showMessageDialog(frame,"wrong password ","warning",JOptionPane.WARNING_MESSAGE);
            else if(situation==-1) JOptionPane.showMessageDialog(frame,"there is no account with id : "+id,"warning",JOptionPane.WARNING_MESSAGE);
        }catch (NumberFormatException e1){
            JOptionPane.showMessageDialog(frame,"pleas enter correct number format ","warning",JOptionPane.WARNING_MESSAGE);
        }
    }

    public void signin(){
        try{
            String  id=this.userName.getText();
            int pass=Integer.parseInt(this.userPassword.getText());
            boolean alreadyToken=false;
            for (Customer customer : this.controller.customers) {
                if(customer.getId()==id.hashCode()) alreadyToken=true;
            }
            if(!alreadyToken){
                this.controller.signing(id,pass);
                this.fileOperating.jasonWriter(this.controller);
                JOptionPane.showMessageDialog(frame,title+" with id : "+this.controller.getCurentCoustomerId()+" and pass :"+this.userPassword.getText());
                frame.dispose();
                for (Customer customer : this.controller.customers) {
                    if(customer.getId()==this.controller.getCurentCoustomerId()) customer.money=1_000_000;
                }
                MYMenuBar menuBar=new MYMenuBar(this.controller);
            }
            else  JOptionPane.showMessageDialog(frame,"the "+id+"is already token","warning",JOptionPane.WARNING_MESSAGE);
        }catch (NumberFormatException e1){
            JOptionPane.showMessageDialog(frame,"pleas enter correct number format ","warning",JOptionPane.WARNING_MESSAGE);
        }
    }

}
