package Queues;

import LinkedLists.Node;

import java.util.NoSuchElementException;

public class LinkedBasedQueue<E> implements Cloneable {
    private Node<E> front, rear;
    private int manyNodes;

    public LinkedBasedQueue() {
        front = rear = null;
        manyNodes = 0;
    }

    public void add(E obj) {
        //Node<E> newNode;
        if (isEmpty()) {
            front = new Node<E>(obj, null);
            rear = front;
        } else {
            rear.addNodeAfter(obj);
            rear = rear.getLink();
        }
        manyNodes++;
    }

    public boolean isEmpty() {
        return (front == null);
    }

    public E getFront() {
        if (!isEmpty()) return front.getData();
        return null;
    }

    public E remove() {
        E answer;
        if (isEmpty())
            throw new NoSuchElementException("Queue underflow");
        //return null;
        answer = front.getData();
        front = front.getLink();
        manyNodes--;
        if (manyNodes == 0)
            rear = null;
        return answer;
    }

    public int size() {
        return manyNodes;
    }

    public LinkedBasedQueue<E> clone() {
        LinkedBasedQueue<E> answer;
        try {
            answer = (LinkedBasedQueue<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable");
        }
        answer.front = Node.listCopy(front);
        return answer;
    }

    public String displayQueue() {
        String str = "Queue data: ";
        Node<E> cursor;
        if (!isEmpty()) {
            for (cursor = front; cursor != rear.getLink(); cursor = cursor.getLink())
                str += cursor.getData() + " | ";

             //+ " - No. of elements: " + size() + " - front: " + front.getData() + " - rear: " + rear.getData();
        }
        return str;
    }


}
