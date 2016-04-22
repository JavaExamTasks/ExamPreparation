import java.util.Scanner;

/**
 * Created by pc on 4/22/2016.
 */
public class TinySporebat {
    public static void main(String[] args) {
        int points = 5800;
        int glowcaps = 0;
        Scanner console = new Scanner(System.in);
        String input = console.nextLine();

        while (!input.equals("Sporeggar")){
            for (int i = 0; i < input.length() - 1; i++) {
                char symbol = input.charAt(i);

                if (symbol == 'L'){
                    points += 200;
                }else{
                    points -= symbol;
                }

                if (points <= 0){
                    System.out.printf("Died. Glowcaps: %d%n", glowcaps);
                    return;
                }
            }

            glowcaps += input.charAt(input.length() - 1) - '0';

            input = console.nextLine();
        }

        System.out.printf("Health left: %d%n", points);

        if (glowcaps >= 30){
            System.out.printf("Bought the sporebat. Glowcaps left: %d%n", glowcaps - 30);
        }else{
            System.out.printf("Safe in Sporeggar, but another %d Glowcaps needed.%n", 30 - glowcaps);
        }
    }
}
