package Trees;

import Entry.StructureSelection;

import java.util.TreeSet;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class DisplayTree {
    static JFrame f;

    static String displayStr = "";
    static int i = 0, j = 0;

    public static void main(String[] args) {
        IntTree tree = new IntTree();

        final int[] flag = {0};
        final int[] flagStr = {0};
        final int[] count = new int[1];
        final int[] input = new int[1];

        f = new JFrame("Binary Tree");

        JLabel l = new JLabel("Enter the item you want to push into your tree:");
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


        DefaultTableModel tModelAdd = new DefaultTableModel();
        tModelAdd.addColumn("Added Item");
        tModelAdd.addColumn("No. of Items");
        tModelAdd.addColumn("Time (ms)");
        JTable jtAdd = new JTable(tModelAdd);
        jtAdd.setEnabled(false);
        jtAdd.getTableHeader().setReorderingAllowed(false);

        JScrollPane spAdd = new JScrollPane(jtAdd);
        spAdd.setBounds(100, 130, 200, 200);
        spAdd.setVisible(false);


        DefaultTableModel tModelRemove = new DefaultTableModel();
        tModelRemove.addColumn("Removed Item");
        tModelRemove.addColumn("No. of Items");
        tModelRemove.addColumn("Time (ms)");
        JTable jtRemove = new JTable(tModelRemove);
        jtRemove.setEnabled(false);
        jtRemove.getTableHeader().setReorderingAllowed(false);

        JScrollPane spRemove = new JScrollPane(jtRemove);
        spRemove.setBounds(400, 130, 200, 200);
        spRemove.setVisible(false);

        JButton bAdd = new JButton("Add");
        bAdd.setBounds(150, 370, 90, 30);
        bAdd.setForeground(new Color(42, 44, 43));
        bAdd.setBorder(new RoundedBorder(10));


        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inpText = t1.getText();
                input[0] = Integer.parseInt(inpText);

                long startTime = 0;
                long endTime = 0;

                input[0] = Integer.parseInt(inpText);
                count[0] = Integer.parseInt(tCount.getText());
                if (inpText.length() == 0 && tCount.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter the item and the amount to be added");
                    return;
                }
                try { //number added
                    tree.clearTree();
                    if (flagStr[0] != 1) flag[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same list");
                        return;
                    }
                    //System.out.println(input);
                    System.out.println(tree.size());
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        tree.add(input[0]);
                    }

                    endTime = System.nanoTime();

                    // lDisplay.setText("Linked list data: " + LinkedList.display(list));
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "cannot enter strings in tree");

                }
                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);
                tModelAdd.insertRow(i++, new String[]{inpText, tCount.getText(), String.valueOf(elapsedTime)});
                spAdd.setVisible(true);
            }
        });

        JButton bRem = new JButton("Remove");
        bRem.setBounds(300, 370, 90, 30);
        bRem.setForeground(new Color(42, 44, 43));
        bRem.setBorder(new RoundedBorder(10));
        bRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inpText = t1.getText();
                input[0] = Integer.parseInt(inpText);
                long startTime = 0;
                long endTime = 0;
                if (t1.getText() == "")
                    JOptionPane.showMessageDialog(null, "you have to enter the l");

                count[0] = Integer.parseInt(tCount.getText());

                if (tree.root == null) {
                    JOptionPane.showMessageDialog(null, "The Tree is empty");
                    return;
                }
                startTime = System.nanoTime();
                for (int i = 1; i <= count[0]; i++) {
                    tree.remove(input[0]);
                }
                tree.root = null;
                endTime = System.nanoTime();
                System.out.println("size" + tree.size());

                //lDisplay.setText("Linked list data: " + LinkedList.display(list));
                if (tree.size() == 0) {
                    flag[0] = 0;
                    lDisplay.setText("");
                }

            double elapsedTime = (double) (endTime - startTime) * 1.0E-6;
                tModelRemove.insertRow(j++,new String[]{
                (String) tModelAdd.getValueAt((--i), 0), (String) tModelAdd.getValueAt((i), 1), String.valueOf(elapsedTime)
            });
            //tModelAdd.removeRow((i));
                spRemove.setVisible(true);
            i++;
        }
    });


    JButton b3 = new JButton("Get Front");
        b3.setBounds(450,370,90,30);
        b3.setForeground(new

    Color(42,44,43));
        b3.setBorder(new

    RoundedBorder(10));
        b3.addActionListener(new

    ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            if (tree.isEmpty())
                JOptionPane.showMessageDialog(null, "The tree is empty");
            else {
                JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the tree is: "), new JLabel(String.valueOf(tree.getRoot()))});
            }
        }
    });

    JButton bBack = new JButton("Back");
        bBack.setBounds(550,450,90,30);
        bBack.setForeground(new

    Color(42,44,43));
        bBack.setBorder(new

    RoundedBorder(10));

        bBack.addActionListener(new

    ActionListener() {
        @Override
        public void actionPerformed (ActionEvent e){
            int a = JOptionPane.showConfirmDialog(null, "Are you sure to go back?\n **NOTE: your data will be lost**");
            if (a == 0) {
                StructureSelection.main(new String[0]);
                i = j = 0;
                f.setVisible(false);
            }
            System.out.println(a);
        }
    });


        f.add(spAdd);
        f.add(spRemove);
        f.add(l);
        f.add(lDisplay);
        f.add(t1);
        f.add(lCount);
        f.add(tCount);
        f.add(spAdd);
        f.add(spRemove);
        f.add(bAdd);
        f.add(bRem);
    //f.add(b3);
        f.add(bBack);

        f.addWindowListener(new

    WindowAdapter() {
        @Override
        public void windowClosing (WindowEvent e){
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure to exit?");
            System.out.println(choice);
            if (choice == 0) {
                System.exit(0);
            } else {
                f.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            }
        }
    });

        f.setSize(700,550);
        f.setLayout(null);
        f.setLocation(400,125);
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
