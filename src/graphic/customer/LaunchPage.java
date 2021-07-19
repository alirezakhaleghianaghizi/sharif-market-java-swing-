package graphic.customer;


import com.google.gson.JsonSyntaxException;
import controller.Controller;
import view.FileOperating;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage implements ActionListener {
    FileOperating fileOperating =new FileOperating();
    public Controller controller;
    JFrame frame = new JFrame();
    JButton LogButton = new JButton("LOG IN");
    JButton signButton = new JButton("SIGN IN");

    public LaunchPage(){
        this.addButtons();
        this.makeFrame();
        try{
            this.controller= fileOperating.readFile("goodlist.txt",this.controller);
        }
        catch (JsonSyntaxException e){
            e.printStackTrace();
        }
        if (this.controller==null){
            this.controller=new Controller(-1);
        }
    }

    public  void addButtons(){
        addButton(LogButton,170,150,130,50);
        addButton(signButton,170,250,130,50);
    }

    public void addButton(JButton myButton,int x ,int y,int w ,int h){
        myButton.setBounds(x,y,w,h);
        myButton.setFocusable(false);
        myButton.addActionListener(this);
        frame.add(myButton);
    }

    public void makeFrame(){
        Container container=frame.getContentPane();
        container.setBackground(new Color(0x5FB0EE));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500,500);
        frame.setLayout(null);
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if(e.getSource()==LogButton) {
            frame.dispose();
            NewWindow myWindow = new NewWindow("LOG IN","SIGN IN",this.controller);
        }
        if(e.getSource()==signButton) {
            frame.dispose();
            NewWindow myWindow = new NewWindow("SIGN IN","LOG IN",this.controller);
        }
    }
}


