package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: defines a generic linked list node with fields for an element and its next reference, along with basic accessor and mutator methods.
//-----------------------------------------------------

public class Node<T>{

    private T element;
    private Node<T> next;

    /** Creates a node. */
    public Node(T s, Node<T> n){
        element = s;
        next = n;
    }

    /** Gets the element. */
    public T getElement()
    {return element;}

    /** Gets the next node. */
    public Node<T> getNext()
    {return next;}

    /** Sets the next node. */
    public void setNext(Node<T> newNext)
    {next = newNext;}

    /** Sets the element. */
    public void setElement(T data){
        element = data;
    }
    
}