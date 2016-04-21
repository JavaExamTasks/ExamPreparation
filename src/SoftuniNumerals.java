import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/8/2016.
 */
public class SoftuniNumerals {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        ArrayList<String> arr = new ArrayList<String>() {{
            addAll(Arrays.asList("aa", "aba", "bcc", "cc", "cdc"));
        }};

        Pattern pattern = Pattern
                .compile("(aa|aba|bcc|cc|cdc)");
        Matcher matcher = pattern.matcher(input);

        StringBuilder sb = new StringBuilder();

        while (matcher.find()){
            sb.append(arr.indexOf(matcher.group(1)));
        }

        BigInteger decimalNumber = new BigInteger(sb.toString(), 5);

        System.out.println(decimalNumber);
    }
}
