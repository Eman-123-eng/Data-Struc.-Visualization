package Queues;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DisplayArrayQueue {
    static String s = "The elapsed time:";
    static String[][] data = {{"it", "t"}, {"it2", "t2"}};
    static String[] header = {"Item", "Time (ms)"};
    static int i = 0, j = 0;

    public static void main(String[] args) {
        ArrayBasedQueue<Integer> arrayQueue = new ArrayBasedQueue<Integer>();

        JFrame f = new JFrame("Array Queue");

        JLabel l = new JLabel("Enter the item you want to push to your queue ");
        Dimension size = l.getPreferredSize();
        l.setBounds(50, 20, size.width, size.height);

        JTextField t1 = new JTextField();
        t1.setBounds(450, 20, 100, 25);
        t1.setVisible(true);

        JLabel lTime = new JLabel();
        lTime.setBounds(50, 100, 300, 30);

        DefaultTableModel tModelAdd = new DefaultTableModel();
        //JTable jt = new JTable(data, header);
        tModelAdd.addColumn("Added Item");
        tModelAdd.addColumn("Time (ms)");
        JTable jtAdd = new JTable(tModelAdd);
        jtAdd.setEnabled(false);
        jtAdd.getTableHeader().setReorderingAllowed(false);

        JScrollPane spAdd = new JScrollPane(jtAdd);
        spAdd.setBounds(30, 100, 200, 150);
        spAdd.setVisible(false);

        DefaultTableModel tModelRemove = new DefaultTableModel();
        tModelRemove.addColumn("Removed Item");
        tModelRemove.addColumn("Time (ms)");
        JTable jtRemove = new JTable(tModelRemove);
        jtRemove.setEnabled(false);
        jtRemove.getTableHeader().setReorderingAllowed(false);

        JScrollPane spRemove = new JScrollPane(jtRemove);
        spRemove.setBounds(300, 100, 200, 150);
        spRemove.setVisible(false);

        /*JTextArea dis = new JTextArea();
        dis.setEditable(false);
        dis.setBounds(350, 75, 300, 185);

        JScrollPane sc = new JScrollPane(dis);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sc.setBounds(350, 75, 300, 185);
        sc.setVisible(true);*/


        JButton b1 = new JButton("Enqueue");
        b1.setBounds(150, 270, 90, 30);

        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1.getText().length() == 0)
                    JOptionPane.showMessageDialog(null, "You must write the item to be added");
                else {
                    int num = Integer.parseInt(t1.getText());
                    long startTime = System.nanoTime();
                    arrayQueue.add(num);
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    //System.out.println((endTime - startTime));
                    //s += "- " + elapsedTime + " ";
                    //lTime.setText(s);
                    tModelAdd.insertRow(i++, new String[]{t1.getText(), String.valueOf(elapsedTime)});
                    spAdd.setVisible(true);
                    t1.setText("");
                }
            }
        });

        JButton b2 = new JButton("Dequeue");
        b2.setBounds(300, 270, 90, 30);
        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arrayQueue.isEmpty()) JOptionPane.showMessageDialog(null, "The queue is empty");
                else {
                    long startTime = System.nanoTime();
                    arrayQueue.remove();
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    spRemove.setVisible(true);
                    tModelRemove.insertRow(j++, new String[]{(String) tModelAdd.getValueAt(0, 0), String.valueOf(elapsedTime)});
                    tModelAdd.removeRow((0));
                }
            }
        });

        JButton b3 = new JButton("Get Front");
        b3.setBounds(450, 270, 90, 30);
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arrayQueue.isEmpty())
                    JOptionPane.showMessageDialog(null, "The queue is empty");
                else {
                    JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the queue is: "), new JLabel(arrayQueue.getFront().toString())});
                }
            }
        });

        /*JButton b4 = new JButton("Size");
        b4.setBounds(150,350,80,30);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dis.setText("The size of your stack is "+ s.size());
            }
        });*/

        JButton b5 = new JButton("Display");
        b5.setBounds(300, 350, 90, 30);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                /*dis.setText("");
                dis.append("The items in your Queue are : \n");
                dis.append(arrayQueue.displayQueue());
                dis.append("\n");
                dis.append("\n The elapsed time is:  ");
                *//*for (int i = 0; i < s.timecal.length; i++) {
                    if (s.timecal[i] != null) {
                        dis.append(s.timecal[i]);
                        dis.append("-->");
                    }
                }*//*
                dis.append("\n");*/
            }
        });

        JButton b6 = new JButton("Clone");
        b6.setBounds(450, 350, 80, 30);
        b6.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arrayQueue.isEmpty())
                    JOptionPane.showMessageDialog(null, "Your Stack is empty!");
                else {
                    ArrayBasedQueue<Integer> c = arrayQueue.clone();
                    //dis.append("\n your queue has been copied successfuly! \n");
                    //dis.append("The elapsed time is : " + c.time + "\n");
                }
            }
        });

        f.add(spAdd);
        f.add(spRemove);
        f.add(l);
        f.add(t1);
        f.add(b1);
        //f.add(sc);
        f.add(b2);
        f.add(b3);
        //f.add(b4);
        f.add(b5);
        f.add(b6);
        f.add(lTime);


        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(650, 500);
        f.setLayout(null);
        f.setLocation(400, 200);
        f.setVisible(true);
    }
}
