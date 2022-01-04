package Queues;

import Entry.StructureSelection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class DisplayArrayQueue {
    static JFrame f;

    /*static String[][] data = {{"it", "t"}, {"it2", "t2"}};
    static String[] header = {"Item", "Time (ms)"};*/
    static String displayStr = "";
    static int i = 0, j = 0;

    public static void main(String[] args) {
        ArrayBasedQueue<Integer> arrayQueue = new ArrayBasedQueue<>(20);
        ArrayBasedQueue<String> strArrayQueue = new ArrayBasedQueue<>(20);
        final int[] flag = {0};
        final int[] flagStr = {0};
        final int[] count = new int[1];

        f = new JFrame("Array Queue");

        JLabel l = new JLabel("Enter the item you want to add into your queue:");
        l.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JLabel lDisplay = new JLabel();
        lDisplay.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lDisplay.setBounds(70, 70, 500, 20);

        JTextField t1 = new JTextField();
        t1.setBounds(400, 20, 100, 25);

        JLabel lCount = new JLabel("Enter the amount: ");
        lCount.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lCoSize = lCount.getMaximumSize();
        lCount.setBounds(50, 50, (lCoSize.width + 10), lCoSize.height);

        JTextField tCount = new JTextField();
        tCount.setBounds(400, 50, 100, 25);

        /*JLabel lTime = new JLabel();
        lTime.setBounds(50, 100, 300, 30);*/

        DefaultTableModel tModelAdd = new DefaultTableModel();
        //JTable jt = new JTable(data, header);
        tModelAdd.addColumn("Added Item");
        tModelAdd.addColumn("No. of Items");
        tModelAdd.addColumn("Time (ms)");
        JTable jtAdd = new JTable(tModelAdd);
        jtAdd.setEnabled(false);
        jtAdd.getTableHeader().setReorderingAllowed(false);

        JScrollPane spAdd = new JScrollPane(jtAdd);
        spAdd.setBounds(80, 130, 230, 200);
        spAdd.setVisible(false);


        DefaultTableModel tModelRemove = new DefaultTableModel();
        tModelRemove.addColumn("Removed Item");
        tModelRemove.addColumn("No. of Items");
        tModelRemove.addColumn("Time (ms)");
        JTable jtRemove = new JTable(tModelRemove);
        jtRemove.setEnabled(false);
        jtRemove.getTableHeader().setReorderingAllowed(false);

        JScrollPane spRemove = new JScrollPane(jtRemove);
        spRemove.setBounds(380, 130, 245, 200);
        spRemove.setVisible(false);

        /*JTextArea dis = new JTextArea();
        dis.setEditable(false);
        dis.setBounds(350, 75, 300, 185);

        JScrollPane sc = new JScrollPane(dis);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sc.setBounds(350, 75, 300, 185);
        sc.setVisible(true);*/

        JButton bEnq = new JButton("Enqueue");
        bEnq.setBounds(200, 370, 90, 30);
        bEnq.setForeground(new Color(42, 44, 43));
        bEnq.setBorder(new RoundedBorder(10));

        bEnq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num;
                long startTime = 0, endTime = 0;
                count[0] = Integer.parseInt(tCount.getText());
                if (t1.getText().length() == 0 && tCount.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter the item and the amount to be added");
                    return;
                }
                try {  //number is added
                    arrayQueue.clear();
                    num = Integer.parseInt(t1.getText());
                    if (flagStr[0] != 1) flag[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same queue");
                        //t1.setText("");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        arrayQueue.add(num);
                    }
                    endTime = System.nanoTime();
                    //displayStr = arrayQueue.displayQueue();
                    //lDisplay.setText(displayStr);

                } catch (Exception ex) {
                    //String added
                    strArrayQueue.clear();
                    if (flag[0] != 1)
                        flagStr[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same queue");
                        //t1.setText("");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        strArrayQueue.add(t1.getText());
                    }
                    endTime = System.nanoTime();

                    // displayStr = strArrayQueue.displayQueue();
                    //  lDisplay.setText(displayStr);
                }

                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);

                tModelAdd.insertRow(i++, new String[]{t1.getText(), tCount.getText(), String.valueOf(elapsedTime)});
                spAdd.setVisible(true);
                //t1.setText("");
            }
        });

        JButton bDeq = new JButton("Dequeue");
        bDeq.setBounds(350, 370, 90, 30);
        bDeq.setForeground(new Color(42, 44, 43));
        bDeq.setBorder(new RoundedBorder(10));
        bDeq.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = 0, endTime = 0;
                count[0] = Integer.parseInt(tCount.getText());
                if (flag[0] == 1) { //numbers
                    if (arrayQueue.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The queue is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        arrayQueue.remove();
                    }
                    endTime = System.nanoTime();
                    //displayStr = arrayQueue.displayQueue();
                    // lDisplay.setText(displayStr);

                    if (arrayQueue.isEmpty())
                        lDisplay.setText("");
                } else {
                    if (strArrayQueue.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The queue is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        strArrayQueue.remove();
                    }
                    endTime = System.nanoTime();
                    //displayStr = strArrayQueue.displayQueue();
                    // lDisplay.setText(displayStr);

                    if (strArrayQueue.isEmpty())
                        lDisplay.setText("");
                }
                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);

                tModelRemove.insertRow(j++, new String[]{(String) tModelAdd.getValueAt(--i, 0), (String) tModelAdd.getValueAt(i, 1), String.valueOf(elapsedTime)});
                spRemove.setVisible(true);
                i++;
                //tModelAdd.removeRow((0));
            }
        });

        JButton b3 = new JButton("Get Front");
        b3.setBounds(200, 420, 90, 30);
        b3.setForeground(new Color(42, 44, 43));
        b3.setBorder(new RoundedBorder(10));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[0] == 1) { //numbers
                    if (arrayQueue.isEmpty()) JOptionPane.showMessageDialog(null, "The queue is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the queue is: "), new JLabel(arrayQueue.getFront().toString())});

                } else {
                    if (strArrayQueue.isEmpty()) JOptionPane.showMessageDialog(null, "The queue is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the queue is: "), new JLabel(strArrayQueue.getFront())});
                }
            }
        });

        JButton b4 = new JButton("Size");
        b4.setBounds(350, 420, 90, 30);
        b4.setForeground(new Color(42, 44, 43));
        b4.setBorder(new RoundedBorder(10));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[0] == 1) { //numbers
                    if (arrayQueue.isEmpty()) JOptionPane.showMessageDialog(null, "The queue is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the queue is: "), new JLabel(String.valueOf(arrayQueue.size()))});
                } else {
                    if (strArrayQueue.isEmpty()) JOptionPane.showMessageDialog(null, "The queue is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the queue is: "), new JLabel(String.valueOf(arrayQueue.size()))});
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
                int a = JOptionPane.showConfirmDialog(null, "Are you sure to go back?\n **NOTE: your data will be lost**");
                if (a == 0) { //yes will go back
                    StructureSelection.main(new String[0]);
                    i = j = 0;
                    f.setVisible(false);
                }
                System.out.println(a);
            }
        });



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


        f.add(spAdd);
        f.add(spRemove);
        f.add(l);
        f.add(lDisplay);
        f.add(t1);
        f.add(lCount);
        f.add(tCount);
        f.add(bEnq);
        //f.add(sc);
        f.add(bDeq);
        //  f.add(b3);
        //  f.add(b4);
        f.add(bBack);
        // f.add(b5);
        //f.add(b6);
        // f.add(lTime);


        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure to exit?");
                System.out.println(choice);
                if (choice == 0) {
                    System.exit(0);
                } else {
                    f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
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
