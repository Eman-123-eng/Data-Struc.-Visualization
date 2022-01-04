package Arrays;

import Entry.StructureSelection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;


public class DisplayArray {
    static int i = 0, j = 0;

    public static void main(String[] args) {
        Array array = new Array();

        JFrame f = new JFrame("Array");

        JLabel l = new JLabel("Enter the item you want to push into your array:");
        l.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        JTextField t1 = new JTextField();
        t1.setBounds(450, 20, 100, 25);

        Graphic graphic = new Graphic();

        JScrollPane grSp = new JScrollPane(graphic);
        grSp.setBounds(60, 70, 675, 45);
        grSp.setBorder(createEmptyBorder());
        /*grSp.setBackground(new Color(238, 238, 238));
        grSp.setForeground(new Color(238, 238, 238));*/
        grSp.setVisible(false);


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
              //  try {
                    if (t1.getText().length() == 0)
                        JOptionPane.showMessageDialog(null, "You must enter the item to be added");
                    else {
                        int num = Integer.parseInt(t1.getText());
                        long startTime = System.nanoTime();
                        array.add(array.size(), num);
                        long endTime = System.nanoTime();
                        double elapsedTime = (double) (endTime - startTime) / 1000;

                        if (array.size() == 1) {
                           grSp.setVisible(true);
                        }
                        graphic.addRectangle(Graphic.num, 0, 45, 41, t1.getText());

                        tModelAdd.insertRow(i++, new String[]{t1.getText(), String.valueOf(elapsedTime)});
                        spAdd.setVisible(true);
                       // grSp.setSize((Graphic.num), 45);
                        //f.add(grSp);

                        t1.setText("");
                    }
              //  }
                    /* catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Only integers can be entered");
                }*/
            }
        });

        JButton bRem = new JButton("Remove");
        bRem.setBounds(300, 370, 90, 30);
        bRem.setForeground(new Color(42, 44, 43));
        bRem.setBorder(new RoundedBorder(10));

        bRem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (array.isEmpty()) JOptionPane.showMessageDialog(null, "The array is empty");
                else {
                    long startTime = System.nanoTime();
                    array.remove((array.size()) - 1);
                    long endTime = System.nanoTime();
                    double elapsedTime = (double) (endTime - startTime) / 1000;

                    graphic.removeRec();
                    if (array.isEmpty()) {
                       grSp.setVisible(false);
                    }

                    tModelRemove.insertRow(j++, new String[]{(String) tModelAdd.getValueAt(--i, 0), String.valueOf(elapsedTime)});
                    spRemove.setVisible(true);
                    tModelAdd.removeRow((i));
                }
            }
        });

        JButton b3 = new JButton("Get First");
        b3.setBounds(450, 370, 90, 30);
        b3.setForeground(new Color(42, 44, 43));
        b3.setBorder(new RoundedBorder(10));
        b3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (array.isEmpty())
                    JOptionPane.showMessageDialog(null, "The array is empty");
                else {
                    JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the array is: "), new JLabel(String.valueOf(array.getFirst()))});
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
                    i = j =0;
                    graphic.clear();
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

        f.add(spAdd);
        f.add(spRemove);
        f.add(l);
        f.add(t1);
        f.add(bAdd);
        f.add(bRem);
        f.add(b3);
        f.add(bBack);
        f.add(grSp);
        //f.getContentPane().add(drawingArea);

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

class Graphic extends JPanel {
    static int  num = 0;
    static String val;
    private static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private static ArrayList<String> values = new ArrayList<String>();
    private Rectangle shape;

    public Graphic() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        /*for (Rectangle cr : rectangles) {
            g.drawRect(cr.x, cr.y, cr.width, cr.height);
            g.drawString(val, (cr.x) + 15, 25);
            System.out.println(cr.x);
        }*/
        for (int i = 0; i < rectangles.size(); i++) {
            g.drawRect((rectangles.get(i)).x, (rectangles.get(i)).y, (rectangles.get(i)).width, (rectangles.get(i)).height);
            g.drawString(values.get(i), ((rectangles.get(i)).x) + 15, 25);
            //System.out.println((rectangles.get(i).x) + " " + (rectangles.get(i).y)+" "+(rectangles.get(i).width));
        }
    }

    public void addRectangle(int x, int y, int w, int h, String val) {
        shape = new Rectangle(x, y, w, h);
        rectangles.add(shape);
        values.add(val);
        Graphic.num += 45;
        repaint();
    }

    public void removeRec() {
        rectangles.remove(rectangles.size() - 1);
        values.remove(values.size() - 1);
        Graphic.num -= 45;
        repaint();
    }
    public void clear(){
        rectangles.clear();
        values.clear();
        num = 0;
        repaint();
    }
   /* @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.setBackground(Color.WHITE);

        g.setColor(Color.BLUE);
        for (; j != n && it < num; j++, it += 50) {
            //if(it!= num) {

            //  }
        }
        for (int i = 0; i < 200; i += 50) {
            g.drawRect(i, 0, 50, 45);
            g.setColor(Color.red);
            g.drawString(("5"), (i + 15), 25);
        }
        //gr.setBounds(60, 70, 200, 45);
    }*/

}

