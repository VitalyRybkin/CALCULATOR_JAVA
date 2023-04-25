import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ComplexNumber currentComplexNumber = new ComplexNumber(0.0, 0.0);
        Calculations calculations = new Calculations(currentComplexNumber);

        calculations.calculationsRun();
    }
}