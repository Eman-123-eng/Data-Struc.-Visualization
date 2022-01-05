package Entry;

import Arrays.DisplayArray;
import LinkedLists.DisplayLinked;
import Queues.DisplayArrayQueue;
import Queues.DisplayLinkedQueue;
import Trees.DisplayTree;

import javax.swing.*;
import javax.swing.border.Border;

import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class StructureSelection implements ActionListener {
    JButton bArray, bLinked, bStack, bQueue, bTree;
    JFrame f;

    public StructureSelection() {
        f = new JFrame("Data Structures Performance");

        JLabel l = new JLabel("Welcome to data structure performance!");
        l.setFont(new Font("Courier", Font.ITALIC, 22));
        Dimension lSize = l.getMaximumSize();
        l.setBounds(120, 50, (lSize.width + 50), lSize.height);

        JLabel lSelect = new JLabel("Select one of the following data structures");
        lSelect.setFont(new Font("AvantGarde", Font.BOLD, 16));
        Dimension lSelSize = lSelect.getMaximumSize();
        lSelect.setBounds(160, 100, (lSelSize.width + 70), lSelSize.height);

        /*JTextField t1 = new JTextField();  //Enter your name
        t1.setBounds(450, 20, 100, 25);
        t1.setVisible(true);*/

        bArray = new JButton("Array");
        bArray.setBounds(170, 190, 130, 50);
        bArray.setFont(new Font("Helvetica", Font.BOLD, 14));
        bArray.setForeground(new Color(42, 44, 43));
        bArray.setBorder(new StructureSelection.RoundedBorder(10));

        bLinked = new JButton("Linked List");
        bLinked.setBounds(350, 190, 130, 50);
        bLinked.setFont(new Font("Helvetica", Font.BOLD, 14));
        bLinked.setForeground(new Color(42, 44, 43));
        bLinked.setBorder(new StructureSelection.RoundedBorder(10));

        bStack = new JButton("Stack");
        bStack.setBounds(170, 300, 130, 50);
        bStack.setFont(new Font("Helvetica", Font.BOLD, 14));
        bStack.setForeground(new Color(42, 44, 43));
        bStack.setBorder(new StructureSelection.RoundedBorder(10));

        bQueue = new JButton("Queue");
        bQueue.setBounds(350, 300, 130, 50);
        bQueue.setFont(new Font("Helvetica", Font.BOLD, 14));
        bQueue.setForeground(new Color(42, 44, 43));
        bQueue.setBorder(new StructureSelection.RoundedBorder(10));

        bTree = new JButton("Tree");
        bTree.setBounds(260, 375, 130, 50);
        bTree.setFont(new Font("Helvetica", Font.BOLD, 14));
        bTree.setForeground(new Color(42, 44, 43));
        bTree.setBorder(new StructureSelection.RoundedBorder(10));

        bArray.addActionListener(this);
        bLinked.addActionListener(this);
        bStack.addActionListener(this);
        bQueue.addActionListener(this);
        bTree.addActionListener(this);

        f.add(l);
        f.add(lSelect);
        //f.add(t1);
        f.add(bArray);
        f.add(bLinked);
        f.add(bStack);
        f.add(bQueue);
        f.add(bTree);

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
        f.setSize(675, 500);
        f.setLayout(null);
        f.setLocation(400, 125);
        f.setVisible(true);
    }

    public static void main(String[] args) {
        new StructureSelection();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == bArray) {
            DisplayArray.main(null);
            f.setVisible(false);
        } else if (ae.getSource() == bLinked) {
            DisplayLinked.main(null);
            f.setVisible(false);
        } else if (ae.getSource() == bStack) {
            JFrame fS = new JFrame("Stack");
            JLabel l1 = new JLabel("You have chosen the stack structure");
            l1.setFont(new Font("Courier", Font.BOLD, 14));
            JLabel l2 = new JLabel("Choose which implementation you would like to try:");
            l2.setFont(new Font("Courier", Font.BOLD, 14));
            JLabel lArrS = new JLabel("1. Visualizer.Array Stack");
            lArrS.setFont(new Font("Helvetica", Font.BOLD, 14));
            JLabel lLinkedS = new JLabel("2. Linked Stack");
            lLinkedS.setFont(new Font("Helvetica", Font.BOLD, 14));

            String choice = JOptionPane.showInputDialog(fS, new Object[]{l1, l2, lArrS, lLinkedS}, "Stack", 1);

            if (Objects.equals(choice, "1")) {
                ArrayStack.Main.main(null);
                f.setVisible(false);
            } else if (Objects.equals(choice, "2")) {
                LINKED_STACK.Main.main(null);
                f.setVisible(false);
            }
            System.out.println(choice);
        } else if (ae.getSource() == bQueue) {
            JFrame fQ = new JFrame("Queue");
            JLabel l1 = new JLabel("You have chosen the Queue structure");
            l1.setFont(new Font("Courier", Font.BOLD, 14));
            JLabel l2 = new JLabel("Choose which implementation you would like to try:");
            l2.setFont(new Font("Courier", Font.BOLD, 14));
            JLabel lArrQ = new JLabel("1. Visualizer.Array Queue");
            lArrQ.setFont(new Font("Helvetica", Font.BOLD, 14));
            JLabel lLinkedQ = new JLabel("2. Linked Queue");
            lLinkedQ.setFont(new Font("Helvetica", Font.BOLD, 14));

            String choice = JOptionPane.showInputDialog(fQ, new Object[]{l1, l2, lArrQ, lLinkedQ}, "Queue", 1);

            if (Objects.equals(choice, "1")) {
                DisplayArrayQueue.main(null); //will send the name of the user
                f.setVisible(false);
            } else if (Objects.equals(choice, "2")) {
                DisplayLinkedQueue.main(null);
                f.setVisible(false);
            }
            System.out.println(choice);
        }else if (ae.getSource() == bTree) {
            DisplayTree.main(null);
            f.setVisible(false);
        }
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
