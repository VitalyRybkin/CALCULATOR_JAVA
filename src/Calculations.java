import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Calculations {

    private int calculationResult;

    public Calculations(int calculationResult) {
        this.calculationResult = calculationResult;
    }

    public void calculationsRun() throws FileNotFoundException {
        int arg = argInput();
        this.calculationResult = arg;
        logResult("Промежуточный результат", this.calculationResult);
        while (true){
            String operation = operationInput();
            if (operation.equals("+")){
                arg = argInput();
                ICalculateFactory calculateFactory = new SumCalculateFactory();
                ICalculate calculate = calculateFactory.createCalculation();
                Ilog ilog = new LogTxt(calculate);
                ilog.logString(arg, this.calculationResult, operation);
                this.calculationResult = calculate.calculations(arg, this.calculationResult);
                continue;
            }
            if (operation.equals("*")){
                arg = argInput();
                ICalculateFactory calculateFactory = new MultCalculateFactory();
                ICalculate calculate = calculateFactory.createCalculation();
                Ilog ilog = new LogTxt(calculate);
                ilog.logString(arg, this.calculationResult, operation);
                this.calculationResult = calculate.calculations(arg, this.calculationResult);
                continue;
            }
            if (operation.equals("=")){
                logResult("Результат расчета", this.calculationResult);
                break;
            }
        }

        System.out.print("Результат: " + this.calculationResult);
    }

    public int argInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите аргумент: ");
        return scanner.nextInt();
    }

    public String operationInput(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Введите операцию (+, *, =): ");
        return scanner.next();
    }

    public void logResult(String resultString, int currentResult){
        try (FileWriter writer = new FileWriter("log.txt", true)) {
            writer.write(String.format("%s: %d", resultString, currentResult));
            writer.append('\n');
            writer.flush();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
