// Logger.java
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger instance;
    private String logFilePath;

    private Logger(String logFilePath) {
        this.logFilePath = logFilePath;
    }

    public static Logger getInstance(String logFilePath) {
        if (instance == null) {
            instance = new Logger(logFilePath);
        }
        return instance;
    }

    public void logInfo(String message) {
        log("[INFO] " + message);
    }

    public void logWarning(String message) {
        log("[WARNING] " + message);
    }

    public void logError(String message) {
        log("[ERROR] " + message);
    }

    private void log(String message) {
        try (FileWriter writer = new FileWriter(logFilePath, true)) {
            writer.write(message + "\n");
        } catch (IOException e) {
            System.err.println("Logging failed: " + e.getMessage());
        }
    }
}
