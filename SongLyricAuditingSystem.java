package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: managing lyric approval workflow with queues and a priority stack
//-----------------------------------------------------

/** Manages the lyric approval workflow using queues and a stack. */
public class SongLyricAuditingSystem {
    private LinkedList<SongLyric> songLyrics;
    private StepQueue draftingQueue;
    private StepQueue auditingQueue;
    private StepQueue finalRecordingQueue;
    private SongLyricStack crucialSongLyrics;

    /** Initializes the system with empty queues and a stack. */
    public SongLyricAuditingSystem(){
        songLyrics = new LinkedList<>();
        draftingQueue = new StepQueue();
        auditingQueue = new StepQueue();
        finalRecordingQueue = new StepQueue();
        crucialSongLyrics = new SongLyricStack();
    }

    /** Adds a song lyric to the system and drafting queue. */
    public void addSongLyric(SongLyric songLyric){
        songLyrics.add(songLyric);
        draftingQueue.enqueue(songLyric);
    }


    /** Processes the next song lyric in the specified step. */
    public void processNextSongLyricInQueue(String step){
        SongLyric song = null;
        if(step.equals("Drafting")){
            if(!crucialSongLyrics.isEmpty()){
                song = crucialSongLyrics.pop();
                System.out.println("Processing Crucial SongLyric ID: " + song.getID() + " , Name: " + song.getName());
                moveSongLyricToNextStep(song);
            }
            else{
                song = draftingQueue.dequeue();
                System.out.println("Processing SongLyric ID: " + song.getID() + ", Name: " + song.getName());
                moveSongLyricToNextStep(song);

            }
        }
        else if(step.equals("Auditing")){
            if(!crucialSongLyrics.isEmpty()){
                song = crucialSongLyrics.pop();
                System.out.println("Processing Crucial SongLyric ID: " + song.getID() + " , Name: " + song.getName());
                moveSongLyricToNextStep(song);
            }
            else{
                song = auditingQueue.dequeue();
                System.out.println("Processing SongLyric ID: " + song.getID() + ", Name: " + song.getName());
                moveSongLyricToNextStep(song);

            }
        }
        else if(step.equals("Final Recording")){
            if(!crucialSongLyrics.isEmpty()){
                song = crucialSongLyrics.pop();
                System.out.println("Processing Crucial SongLyric ID: " + song.getID() + " , Name: " + song.getName());
                //moveSongLyricToNextStep(song);
            }
            else{
                song = finalRecordingQueue.dequeue();
                System.out.println("Processing SongLyric ID: " + song.getID() + ", Name: " + song.getName());
                //moveSongLyricToNextStep(song);

            }
        }
    }


    /** Moves a song lyric to the next step based on approval. */
    public void moveSongLyricToNextStep(SongLyric songLyric){
        String currentStep = songLyric.getCurrentStep();
        if(currentStep.equals("Drafting")){
            if(songLyric.allAuditorsApproved()){
                songLyric.updateStatus("Awaiting Auditing");
                songLyric.updateCurrentStep("Auditing");
                System.out.println("SongLyric ID: " + songLyric.getID() + " moved to Auditing step.");
                auditingQueue.enqueue(songLyric);
            }
            else{
                songLyric.updateStatus("Lyric Rejected");
                moveSongLyricToStack(songLyric);
            }
        }
        else if(currentStep.equals("Auditing")){
            if(songLyric.allAuditorsApproved()){
                songLyric.updateCurrentStep("Final Recording");
                System.out.println("SongLyric ID: " + songLyric.getID() + " moved to Final Recording step.");
                finalRecordingQueue.enqueue(songLyric);
            }
            else{
                songLyric.updateStatus("Lyric Rejected");
                moveSongLyricToStack(songLyric);
            }
        }
        else if(currentStep.equals("Final Recording")){
            if(songLyric.allAuditorsApproved()){
                songLyric.updateStatus("Lyric Finalized");
                System.out.println("SongLyric ID: " + songLyric.getID() + " finalized.");
            }
            else{
                songLyric.updateStatus("Lyric Rejected");
                moveSongLyricToStack(songLyric);
            }
        }
    }


    /** Moves a rejected song lyric to the stack. */
    public void moveSongLyricToStack(SongLyric song) {
        crucialSongLyrics.push(song);
        System.out.println("SongLyric ID: " + song.getID() + " has been moved to the stack crucially. Current Status: " + song.getStatus());
    }

    /** Displays the crucial song lyric stack. */
    public void showStack() {
        crucialSongLyrics.viewStack();
    }

    /** Displays the queue for the specified step. */
    public void showQueue(String step) {
        if (step.equalsIgnoreCase("Drafting")) {
            draftingQueue.viewQueue();
        } else if (step.equalsIgnoreCase("Auditing")) {
            auditingQueue.viewQueue();
        } else if (step.equalsIgnoreCase("Final Recording")) {
            finalRecordingQueue.viewQueue();
        }
    }

    /** Retrieves the status of a song lyric by ID. */
    public String getSongLyricStatus(int lyricID) {
        int count = songLyrics.getSize();
        for (int i = 0; i < count; i++) {
            SongLyric song = songLyrics.get(i).getElement();
            if (song.getID() == lyricID) {
                return "SongLyric ID: " + song.getID() + ", Status: " + song.getStatus() + ", Current Step: " + song.getCurrentStep() + ", Latest Auditing: " + song.getLatestAuditing();
            }
        }
        return "Song Lyric not found";
    }

    /** Displays all song lyrics in the system. */
    public void showAllSongLyrics() {
        int count = songLyrics.getSize();
        for (int i = 0; i < count; i++) {
            SongLyric song = songLyrics.get(i).getElement();
            System.out.println("SongLyric ID: " + song.getID() + ", Name: " + song.getName() + ", Status: " + song.getStatus() + ", Current Step: " + song.getCurrentStep());
        }
    }

    /** Retrieves a song lyric by ID. */
    public SongLyric getSongLyricById(int id) {
        int count = songLyrics.getSize();
        for (int i = 0; i < count; i++) {
            SongLyric song = songLyrics.get(i).getElement();
            if (song.getID() == id) {
                return song;
            }
        }
        return null;
    }

}
