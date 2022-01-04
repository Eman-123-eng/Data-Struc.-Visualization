

package LINKED_STACK;

import java.util.EmptyStackException;
import LinkedLists.LinkedList;
import LinkedLists.Node;

public class LinkedStack<E> extends LinkedList<E> implements Cloneable{
    protected LinkedList m = new LinkedList();


    public LinkedStack(){
        m.tail=null;
        m.head=null;
    }

    public LinkedStack<E> clone( ){
        LinkedStack<E> answer;
        answer=(LinkedStack<E>)m.clone();

        /*try
        {
            answer = (LinkedStack<E>) super.clone( );
        }
        catch (CloneNotSupportedException e)
        {
            throw new RuntimeException
                    ("This class does not implement Cloneable.");
        }
        answer.top = Node.listCopy(top); // Generic listCopy method

         */

        return answer;
    }

    public boolean isEmpty( ){
        return (m.head == null);
    }

    public E peek( ){
        if (m.head == null)
// EmptyStackException is from java.util, and its constructor has no argument.
            throw new EmptyStackException( );
        return (E) m.tail.getData( );
    }

    public E pop( ){
        E answer;
        if (m.head == null)
// EmptyStackException is from java.util, and its constructor has no argument.
            throw new EmptyStackException( );
        answer = (E) m.tail.getData( );
        m.removeCurrent();
        return answer;
    }

    public void push(E item){
        m.addLast(item);
    }

    public int size( ){
        return Node.listLength(m.head); // Generic listLength method
    }

    public void clear() {
        m.head=null;
        m.tail=null;
    }
}
