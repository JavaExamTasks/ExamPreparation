import java.util.Scanner;

/**
 * Created by pc on 4/17/2016.
 */
public class LetterschangeNumbers {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] dimentions = sc.nextLine().split("\\s+");

        double result = 0;
        double finalSum = 0;

        for (int i = 0; i < dimentions.length; i++) {
            String dimention = dimentions[i];
            char letter1 = dimention.charAt(0);
            char letter2 = dimention.charAt(dimention.length() - 1);
            String numberAsString = dimention.substring(1, dimention.length() - 1);
            double number = Double.parseDouble(numberAsString);

            if (letter1 >= 'a' && letter1 <= 'z'){
                result = number * (int)(letter1 - 'a' + 1);
            }else{
                result = number / (int)(letter1 - 'A' + 1);
            }

            if (letter2 >= 'a' && letter2 <= 'z'){
                result = result + (int)(letter2 - 'a' + 1);
            }else{
                result = result - (int)(letter2 - 'A' + 1);
            }

            finalSum += result;
        }

        System.out.printf("%.2f", finalSum);
    }
}
