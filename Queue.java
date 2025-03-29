package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: implements a generic FIFO queue using linked nodes with methods for enqueuing, dequeuing, and utility operations.
//-----------------------------------------------------

public class Queue<T> {
    protected Node<T> head, tail;
    protected int size;

    /** Creates an empty queue. */
    public Queue(){
        head = tail = null;
        size = 0;
    }

    /** Adds an element to the queue. */
    public void enqueue(T element){
        Node newNode = new Node<T>(element, null);
        if(tail == null){
            head = tail = newNode;
        }
        else{
            tail.setNext(newNode);
            tail = newNode;
        }
        size++;
    }

    /** Removes and returns the first element. */
    public T dequeue(){
        if(size == 0)
        {return null;}
        T data = head.getElement();
        head = head.getNext();
        size--;
        return data;
    }

    /** Checks if the queue is empty. */
    public boolean isEmpty(){
        if(size == 0){return true;}
        else{return false;}
    }

    /** Returns the queue size. */
    public int getSize(){return size;}
    
    /** Returns all elements as an array. */
    public Object[] returnAllArray(){
        Node current = head;
        Object[] arr = new Object[size];
        int index = 0;
        while(current != null){
            arr[index] = current.getElement();
            index++;
            current = current.getNext();
        }
        return arr;
    }
    
}
