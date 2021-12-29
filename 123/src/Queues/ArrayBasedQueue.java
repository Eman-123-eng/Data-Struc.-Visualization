package Queues;

import java.util.NoSuchElementException;

public class ArrayBasedQueue<E> implements Cloneable {
    private E[] data;
    private int front, rear;
    private int manyItems;

    public ArrayBasedQueue() {
        front = rear = -1;
        manyItems = 0;
        data = (E[]) new Object[10];
    }

    public ArrayBasedQueue(int initCapacity) {
        if (initCapacity < 0)
            throw new IllegalArgumentException("The initial capacity is too small");
        front = rear = -1;
        manyItems = 0;
        data = (E[]) new Object[initCapacity];
    }

    //enqueue
    public void add(E item) {
        if (manyItems == data.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        if (isEmpty())
            front = rear = 0;
        else
            rear = nextIndex(rear);

        data[rear] = item;
        manyItems++;
    }

    private int nextIndex(int index) {
        if (++index == data.length)
            return 0;
        return index;
    }

    public boolean isEmpty() {
        return (manyItems == 0);
    }

    //dequeue
    public E remove() {
        E answer;
        if (isEmpty())
            throw new NoSuchElementException("The queue is empty (Queue underflow)");
        answer = (E) data[front];
        front = nextIndex(front);
        manyItems--;
        return answer;
    }

    public int size() {
        return manyItems;
    }

    public ArrayBasedQueue<E> clone() {
        ArrayBasedQueue<E> answer;
        try {
            answer = (ArrayBasedQueue<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("This class does not implement Cloneable");
        }
        answer.data = data.clone();
        return answer;
    }

    public void ensureCapacity(int minCapacity) {
        E[] biggerArray;
        int frontNums, rearNums;
        if (data.length >= minCapacity)
            return;
        else if (manyItems == 0)
            data = (E[]) new Object[minCapacity];
        else if (front <= rear) {
            biggerArray = (E[]) new Object[minCapacity];
            System.arraycopy(data, front, biggerArray, front, manyItems);
            data = biggerArray;
        } else {
            biggerArray = (E[]) new Object[minCapacity];
            frontNums = data.length - front;
            rearNums = rear + 1;
            System.arraycopy(data, front, biggerArray, 0, frontNums);
            System.arraycopy(data, 0, biggerArray, frontNums, rearNums);
            front = 0;
            rear = manyItems - 1;
            data = biggerArray;
        }
    }

    public int getCapacity() {
        return data.length;
    }

    public void trimToSize() {
        E[] trimmedArray;
        int frontNums, rearNums;
        if (manyItems == data.length)
            return;
        else if (manyItems == 0)
            data = (E[]) new Object[0];
        else if (front <= rear) {
            trimmedArray = (E[]) new Object[manyItems];
            System.arraycopy(data, front, trimmedArray, front, manyItems);
            data = trimmedArray;
        } else {
            trimmedArray = (E[]) new Object[manyItems];
            frontNums = data.length - front;
            rearNums = rear + 1;
            System.arraycopy(data, front, trimmedArray, 0, frontNums);
            System.arraycopy(data, 0, trimmedArray, frontNums, rearNums);
            front = 0;
            rear = manyItems - 1;
            data = trimmedArray;
        }
    }

    public String displayQueue() {
        String str = "The queue data: ";
        if (!isEmpty())
            for (int i = front; i != rear + 1; i = nextIndex(i))
                str += data[i] + " | ";

        return str + " - No. of elements: " + size() + " - front: " + front + " - rear: " + rear;
    }


}
