package LinkedLists;
//import java.util.LinkedList;

public class Node<E> {
    private E data;
    private Node<E> link;

    public Node() {
    }

    public Node(E initialData, Node<E> initialLink) {
        data = initialData;
        link = initialLink;
    }

    public void addNodeAfter(E element) {
        link = new Node<E>(element, link);
    }

    public E getData() {
        return data;
    }

    public Node<E> getLink() {
        return link;
    }

    public static Node listCopy(Node source) {
        Node copyHead;
        Node copyTail;

        if (source == null)
            return null;

        copyHead = new Node(source.data, null);
        copyTail = copyHead;

        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        return copyHead;
    }

    public static Object[] listCopyWithTail(Node source) {
        Node copyHead;
        Node copyTail;
        Object[] answer = new Object[2];

        if (source == null)
            return answer;

        copyHead = new Node(source.data, null);
        copyTail = copyHead;

        while (source.link != null) {
            source = source.link;
            copyTail.addNodeAfter(source.data);
            copyTail = copyTail.link;
        }

        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    public static int listLength(Node head) {
        Node cursor;
        int answer;

        answer = 0;
        for (cursor = head; cursor != null; cursor = cursor.link)
            answer++;

        return answer;
    }

    public static Node[] listPart(Node start, Node end) {
        Node copyHead;
        Node copyTail;
        Node cursor;
        Node[] answer = new Node[2];

        if (start == null)
            throw new IllegalArgumentException("start is null");
        if (end == null)
            throw new IllegalArgumentException("end is null");

        copyHead = new Node(start.data, null);
        copyTail = copyHead;
        cursor = start;

        while (cursor != end) {
            cursor = cursor.link;
            if (cursor == null)
                throw new IllegalArgumentException
                        ("end node was not found on the list");
            copyTail.addNodeAfter(cursor.data);
            copyTail = copyTail.link;
        }

        answer[0] = copyHead;
        answer[1] = copyTail;
        return answer;
    }

    public static Node listPosition(Node head, int position) {
        Node cursor;
        int i;

        if (position == 0)
            throw new IllegalArgumentException("position is zero");

        cursor = head;
        for (i = 1; (i < position) && (cursor != null); i++)
            cursor = cursor.link;

        return cursor;
    }

    /*public static Node listSearch(Node head, E target) {
        Node cursor;

        if (target == null) {
            for (cursor = head; cursor != null; cursor = cursor.link)
                if (cursor.data == null)
                    return cursor;
        } else {
            for (cursor = head; cursor != null; cursor = cursor.link)
                if (target == cursor.data)
                    return cursor;
        }

        return null;
    }*/

    public void removeNodeAfter() {
        link = link.link;
    }

    public void setData(E newData) {
        data = newData;
    }

    public void setLink(Node<E> newLink) {
        link = newLink;
    }

    /* public static Node[] listSplitter(Node head, int splitterValue) {
         Node[] answer = new Node[2];
         Node cursor = head;
         Node dummyHead1 = new Node();
         Node dummyHead2 = new Node();
         Node tail1 = new Node(), tail2 = new Node();

         int flag1 = 0; //no head yet
         int flag2 = 0; //no head yet

         for (; cursor != null; cursor = cursor.link) {
             if (cursor.data < (E) splitterValue) {
                 if (flag1 == 0) {
                     dummyHead1 = new Node(cursor.data, null);
                     tail1 = dummyHead1;
                     flag1 = 1;
                 } else {
                     tail1.addNodeAfter(cursor.data);
                 }

             } else {
                 if (flag2 == 0) {
                     dummyHead2 = new Node(cursor.data, null);
                     tail2 = dummyHead2;
                     flag2 = 1;
                 } else {
                     tail2.addNodeAfter(cursor.data);
                 }
             }
         }
         display(dummyHead1);
         display(dummyHead2);
         answer[0] = dummyHead1;
         answer[1] = dummyHead2;
         return answer;
     }
 */
    public static void display(Node head) {
        Node cursor = head;
        for (; cursor != null; cursor = cursor.link) {
            System.out.print(cursor.data + " ");
        }
        System.out.println();
    }


}


