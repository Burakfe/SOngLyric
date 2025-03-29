package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: defines a class for managing a stack of SongLyric objects with standard stack operations like push, pop, and display
//-----------------------------------------------------

public class SongLyricStack {
    private Stack<SongLyric> stack;

    /** Initializes an empty stack. */
    public SongLyricStack(){
        stack = new Stack<>();
    }

    /** Pushes a song lyric onto the stack. */
    public void push(SongLyric songlyric){
        stack.push(songlyric);
    }

    /** Pops and returns the top song lyric. */
    public SongLyric pop(){
        return stack.pop();
    }
    
    /** Checks if the stack is empty. */
    public boolean isEmpty(){
        return stack.isEmpty();
    }

    /** Displays all song lyrics in the stack. */
    public void viewStack(){
        Object[] arr = stack.returnAllArray();
        for(int i = 0; i < stack.getSize(); i++){
            SongLyric lyric = (SongLyric) arr[i];
            System.out.println("Crucial Song Lyric ID: " + lyric.getID() + ", Name: " + lyric.getName());
        }
    }
}
