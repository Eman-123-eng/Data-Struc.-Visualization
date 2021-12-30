package LINKED_STACK;

import LinkedLists.LinkedList;
import LinkedLists.Node;
import PACKAGE_NAME.ArrStack;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {
        final String[] timeStr = {"", ""};
        LinkedStack s = new LinkedStack();


        JFrame f = new JFrame("LinkedStack");
        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setVisible(true);
        f.setSize(700, 600);
        f.setLayout(null);


        JLabel l = new JLabel("Enter the item you want to push to your stack ");
        Dimension size = l.getPreferredSize();
        l.setBounds(50, 20, size.width, size.height);

        JLabel l1 = new JLabel("\n Note: (you can only push one element in the stack)");
        Dimension size1 = l1.getPreferredSize();
        l1.setBounds(50, 40, size1.width, size1.height);


        JTextField t1 = new JTextField();
        t1.setBounds(450, 20, 100, 25);
        t1.setVisible(true);

        JTextArea dis = new JTextArea();
        dis.setBounds(400, 75, 230, 185);

        JScrollPane sc = new JScrollPane(dis);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sc.setBounds(400, 75, 230, 185);
        sc.setVisible(true);

        JLabel lpushTime = new JLabel();
        lpushTime.setBounds(40, 400, 600, 30);

        JLabel lpopTime = new JLabel();
        lpopTime.setBounds(40, 450, 600, 30);


        JButton b1 = new JButton("Push");
        b1.setBounds(150, 270, 80, 30);
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               long startTime = System.nanoTime();
                if (t1.getText().length() > 1) {
                    JOptionPane.showMessageDialog(null, "You can't enter more than one item");
                    t1.setText("");
                } else {
                    s.push(t1.getText());

                    t1.setText("");
                }
                long endTime = System.nanoTime();
                timeStr[0] += (endTime - startTime) + "  ";
                lpushTime.setText("Adding time: " + timeStr[0]);
            }
        });

        JButton b2 = new JButton("Pop");
        b2.setBounds(300,270,80,30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = System.nanoTime();
                dis.append("\n The element removed is  "+ s.pop());
                if(s.isEmpty())
                    dis.append("\n there's no items left in your stack! ");
                long endTime = System.nanoTime();
                timeStr[1] += (endTime - startTime) + "  ";
                lpopTime.setText("Removing time : " + timeStr[1]);
            }

        });

        JButton b3 = new JButton("Peek");
        b3.setBounds(450,270,80,30);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(s.isEmpty())
                    dis.append("\n there's no items in your stack! ");
                else{
                    dis.setText("The item at the top of your stack is "+ s.peek());
                }
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

                dis.setText("");
                if(s.isEmpty())
                    dis.append("your stack is Empty!");
                else {
                    dis.append("the items in your stack are : \n");
                    LinkedList p = s.m.clone();
                    Node curr = p.head;
                    Node next = null;
                    Node prev = null;
                    while (curr != null) {
                        next = curr.link;
                        curr.link = prev;
                        prev = curr;
                        curr = next;
                    }
                    p.head = prev;

                    for(Node v =p.head;v != null; v = v.link){
                        dis.append(v.getData() +"\n");
                    }







                }









            }
        });

        /*JButton b6=new JButton("clone");
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
        });*/

        f.add(l);
        f.add(l1);
        f.add(t1);
        f.add(b1);
        f.add(sc);
        f.add(b2);
        f.add(b3);
        f.add(b4);
        f.add(b5);
        f.add(lpopTime);
        f.add(lpushTime);
        //f.add(b6);
    }
}
