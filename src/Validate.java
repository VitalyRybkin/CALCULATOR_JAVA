import java.util.Scanner;

class Validate {

    double ValidateInput (String input){
        Scanner scanner = new Scanner(System.in);
        while (true){
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Ошибка ввода! Введите число: ");
                input = scanner.next();
            }
        }
    }
}
