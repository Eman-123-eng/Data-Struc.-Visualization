

package LINKED_STACK;

import java.util.EmptyStackException;

public class LinkedStack<E> extends Node implements Cloneable{
    protected Node<E> top;
    protected Node<E> bottom;

    public LinkedStack(){
        top=null;
    }

    public LinkedStack<E> clone( ){
        LinkedStack<E> answer;
        try
        {
            answer = (LinkedStack<E>) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException
                    ("This class does not implement Cloneable.");
        }
        answer.top = Node.listCopy(top); // Generic listCopy method
        return answer;
    }

    public boolean isEmpty( ){
        return (top == null);
    }

    public E peek( ){
        if (top == null)
// EmptyStackException is from java.util, and its constructor has no argument.
            throw new EmptyStackException( );
        return top.getData( );
    }

    public E pop( ){
        E answer;
        if (top == null)
// EmptyStackException is from java.util, and its constructor has no argument.
            throw new EmptyStackException( );
        answer = top.getData( );
        top = top.getLink( );
        return answer;
    }

    public void push(E item){
        if(isEmpty()) {
            bottom = new Node<E>(item, null);
            top=bottom;
        }
        else
            top = new Node<E>(item,top);
    }

    public int size( ){
        return Node.listLength(top); // Generic listLength method
    }





}
