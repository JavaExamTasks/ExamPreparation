import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 5/5/2016.
 */
public class BasicMarkupLanguage1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        Pattern pattern1 = Pattern.compile("(inverse|reverse|repeat)");

        int count = 1;

        while (!line.equals("<stop/>")){
            Matcher matcher1 = pattern1.matcher(line);

            if(matcher1.find()){
                String command = matcher1.group();

                switch (command) {
                    case "inverse":
                        Pattern pattern2 = Pattern.compile("content\\s*=\\s*\"(.+?)\"");
                        Matcher matcher2 = pattern2.matcher(line);

                        if(matcher2.find()){
                            String str = matcher2.group(1);
                            char[] arr = str.toCharArray();
                            String output = inverseStr(arr);
                            System.out.println(count + ". " + output);
                            count++;
                        }
                        break;
                    case "reverse":
                        pattern2 = Pattern.compile("content\\s*=\\s*\"(.+?)\"");
                        matcher2 = pattern2.matcher(line);

                        if(matcher2.find()){
                            String str = matcher2.group(1);
                            StringBuilder sb = new StringBuilder(str);
                            String output = reverseStr(sb);
                            System.out.println(count + ". " + output);
                            count++;
                        }
                        break;
                    case "repeat":
                        Pattern pattern3 = Pattern.compile("value\\s*=\\s*\"(.+)\"\\s*content\\s*=\\s*\"(.+)\"");
                        Matcher matcher3 = pattern3.matcher(line);

                        if(matcher3.find()){
                            int iter = Integer.parseInt(matcher3.group(1));
                            String str = matcher3.group(2);

                            for (int i = 0; i < iter; i++) {
                                System.out.println(count + ". " + str);
                                count++;
                            }
                        }
                        break;
                	default:
                        break;
                }
            }

            line = sc.nextLine();
        }
    }

    private static String reverseStr(StringBuilder sb) {
        sb.reverse();
        return sb.toString();
    }

    private static String inverseStr(char[] arr) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < arr.length; i++) {
            char symbol = arr[i];
            if (Character.isLowerCase(symbol)){
                sb.append(Character.toUpperCase(symbol));
            } else if (Character.isUpperCase(symbol)){
                sb.append(Character.toLowerCase(symbol));
            } else{
                sb.append(symbol);
            }
        }

        return sb.toString();
    }
}
