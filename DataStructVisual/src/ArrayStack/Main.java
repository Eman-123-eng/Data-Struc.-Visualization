package ArrayStack;

import Entry.StructureSelection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class Main extends JPanel {
    static int i = 0, j = 0;

    public static void main(String[] args) {
        final String[] timeStrg = {"", ""};
        ArrStack s = new ArrStack();

        JFrame f = new JFrame("Array Stack");

        JLabel l = new JLabel("Enter the item you want to push into your stack ");
        l.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        /*JLabel l1 = new JLabel("\n Note: (you can only push one element in the stack)");
        Dimension size1 = l1.getPreferredSize();
        l1.setBounds(50,40,size1.width,size1.height);*/

        JTextField t1 = new JTextField();
        t1.setBounds(450, 20, 100, 25);

        Graphic graphic = new Graphic();

        JScrollPane grSp = new JScrollPane(graphic);
        grSp.setBounds(580, 60, 45, 360);
        grSp.setBorder(createEmptyBorder());
        grSp.setVisible(true);

        DefaultTableModel tModelAdd = new DefaultTableModel();
        tModelAdd.addColumn("Added Item");
        tModelAdd.addColumn("Time (ms)");
        JTable jtAdd = new JTable(tModelAdd);
        jtAdd.setEnabled(false);
        jtAdd.getTableHeader().setReorderingAllowed(false);

        JScrollPane spAdd = new JScrollPane(jtAdd);
        spAdd.setBounds(80, 130, 200, 200);
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
        spRemove.setBounds(350, 130, 200, 200);
        spRemove.setVisible(false);

        JButton b1 = new JButton("Push");
        b1.setBounds(130, 370, 90, 30);
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

                    if (s.size() == 1) {
                        grSp.setVisible(true);
                    }
                    graphic.addRectangle(0, ArrayStack.Graphic.num, 43, 39, t1.getText());

                    tModelAdd.insertRow(i, new String[]{t1.getText(), String.valueOf(elapsedTime)});
                    spAdd.setVisible(true);
                    t1.setText("");
                    // timeStrg[0] += (endTime - startTime) + "  ";
                    // lpushTime.setText("Adding time: " + timeStrg[0]);
                }
            }
        });

        JButton b2 = new JButton("Pop");
        b2.setBounds(280, 370, 90, 30);
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

                    graphic.removeRec();
                    if (s.isEmpty()) {
                        grSp.setVisible(false);
                    }

                    tModelRemove.insertRow(j, new String[]{(String) tModelAdd.getValueAt(0, 0), String.valueOf(elapsedTime)});
                    spRemove.setVisible(true);
                    tModelAdd.removeRow((0));
                    //timeStrg[1] += (endTime - startTime) + "  ";
                    //lpopTime.setText("Removing time : " + timeStrg[1]);
                }
            }
        });

        JButton b3 = new JButton("Peek");
        b3.setBounds(430, 370, 90, 30);
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
                int a = JOptionPane.showConfirmDialog(null, "Are you sure to go back?\n **NOTE: your data will be lost**");
                if (a == 0) {
                    StructureSelection.main(new String[0]);
                    i = j =0;
                    graphic.clear();
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
        f.add(bBack);
        f.add(grSp);
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

}


class Graphic extends JPanel {
    static int  num = 320;

    private static ArrayList<Rectangle> rectangles = new ArrayList<Rectangle>();
    private static ArrayList<String> values = new ArrayList<String>();
    private Rectangle shape;

    public Graphic() {
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < rectangles.size(); i++) {
            g.drawRect((rectangles.get(i).x), (rectangles.get(i).y), (rectangles.get(i)).width, (rectangles.get(i)).height);
            g.drawString(values.get(i), 15, ((rectangles.get(i)).y) + 25 );
            //System.out.println((rectangles.get(i).x) + " " + (rectangles.get(i).y)+" "+(rectangles.get(i).width));
        }
    }

    public void addRectangle(int x, int y, int w, int h, String val) {
        shape = new Rectangle(x, y, w, h);
        rectangles.add(shape);
        values.add(val);
        ArrayStack.Graphic.num -= 40;
        repaint();
    }

    public void removeRec() {
        rectangles.remove(rectangles.size() - 1);
        values.remove(values.size() - 1);
        ArrayStack.Graphic.num += 40;
        repaint();
    }

    public void clear(){
        rectangles.clear();
        values.clear();
        num = 320;
        repaint();
    }
}

