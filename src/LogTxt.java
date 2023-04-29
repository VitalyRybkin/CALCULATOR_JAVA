import java.io.FileWriter;
import java.io.IOException;

class LogTxt implements Ilog {

    ICalculate calculate;

    LogTxt(ICalculate calculate) {
        this.calculate = calculate;
    }

    @Override
    public void logString(ComplexNumber arg, ComplexNumber currentResult, String operation) {
        String operandCurrentResult;
        if (currentResult.i >= 0.0) {
            operandCurrentResult = "+";
        } else operandCurrentResult = "";

        String operandArg;
        if (arg.i >= 0.0) {
            operandArg = "+";
        } else operandArg = "";

        String logString = String.format("Результат: %.2f%s%.2fi, пользователь ввел: %s %.2f%s%.2fi\n",
                currentResult.r, operandCurrentResult, currentResult.i, operation, arg.r, operandArg, arg.i);
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(logString);
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }

    }
}
