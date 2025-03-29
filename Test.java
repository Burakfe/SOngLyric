package HW1;
//-----------------------------------------------------
// Title: SongLyricAuditingSystem Class
// Author: Arda Eray Başparmak
// ID: 1010520243
// Author: Burak Efe Taşkın
// ID: 10300188292
// Section: 3
// Assignment: 1
// Description: main class that reads commands from a file and processes them using the SongLyricAuditingSystem.
//-----------------------------------------------------

import java.io.*;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        SongLyricAuditingSystem system = new SongLyricAuditingSystem();
        try {
            File file = new File("sample_input.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (line.isEmpty())
                    continue;
                processCommand(line, system);
            }
            scanner.close();
        } catch (Exception e) {
            System.out.println("Error reading input file: " + e.getMessage());
        }
    }
    
    private static void processCommand(String line, SongLyricAuditingSystem system) {
        // Handle adding a new song lyric
        if (line.startsWith("ADD_SONGLYRIC")) {
            try {
                int firstQuote = line.indexOf("\"");
                int secondQuote = line.indexOf("\"", firstQuote + 1);
                String beforeQuote = line.substring(0, firstQuote).trim();
                String name = line.substring(firstQuote + 1, secondQuote);
                String afterQuote = line.substring(secondQuote + 1).trim();
                String[] partsBefore = beforeQuote.split("\\s+");
                int id = Integer.parseInt(partsBefore[1]);
                boolean flag = Boolean.parseBoolean(afterQuote);
                SongLyric song = new SongLyric(id, name, flag);
                system.addSongLyric(song);
            } catch (Exception e) {
                System.out.println("Error processing ADD_SONGLYRIC: " + e.getMessage());
            }
        // Handle adding an auditor to a song lyric
        } else if (line.startsWith("ADD_AUDITOR")) {
            try {
                int firstQuote = line.indexOf("\"");
                int secondQuote = line.indexOf("\"", firstQuote + 1);
                String beforeQuote = line.substring(0, firstQuote).trim();
                String auditorName = line.substring(firstQuote + 1, secondQuote);
                String[] partsBefore = beforeQuote.split("\\s+");
                int id = Integer.parseInt(partsBefore[1]);
                SongLyric song = system.getSongLyricById(id);
                if (song != null) {
                    song.addAuditor(auditorName);
                }
            } catch (Exception e) {
                System.out.println("Error processing ADD_AUDITOR: " + e.getMessage());
            }
        // Handle checking the status of a song lyric
        } else if (line.startsWith("CHECK_STATUS")) {
            try {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[1]);
                System.out.println(system.getSongLyricStatus(id));
            } catch (Exception e) {
                System.out.println("Error processing CHECK_STATUS: " + e.getMessage());
            }
        // Handle processing the next song lyric in the queue
        } else if (line.startsWith("PROCESS_QUEUE")) {
            try {
                String[] parts = line.split("\\s+");
                String step = parts[1];
                system.processNextSongLyricInQueue(step);
            } catch (Exception e) {
                System.out.println("Error processing PROCESS_QUEUE: " + e.getMessage());
            }
        // Handle adding auditing feedback to a song lyric
        } else if (line.startsWith("ADD_AUDITING")) {
            try {
                int firstQuote = line.indexOf("\"");
                int secondQuote = line.indexOf("\"", firstQuote + 1);
                String auditInfo = line.substring(firstQuote + 1, secondQuote);
                String[] auditParts = auditInfo.split(":", 2);
                if (auditParts.length < 2) {
                    System.out.println("Invalid ADD_AUDITING format. Expected \"AuditorName: Feedback\"");
                    return;
                }
                String auditorName = auditParts[0].trim();
                String feedback = auditParts[1].trim();
                String[] partsBefore = line.substring(0, firstQuote).trim().split("\\s+");
                int id = Integer.parseInt(partsBefore[1]);
                SongLyric song = system.getSongLyricById(id);
                if (song != null) {
                    song.addAuditing(auditorName, feedback);
                }
            } catch (Exception e) {
                System.out.println("Error processing ADD_AUDITING: " + e.getMessage());
            }
            
        // Handle moving a song lyric to the next step
        } else if (line.startsWith("MOVE_TO_NEXT_STEP")) {
            try {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[1]);
                SongLyric song = system.getSongLyricById(id);
                if (song != null) {
                    system.moveSongLyricToNextStep(song);
                }
            } catch (Exception e) {
                System.out.println("Error processing MOVE_TO_NEXT_STEP: " + e.getMessage());
            }
            
        // Handle moving a song lyric to the stack
        } else if (line.startsWith("MOVE_TO_STACK")) {
            try {
                String[] parts = line.split("\\s+");
                int id = Integer.parseInt(parts[1]);
                SongLyric song = system.getSongLyricById(id);
                if (song != null) {
                    system.moveSongLyricToStack(song);
                }
            } catch (Exception e) {
                System.out.println("Error processing MOVE_TO_STACK: " + e.getMessage());
            }
        // Handle displaying the song lyric stack
        } else if (line.startsWith("SHOW_STACK")) {
            system.showStack();
        // Handle displaying all song lyrics
        } else if (line.startsWith("SHOW_ALL_SONGLYRICS")) {
            system.showAllSongLyrics();
        }
    }
}