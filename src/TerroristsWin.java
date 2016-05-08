import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 5/7/2016.
 */
public class TerroristsWin {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        StringBuilder output = new StringBuilder(line);

        Pattern pattern = Pattern.compile("\\|(.*?)\\|");
        Matcher matcher = pattern.matcher(line);

        while(matcher.find()){
            String bomb = matcher.group(1);
            int bombPower = 0;

            for (int i = 0; i < bomb.length(); i++) {
                bombPower += bomb.charAt(i);
            }

            int lastDigit = bombPower % 10;
            int startIndex = matcher.start();
            startIndex -= lastDigit;
            int lastIndex = matcher.end();
            lastIndex += lastDigit;

            for (int i = startIndex; i < lastIndex; i++) {
                if (i >= 0 && i < line.length()){
                    output.replace(i, i + 1, ".");
                }
            }
        }

        System.out.println(output.toString());
    }
}
