package com.github.mathlon26.ribble.io.output.sys;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public final class Logger {

    public enum LogLevel {
        DEBUG,
        WARNING,
        INFO,
        ERROR,
    }

    private static volatile Logger instance;
    private LogLevel level;
    private PrintWriter fileWriter;

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private Logger() {
        this.level = LogLevel.DEBUG;
    }

    public static Logger getInstance() {
        if (instance == null) {
            instance = new Logger();
            instance.out("--- Starting a new log ---");
        }
        return instance;
    }

    public static Logger getInstance(String logPath) {
        if (instance == null) {
            instance = new Logger();
            try {
                instance.fileWriter = new PrintWriter(new FileWriter(logPath, true), true);
            } catch (IOException e) {
                instance.log(LogLevel.ERROR, e.getMessage());
            }
            instance.out("--- Starting a new log ---");
        }
        return instance;
    }

    // Set log level
    public void setLevel(LogLevel level) {
        this.level = level;
    }

    // Optional: set a file to log to
    public void setLogFile(String path) {
        try {
            this.fileWriter = new PrintWriter(new FileWriter(path, true), true);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void log(LogLevel msgLevel, String message) {
        if (msgLevel.ordinal() < level.ordinal()) return;

        String timeStamp = LocalDateTime.now().format(formatter);
        String formattedMessage = String.format("[%s] [%s] %s", timeStamp, msgLevel, message);
        out(formattedMessage);
    }

    private void out(String msg) {
        System.out.println(msg);

        if (fileWriter != null) {
            fileWriter.println(msg);
        }
    }

    public void debug(String message) {
        log(LogLevel.DEBUG, message);
    }

    public void info(String message) {
        log(LogLevel.INFO, message);
    }

    public void warn(String message) {
        log(LogLevel.WARNING, message);
    }

    public void error(String message) {
        log(LogLevel.ERROR, message);
    }

    public void close() {
        instance.out("--- Ending the log ---\n");
        if (fileWriter != null) {
            fileWriter.close();
        }
    }
}
