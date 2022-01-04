package ArrayStack;

import Entry.StructureSelection;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import static javax.swing.BorderFactory.createEmptyBorder;

public class Main extends JPanel {
    static int i = 0, j = 0;

    public static void main(String[] args) {
        ArrStack<Integer> arrStack = new ArrStack<>();
        ArrStack<String> strArrStack = new ArrStack<>();
        final int[] flag = {0};
        final int[] flagStr = {0};
        final int[] count = new int[1];

        JFrame f = new JFrame("Array Stack");

        JLabel l = new JLabel("Enter the item you want to push into your stack ");
        l.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lSize = l.getMaximumSize();
        l.setBounds(50, 20, (lSize.width + 10), lSize.height);

        /*JLabel l1 = new JLabel("\n Note: (you can only push one element in the stack)");
        Dimension size1 = l1.getPreferredSize();
        l1.setBounds(50,40,size1.width,size1.height);*/

        JTextField t1 = new JTextField();
        t1.setBounds(400, 20, 100, 25);

        JLabel lCount = new JLabel("Enter the amount: ");
        lCount.setFont(new Font("Courier", Font.PLAIN, 14));
        Dimension lCoSize = lCount.getMaximumSize();
        lCount.setBounds(50, 50, (lCoSize.width + 10), lCoSize.height);

        JTextField tCount = new JTextField();
        tCount.setBounds(400, 50, 100, 25);

        Graphic graphic = new Graphic();

        JScrollPane grSp = new JScrollPane(graphic);
        grSp.setBounds(560, 60, 60, 360);
        grSp.setBorder(createEmptyBorder());
        grSp.setVisible(true);

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
                long startTime = 0;
                long endTime = 0;
                count[0] = Integer.parseInt(tCount.getText());
                if (t1.getText().length() == 0 && tCount.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "You must enter the item and the amount to be added");
                    return;
                }
                try {  //number is added
                    num = Integer.parseInt(t1.getText());
                    arrStack.clear();
                    if (flagStr[0] != 1) flag[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same stack");
                        //t1.setText("");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        arrStack.push(num);
                    }
                    endTime = System.nanoTime();
                    /*if (arrStack.size() == 1) {
                        grSp.setVisible(true);
                    }*/

                } catch (Exception ex) {
                    //String added
                    strArrStack.clear();
                    if (flag[0] != 1)
                        flagStr[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same stack");
                        //t1.setText("");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        strArrStack.push(t1.getText());
                    }
                    endTime = System.nanoTime();
                    /*if (strArrStack.size() == 1) {
                        grSp.setVisible(true);
                    }*/
                }
                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);

                graphic.addRectangle(0, ArrayStack.Graphic.num, 50, 39, t1.getText());

                tModelAdd.insertRow(i, new String[]{t1.getText(), tCount.getText(),String.valueOf(elapsedTime)});
                spAdd.setVisible(true);
                //t1.setText("");
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
                    if (arrStack.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The stack is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        arrStack.pop();
                    }
                    endTime = System.nanoTime();

                    graphic.removeRec();
                    if (arrStack.isEmpty()) {
                        flag[0] = 0;
                        grSp.setVisible(false);
                    }
                } else {
                    if (strArrStack.isEmpty()) {
                        JOptionPane.showMessageDialog(null, "The stack is empty");
                        return;
                    }
                    startTime = System.nanoTime();
                    for (int i = 1; i <= count[0]; i++) {
                        strArrStack.pop();
                    }
                    endTime = System.nanoTime();

                    graphic.removeRec();
                    if (strArrStack.isEmpty()) {
                        flagStr[0] = 0;
                        grSp.setVisible(false);
                    }
                }
                double elapsedTime = ((double) (endTime - startTime) * 1.0E-6);
                tModelRemove.insertRow(j, new String[]{(String) tModelAdd.getValueAt(0, 0),(String) tModelAdd.getValueAt(0, 1), String.valueOf(elapsedTime)});
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
                // dis.setText("The item at the top of your stack is "+ s.peek());
                if (flag[0] == 1) { //numbers
                    if (arrStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the stack is: "), new JLabel(arrStack.peek().toString())});

                } else {
                    if (strArrStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The first item in the stack is: "), new JLabel(strArrStack.peek())});
                }
            }
        });

        JButton b4 = new JButton("Size");
        b4.setBounds(350,420,90,30);
        b4.setForeground(new Color(42, 44, 43));
        b4.setBorder(new RoundedBorder(10));
        b4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (flag[0] == 1) { //numbers
                    if (arrStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the stack is: "), new JLabel(String.valueOf(arrStack.size()))});
                } else {
                    if (strArrStack.isEmpty()) JOptionPane.showMessageDialog(null, "The stack is empty");
                    else
                        JOptionPane.showMessageDialog(null, new Object[]{new JLabel("The size of the stack is: "), new JLabel(String.valueOf(strArrStack.size()))});
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
                    graphic.clear();
                    f.setVisible(false);
                }
            }
        });


        f.add(l);
        //  f.add(l1);
        f.add(t1);
        f.add(lCount);
        f.add(tCount);
        f.add(b1);
        //  f.add(sc);
        f.add(b2);
       // f.add(b3);
       // f.add(b4);
        f.add(spAdd);
        f.add(spRemove);
        f.add(bBack);
        f.add(grSp);


        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                int choice = JOptionPane.showConfirmDialog(null, "Are you sure to exit?");
                System.out.println(choice);
                if(choice == 0){
                    System.exit(0);
                }else{
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


class Graphic extends JPanel {
    static int num = 320 , strSpace;

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
            if (values.get(i).length() < 4) {
                strSpace = 18;
            } else {
                strSpace = 7;
            }
            g.drawString(values.get(i), strSpace, ((rectangles.get(i)).y) + 25);
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

    public void clear() {
        rectangles.clear();
        values.clear();
        num = 320;
        repaint();
    }
}

