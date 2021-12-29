package LinkedLists;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayLinked {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        LinkedList<String> strList = new LinkedList<String>();
        final int[] flag = {0};
        final int[] flagStr = {0};
        final String[] timeStr = {"", ""};

        JFrame frame = new JFrame("Linked List Data Structure");

        JLabel l1 = new JLabel("Enter either numbers or strings (not both) to be added in the linked list:");
        l1.setBounds(45, 50, 400, 30);

        JLabel l2 = new JLabel();
        l2.setBounds(50, 300, 400, 30);

        JLabel lAddTime = new JLabel();
        lAddTime.setBounds(40, 400, 600, 30);

        JLabel lRemoveTime = new JLabel();
        lRemoveTime.setBounds(40, 450, 600, 30);

        JTextField textAdd = new JTextField();
        textAdd.setBounds(50, 90, 120, 30);

        JButton bAdd = new JButton("Add");
        bAdd.setBounds(200, 90, 70, 30);

        JButton bRemove = new JButton("Remove");
        bRemove.setBounds(300, 90, 90, 30);

        JButton bDisplay = new JButton("Display");
        bDisplay.setBounds(250, 150, 80, 30);

        //JOptionPane jop = new JOptionPane();

        bAdd.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String inpText = textAdd.getText();
                int input;
                long startTime;
                long endTime;
                if (inpText.length() == 0) {
                    JOptionPane.showMessageDialog(null, " The text field must not be empty ");
                    return;
                }
                /*try { //number added
                    flag[0] = 1;
                    if (flagStr[0] == 0) {
                        //flag[0] = 1;
                        input = new Integer(inpText);
                        System.out.println(input);
                        startTime = System.nanoTime();
                        list.addLast(input);
                        endTime = System.nanoTime();
                        textAdd.setText("");
                        l2.setText(LinkedList.display(list));
                        timeStr[0] += (endTime - startTime) + " -- ";
                        lAddTime.setText("Time elapsed: " + timeStr[0]);
                    } else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same list");
                        textAdd.setText("");
                    }
                } catch (Exception ex) { //String added
                    if (flag[0] == 0) {
                        flagStr[0] = 1;
                        System.out.println(inpText);
                        strList.addLast(inpText);
                        textAdd.setText("");
                        l2.setText(LinkedList.display(strList));
                    } else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same list");
                        textAdd.setText("");

                    }
                }*/
                try { //number added
                    input = new Integer(inpText);
                    if (flagStr[0] != 1) flag[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same list");
                        textAdd.setText("");
                        return;
                    }
                } catch (Exception ex) {
                    //String added
                    if (flag[0] != 1)
                        flagStr[0] = 1;
                    else {
                        JOptionPane.showMessageDialog(null, " Cannot add numbers and strings in the same list");
                        textAdd.setText("");
                        return;
                    }
                }
                if (flagStr[0] != 1) {
                    input = new Integer(inpText);
                    System.out.println(input);
                    startTime = System.nanoTime();
                    list.addLast(input);
                    endTime = System.nanoTime();
                    textAdd.setText("");
                    l2.setText(LinkedList.display(list));
                } else {
                    System.out.println(inpText);
                    startTime = System.nanoTime();
                    strList.addLast(inpText);
                    endTime = System.nanoTime();
                    textAdd.setText("");
                    l2.setText(LinkedList.display(strList));
                }
                timeStr[0] += (endTime - startTime) + "  ";
                lAddTime.setText("Adding time: " + timeStr[0]);
            }
        });

        bRemove.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                long startTime;
                long endTime;
                try {
                    if (flag[0] == 1) {
                        //numbers
                        startTime = System.nanoTime();
                        list.removeCurrent();
                        endTime = System.nanoTime();
                        l2.setText(LinkedList.display(list));
                        if(list.size() == 0){
                            flag[0] = 0;
                            timeStr[1] = " ";
                        }
                    } else {
                        startTime = System.nanoTime();
                        strList.removeCurrent();
                        endTime = System.nanoTime();
                        l2.setText(LinkedList.display(strList));
                        if(strList.size() == 0){
                            flagStr[0] = 0;
                            timeStr[1] = " ";
                        }
                    }
                    timeStr[1] += (endTime - startTime) + "  ";
                    lRemoveTime.setText("Removing time : " + timeStr[1]);
                } catch (Exception ex) {
                    //there is no element
                    JOptionPane.showMessageDialog(null, " The list is empty cannot remove");
                }
            }
        });

        bDisplay.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                LinkedList.display(list);
                System.out.println(Node.listLength(list.head));
            }
        });


        frame.add(l1);
        frame.add(textAdd);
        frame.add(bAdd);
        frame.add(bRemove);
        frame.add(bDisplay);
        frame.add(l2);
        frame.add(lAddTime);
        frame.add(lRemoveTime);
        frame.setLayout(null);
        frame.setSize(500, 600);
        frame.setVisible(true);
    }
}
