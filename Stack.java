package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: implements a generic stack using linked nodes with operations such as push, pop, and array conversion for inspection.
//-----------------------------------------------------

public class Stack<T> {
    protected Node<T> head;
    int size;

    /** Initializes an empty stack. */
    public Stack(){
        head = null;
        size = 0;
    }

    /** Pushes an element onto the stack. */
    public void push(T element){
        Node newNode = new Node<T>(element, head);
        head = newNode;
        size++;
    }

    /** Pops and returns the top element. */
    public T pop(){
        T data = head.getElement();
        head = head.getNext();
        size--;
        return data;
    }

    /** Checks if the stack is empty. */
    public boolean isEmpty(){
        if(size == 0){return true;}
        else{return false;}
    }

    /** Returns the stack size. */
    public int getSize(){
        return size;
    }

    /** Converts the stack to an array. */
    public Object[] returnAllArray(){
        Object[] arr = new Object[size];
        Node currentNode = head;
        int index = 0;
        while(currentNode != null){
            arr[index] = currentNode.getElement();
            index++;
            currentNode = currentNode.getNext();
        }
        return arr;
    }

}
