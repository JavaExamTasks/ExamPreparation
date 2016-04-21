import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/16/2016.
 */
public class BasicMarkupLanguage {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int countLines = 1;

        while (true) {
            String input = sc.nextLine();

            if (input.equals("<stop/>")) {
                break;
            }

            String[] inputArgs = input.split("=");

            Pattern pattern = Pattern
                    .compile("(inverse|reverse|repeat)");
            Matcher matcher = pattern.matcher(input);

            if(!matcher.find()) {
                continue;
            }

            String command = matcher.group(1);
            String content = "";

            Pattern pat = Pattern.compile("\"(.+)\"");
            Matcher matcher2 = pat.matcher(inputArgs[1]);

            if(matcher2.find()) {
                content = matcher2.group(1);
            }else{
                continue;
            }


            switch (command) {
                case "inverse":
                    inverseContent(content, countLines);
                    countLines++;
                    break;
                case "reverse":
                    reverseContent(content, countLines);
                    countLines++;
                    break;
                case "repeat":
                    Matcher matcher3 = pat.matcher(inputArgs[2]);

                    if (matcher3.find()){
                        int count = Integer.parseInt(content);
                        content = matcher3.group(1);
                        for (int j = 0; j < count; j++) {
                            repeatValue(content, countLines );
                            countLines++;
                        }
                    }

                    break;
                default:
                    break;

            }
    }
}

    private static void repeatValue(String content, int i) {
            System.out.println(i + ". " + content);
    }

    private static void reverseContent(String content, int i) {
        System.out.print(i + ". ");

        for (int j = content.length() - 1; j >= 0 ; j--) {
            System.out.print(content.charAt(j));
        }

        System.out.println();
    }

    private static void inverseContent(String content, int i) {
        System.out.print(i + ". ");

        for (int j = 0; j < content.length(); j++) {
            if (content.charAt(j) >= 'a' && content.charAt(j) <= 'z'){
                System.out.print((char)(content.charAt(j) - 32));
            }else if (content.charAt(j) >= 'A' && content.charAt(j) <= 'Z'){
                System.out.print((char)(content.charAt(j) + 32));
            }else{
                System.out.print(content.charAt(j));
            }
        }

        System.out.println();
    }
}
