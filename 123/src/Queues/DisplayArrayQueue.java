package Queues;

import Entry.StructureSelection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DisplayArrayQueue {
    static JFrame f;

    /*static String[][] data = {{"it", "t"}, {"it2", "t2"}};
    static String[] header = {"Item", "Time (ms)"};*/
    static String displayStr = "";
    static int i = 0, j = 0;

    public static void main(String[] args) {
        ArrayBasedQueue<Integer> arrayQueue = new ArrayBasedQueue<Integer>(20);

        f = new JFrame("Array Queue");

        JLabel l = new JLabel("Enter the item you want to push into your queue:");
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JLabel lDisplay = new JLabel();
        lDisplay.setBounds(70, 70, 350, 20);

        JTextField t1 = new JTextField();
        t1.setBounds(450, 20, 100, 25);

        /*JLabel lTime = new JLabel();
        lTime.setBounds(50, 100, 300, 30);*/

        DefaultTableModel tModelAdd = new DefaultTableModel();
        //JTable jt = new JTable(data, header);
        tModelAdd.addColumn("Added Item");
        tModelAdd.addColumn("Time (ms)");
        JTable jtAdd = new JTable(tModelAdd);
        jtAdd.setEnabled(false);
        jtAdd.getTableHeader().setReorderingAllowed(false);

        JScrollPane spAdd = new JScrollPane(jtAdd);
        spAdd.setBounds(100, 130, 200, 200);
        spAdd.setVisible(false);


        DefaultTableModel tModelRemove = new DefaultTableModel();
        tModelRemove.addColumn("Removed Item");
        tModelRemove.addColumn("Time (ms)");
        JTable jtRemove = new JTable(tModelRemove);
        jtRemove.setEnabled(false);
        jtRemove.getTableHeader().setReorderingAllowed(false);

        JScrollPane spRemove = new JScrollPane(jtRemove);
        spRemove.setBounds(400, 130, 200, 200);
        spRemove.setVisible(false);

        /*JTextArea dis = new JTextArea();
        dis.setEditable(false);
        dis.setBounds(350, 75, 300, 185);

        JScrollPane sc = new JScrollPane(dis);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sc.setBounds(350, 75, 300, 185);
        sc.setVisible(true);*/


        JButton bEnq = new JButton("Enqueue");
        bEnq.setBounds(150, 370, 90, 30);
        bEnq.setForeground(new Color(42, 44, 43));
        bEnq.setBorder(new RoundedBorder(10));

        bEnq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1.getText().length() == 0)
                    JOptionPane.showMessageDialog(null, "You must enter the item to be added");
                else {
                    int num = Integer.parseInt(t1.getText());
                    long startTime = System.nanoTime();
                    arrayQueue.add(num);
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    //System.out.println((endTime - startTime));
                    //s += "- " + elapsedTime + " ";
                    //lTime.setText(s);

                    displayStr = arrayQueue.displayQueue();
                    lDisplay.setText(displayStr);
                    tModelAdd.insertRow(i++, new String[]{t1.getText(), String.valueOf(elapsedTime)});
                    spAdd.setVisible(true);

                    t1.setText("");
                }
            }
        });

        JButton bDeq = new JButton("Dequeue");
        bDeq.setBounds(300, 370, 90, 30);
        bDeq.setForeground(new Color(42, 44, 43));
        bDeq.setBorder(new RoundedBorder(10));
        bDeq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (arrayQueue.isEmpty()) JOptionPane.showMessageDialog(null, "The queue is empty");
                else {
                    long startTime = System.nanoTime();
                    arrayQueue.remove();
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    displayStr = arrayQueue.displayQueue();
                    lDisplay.setText(displayStr);
                    tModelRemove.insertRow(j++, new String[]{(String) tModelAdd.getValueAt(0, 0), String.valueOf(elapsedTime)});
                    spRemove.setVisible(true);
                    tModelAdd.removeRow((0));
                }
            }
        });

        JButton b3 = new JButton("Get Front");
        b3.setBounds(450, 370, 90, 30);
        b3.setForeground(new Color(42, 44, 43));
        b3.setBorder(new RoundedBorder(10));
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

        JButton bBack = new JButton("Back");
        bBack.setBounds(550, 450, 90, 30);
        bBack.setForeground(new Color(42, 44, 43));
        bBack.setBorder(new RoundedBorder(10));

        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Are you sure?");
                if (a == 0) { //yes will go back
                    StructureSelection.main(new String[0]);
                    f.setVisible(false);
                }
                System.out.println(a);
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

        /*JButton b5 = new JButton("Display");
        b5.setBounds(300, 450, 90, 30);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                *//*dis.setText("");
                dis.append("The items in your Queue are : \n");
                dis.append(arrayQueue.displayQueue());
                dis.append("\n");
                dis.append("\n The elapsed time is:  ");
                *//**//*for (int i = 0; i < s.timecal.length; i++) {
                    if (s.timecal[i] != null) {
                        dis.append(s.timecal[i]);
                        dis.append("-->");
                    }
                }*//**//*
                dis.append("\n");*//*
            }
        });*/

        /*JButton b6 = new JButton("Clone");
        b6.setBounds(450, 450, 90, 30);
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
        });*/

        f.add(spAdd);
        f.add(spRemove);
        f.add(l);
        f.add(lDisplay);
        f.add(t1);
        f.add(bEnq);
        //f.add(sc);
        f.add(bDeq);
        f.add(b3);
        f.add(bBack);
        //f.add(b4);
        // f.add(b5);
        //f.add(b6);
        // f.add(lTime);


        f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        f.setSize(700, 550);
        f.setLayout(null);
        f.setLocation(400, 125);
        f.setVisible(true);
    }

    private static class RoundedBorder implements Border {
        private int radius;

        RoundedBorder(int radius) {
            this.radius = radius;
        }

        public Insets getBorderInsets(Component c) {
            return new Insets(this.radius + 1, this.radius + 1, this.radius + 2, this.radius);
        }

        public boolean isBorderOpaque() {
            return false;
        }

        public void paintBorder(Component c, Graphics g, int x, int y, int width, int height) {
            g.drawRoundRect(x, y, width - 1, height - 1, radius, radius);
        }
    }
}
