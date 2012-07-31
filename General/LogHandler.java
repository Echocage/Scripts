/*
 * Copyright (c) 2012. Redex Scripting - Unauthorized use prohibited by author.
 */

import java.awt.*;
import java.util.LinkedList;

/**
 * Created by IntelliJ IDEA.
 * User: Swipe
 * Date: 7/14/12
 * Time: 10:28 PM
 * To change this template use File | Settings | File Templates.
 */
public class LogHandler {
    public static int totalCapacity = 5;
    public static final int X_LOC = 5;
    public static final int Y_LOC = 380;
    public static LinkedList<LogEntry> Entries = new LinkedList<LogEntry>();

    public static void add(LogEntry logEntry) {
        Entries.push(logEntry);
    }

    public static void Print(String s) {
        add(new LogEntry(s, Color.WHITE));
    }

    public static void Print(String s, Color c) {
        add(new LogEntry(s, c));
    }

    public static void Draw(Graphics g) {
        Font f = new Font("Calibri", Font.BOLD, 12);
        g.setFont(f);
        int i = 0;
        for (LogEntry logEntry : Entries) {
            g.setColor(new Color(logEntry.getColor().getRed(), logEntry.getColor().getGreen(), logEntry.getColor().getBlue(), logEntry.alpha));
            g.drawString(logEntry.getText(), X_LOC, Y_LOC - (i * 15));
            if (i > 4) {
                logEntry.alpha -= 20;
            }
            if (logEntry.alpha < 0) {
                Entries.remove(logEntry);
            }
            i++;
        }
    }
}