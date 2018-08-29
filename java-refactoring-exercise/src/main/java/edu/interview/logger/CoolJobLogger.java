package edu.interview.logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.*;

public class CoolJobLogger {

    private final static String TYPE_NOT_SPECIFIED = "Error or Warning or Message must be specified";

    public static void main(String... args) {

    }

    public static void logMessage(String message, MessageType type) throws Exception {
        /*String messageText = Optional.ofNullable(message).map(String::trim).orElse("");
        if (messageText.isEmpty()) {
            return;
        }*/
        /*message = (message == null) ? "" : message.trim();
        if (message.isEmpty()) {
            return;
        }*/
        message = Objects.toString(message, "").trim();
        if (message.isEmpty()) {
            return;
        }

        // add log to validation

        if (type == null) {
            throw new LoggerException(TYPE_NOT_SPECIFIED);
        }

        String l = getLogMessage(message, type);

    }

    private static String getLogMessage(String message, MessageType type) {
        return type.getText() + " " + DateFormat.getDateInstance(DateFormat.LONG).format(new Date()) + message;
    }

    private void saveLogInDB(String message, MessageType type, Map dbParams) {
        try(
            Connection conn = createConnection(dbParams);
            Statement stmt = conn.createStatement())
        {
            stmt.executeUpdate("insert into Log_Values('" + message + "', " + type.getType() + ")");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private Connection createConnection(Map dbParams) throws SQLException {
        Properties connectionProps = new Properties();
        connectionProps.put("user", dbParams.get("userName"));
        connectionProps.put("password", dbParams.get("password"));

        String url = new StringBuilder()
                .append("jdbc:")
                .append(dbParams.get("dbms"))
                .append("://")
                .append(dbParams.get("serverName"))
                .append(":")
                .append(dbParams.get("portNumber"))
                .append("/").toString();

        return DriverManager.getConnection(url, connectionProps);
    }

}

enum MessageType {
    MESSAGE(1, "message"), WARNING(2, "warning"), ERROR(3, "error");
    int type;
    String text;
    MessageType(int type, String text) {
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

enum LogDestination {
    FILE, CONSOLE, DATABASE
}

class LoggerException extends RuntimeException {
    public LoggerException(String message) {
        super(message);
    }
}

class LogTo {



    LogTo(){

    }
}
