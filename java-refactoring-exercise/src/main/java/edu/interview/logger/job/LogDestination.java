package edu.interview.logger.job;

public enum LogDestination {

    FILE(1, "FILE"), CONSOLE(2, "CONSOLE"), DATABASE(3, "DATABASE");

    int type;
    String text;

    LogDestination(int type, String text) {
        this.type = type;
        this.text = text;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

}

