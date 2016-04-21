import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by pc on 4/18/2016.
 */
public class FireArrows {
    static char[][] matrix;
    static int rows;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();
        rows = Integer.parseInt(input);

        matrix = new char[rows][];

        for (int i = 0; i < rows; i++) {
            matrix[i] = sc.nextLine().toCharArray();
        }

        int cols = matrix[0].length;

        boolean isFalling = true;

        while(isFalling){
            isFalling = false;

            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    switch (matrix[i][j]) {
                        case '>':
                            if (j+1 < cols && matrix[i][j+1] == 'o'){
                                matrix[i][j+1] = '>';
                                matrix[i][j] = 'o';
                                isFalling = true;
                            }
                            break;
                        case '<':
                            if (j-1 >= 0 && matrix[i][j-1] == 'o'){
                                matrix[i][j-1] = '<';
                                matrix[i][j] = 'o';
                                isFalling = true;
                            }
                            break;
                        case 'v':
                            if (i + 1 < rows && matrix[i + 1][j] == 'o'){
                                matrix[i + 1][j] = 'v';
                                matrix[i][j] = 'o';
                                isFalling = true;
                            }
                            break;
                        case '^':
                            if (i - 1 >= 0 && matrix[i - 1][j] == 'o'){
                                matrix[i-1][j] = '^';
                                matrix[i][j] = 'o';
                                isFalling = true;
                            }
                            break;
                        default:
                            break;

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
