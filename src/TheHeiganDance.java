import java.util.Scanner;

/**
 * Created by pc on 8.2.2016 г..
 */
public class TheHeiganDance {
    private static final double CLOUD = 3500;
    private static final double ERUPTION = 6000;

    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);
        double pointsToTakeFromHeigan = Double.parseDouble(console.nextLine());

        double playerPoints = 18500;
        double heiganPoints = 3000000;

        int playerRow = 7;
        int playerCol = 7;

        boolean isCloud = false;
        boolean isPlayerDead = false;
        boolean isHeiganDead = false;

        String spell= "";

        while (true){
            heiganPoints -= pointsToTakeFromHeigan;

            if (isCloud){
                playerPoints -= CLOUD;
                isCloud ^= true;
            }

            isHeiganDead = isDead(heiganPoints);
            isPlayerDead = isDead(playerPoints);

            if (isHeiganDead || isPlayerDead){
                break;
            }

            //Cloud 7 7
            String[]input = console.nextLine().split(" ");
            String cloudOrEruption = input[0];
            int heiganRow = Integer.parseInt(input[1]);
            int heiganCol = Integer.parseInt(input[2]);

            if (checkIfInDemageZone(playerRow, playerCol, heiganRow, heiganCol)){
                if (!checkIfInDemageZone(playerRow - 1, playerCol, heiganRow, heiganCol) &&
                        isInMatrix(playerRow - 1, playerCol)){
                    playerRow -= 1;
                }else{
                    if (!checkIfInDemageZone(playerRow , playerCol + 1, heiganRow, heiganCol) &&
                            isInMatrix(playerRow, playerCol  + 1)){
                        playerCol += 1;
                    }
                    else{
                        if (!checkIfInDemageZone(playerRow + 1, playerCol , heiganRow, heiganCol)&&
                                isInMatrix(playerRow + 1, playerCol)){
                            playerRow += 1;
                        }else{
                            if (!checkIfInDemageZone(playerRow , playerCol - 1, heiganRow, heiganCol) &&
                                    isInMatrix(playerRow, playerCol  - 1)){
                                playerCol -= 1;
                            }else {
                                if (cloudOrEruption.equals("Cloud")){
                                    playerPoints -= CLOUD;
                                    isCloud ^= true;
                                    spell = "Plague Cloud";
                                }else{
                                    playerPoints -= ERUPTION;
                                    spell = "Eruption";
                                }
                                isPlayerDead = isDead(playerPoints);
                                if (isPlayerDead){
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        String firstLine = isHeiganDead ? "Heigan: Defeated!" : String.format("Heigan: %.2f", heiganPoints);
        System.out.println(firstLine);
        String secondLine = isPlayerDead ? String.format("Player: Killed by %s", spell) : String.format("Player: %.0f", playerPoints);
        System.out.println(secondLine);
        System.out.printf("Final position: %d, %d\n", playerRow, playerCol);
    }

    private static boolean isDead(double points) {
        boolean isDead = points <= 0 ? true : false;

        return isDead;
    }

    private static boolean checkIfInDemageZone(int playerRow, int playerCol, int heiganRow, int heiganCol) {
        if (((playerRow <= heiganRow + 1) && (playerRow >= heiganRow - 1)) &&
                ((playerCol <= heiganCol + 1) && (playerCol >= heiganCol - 1))){
            return true;
        }

        return false;
    }

    private static boolean isInMatrix(int playerRow, int playerCol) {
        if (((playerRow <= 14) && (playerRow >= 0)) &&
                ((playerCol <= 14) && (playerCol >= 0))){
            return true;
        }

        return false;
    }
}
