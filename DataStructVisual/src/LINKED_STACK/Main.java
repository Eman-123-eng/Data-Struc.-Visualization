package LINKED_STACK;

import Entry.StructureSelection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class Main {
    static int i = 0, j = 0;

    public static void main(String[] args) {
        LinkedStack<Integer> linkedStack = new LinkedStack<>();
        LinkedStack<String> strLinkedStack = new LinkedStack<>();
        final int[] flag = {0};
        final int[] flagStr = {0};
        final int[] count = new int[1];

        JFrame f = new JFrame("Linked Stack");

        JLabel l = new JLabel("Enter the item you want to push into your stack: ");
        l.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JTextField t1 = new JTextField();
        t1.setBounds(400, 20, 100, 25);

        JLabel lCount = new JLabel("Enter the amount: ");
        lCount.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lCoSize = lCount.getMaximumSize();
        lCount.setBounds(50, 50, (lCoSize.width + 10), lCoSize.height);

        JTextField tCount = new JTextField();
        tCount.setBounds(400, 50, 100, 25);

        DefaultTableModel tModelAdd = new DefaultTableModel();
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


        JButton b1 = new JButton("Push");
        b1.setBounds(180, 370, 90, 30);
        b1.setForeground(new Color(42, 44, 43));
        b1.setBorder(new RoundedBorder(10));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int num;
                long startTime = 0, endTime = 0;
                if (t1.getText().length() == 0 && tCount.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter the item and the amount to be added");
                    return;
                }
                count[0] = Integer.parseInt(tCount.getText());
                try {
                    linkedStack.clear();
                    num = Integer.parseInt(t1.getText());
                    if (flagStr[0] != 1) flag[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same stack");
                        //t1.setText("");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        linkedStack.push(num);
                    }
                    endTime = System.nanoTime();

                } catch (Exception ex) {
                    //String added
                    strLinkedStack.clear();

                    if (flag[0] != 1) flagStr[0] = 1;
                    /*else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same stack");
                        // t1.setText("");
                        return;
                    }*/
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        strLinkedStack.push(t1.getText());
                    }
                    endTime = System.nanoTime();
                    System.out.println(linkedStack.size());

                }
                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);

                //graphic.addRectangle(0, LINKED_STACK.Graphic.num, 50, 39, t1.getText());

                tModelAdd.insertRow(i, new String[]{ t1.getText(), tCount.getText(), String.valueOf(elapsedTime)});
                spAdd.setVisible(true);
                // t1.setText("");
            }
        });

        JButton b2 = new JButton("Pop");
        b2.setBounds(350, 370, 90, 30);
        b2.setForeground(new Color(42, 44, 43));
        b2.setBorder(new RoundedBorder(10));

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long startTime = 0, endTime = 0;
                count[0] = Integer.parseInt(tCount.getText());
                if (flag[0] == 1) { //numbers
                    if (linkedStack.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The stack is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        linkedStack.pop();
                    }
                    endTime = System.nanoTime();
                    //graphic.removeRec();
                    if (linkedStack.isEmpty()) {
                        flag[0] = 0;
                    }
                } else {
                    if (strLinkedStack.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The stack is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        strLinkedStack.pop();
                    }
                    endTime = System.nanoTime();

                    //graphic.removeRec();
                    if (strLinkedStack.isEmpty()) {
                        flagStr[0] = 0;
                    }
                }
                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);
                tModelRemove.insertRow(j, new String[]{(String) tModelAdd.getValueAt(0, 0), (String) tModelAdd.getValueAt(0, 1),String.valueOf(elapsedTime)});
                spRemove.setVisible(true);
                //tModelAdd.removeRow((0));
            }
        });

        JButton b3 = new JButton("Peek");
        b3.setBounds(180, 420, 90, 30);
        b3.setForeground(new Color(42, 44, 43));
        b3.setBorder(new RoundedBorder(10));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[0] == 1) { //numbers
                    if (linkedStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the stack is: "), new JLabel(linkedStack.peek().toString())});

                } else {
                    if (strLinkedStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the stack is: "), new JLabel(strLinkedStack.peek())});
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
                    if (linkedStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the stack is: "), new JLabel(String.valueOf(linkedStack.size()))});
                } else {
                    if (strLinkedStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the stack is: "), new JLabel(String.valueOf(strLinkedStack.size()))});
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
                if (a == 0) {
                    StructureSelection.main(new String[0]);
                    i = j = 0;
                    f.setVisible(false);
                }
            }
        });

        f.add(l);
        f.add(t1);
        f.add(lCount);
        f.add(tCount);
        f.add(b1);
        f.add(b2);
       // f.add(b3);
      // f.add(b4);
        f.add(spAdd);
        f.add(spRemove);
        f.add(bBack);
        //f.add(b6);


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

