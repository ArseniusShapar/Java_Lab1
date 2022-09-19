import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введіть строку: ");
        String numbers = in.nextLine();
        numbers = numbers.replaceAll("\\\\n", "\n");
        StringCalculator StrCalculate = new StringCalculator();
        System.out.println("Результат: " + StrCalculate.add(numbers));
    }
}