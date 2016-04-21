import java.util.Scanner;

/**
 * Created by pc on 4/18/2016.
 */
public class ParkingSystem {
    static boolean[][] matrix;
    static int rows;
    static int cols;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        String[] a = console.nextLine().split("\\s+");
        rows = Integer.parseInt(a[0]);
        cols = Integer.parseInt(a[1]);

        matrix = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            matrix[i][0] = true;
        }

        String input = console.nextLine();

        while (!input.equals("stop")){
            String[] b = input.split("\\s+");

            int z = Integer.parseInt(b[0]);
            int x = Integer.parseInt(b[1]);
            int y = Integer.parseInt(b[2]);

            if (matrix[x][y]){
                if (isRowFull(x)){
                    System.out.printf("Row %d full", x);
                    System.out.println();
                }else{
                    int freeCol = checkFreeSpot(x, y);
                    int c = Math.abs(x - z);
                    System.out.println(c + freeCol + 1);
                    matrix[x][freeCol] = true;
                }
            }else{
                int c = Math.abs(x - z);
                System.out.println(c + y + 1);
                matrix[x][y] = true;
            }

            input = console.nextLine();
        }
    }

    private static int checkFreeSpot(int x, int y) {
        int index = 1;
        int freeCol = 0;

        while (true) {
            if ((y - index) > 0 && !matrix[x][y - index]){
                freeCol = y - index;
                return freeCol;
            }
            if ((y+index) < cols && !matrix[x][y+index]){
                freeCol = y + index;
                return freeCol;
            }

            index++;
        }
    }

    private static boolean isRowFull(int x) {
        for (int j = 1; j < cols; j++) {
              if (!matrix[x][j]) {
                  return false;
              }
        }

        return true;
    }
}
