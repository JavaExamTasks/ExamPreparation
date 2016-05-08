import java.math.BigInteger;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by pc on 4/8/2016.
 */
public class SoftuniNumerals {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        HashMap<String,String> dict = new HashMap<>();
        dict.put("aa", "0");
        dict.put("aba", "1");
        dict.put("bcc", "2");
        dict.put("cc", "3");
        dict.put("cdc", "4");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            String arg = input.substring(i, i + 2);

            if (dict.containsKey(arg)){
                sb.append(dict.get(arg));
                i++;
            }else {
                arg = input.substring(i, i + 3);
                sb.append(dict.get(arg));
                i += 2;
            }
        }

        BigInteger result = new BigInteger(sb.toString(), 5);
        System.out.println(result);
    }
}
