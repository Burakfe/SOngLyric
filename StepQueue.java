package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: wraps a queue of SongLyric objects with basic enqueue, dequeue, and display operations.
//-----------------------------------------------------

public class StepQueue {
    private Queue<SongLyric> queue;

    /** Initializes an empty queue. */
    public StepQueue(){
        queue = new Queue<>();
    }

    /** Adds a song lyric to the queue. */
    public void enqueue(SongLyric songLyric) {
        queue.enqueue(songLyric);
    }
    
    /** Removes and returns the first song lyric. */
    public SongLyric dequeue() {
        return queue.dequeue();
    }
    
    /** Checks if the queue is empty. */
    public boolean isEmpty() {
        return queue.isEmpty();
    }

    /** Displays all song lyrics in the queue. */
    public void viewQueue(){
        Object[] arr = queue.returnAllArray();
        for(int i = 0; i < queue.getSize(); i++){
            SongLyric lyric = (SongLyric) arr[i];
            System.out.println("Song Lyric ID: " + lyric.getID() + ", Name: " + lyric.getName());
        }
    }

}
