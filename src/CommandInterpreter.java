import java.util.*;

/**
 * Created by pc on 4/20/2016.
 */
public class CommandInterpreter {
    static String[] numbers;
    static int size;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        numbers = console.nextLine().split("\\s+");
        size = numbers.length;

        String input = console.nextLine();

        while (!input.equals("end")){
            String[]cmd = input.split("\\s+");
            String command = cmd[0];

            switch (command) {
                case "reverse":
                    //reverse from [start] count [count]
                    int start = Integer.parseInt(cmd[2]);
                    int count = Integer.parseInt(cmd[4]);

                    if (start < 0 || count < 0 || start > size - 1 || count > size || (start + count) > size){
                        System.out.println("Invalid input parameters.");
                    }else
                    reverseArr(start, count);
                    break;
                case "sort":
                    start = Integer.parseInt(cmd[2]);
                    count = Integer.parseInt(cmd[4]);

                    if (start < 0 || count < 0 || start > size - 1 || count > size || (start + count) > size){
                        System.out.println("Invalid input parameters.");
                    }else
                    sortArr(start, count);
                    break;
                case "rollLeft":
                    //rollLeft [count] times
                    count = Integer.parseInt(cmd[1]) % size;

                    if (count < 0 ){
                        System.out.println("Invalid input parameters.");
                    }else{
                        numbers = rollLeft(count);
                    }
                    break;
                case "rollRight":
                    count = Integer.parseInt(cmd[1]) % size;

                    if (count < 0){
                        System.out.println("Invalid input parameters.");
                    }else{
                        numbers = rollRight(count);
                    }
                    break;
                default:
                    break;
            }


            input = console.nextLine();
        }

        System.out.println("[" + String.join(", ", numbers) + "]");

    }

    private static String[] rollRight(int count) {
        String[] arr = new String[size];

        for (int i = 0; i < size; i++) {
            arr[i] = numbers[(size - count + i ) % size];
        }

        return arr;
    }

    private static String[] rollLeft(int count) {
        String[] arr = new String[size];

        for (int i = 0; i < size; i++) {
            arr[i] = numbers[(count + i) % size];
        }

        return arr;
    }

    private static void sortArr(int start, int count) {
        String[] numToSort = new String[count];

        for (int i = start, j = 0; i < start + count; i++, j++) {
            String number = numbers[i];
            numToSort[j] = number;
        }

        Arrays.sort(numToSort);

        int index = 0;

        for (int i = start; i < start + count; i++) {
            numbers[i] = numToSort[index];
            index++;
        }
    }

    private static void reverseArr(int start, int count) {
        ArrayList<String> numToReverse = new ArrayList<>();

        for (int i = start; i < start + count; i++) {
            String number = numbers[i];
            numToReverse.add(number);
        }
        
        int index = numToReverse.size() - 1;

        for (int i = start; i < start + count; i++) {
            numbers[i] = numToReverse.get(index);
            index--;
        }
    }
}
