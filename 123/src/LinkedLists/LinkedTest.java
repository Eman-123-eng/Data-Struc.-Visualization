package LinkedLists;
public class LinkedTest {
    public static void main(String[] args) {

        LinkedList<Integer> list1= new LinkedList<Integer>();
        list1.addAfter(3);
        list1.addAfter(35);
        list1.addAfter(5);
        list1.addBefore(11);
        list1.addAfter(6);
        list1.addAfter(4);
        //LinkedList.display(list1); //done
        System.out.println();
        System.out.println();
        list1.addBefore(1);
        list1.addAfter(23);
        list1.addBefore(10);
        list1.addAfter(36);
        LinkedList.display(list1); //done
        
        //-----------------------

        LinkedList<Integer> list2= new LinkedList<Integer>();
        list2.addAfter(10);
        list2.addAfter(20);
        list2.addAfter(30);
        list2.addAfter(40);
        LinkedList.display(list2);

        System.out.println("-------------New list for concatenation---------");
        LinkedList<Integer> l = LinkedList.concatenation(list1,list2);
        list1.head.setData(100);  //l 2t2sr b list1 and list2 // NOT DONE !!!!! problem
        list2.head.setData(100);
        LinkedList.display(l);


        //----------------------------
        System.out.println();
        System.out.println("-------------New list for remove and addFirst and addLast ---------");

        LinkedList<Integer> sA,sB,sC;
        sA = new LinkedList<Integer>();
        sA.addAfter(1);
        sA.addAfter(10);
        sA.addLast(55);
        sA.addAfter(5);
        //sA.start(); //done
        sA.addAfter(3);
        //sA.removeCurrent();

        //LinkedList.display(sA);
        sA.addAfter(4);
        sA.addAfter(5);
        sA.addFirst(40);
        sA.addAfter(7);
        sA.addFirst(30);
        sA.addLast(35);
        System.out.println(sA.cursor.getData());
        sA.addAfter(4);
        sA.addAfter(9);
        //sA.removeCurrent();

        System.out.println();
        LinkedList.display(sA); //done

        System.out.println("-------------New list for addAll---------");

        sB = new LinkedList<Integer>();
        sB.addLast(60);
        sB.addLast(70);
        sB.addLast(80);
        //sB.addLast(90);

        sA.addAll(sB);
        //sA.cursor.setData(100);  // done
        //sB.cursor.setData(100);
        LinkedList.display(sA); //done
        System.out.println();
        System.out.println();
        LinkedList.display(sB);

        System.out.println();
        System.out.println("-------------New list for clone---------");
        System.out.println();
        sC = sA.clone();
        sA.cursor.setData(100); //m2srtsh // done
        sC.cursor.setData(100); //m2srsh // NOT DONE  !!!!!!!!! problem
        LinkedList.display(sC);
         
    }
}
