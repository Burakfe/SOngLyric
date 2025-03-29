package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: defines a generic singly linked list class with methods to add, remove, and retrieve elements.
//-----------------------------------------------------
public class LinkedList<T> {
    protected Node<T> head;
    protected int size;

    /** Creates an empty list. */
    public LinkedList(){
        head = null;
        size = 0;
    }

    /** Adds an element at the start. */
    void addFirst(T data){
        Node<T> n = new Node<>(data, null);
        n.setNext(head);
        head = n;
        size++;
    }

    /** Inserts an element after a node. */
    void insertAfter(Node<T> v, T data){
        Node<T> n = new Node<>(data, null);
        n.setNext(v.getNext());
        v.setNext(n);
        size++;
    }

    /** Returns the node at index i. */
    Node<T> get(int i){
        if(i >= size) return null;
        Node<T> ptr = head;
        for(int k = 0; k < i; k++)
        {
            ptr = ptr.getNext();
        }
        return ptr;
    }

    /** Removes and returns the first element. */
    T removeFirst(){
        Node<T> n = head;
        head = head.getNext();
        n.setNext(null);
        size--;
        return n.getElement();
    }

    /** Adds an element at the end. */
    void addLast(T data){
        insertAfter(get(size - 1), data);
    }

    /** Returns the list size. */
    int getSize(){
        return size;
    }

    /** Adds an element at the start or end. */
    public void add(T data){
        if(head == null){
            addFirst(data);
        }
        else {
            addLast(data);
        }
    }

    /** Returns the last element. */
    public T getLast() {
        if (head == null) {
            return null;
        }
        Node curr = head;
        while (curr.getNext() != null) {
            curr = curr.getNext();
        }
        return (T) curr.getElement();
    }
}
