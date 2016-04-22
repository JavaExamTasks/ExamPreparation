import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/22/2016.
 */
public class WeirdScript {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int n = Integer.parseInt(console.nextLine());
        char key;

        n %= 52;

        if (n >= 1 && n <= 26){
            key = (char)('a' + n - 1);
        }else if (n >= 27 && n <= 52){
            n %= 27;
            key = (char)('A' + n );
        }else{
            key = 'Z';
        }

        StringBuilder text = new StringBuilder();

        String input = console.nextLine();

        while (!input.equals("End")){
            text.append(input);

            input = console.nextLine();
        }

        String doubleKey = String.format("%c%c", key, key);

        Pattern pattern = Pattern.compile(String.format("%s(.*?)%s", doubleKey, doubleKey));
        Matcher matcher = pattern.matcher(text.toString());

        while (matcher.find()) {
            System.out.println(matcher.group(1));
        }

    }
}
