import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/17/2016.
 */
public class GandalfStash {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int mood = Integer.parseInt(sc.nextLine());
        String input = sc.nextLine();

        Pattern pattern = Pattern.compile("([a-zA-Z]+)");
        Matcher matcher = pattern.matcher(input);

        while(matcher.find()) {
            String food = matcher.group(1).toLowerCase();

            switch (food) {
                case "cram":
                    mood += 2;
                    break;
                case "lembas":
                    mood += 3;
                    break;
                case "apple":
                    mood += 1;
                    break;
                case "melon":
                    mood += 1;
                    break;
                case "honeycake":
                    mood += 5;
                    break;
                case "mushrooms":
                    mood -= 10;
                    break;
                default:
                    mood -= 1;
                    break;

            }
        }

        System.out.println(mood);

        if (mood < -5){
            System.out.println("Angry");
        }else if (mood >= -5 && mood <= 0){
            System.out.println("Sad");
        }else if (mood > 0 && mood <= 15){
            System.out.println("Happy");
        }else if (mood > 15){
            System.out.println("Special JavaScript mood");
        }
    }
}
