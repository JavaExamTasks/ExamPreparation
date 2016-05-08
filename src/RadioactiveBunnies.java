import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Created by pc on 4/28/2016.
 */
public class RadioactiveBunnies {
    static ArrayList<ArrayList<Character>> lair;
    static int rows;
    static int cols;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int[] dimentions = Arrays.stream(sc.nextLine().split(" "))
                .mapToInt(Integer::parseInt).toArray();
        rows = dimentions[0];
        cols = dimentions[1];

        lair = new ArrayList<>();
        ArrayList<ArrayList<Character>> lairCopy = new ArrayList<>();

        int playerRow = 0;
        int playerCol = 0;

        for (int i = 0; i < rows; i++) {
            String input = sc.nextLine();
            char[]inputArgs = input.toCharArray();
            lair.add(new ArrayList<Character>());
            lairCopy.add(new ArrayList<Character>());

            for (int j = 0; j < cols; j++) {
                char ch = inputArgs[j];
                lair.get(i).add(ch);
                lairCopy.get(i).add(ch);
                if (ch == 'P'){
                    playerRow = i;
                    playerCol = j;
                }
            }
        }

        int priviousPlayerRow = 0;
        int priviousPlayerCol = 0;

        boolean isPlayerDead = false;
        boolean hasPlayerWon = false;

        String input = sc.nextLine();

        for (int i = 0; i < input.length(); i++) {
            char cell = input.charAt(i);

            priviousPlayerRow = playerRow;
            priviousPlayerCol = playerCol;

            switch (cell) {
                case 'L':
                    if (isInMatrix(playerRow, playerCol - 1)){
                        if (lair.get(playerRow).get(playerCol - 1) == 'B'){
                            isPlayerDead = true;
                        }else {
                            lairCopy.get(playerRow).set(playerCol - 1, 'P');
                        }

                        playerCol --;
                    }else {
                        hasPlayerWon = true;
                    }
                    break;
                case 'R':
                    if (isInMatrix(playerRow, playerCol + 1)){
                        if (lair.get(playerRow).get(playerCol + 1) == 'B'){
                            isPlayerDead = true;
                        }else {
                            lairCopy.get(playerRow).set(playerCol + 1, 'P');
                        }

                        playerCol++;
                    }else {
                        hasPlayerWon = true;
                    }
                    break;
                case 'U':
                    if (isInMatrix(playerRow - 1, playerCol)){
                        if (lair.get(playerRow - 1).get(playerCol) == 'B'){
                            isPlayerDead = true;
                        }else {
                            lairCopy.get(playerRow - 1).set(playerCol, 'P');
                        }

                        playerRow--;
                    }else {
                        hasPlayerWon = true;
                    }
                    break;
                default:
                    if (isInMatrix(playerRow + 1, playerCol)){
                        if (lair.get(playerRow + 1).get(playerCol) == 'B'){
                            isPlayerDead = true;
                        }else {
                            lairCopy.get(playerRow + 1).set(playerCol, 'P');
                        }

                        playerRow++;
                    }else {
                        hasPlayerWon = true;
                    }
                    break;
            }

            lairCopy.get(priviousPlayerRow).set(priviousPlayerCol, '.');

            for (int row = 0; row < rows; row++) {
                for (int col = 0; col < cols; col++) {
                    if (lair.get(row).get(col) == 'B'){
                        if (isInMatrix(row + 1, col)){
                            if (lairCopy.get(row + 1).get(col) == 'P'){
                                isPlayerDead = true;
                            }
                            lairCopy.get(row + 1).set(col, 'B');
                        }
                        if (isInMatrix(row - 1, col)){
                            if (lairCopy.get(row - 1).get(col) == 'P'){
                                isPlayerDead = true;
                            }
                            lairCopy.get(row - 1).set(col, 'B');
                        }
                        if (isInMatrix(row, col - 1)){
                            if (lairCopy.get(row).get(col - 1) == 'P'){
                                isPlayerDead = true;
                            }
                            lairCopy.get(row).set(col - 1, 'B');
                        }
                        if (isInMatrix(row, col + 1)){
                            if (lairCopy.get(row).get(col + 1) == 'P'){
                                isPlayerDead = true;
                            }
                            lairCopy.get(row).set(col + 1, 'B');
                        }
                    }
                }
            }

            copyMatrix(lairCopy);

            if (isPlayerDead || hasPlayerWon){
                break;
            }
        }

        printMatrix();

        String output = isPlayerDead ? "dead: " : "won: ";

        System.out.printf("%s%d %d", output, playerRow, playerCol);

    }

    private static void copyMatrix(ArrayList<ArrayList<Character>> lairCopy) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                char ch = lairCopy.get(i).get(j);
                lair.get(i).set(j, ch);
            }
        }
    }

    private static void printMatrix() {
        for (ArrayList<Character> characters : lair) {
            for (Character character : characters) {
                System.out.print(character);
            }

            System.out.println();
        }
    }

    private static boolean isInMatrix(int row, int col) {
        return row >= 0 && row < rows && col >= 0 && col < cols;
    }
}
