import java.util.Scanner;

/**
 * Created by pc on 4/17/2016.
 */
public class Monopoly {
    static char[][] matrix;
    static int rows;
    static int cols;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String[] dimentions = sc.nextLine().split("\\s+");
        rows = Integer.parseInt(dimentions[0]);
        cols = Integer.parseInt(dimentions[1]);

        matrix = new char[rows][];
        for (int i = 0; i < rows; i++) {
            String input = sc.nextLine();
            matrix[i] = input.toCharArray();
        }

        int money = 50;
        int hotelsCount = 0;
        int turns = 0;

        boolean isLeft = false;

        for (int r = 0; r < rows; r++) {
            if (!isLeft){
                for (int c = 0; c < cols; c++) {
                    char currSymbol = matrix[r][c];

                    switch (currSymbol) {
                        case 'H':
                            hotelsCount++;
                            System.out.printf("Bought a hotel for %d. Total hotels: %d.%n",
                                    money,
                                    hotelsCount);
                            money = 0;
                            break;
                        case 'J':
                            System.out.printf("Gone to jail at turn %d.%n", turns);
                            turns += 2;
                            money += 2 * (10 * hotelsCount);
                            break;
                        case 'S':
                            int shopMoney = (r + 1) * (c + 1);

                            if (shopMoney > money){
                                shopMoney = money;
                            }

                            System.out.printf("Spent %d money at the shop.%n", shopMoney);
                            money -= shopMoney;
                            break;
                        default:
                            break;

                    }

                    money += 10 * hotelsCount;
                    turns++;
                }
            }else{
                for (int c = cols - 1; c >= 0; c--) {
                    char currSymbol = matrix[r][c];

                    switch (currSymbol) {
                        case 'H':
                            hotelsCount++;
                            System.out.printf("Bought a hotel for %d. Total hotels: %d.%n",
                                    money,
                                    hotelsCount);
                            money = 0;
                            break;
                        case 'J':
                            System.out.printf("Gone to jail at turn %d.%n", turns);
                            turns += 2;
                            money += 2 * (10 * hotelsCount);
                            break;
                        case 'S':
                            int shopMoney = (r + 1) * (c + 1);

                            if (shopMoney > money){
                                shopMoney = money;
                            }

                            System.out.printf("Spent %d money at the shop.%n", shopMoney);
                            money -= shopMoney;
                            break;
                        default:
                            break;

                    }

                    money += 10 * hotelsCount;
                    turns++;
                }
            }

            isLeft = !isLeft;
        }

            System.out.println("Turns " + turns);
            System.out.println("Money " + money);
    }
}
