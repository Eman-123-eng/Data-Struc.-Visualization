package LINKED_STACK;

import Entry.StructureSelection;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;

public class Main {

    static String displayStr = "";
    static int i = 0, j = 0;

    public static void main(String[] args) {
        final String[] timeStr = {"", ""};
        LinkedStack s = new LinkedStack();


        JFrame f = new JFrame("Linked Stack");


        JLabel l = new JLabel("Enter the item you want to push into your stack: ");
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JLabel lDisplay = new JLabel();
        lDisplay.setBounds(70, 70, 350, 20);

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


        JButton b1 = new JButton("Push");
        b1.setBounds(150, 370, 90, 30);
        b1.setForeground(new Color(42, 44, 43));
        b1.setBorder(new RoundedBorder(10));
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (t1.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter the item to be added");
                } else {
                    int num = Integer.parseInt(t1.getText());
                    long startTime = System.nanoTime();
                    s.push(num);
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;

                    tModelAdd.insertRow(i, new String[]{t1.getText(), String.valueOf(elapsedTime)});
                    spAdd.setVisible(true);
                    t1.setText("");
                }
            }
        });

        JButton b2 = new JButton("Pop");
        b2.setBounds(300, 370, 90, 30);
        b2.setForeground(new Color(42, 44, 43));
        b2.setBorder(new RoundedBorder(10));

        b2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (s.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                else {
                    long startTime = System.nanoTime();
                    s.pop();
                    long endTime = System.nanoTime();

                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    tModelRemove.insertRow(j, new String[]{(String) tModelAdd.getValueAt(0, 0), String.valueOf(elapsedTime)});
                    spRemove.setVisible(true);
                    tModelAdd.removeRow((0));
                }
            }
        });

        JButton b3 = new JButton("Peek");
        b3.setBounds(450, 370, 90, 30);
        b3.setForeground(new Color(42, 44, 43));
        b3.setBorder(new RoundedBorder(10));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (s.isEmpty())
                    JOptionPane.showMessageDialog(null, "The stack is empty");
                else {
                    JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the stack is: "), new JLabel(s.peek().toString())});
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
                if (a == 0) {
                    StructureSelection.main(new String[0]);
                    f.setVisible(false);
                }
            }
        });

        /*JButton b4 = new JButton("Size");
        b4.setBounds(150, 350, 80, 30);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dis.setText("The size of your stack is " + s.size());
            }
        });*/

        /*JButton b5 = new JButton("Display");
        b5.setBounds(300, 350, 80, 30);
        b5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                dis.setText("");
                if (s.isEmpty())
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

                    for (Node v = p.head; v != null; v = v.link) {
                        dis.append(v.getData() + "\n");
                    }


                }


            }
        });*/

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
        f.add(t1);
        f.add(b1);
        f.add(b2);
        f.add(b3);
        f.add(spAdd);
        f.add(spRemove);
        f.add(lDisplay);
        f.add(bBack);
        //f.add(b6);


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
