import java.util.Scanner;

/**
 * Created by pc on 4/17/2016.
 */
public class LegoBlocks {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        int n = Integer.parseInt(console.nextLine());

        String[][] matrix1 = new String[n][];
        String[][] matrix2 = new String[n][];

        for (int i = 0; i < n; i++) {
            String input = console.nextLine();
            matrix1[i] = input.trim().split("\\s+");
        }

        for (int i = 0; i < n; i++) {
            String input = console.nextLine();
            matrix2[i] = input.trim().split("\\s+");
        }

        boolean isRectangular = true;
        int[] rowsLength = new int[n];
        int count = 0;
        for (int i = 0; i < n; i++) {
            rowsLength[i] = matrix1[i].length + matrix2[i].length;
            count += rowsLength[i];
        }

        for (int i = 1; i < n; i++) {
            int currRow = rowsLength[i];
            int oldRow = rowsLength[i - 1];

            if (currRow != oldRow){
                isRectangular = false;
                break;
            }
        }

        if (isRectangular){
            for (int i = 0; i < n; i++) {
                StringBuilder output = new StringBuilder();

                for (int j = 0; j < matrix1[i].length; j++) {
                    output.append(matrix1[i][j] + ", ");
                }

                for (int j = matrix2[i].length - 1; j >= 0 ; j--) {
                    output.append(matrix2[i][j] + ", ");
                }

                String str = output.toString().substring(0, output.length() - 2);
                System.out.println("[" + str + "]");
            }
        }else{
            System.out.println("The total number of cells is: " + count);
        }
    }
}
