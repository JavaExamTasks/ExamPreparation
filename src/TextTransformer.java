import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/19/2016.
 */
public class TextTransformer {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        StringBuilder line = new StringBuilder();

        while (!input.equals("burp")){
            line.append(input);

            input = sc.nextLine();
        }

        String text = line.toString().replaceAll("\\s+", " ");

        Pattern pattern = Pattern.compile("([$&%'])([^$&%']+)\\1");
        Matcher matcher = pattern.matcher(text);

        //('$') with weight of 1, a percentage sign ('%') with weight of 2,
        // ampersand ('&') with weight of 3 and a single quote ('’') with weight of 4
        ArrayList<String> specialSymbols = new ArrayList<String>() {{
            addAll(Arrays.asList("$", "%", "&", "\'"));
        }};



        while (matcher.find()) {
            String spSymbol = matcher.group(1);
            String capturedString = matcher.group(2);
            int weight = specialSymbols.indexOf(spSymbol) + 1;

            ArrayList<Character> output = new ArrayList<Character>();

            for (int i = 0; i < capturedString.length(); i++) {
                char symbol = capturedString.charAt(i);

                if (i % 2 == 0){
                    output.add((char)(symbol + weight));
                }else{
                    output.add((char)(symbol - weight));
                }
            }

            for (Character character : output) {
                System.out.print(character);
            }

            System.out.print(" ");
        }
    }
}

