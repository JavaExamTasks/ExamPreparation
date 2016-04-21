import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by pc on 4/17/2016.
 */
public class ArrangeIntegers {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String input = console.nextLine();
        String[] numbers = input.split(", ");

        TreeMap<String, String> sortedNumbers = new TreeMap<>();
        HashMap<String, Integer> repetitions = new HashMap<>();

        HashMap<String, String> enNames = new HashMap<>();
        enNames.put("0", "zero");
        enNames.put("1", "one");
        enNames.put("2", "two");
        enNames.put("3", "three");
        enNames.put("4", "four");
        enNames.put("5", "five");
        enNames.put("6", "six");
        enNames.put("7", "seven");
        enNames.put("8", "eight");
        enNames.put("9", "nine");

        for (int i = 0; i < numbers.length; i++) {
            String number = numbers[i];

            if (enNames.containsKey(number)){
                String englishName = enNames.get(number);

                if (!sortedNumbers.containsKey(englishName)){
                    sortedNumbers.put(englishName, number);
                }else{
                    if (!repetitions.containsKey(englishName)){
                        repetitions.put(englishName, 0);
                    }

                    int oldValue = repetitions.get(englishName);
                    repetitions.put(englishName, oldValue + 1);
                }

            }else{
                String englishName = "";

                for (int j = 0; j < number.length(); j++) {
                    char ch = number.charAt(j);
                    String currNumber = "" + ch;
                    englishName += enNames.get(currNumber);
                    if (j != number.length() - 1){
                        englishName += "-";
                    }
                }

                if (!sortedNumbers.containsKey(englishName)){
                    sortedNumbers.put(englishName, number);
                }else{
                    if (!repetitions.containsKey(englishName)){
                        repetitions.put(englishName, 0);
                    }

                    int oldValue = repetitions.get(englishName);
                    repetitions.put(englishName, oldValue + 1);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String, String> number : sortedNumbers.entrySet()) {
            String key = number.getKey();
            String value = number.getValue();
            if (repetitions.containsKey(key)){
                int count = repetitions.get(key);

                for (int i = 0; i < count; i++) {
                    sb.append(value + ", ");
                }
            }
            sb.append(value + ", ");
        }

        String output = sb.toString().substring(0, sb.length() - 2);
        System.out.println(output);
    }
}
