import java.util.ArrayList;
import java.util.Scanner;

/**
 * Created by pc on 4/19/2016.
 */
public class DragonTrapMine {
    static char[][] matrix;
    static char[][] copyMatrix;
    static int rows;
    static int cols;
    static ArrayList<Character> charsToRotate = new ArrayList<>();

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        rows = Integer.parseInt(console.nextLine());

        matrix = new char[rows][];
        copyMatrix = new char[rows][];

        for (int i = 0; i < rows; i++) {
            String input = console.nextLine();
            matrix[i] = input.toCharArray();
            copyMatrix[i] = input.toCharArray();
        }

        cols = matrix[0].length;
        String input = console.nextLine();

        while (!input.equals("End")) {
            String[] data = input.replaceAll("[()]+", "")
                    .trim()
                    .split("\\s+");
            int row = Integer.parseInt(data[0]);
            int col = Integer.parseInt(data[1]);
            int radius = Integer.parseInt(data[2]);
            int rotatins = Integer.parseInt(data[3]);

            int pattren[][] = new int[rows][cols];

            int counter = 1;
            int startRow = row - radius;
            int endRow = row + radius;
            int startCol = col - radius;
            int endCol = col + radius;
            //1
            for (int j = startCol; j <= endCol; j++) {
                if (startRow >= 0 && startRow < rows && j >= 0 && j < cols){
                    pattren[startRow][j] = counter;
                    counter++;

                    charsToRotate.add(matrix[startRow][j]);
                }
            }

            //2
            for (int j = startRow + 1; j <= endRow - 1; j++) {
                if (endCol < cols && endCol >= 0 && j >= 0 && j < rows){
                    pattren[j][endCol] = counter;
                    counter++;
                    charsToRotate.add(matrix[j][endCol]);
                }
            }

            //3
            for (int j = endCol; j >= startCol; j--) {
                if (endRow < rows && endRow >= 0 && j >= 0 && j < cols){
                    pattren[endRow][j] = counter;
                    counter++;
                    charsToRotate.add(matrix[endRow][j]);
                }
            }

            //4
            for (int j = endRow - 1; j >= startRow + 1; j--) {
                if (startCol >= 0 && startCol < cols && j >= 0 && j < rows){
                    pattren[j][startCol] = counter;
                    counter++;
                    charsToRotate.add(matrix[j][startCol]);
                }
            }

            if (charsToRotate.size() < 2){
                input = console.nextLine();
                continue;
            }

            //rotate chars counterclockwise
            if (rotatins < 0){
                rotatins *= -1;
                int timesToRotate = rotatins % charsToRotate.size();

                if (timesToRotate != 0){
                    for (int i = 0; i < timesToRotate; i ++) {
                        charsToRotate.add(charsToRotate.get(0));
                        charsToRotate.remove(0);
                    }
                }
                //rotate chars clockwise
            }else {
                int timesToRotate = rotatins % charsToRotate.size();
                if (timesToRotate != 0){
                    for (int i = 0; i < timesToRotate; i ++) {
                        charsToRotate.add(0, charsToRotate.get(charsToRotate.size() - 1));
                        charsToRotate.remove(charsToRotate.size() - 1);
                    }
                }
            }

            //fill matrix with changed chars
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (pattren[i][j] != 0){
                        matrix[i][j] = charsToRotate.get(pattren[i][j] - 1);
                    }
                }
            }

            charsToRotate.clear();
            input = console.nextLine();
        }

        printMatrix();
    }

    private static void printMatrix() {
        int changedChars = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j]);

                if (!(matrix[i][j] == copyMatrix[i][j])){
                    changedChars++;
                }
            }

            System.out.println();
        }

        System.out.printf("Symbols changed: %d", changedChars);
    }
}
