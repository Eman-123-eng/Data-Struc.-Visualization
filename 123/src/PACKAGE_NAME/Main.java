package PACKAGE_NAME;
import PACKAGE_NAME.ArrStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EmptyStackException;

public class Main {

    public static void main(String[] args){


        ArrStack s = new ArrStack();

        JFrame f = new JFrame("ArrayStack");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setSize(700,500);
        f.setLayout(null);


        JLabel l = new JLabel("Enter the item you want to push to your stack ");
        Dimension size = l.getPreferredSize();
        l.setBounds(50,20,size.width,size.height);

        JLabel l1 = new JLabel("\n Note: (you can only push one element in the stack)");
        Dimension size1 = l1.getPreferredSize();
        l1.setBounds(50,40,size1.width,size1.height);


        JTextField t1 = new JTextField();
        t1.setBounds(450,20,100,25);
        t1.setVisible(true);

        JTextArea dis = new JTextArea();
        dis.enableInputMethods(false);
        dis.setBounds(400,75,230,185);

        JScrollPane sc = new JScrollPane(dis);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sc.setBounds(400,75,230,185);
        sc.setVisible(true);


        JButton b1 = new JButton("Push");
        b1.setBounds(150,270,80,30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(t1.getText().length()>1) {
                    JOptionPane.showMessageDialog(null, "You can't enter more than one item");
                    t1.setText("");
                }
                else {
                    s.push(t1.getText());
                    t1.setText("");
                }
            }
        });

        JButton b2 = new JButton("Pop");
        b2.setBounds(300,270,80,30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                s.pop();
                if(s.manyItems == 0)
                    dis.append("\n there's no items left in your stack! ");
            }
        });

        JButton b3 = new JButton("Peek");
        b3.setBounds(450,270,80,30);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dis.setText("The item at the top of your stack is "+ s.peek());
            }
        });

        JButton b4 = new JButton("Size");
        b4.setBounds(150,350,80,30);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dis.setText("The size of your stack is "+ s.size());
            }
        });

        JButton b5 = new JButton("Display");
        b5.setBounds(300,350,80,30);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dis.append("the items in your stack are : \n");
                for(int i = (s.manyItems -1); i >= 0; i--){
                   dis.append((String)s.data[i]);
                   if (i!=0)
                       dis.append("\n");

                }
                dis.append("\n The elapsed time is:  ");
                for (int i = 0; i<s.timecal.length; i++){
                    dis.append(s.timecal[i]);
                    if(i!=0)
                        dis.append("-->");
                }
                dis.append("\n");
            }
        });

        JButton b6=new JButton("clone");
        b6.setBounds(450,350,80,30);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(s.manyItems==0)
                    JOptionPane.showMessageDialog(null,"Your Stack is empty!");
                else {
                    ArrStack c = s.clone();
                    dis.append("\n your stack has been copied successfuly! \n");
                    dis.append("The elapsed time is : " + c.time +"\n");
                }
            }
        });


        f.add(l);
        f.add(l1);
        f.add(t1);
        f.add(b1);
        f.add(sc);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(b6);

    }
}