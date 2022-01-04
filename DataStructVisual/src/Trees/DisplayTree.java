package Trees;

import Entry.StructureSelection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class DisplayArrayQueue {
    static JFrame f;

    static String displayStr = "";
    static int i = 0, j = 0;

    public static void main(String[] args) {
        IntTree tree = new IntTree();

        f = new JFrame("Binary Tree");

        JLabel l = new JLabel("Enter the item you want to push into your tree:");
        l.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JLabel lDisplay = new JLabel();
        lDisplay.setFont(new Font("Monospaced", Font.PLAIN, 13));
        lDisplay.setBounds(70, 70, 500, 20);

        JTextField t1 = new JTextField();
        t1.setBounds(450, 20, 100, 25);

        DefaultTableModel tModelAdd = new DefaultTableModel();
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

        JButton bAdd = new JButton("Add");
        bAdd.setBounds(150, 370, 90, 30);
        bAdd.setForeground(new Color(42, 44, 43));
        bAdd.setBorder(new RoundedBorder(10));

        bAdd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1.getText().length() == 0)
                    JOptionPane.showMessageDialog(null, "You must enter the item to be added");
                else {
                    int num = Integer.parseInt(t1.getText());
                    long startTime = System.nanoTime();
                    tree.add(num);
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;

                   // displayStr = arrayQueue.displayQueue();
                    //lDisplay.setText(displayStr);
                    tModelAdd.insertRow(i++, new String[]{t1.getText(), String.valueOf(elapsedTime)});
                    spAdd.setVisible(true);

                    t1.setText("");
                }
            }
        });

        JButton bRem = new JButton("Remove");
        bRem.setBounds(300, 370, 90, 30);
        bRem.setForeground(new Color(42, 44, 43));
        bRem.setBorder(new RoundedBorder(10));
        bRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (tree.isEmpty()) JOptionPane.showMessageDialog(null, "The tree is empty");
                else {
                    long startTime = System.nanoTime();
                    //tree.remove();  //here we need to pass a target
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    //displayStr = arrayQueue.displayQueue();
                    //lDisplay.setText(displayStr);
                    if(tree.isEmpty())
                        lDisplay.setText("");
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
                if (tree.isEmpty())
                    JOptionPane.showMessageDialog(null, "The tree is empty");
                else {
                    JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the tree is: "), new JLabel(String.valueOf(tree.getRoot()))});
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
                System.out.println(a);
            }
        });


        f.add(spAdd);
        f.add(spRemove);
        f.add(l);
        f.add(lDisplay);
        f.add(t1);
        f.add(bAdd);
        f.add(bRem);
        f.add(b3);
        f.add(bBack);


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
