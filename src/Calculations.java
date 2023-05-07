import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class Calculations {

    ComplexNumber currentComplexNumber;

    Validate validate = new Validate();

    private final String logFileType = "txt";

    Calculations(ComplexNumber currentComplexNumber) {
        this.currentComplexNumber = currentComplexNumber;
    }

    void calculationsRun() throws IOException {
        ComplexNumber complexNumber = numInput();
        this.currentComplexNumber.r = complexNumber.r;
        this.currentComplexNumber.i = complexNumber.i;
        usersInput(complexNumber);
        logResult("Результат", this.currentComplexNumber, logFileType);
        while (true){
            String operation = operationInput();
            if (operation.equals("=")){
                logResult("Результат", this.currentComplexNumber, logFileType);
                break;
            }
                complexNumber = numInput();
                usersInput(complexNumber);
                ICalculate calculate = setCalculation(operation);
                Ilog ilog = setLogFile(calculate, logFileType);
                ilog.logString(complexNumber, this.currentComplexNumber, operation);
                this.currentComplexNumber = calculate.calculations(complexNumber, this.currentComplexNumber);
        }

        String operand = getOperand(this.currentComplexNumber);
        System.out.printf("Результат расчета: %.2f%s%.2fi", this.currentComplexNumber.r, operand, this.currentComplexNumber.i);
    }

    ICalculate setCalculation(String operation) {
        ICalculate calculate;
        ICalculateFactory calculateFactory;
        if (operation.equals("+")) {
            calculateFactory = new SumCalculateFactory();
        }
        else {
            calculateFactory = new MultCalculateFactory();
        }
        calculate = calculateFactory.createCalculation();
        return calculate;
    }

    Ilog setLogFile (ICalculate calculate, String logFileType) {
        Ilog ilog;
        if (logFileType.equals("txt")) {
            ilog = new LogTxt(calculate);
        }
        else {
            ilog = new LogCsv(calculate);
        }
        return ilog;
    }

    String operationInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите операцию (+, *, =): ");
        return scanner.next();
    }

    ComplexNumber numInput(){
        Scanner scanner = new Scanner(System.in);
        double r;
        double i;
        System.out.print("Введите действительную часть числа: ");
        String input = scanner.next();
        r = validate.ValidateInput(input);
        System.out.print("Введите мнимую часть числа: ");
        input = scanner.next();
        i = validate.ValidateInput(input);
        return new ComplexNumber(r, i);
    }

    void usersInput (ComplexNumber complexNumber){
        String operand = getOperand(complexNumber);
        System.out.println("Вы ввели: " + complexNumber.r + operand + complexNumber.i + "i");
    }

    void logResult(String resultString, ComplexNumber currentResult, String logFileType){
        String operand = getOperand(currentResult);
        if (logFileType.equals("txt")) {
            try (FileWriter writer = new FileWriter("log.txt", true)) {

                writer.write(String.format("%s: %.2f%s%.2fi, пользователь ввел, null\n",
                        resultString, currentResult.r, operand, currentResult.i));

                writer.flush();
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }

        if (logFileType.equals("csv")) {
            try {
                FileWriter csvWriter = new FileWriter("LogCsv.csv", true);

                csvWriter.write(String.format("%s, %.2f%s%.2fi, пользователь ввел, null\n",
                        resultString, currentResult.r, operand, currentResult.i));

                csvWriter.flush();
                csvWriter.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    String getOperand(ComplexNumber complexNumber) {
        String operand;
        if (complexNumber.i >= 0.0) {
            operand = "+";
        }
        else operand ="";
        return operand;
    }
}
