import java.util.Scanner;

/**
 * Created by pc on 4/19/2016.
 */
public class TargetPractice {
    static char[][] matrix;
    static int rows;
    static int cols;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] dimentions = sc.nextLine().split("\\s+");
        rows = Integer.parseInt(dimentions[0]);
        cols = Integer.parseInt(dimentions[1]);

        matrix = new char[rows][cols];

        String snake = sc.nextLine();
        int length = snake.length();
        int index = 0;
        boolean isMovingLeft = true;

        for (int i = rows - 1; i >= 0; i--) {
            if (isMovingLeft){
                for (int j = cols - 1; j >= 0; j--) {
                    matrix[i][j] = snake.charAt(index % length);
                    index++;
                }

                isMovingLeft = false;
            }else{
                for (int j = 0; j < cols; j++) {
                    matrix[i][j] = snake.charAt(index % length);
                    index++;
                }

                isMovingLeft = true;
            }
        }

        dimentions = sc.nextLine().split("\\s+");
        int impactRow = Integer.parseInt(dimentions[0]);
        int impactCol = Integer.parseInt(dimentions[1]);
        int radius = Integer.parseInt(dimentions[2]);

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                //(x - center_x)^2 + (y - center_y)^2 <= radius^2

                if((row - impactRow) * (row - impactRow) +
                        (col - impactCol) * (col - impactCol) <=
                        radius * radius){
                    matrix[row][col] = ' ';
                }
            }
        }

        boolean isFalling = true;

        while (isFalling){
            isFalling = false;

            for (int row = 0; row < rows - 1; row++) {
                for (int col = 0; col < matrix[row].length; col++) {
                    char upChar = matrix[row][col];
                    char downChar = matrix[row + 1][col];

                    if (upChar != ' ' && downChar == ' '){
                        isFalling = true;
                        matrix[row][col] = ' ';
                        matrix[row + 1][col] = upChar;
                    }
                }
            }
        }

        printMatrix();
    }

    private static void printMatrix() {
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                System.out.print(matrix[row][col]);
            }

            System.out.println();
        }
    }

}
