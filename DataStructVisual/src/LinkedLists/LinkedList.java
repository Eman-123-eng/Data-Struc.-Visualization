package LinkedLists;

public class LinkedList<E> extends Node implements Cloneable {
    public int manyNodes;
    public Node<E> head, tail;
    public Node<E> cursor, precursor;/*refer to the current node*/

    public LinkedList() {

    }

    public void addFirst(E element) {
        if (head == null) { //empty list
            head = new Node<E>(element, head);
            tail = head;
            cursor = head;
            precursor = null;
        } else { //single element //done
            head = new Node<E>(element, head);
            cursor = head;
            precursor = null;
        }
        manyNodes++;
    }

    public void addLast(E element) {
        if (head == null) {
            addFirst(element);
            return;
        }
        if (head == tail) { //single element
            Node<E> newNode = new Node<E>(element, null);
            tail.setLink(newNode);
            precursor = cursor;
            tail = newNode;
            cursor = tail;
        } else { //end case
            tail.addNodeAfter(element);
            tail = tail.getLink();
            advance();
        }
        manyNodes++;
    }

    public void addAfter(E element) {
        if (head == null) { //empty list
            addFirst(element);
            return;
        }
         /*if(cursor == head){ //single element and beginning case //done
            Node<E> newNode = new Node<>(element, cursor.getLink());
            cursor.setLink(newNode);
            precursor = cursor;
            cursor = cursor.getLink();
        }*/
        //else
        if (cursor == tail) { // the end case //done
            Node<E> newNode = new Node<>(element, cursor.getLink());
            cursor.setLink(newNode);
            //cursor.addNodeAfter(element);
            /*precursor = cursor;
            cursor = cursor.getLink();*/
            advance();
            tail = cursor;
        } else { // in the middle case //done
            Node<E> newNode = new Node<>(element, cursor.getLink());
            cursor.setLink(newNode);
            advance();
        }
        manyNodes++;
    }

    public void addBefore(E element) {
        if (head == null) { //empty list
            addFirst(element);
            return;
        }
        if (cursor == head) { //single element //done
            Node<E> newNode = new Node<>(element, head);
            //precursor = cursor; //will remain
            tail = cursor;
            head = newNode;
            cursor = head;
        }
        /*else if(cursor == tail){ // add before last
            Node<E> newNode = new Node<>(element, cursor);
            precursor.setLink(newNode);
            cursor = newNode;
            manyNodes++;
        }*/
        else { //add in middle AND in the end case //done
            Node<E> newNode = new Node<>(element, cursor);
            precursor.setLink(newNode);
            //precursor = cursor; //will remain
            cursor = newNode;
            /*Node ctrl = head;
            for (; ctrl != null && ctrl.getLink() != cursor; ctrl = ctrl.getLink()) ;
            Node newNode = new Node(element, cursor);
            ctrl.setLink(newNode);
            cursor = newNode;
            */
        }
        manyNodes++;
    }

    public void addAll(LinkedList<E> addend) {
        Node<E> it;
        if (addend == null)
            throw new IllegalArgumentException("addend is null");
        if (head == null) {
            it = addend.head;
            head = new Node<E>(it.getData(), null);
            tail = head;
            for (; it.getLink() != null; it = it.getLink()) {
                tail.addNodeAfter(it.getData());
                tail = tail.getLink();
            }
        } else if (tail != null) {
            //tail.setLink(addend.head);
            for (it = addend.head; it != null; it = it.getLink()) {
                tail.addNodeAfter(it.getData());
                tail = tail.getLink();
            }
        }
        //tail.setLink(LinkedList.listCopy(head));
        manyNodes += addend.manyNodes;
        /*while (cursor.getLink() != null) cursor = cursor.getLink();
        Node ctrlA = addend.head;
        Node ctrlT = head;
        while (ctrlA != null) {
            ctrlT.setLink(new Node(ctrlA.getData(), null));
            ctrlT = ctrlT.getLink();
        }
        manyNodes += addend.manyNodes;*/
    }

    public LinkedList<E> clone() {
        LinkedList<E> copyList;
        try {
            copyList = (LinkedList<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not support Cloneable");
        }
        copyList.head = LinkedList.listCopy(head);
        copyList.cursor = cursor;
        return copyList;
    }

    //the concatenation without a copy of the two lists . tmam ??
    public static <E> LinkedList<E> concatenation(LinkedList<E> s1, LinkedList<E> s2) {
        if (s1 == null || s2 == null) {
            throw new NullPointerException("One of the arguments is null");
        } else {
            s1.tail.setLink(s2.head);
            LinkedList<E> s3 = new LinkedList<>();
            s3.head = s1.head;
            s3.tail = s2.tail;
            s3.manyNodes = s1.manyNodes + s2.manyNodes;
            //s3.cursor = s2.tail;
            return s3;
        }
    }

    public void removeCurrent() {
        if (isCurrent()) {
            if (cursor == head) {  //beginning case or single case
                head = head.getLink();
                cursor = head;
            } else if (manyNodes == 1) {
                head = tail = cursor = precursor = null;
            } else if (cursor == tail) { //end case
                Node<E> it;
                for (it = head; (manyNodes != 2) && it.getLink() != precursor; it = it.getLink()) {
                }
                cursor = precursor;
                precursor = it;
                tail = cursor;
                tail.setLink(null);
            } else { //in the middle case
                precursor.setLink(cursor.getLink());
                cursor = cursor.getLink();
            }
            manyNodes--;
        } else {
            throw new IllegalStateException("There is no current element");
        }
    }

    public int size() {
        return manyNodes;
    }

    public void start() {
        cursor = head;
    }

    public boolean isEmpty() {
        return (manyNodes == 0);
    }

    public E getHead(){
        if(isEmpty()) return null;
        return head.getData();
    }

    public E getCurrent() {
        if (!isCurrent())
            throw new IllegalStateException();
        return cursor.getData();
    }

    public boolean isCurrent() {
        return (cursor != null);
    }

    public void advance() {
        if (!isCurrent())
            throw new IllegalStateException("cursor is null");
        precursor = cursor;
        cursor = cursor.getLink();
    }

    public static <E> String display(LinkedList<E> l) {
        Node<E> it = l.head;
        String str = "";
        if (it != null) {
            str = it.getData().toString();
          //  System.out.print("list data: " + it.getData());
            for (it = it.getLink(); it != null; it = it.getLink()) {
              //  System.out.print(" -> " + it.getData());
                str += " -> " + it.getData();
            }
           // System.out.println();
          //  System.out.println("head: " + l.head.getData());
          //  System.out.println("tail: " + l.tail.getData());
          //  if (l.cursor != null && l.precursor != null) {
              //  System.out.println("cursor: " + l.getCurrent());
               // System.out.println("precursor: " + l.precursor.getData());
          //  }
           // System.out.println("manyNodes: " + l.size() + " OR " + Node.listLength(l.head));
        }
        return str;
    }


}
