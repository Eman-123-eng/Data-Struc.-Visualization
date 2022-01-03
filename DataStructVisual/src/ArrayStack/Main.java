package ArrayStack;


import Entry.StructureSelection;
import Queues.DisplayArrayQueue;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JPanel {
    static String s = "The elapsed time:";
    static String displayStr = "";
    static int i = 0, j = 0;

    public static void main(String[] args) {
        final String[] timeStrg = {"", ""};
        ArrStack s = new ArrStack();

        JFrame f = new JFrame("Array Stack");

        JLabel l = new JLabel("Enter the item you want to push into your stack ");
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JLabel lDisplay = new JLabel();
        lDisplay.setBounds(70, 70, 350, 20);

        /*JLabel l1 = new JLabel("\n Note: (you can only push one element in the stack)");
        Dimension size1 = l1.getPreferredSize();
        l1.setBounds(50,40,size1.width,size1.height);*/

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

        /*JTextArea dis = new JTextArea();
        dis.enableInputMethods(false);
        dis.setBounds(350,75,300,185);*/

        /*JScrollPane sc = new JScrollPane(dis);
        sc.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        sc.setBounds(350,75,300,185);
        sc.setVisible(true);*/

        /*JLabel lpushTime = new JLabel();
        lpushTime.setBounds(40, 400, 600, 30);

        JLabel lpopTime = new JLabel();
        lpopTime.setBounds(40, 450, 600, 30);*/

        DefaultTableModel tModelRemove = new DefaultTableModel();
        tModelRemove.addColumn("Removed Item");
        tModelRemove.addColumn("Time (ms)");
        JTable jtRemove = new JTable(tModelRemove);
        jtRemove.setEnabled(false);
        jtRemove.getTableHeader().setReorderingAllowed(false);
        JScrollPane spRemove = new JScrollPane(jtRemove);
        spRemove.setBounds(400, 130, 200, 200);
        spRemove.setVisible(false);


        MyCanvas mPaint = new MyCanvas();
        f.add(mPaint);

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
                    //displayStr = ArrayStack.displayArrStack();
                    Object[] data = s.data();

                    // lDisplay.setText(displayStr);
                    tModelAdd.insertRow(i, new String[]{t1.getText(), String.valueOf(elapsedTime)});
                    spAdd.setVisible(true);
                    t1.setText("");
                    // timeStrg[0] += (endTime - startTime) + "  ";
                    // lpushTime.setText("Adding time: " + timeStrg[0]);
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
                    //  if(s.manyItems == 0)
                    //  dis.append("\n there's no items left in your stack! ");
                    double elapsedTime = (double) (endTime - startTime) / 1000;
                    tModelRemove.insertRow(j, new String[]{(String) tModelAdd.getValueAt(0, 0), String.valueOf(elapsedTime)});
                    spRemove.setVisible(true);
                    tModelAdd.removeRow((0));
                    //timeStrg[1] += (endTime - startTime) + "  ";
                    //lpopTime.setText("Removing time : " + timeStrg[1]);
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
                // dis.setText("The item at the top of your stack is "+ s.peek());
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

        JButton b4 = new JButton("Size");
        b4.setBounds(150, 350, 80, 30);
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //  dis.setText("The size of your stack is "+ s.size());
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
                }
            }
        });*/


        f.add(l);
        //  f.add(l1);
        f.add(t1);
        f.add(b1);
        //  f.add(sc);
        f.add(b2);
        f.add(b3);
        // f.add(b4);
        f.add(spAdd);
        f.add(spRemove);
        f.add(lDisplay);
        f.add(bBack);
        // f.add(b5);
        // f.add(b6);
        // f.add(lpopTime);
        // f.add(lpushTime);


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


    //not Done
    static class MyCanvas extends JPanel {
        String ss = "message";
        int x = 45;
        int y = 45;

        @Override
        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawRect(50, 100, 200, 200);
            g.setColor(Color.red);
            g.drawString(s, x, y);
        }
    }
    /*public void paint(Graphics g) {
        g.setFont(new Font("",0,100));
        FontMetrics fm = getFontMetrics(new Font("",0,100));
        String s = "message";
        int x = 5;
        int y = 5;

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int h = fm.getHeight();
            int w = fm.charWidth(c);

            g.drawRect(x, y, w, h);
            g.drawString(String.valueOf(c), x, y + h);
            x = x + w;
        }
    }*/
}
