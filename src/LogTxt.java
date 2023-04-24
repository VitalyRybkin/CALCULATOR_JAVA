import java.io.*;

public class LogTxt implements Ilog{

    ICalculate calculate;

    public LogTxt(ICalculate calculate) {
        this.calculate = calculate;
    }

    @Override
    public void logString(int arg, int currentResult, String operation) {
        String logString = String.format("Промежуточный результат: %d, пользователь ввел: %s %d\n", currentResult, operation, arg);
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(logString);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
