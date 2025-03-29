package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: defines a class to manage song lyrics, tracking their auditing workflow, status updates, and auditor feedback.
//-----------------------------------------------------
public class SongLyric {
    private int lyricID;
    private String name;
    private String currentStep;
    private String status;
    private LinkedList<String> auditors;
    private LinkedList<String> auditorStatuses;
    private LinkedList<String> auditingHistory;
    private boolean formalAuditingFlag;

    /** Initializes a song lyric with an ID, name, and auditing flag. */
    public SongLyric(int lyricID, String name, boolean formalAuditingFlag){
        this.lyricID = lyricID;
        this.name = name;
        this.formalAuditingFlag = formalAuditingFlag;
        status = "Awaiting Auditing";
        this.currentStep = "Drafting"; // initial step
        auditors = new LinkedList<String>();
        auditorStatuses = new LinkedList<String>();
        auditingHistory = new LinkedList<String>();

    }
    
    /** Adds an auditor to the list. */
    public void addAuditor(String auditor){
        auditors.addFirst(auditor);
        auditorStatuses.addFirst("Awaiting Auditing");
    }

    /** Checks if all auditors have approved. */
    public boolean allAuditorsApproved(){
        boolean returnable = true;
        int size = auditorStatuses.getSize();
        for(int i = 0; i < size; i++){
            if(!auditorStatuses.get(i).getElement().equals("Approved")){
                returnable = false;
            }
        }
        return returnable;
    }

    /** Records auditing feedback and updates status. */
    public void addAuditing(String auditor, String auditing) {
        int count = auditors.getSize();
        for (int i = 0; i < count; i++) {
            if (auditors.get(i).getElement().equals(auditor)) {
                if (auditing.equalsIgnoreCase("Approved")) {
                    setAuditorStatus(i, "Approved");
                } else if (auditing.equalsIgnoreCase("Rejected")) {
                    setAuditorStatus(i, "Rejected");
                    // Update overall status if any rejection occurs
                    this.status = "Lyric Rejected";
                }
                break;
            }
        }
        auditingHistory.add(auditor + ": " + auditing);
    }

    /** Sets an auditor's status. */
    public void setAuditorStatus(int index, String status) {
        auditorStatuses.get(index).setElement(status);
    }

    /** Updates the overall lyric status. */
    public void updateStatus (String status){
        this.status = status;
    }

    public int getID(){return lyricID;}
    public String getName(){return name;}
    public String getCurrentStep(){return currentStep;}
    public String getStatus(){return status;}
    public LinkedList<String> getAuditingHistory() {
        return auditingHistory;
    }
    /** Updates the current step in the process. */
    public void updateCurrentStep(String step) {
        this.currentStep = step;
    }
    
    /** Returns the latest auditing feedback. */
    public String getLatestAuditing() {
        if (auditingHistory.getSize() == 0) {
            return "None";
        }
        return auditingHistory.getLast();
    }
}
