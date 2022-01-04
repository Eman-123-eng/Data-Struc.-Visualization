package ArrayStack;

import java.util.EmptyStackException;


public class ArrStack<E> implements Cloneable {

    protected Object[] data;
    protected int manyItems;


    public ArrStack() {
        final int INITIAL_CAPACITY = 10;
        manyItems = 0;
        data = new Object[INITIAL_CAPACITY];
    }

    public ArrStack(int initialCapacity) {
        if (initialCapacity < 0)
            throw new IllegalArgumentException
                    ("initialCapacity too small " + initialCapacity);
        manyItems = 0;
        data = new Object[initialCapacity];
    }

    public ArrStack<E> clone() {

        ArrStack<E> answer;
        try {
            answer = (ArrStack<E>) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException
                    ("This class does not implement Cloneable.");
        }
        answer.data = data.clone();
        return answer;
    }

    public void ensureCapacity(int minimumCapacity) {
        Object[] biggerArray;
        if (data.length < minimumCapacity) {
            biggerArray = new Object[minimumCapacity];
            System.arraycopy(data, 0, biggerArray, 0, manyItems);
            data = biggerArray;
        }
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return (manyItems == 0);
    }

    public E peek() {
        if (manyItems == 0)
            throw new EmptyStackException();
        return (E) data[manyItems - 1];
    }

    public E pop() {
        E answer = null;
        if (manyItems != 0) {
            //throw new EmptyStackException();
            answer = (E) data[--manyItems];
            data[manyItems] = null; // For the garbage collector
        }
        return answer;
    }

    public void push(E item) {
        if (isEmpty()) {
            data = (E[]) new Object[10];
        }
        if (manyItems == data.length) {
            ensureCapacity(manyItems * 2 + 1);
        }
        data[manyItems] = item;
        manyItems++;

    }

    public int size() {
        return manyItems;
    }

    public void trimToSize() {
        Object[] trimmedArray;
        if (data.length != manyItems) {
            trimmedArray = new Object[manyItems];
            System.arraycopy(data, 0, trimmedArray, 0, manyItems);
            data = trimmedArray;
        }

    }

    public static void main(String[] args) {
        ArrStack s1 = new ArrStack(2);
        s1.push(0);
        s1.push(1);
        s1.push(5642);
        s1.push(3);
        /*System.out.println("the size if the stack is " + s1.size());
        System.out.println("the item at the top is " + s1.peek());
        s1.pop();
        s1.trimToSize();
        System.out.println("the size of the stack after popping "+ s1.size());
        System.out.println("the stack contains:");
        for (int i = (s1.manyItems -1); i >= 0; i--) {
            System.out.println(s1.data[i]);
        }
        System.out.println("the size if the stack is " + s1.size());
        System.out.println("the item at the top is " + s1.peek());
        ArrStack s2 = s1.clone();
        System.out.println("the copied stack contains:");
        for (int i = (s2.manyItems -1); i >= 0; i--) {
            System.out.println(s2.data[i]);
        }*/

        /*String[] ss =  s1.displayArrStack();
        for (int i = 0; i < ss.length; i++){
            System.out.println(ss[i]);
        }*/


    }

    public void clear() {
        data = null;
        manyItems = 0;
    }

}




