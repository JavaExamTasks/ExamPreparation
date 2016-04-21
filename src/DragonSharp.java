import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/18/2016.
 */
public class DragonSharp {
    static StringBuilder output = new StringBuilder();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());

        boolean isConditiontrue = false;

        for (int i = 1; i <= n; i++) {
            String input = sc.nextLine();

            Pattern patternCondition = Pattern.compile("(.+?) \"(.+)\";");
            Matcher matcherCondition = patternCondition.matcher(input);
            String operator = "";

            if (matcherCondition.find()){
                String[] inputArgs = matcherCondition.group(1).split("\\s+");
                int length = inputArgs.length;
                String toPrint = matcherCondition.group(2);

                switch (length) {
                    case 2:
                        //else out "pesho";

                        if (!isConditiontrue){
                            output.append(toPrint);
                            output.append("\n");
                        }
                        break;
                    case 3:
                        //if (5==5) out “gosho”;
                        String condition = inputArgs[1];
                        String[]digits= condition.split("([=<>]+)");
                        int firstDigit = Integer.parseInt(digits[0].replace("(", ""));
                        int secondDigit = Integer.parseInt(digits[1].replace(")", ""));
                        Pattern pattern = Pattern.compile("([=<>]+)");
                        Matcher matcher = pattern.matcher(condition);
                        if (matcher.find()){
                            operator = matcher.group(1);
                        }

                        isConditiontrue = checkCondition(firstDigit, secondDigit, operator);
                        if (isConditiontrue){
                            output.append(toPrint);
                            output.append("\n");
                        }
                        break;
                    case 4:
                        //else loop 3 out "pesho";
                        int count = Integer.parseInt(inputArgs[2]);

                        if (!isConditiontrue){
                            printString(toPrint, count);
                        }
                        break;
                    default:
                        //if (5==5) loop 3 out “gosho”;
                        condition = inputArgs[1];
                        digits= condition.split("([=<>]+)");
                        firstDigit = Integer.parseInt(digits[0].replace("(", ""));
                        secondDigit = Integer.parseInt(digits[1].replace(")", ""));
                        pattern = Pattern.compile("([=<>]+)");
                        matcher = pattern.matcher(condition);
                        if (matcher.find()){
                            operator = matcher.group(1);
                        }
                        count = Integer.parseInt(inputArgs[3]);

                        isConditiontrue = checkCondition(firstDigit, secondDigit, operator);
                        if (isConditiontrue){
                            printString(toPrint, count);
                        }
                        break;
                }
            }else{
                System.out.println("Compile time error @ line " + i);
                return;
            }
        }

        System.out.println(output.toString());
    }

    private static void printString(String toPrint, int count) {
        for (int i = 0; i < count; i++) {
            output.append(toPrint);
            output.append("\n");
        }
    }

    private static boolean checkCondition(int firstDigit, int secondDigit, String operator) {
        switch (operator) {
            case "==":
                if (firstDigit == secondDigit){
                    return true;
                }
                break;
            case ">":
                if (firstDigit > secondDigit){
                    return true;
                }
                break;
            case "<":
                if (firstDigit < secondDigit){
                    return true;
                }
                break;
            default:
                break;
        }

        return false;
    }
}
