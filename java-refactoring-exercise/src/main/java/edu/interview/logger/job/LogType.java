package edu.interview.logger.job;

public enum LogType {

    MESSAGE(1, "message"), WARNING(2, "warning"), ERROR(3, "error");

    int type;
    String text;

    LogType(int type, String text) {
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

