import java.io.FileNotFoundException;

public interface Ilog {
    void logString(int arg, int currentResult, String operation) throws FileNotFoundException;
}
