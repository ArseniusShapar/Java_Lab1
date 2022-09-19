import java.util.ArrayList;
import java.util.List;
import java.lang.*;


public class StringCalculator {

    private String str;
    private final List<String> Delimiters = new ArrayList<>();
    private final List<Integer> Numbers = new ArrayList<>();
    private final List<Integer> NegativeNumbers = new ArrayList<>();

    private void AllDelimiters() {
        Delimiters.add(",");
        Delimiters.add("\n");

        if (!str.startsWith("//")) return;

        String strDelimiters = str.split("\n")[0].substring(2);
        str = str.replaceFirst("[^\n]+\n", "");
        for (String s : strDelimiters.split("]")) {
            Delimiters.add(s.substring(1));
        }
    }

    private void AllNumbers() {
        StringBuilder CurrentNumber = new StringBuilder();
        StringBuilder CurrentDelimiter = new StringBuilder();
        boolean LastIsDigit;
        boolean CurrentIsDigit = Character.isDigit(str.charAt(0));

        for (char c : str.toCharArray()) {
            LastIsDigit = CurrentIsDigit;
            CurrentIsDigit = Character.isDigit(c) || c == '-';

            if (CurrentIsDigit) CurrentNumber.append(c);
            else CurrentDelimiter.append(c);

            if (LastIsDigit && !CurrentIsDigit) {
                Numbers.add(Integer.parseInt(CurrentNumber.toString()));
                CurrentNumber.setLength(0);
            } else if (!LastIsDigit && CurrentIsDigit) {
                if (Delimiters.contains(CurrentDelimiter.toString())) CurrentDelimiter.setLength(0);
                else throw new IllegalArgumentException("Недопустимий ввід!");
            }
        }
        if (CurrentNumber.isEmpty()) throw new IllegalArgumentException("Недопустимий ввід!");
        else Numbers.add(Integer.parseInt(CurrentNumber.toString()));
    }

    private void AllNegativeNumbers() {
        for (Integer num : Numbers) if (num < 0) NegativeNumbers.add(num);
    }

    public int add(String numbers) {
        str = numbers;
        AllDelimiters();
        AllNumbers();
        AllNegativeNumbers();

        if (NegativeNumbers.size() > 0)
            throw new IllegalArgumentException("Недозволені від'ємні числа: " + NegativeNumbers);

        int sum = 0;
        for (Integer num : Numbers) if (num <= 1000) sum += num;

        return sum;
    }
}