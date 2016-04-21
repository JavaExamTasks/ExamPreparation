import java.util.Scanner;

/**
 * Created by pc on 4/18/2016.
 */
public class CollectResources {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String input = console.nextLine();
        String[] resourses = input.split("\\s+");

        int length = resourses.length;
        int n = Integer.parseInt(console.nextLine());
        int maxSum = 0;

        for (int i = 0; i < n; i++) {
            boolean[]visited = new boolean[length];

            String[] a = console.nextLine().split("\\s+");
            int start = Integer.parseInt(a[0]);
            int step = Integer.parseInt(a[1]);

            int currSum = 0;

            while (true){
                if (visited[start]){
                    break;
                }

                String resourse = resourses[start];
                int quantity = 1;

                if (isValid(resourse)){
                    int indexOfDash = resourse.indexOf("_");

                    if (indexOfDash >= 0){
                        String[]b= resourse.split("_");
                        quantity = Integer.parseInt(b[1]);
                    }

                    currSum += quantity;
                    visited[start] = true;
                }

                start += step;
                start %= length;

                if (currSum > maxSum){
                    maxSum = currSum;
                }
            }
        }

        System.out.println(maxSum);
    }

    private static boolean isValid(String s){
        if (s.startsWith("stone") || s.startsWith("gold") || s.startsWith("wood") || s.startsWith("food")){
            return true;
        }

        return false;
    }
}
