import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by pc on 5/6/2016.
 */
public class Pyramid {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        int[][] matrix = new int[n][];

        for (int i = 0; i < matrix.length; i++) {
            String line = sc.nextLine().trim();
            String[] dimentions = line.split("\\s+");
            int[] arr = new int[dimentions.length];
            for (int j = 0; j < dimentions.length; j++) {
                arr[j]= Integer.parseInt(dimentions[j]);
            }

            Arrays.sort(arr);
            matrix[i] = arr;
        }

        int numberCompare = matrix[0][0];
        ArrayList<Integer> output = new ArrayList<>();
        output.add(numberCompare);

        for (int i = 1; i < matrix.length; i++) {
            boolean found = false;
            for (int j = 0; j < matrix[i].length; j++) {
                int number = matrix[i][j];

                if(number > numberCompare){
                    output.add(number);
                    numberCompare = number;
                    found = true;
                    break;
                }
            }

            if (!found){
               numberCompare++;
            }

        }

        for (int i = 0; i < output.size(); i++) {
            System.out.print(output.get(i));

            if (i < output.size() - 1){
                System.out.print(", ");
            }
        }
    }
}
