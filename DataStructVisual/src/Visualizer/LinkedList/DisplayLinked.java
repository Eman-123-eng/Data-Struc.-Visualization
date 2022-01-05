package Visualizer.LinkedList;

import Entry.StructureSelection;
import LinkedLists.LinkedList;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class DisplayLinked {
    static int i = 0, j = 0;

    public DisplayLinked() {
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        LinkedList<String> strList = new LinkedList<>();
        final int[] flag = {0};
        final int[] flagStr = {0};

        JFrame frame = new JFrame("Linked List");

        JLabel l1 = new JLabel("Enter the item to be added to your linked list:");
        l1.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lSize = l1.getMaximumSize();
        l1.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JLabel lDisplay = new JLabel();
        lDisplay.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lDisplay.setBounds(60, 70, 500, 20);

        JTextField textAdd = new JTextField();
        textAdd.setBounds(400, 20, 100, 25);

        DefaultTableModel tModelAdd = new DefaultTableModel();
        tModelAdd.addColumn("Added Item");
        tModelAdd.addColumn("Time (ms)");
        JTable jtAdd = new JTable(tModelAdd);
        jtAdd.setEnabled(false);
        jtAdd.getTableHeader().setReorderingAllowed(false);

        JScrollPane spAdd = new JScrollPane(jtAdd);
        spAdd.setBounds(80, 130, 230, 200);
        spAdd.setVisible(false);

        DefaultTableModel tModelRemove = new DefaultTableModel();
        tModelRemove.addColumn("Removed Item");
        tModelRemove.addColumn("Time (ms)");
        JTable jtRemove = new JTable(tModelRemove);
        jtRemove.setEnabled(false);
        jtRemove.getTableHeader().setReorderingAllowed(false);

        JScrollPane spRemove = new JScrollPane(jtRemove);
        spRemove.setBounds(380, 130, 245, 200);
        spRemove.setVisible(false);

        JButton bAdd = new JButton("Add");
        bAdd.setBounds(200, 370, 90, 30);
        bAdd.setForeground(new Color(42, 44, 43));
        bAdd.setBorder(new RoundedBorder(10));

        JButton bRemove = new JButton("Remove");
        bRemove.setBounds(350, 370, 90, 30);
        bRemove.setForeground(new Color(42, 44, 43));
        bRemove.setBorder(new RoundedBorder(10));

        JButton b3 = new JButton("Get Head");
        b3.setBounds(200, 420, 90, 30);
        b3.setForeground(new Color(42, 44, 43));
        b3.setBorder(new RoundedBorder(10));

        JButton b4 = new JButton("Size");
        b4.setBounds(350, 420, 90, 30);
        b4.setForeground(new Color(42, 44, 43));
        b4.setBorder(new RoundedBorder(10));

        JButton bBack = new JButton("Back");
        bBack.setBounds(550, 450, 90, 30);
        bBack.setForeground(new Color(42, 44, 43));
        bBack.setBorder(new RoundedBorder(10));

        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inpText = textAdd.getText();
                int input;
                long startTime = 0, endTime = 0;
                if (inpText.length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter the item to be added");
                    return;
                }
                try { //number added
                    input = Integer.parseInt(inpText);
                    if (flagStr[0] != 1) flag[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same list");
                        textAdd.setText("");
                        return;
                    }
                    //System.out.println(input);
                    startTime = System.nanoTime();
                    list.addLast(input);
                    endTime = System.nanoTime();

                    lDisplay.setText("Linked list data: " + LinkedList.display(list));
                } catch (Exception ex) {
                    //String added
                    if (flag[0] != 1)
                        flagStr[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same list");
                        textAdd.setText("");
                        return;
                    }
                    startTime = System.nanoTime();
                    strList.addLast(inpText);
                    endTime = System.nanoTime();
                    lDisplay.setText("Linked list data: " + LinkedList.display(strList));
                }
                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);
                tModelAdd.insertRow(i++, new String[]{inpText, String.valueOf(elapsedTime)});
                spAdd.setVisible(true);
                textAdd.setText("");
            }
        });

        bRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long startTime, endTime;
                if (flag[0] == 1) { //numbers
                    if (list.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The linked list is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    list.removeCurrent();
                    endTime = System.nanoTime();
                    lDisplay.setText("Linked list data: " + LinkedList.display(list));
                    if (list.size() == 0) {
                        flag[0] = 0;
                        lDisplay.setText("");
                    }
                } else {
                    if (strList.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The linked list is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    strList.removeCurrent();
                    endTime = System.nanoTime();
                    lDisplay.setText("Linked list data: " + LinkedList.display(strList));
                    if (strList.size() == 0) {
                        flagStr[0] = 0;
                    }
                }
                double elapsedTime = (double) (endTime - startTime) * 1.0E-6;
                tModelRemove.insertRow(j++, new String[]{(String) tModelAdd.getValueAt((--i), 0), String.valueOf(elapsedTime)});
                tModelAdd.removeRow((i));
                spRemove.setVisible(true);

            }
        });

        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[0] == 1) { //numbers
                    if (list.isEmpty()) JOptionPane.showMessageDialog(null, "The linked list is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the list is: "), new JLabel(list.getHead().toString())});

                } else {
                    if (strList.isEmpty()) JOptionPane.showMessageDialog(null, "The linked list is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the list is: "), new JLabel(strList.getHead())});
                }
            }
        });

        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[0] == 1) { //numbers
                    if (list.isEmpty()) JOptionPane.showMessageDialog(null, "The linked list is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the linked list is: "), new JLabel(String.valueOf(list.size()))});
                } else {
                    if (strList.isEmpty()) JOptionPane.showMessageDialog(null, "The linked list is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the linked list is: "), new JLabel(String.valueOf(strList.size()))});
                }
            }
        });

        bBack.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int a = JOptionPane.showConfirmDialog(null, "Are you sure to go back?\n **NOTE: your data will be lost**");
                if (a == 0) { //yes will go back
                    Visualizer.Entry.StructureSelection.main(new String[0]);
                    i = j = 0;
                    frame.setVisible(false);
                }
                System.out.println(a);
            }
        });

        frame.add(l1);
        frame.add(lDisplay);
        frame.add(textAdd);
        frame.add(bAdd);
        frame.add(bRemove);
        frame.add(spAdd);
        frame.add(spRemove);
        frame.add(b3);
        frame.add(b4);
        frame.add(bBack);

        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure to exit?");
                System.out.println(choice);
                if (choice == 0) {
                    System.exit(0);
                } else {
                    frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                }
            }
        });
        frame.setSize(700, 550);
        frame.setLayout(null);
        frame.setLocation(400, 125);
        frame.setVisible(true);
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
