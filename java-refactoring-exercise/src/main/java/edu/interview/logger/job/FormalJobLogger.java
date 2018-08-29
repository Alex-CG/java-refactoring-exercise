package edu.interview.logger.job;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Stream;

public final class FormalJobLogger {

    private final static String TYPE_NOT_SPECIFIED = "Error or Warning or Message must be specified";

    private HashMap<LogDestination, Boolean> logToMap;
    private HashMap<LogType, Boolean> logTypeMap;
    private static Map dbParams;
    private Logger logger;

    public FormalJobLogger() {
        logToMap = new HashMap<>();
        logTypeMap = new HashMap<>();
        logger = Logger.getLogger("MyLog");

        Stream.of(LogDestination.values()).forEach( dest -> logToMap.put(dest, Boolean.FALSE) );
        Stream.of(LogType.values()).forEach( dest ->  logTypeMap.put(dest, Boolean.FALSE) );
    }

    public void enableLogTo(LogDestination dest, Boolean enable) {
        logToMap.put(dest, enable);
    }

    public void enableLogType(LogType type, Boolean enable) {
        logTypeMap.put(type, enable);
    }

    public void logMessage(String message, LogType type) throws Exception {
        message = Objects.toString(message, "").trim();
        if (message.isEmpty()) {
            return;
        }

        // add Invalid configuration validation
//        if(Stream.of(logToMap).filter(set -> map.get(map.))){
//        }

        if (type == null) {
            throw new LoggerException(TYPE_NOT_SPECIFIED);
        }

        MessageLog msgLog = new MessageLog(message, type);

        if(logToMap.get(LogDestination.FILE)) {
            writeLogInFile(msgLog, dbParams.get("logFileFolder").toString());
        }

        if(logToMap.get(LogDestination.CONSOLE)) {
            printInConsole(msgLog);
        }

        if(logToMap.get(LogDestination.DATABASE)) {
            saveLogInDB(msgLog, dbParams);
        }

    }

    private void printInConsole(MessageLog msgLog) {
        ConsoleHandler ch = new ConsoleHandler();
        logger.addHandler(ch);
        logger.log(Level.INFO, msgLog.toString());
    }

    private void writeLogInFile(MessageLog msgLog, String fileName) {
        try (
            FileOutputStream outputStream = new FileOutputStream(fileName))
        {
            outputStream.getChannel().position(0);
            outputStream.write(msgLog.toString().getBytes());
        } catch (IOException e) {
            throw new LoggerException(e.getMessage());
        }
    }

    private void saveLogInDB(MessageLog msgLog, Map dbParams) {
        try(
            Connection conn = createConnection(dbParams);
            Statement stmt = conn.createStatement())
        {
            stmt.executeUpdate("insert into Log_Values('" + msgLog.toString() + "', " + msgLog.getType() + ")");
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

    private class MessageLog {
        private final String message;
        private final LogType type;
        private Date date;
        MessageLog(final String message, final LogType type) {
            this(message, type, new Date());
        }
        MessageLog(final String message, final LogType type, final Date date) {
            this.message = message;
            this.type = type;
            this.date = date;
        }
        public String getMessage() {
            return message;
        }
        public LogType getType() {
            return type;
        }
        public Date getDate() {
            return date;
        }
        @Override
        public String toString() {
            return type.getText() + " " + DateFormat.getDateInstance(DateFormat.LONG).format(date) + message;
        }
    }
}
