import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by pc on 4/18/2016.
 */
public class DragonTrap {
    static char[][] matrix;
    static char[][] originalMatrix;
    static ArrayList<Character> charsToRotate = new ArrayList<>();
    static ArrayList<Cell> indexes = new ArrayList<>();
    static int rows;
    static int cols;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        rows = Integer.parseInt(sc.nextLine());
        matrix = new char[rows][];
        originalMatrix = new char[rows][];

        for (int i = 0; i < rows; i++) {
            String input = sc.nextLine();
            matrix[i] = input.toCharArray();
            originalMatrix[i] = input.toCharArray();
        }

        cols = matrix[0].length;

        String line = sc.nextLine();
        while (!line.equals("End")) {
            String[] digits = line.split("\\s+");
            //String[] tokens = command.replaceAll("[()]+", "").trim().split("\\s+");

            int dragonRow = Integer.parseInt(digits[0].replace("(", ""));
            int dragonCol = Integer.parseInt(digits[1].replace(")", ""));
            int radius = Integer.parseInt(digits[2]);
            int rotations = Integer.parseInt(digits[3]);

            //if (rotations == 0) {
             //   continue;
            //}

            getSymbolsToRotate(dragonRow, dragonCol, radius);

            if (charsToRotate.size() > 0){
                rotations %= charsToRotate.size();

                if (rotations != 0){
                    int index = rotations < 0 ? -rotations : charsToRotate.size() - rotations;
                    changeMatrix(index);
                }
            }

            charsToRotate.clear();
            indexes.clear();

            line = sc.nextLine();
        }

        printOutput();
    }

    private static void changeMatrix(int index) {
        for (int i = 0; i < indexes.size(); i++) {
            int row = indexes.get(i).getRow();
            int col = indexes.get(i).getColumn();
            char symbol = charsToRotate.get(index);

            matrix[row][col] = symbol;

            index = (index + 1) % charsToRotate.size();
        }
    }

    private static void getSymbolsToRotate(int dragonRow, int dragonCol, int radius) {
        int startRow = dragonRow - radius;
        int endRow = dragonRow + radius;
        int startCol = dragonCol - radius;
        int endCol = dragonCol + radius;

        for (int col = startCol; col <= endCol; col++) {
            if (isInsideMatrix(startRow, col)) {
                charsToRotate.add(matrix[startRow][col]);
                indexes.add(new Cell(startRow, col));
            }
        }

        startRow++;

        for (int row = startRow; row <= endRow; row++) {
            if (isInsideMatrix(row, endCol)) {
                charsToRotate.add(matrix[row][endCol]);
                indexes.add(new Cell(row, endCol));
            }
        }

        endCol--;

        for (int col = endCol; col >= startCol; col--) {
            if (isInsideMatrix(endRow, col)) {
                charsToRotate.add(matrix[endRow][col]);
                indexes.add(new Cell(endRow, col));
            }
        }

        endRow--;

        for (int row = endRow; row >= startRow; row--) {
            if (isInsideMatrix(row, startCol)) {
                charsToRotate.add(matrix[row][startCol]);
                indexes.add(new Cell(row, startCol));
            }
        }
    }

    private static boolean isInsideMatrix(int currentRow, int currentCol) {

        return 0 <= currentRow
                && currentRow < matrix.length
                && 0 <= currentCol
                && currentCol < matrix[currentRow].length;
    }

    private static void printOutput() {
        int changedSymbols = 0;

        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < matrix[row].length; col++) {
                if (matrix[row][col] != originalMatrix[row][col]){
                    changedSymbols++;
                }

                System.out.print(matrix[row][col]);
            }

            System.out.println();
        }

        System.out.printf("Symbols changed: %d", changedSymbols);
    }
}

    class Cell {
        public int row;
        public int column;

        public Cell(int row, int column) {
            this.row = row;
            this.column = column;
        }

        public int getRow() {
            return this.row;
        }

        public int getColumn() {
            return this.column;
        }
}
