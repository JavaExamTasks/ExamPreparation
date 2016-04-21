import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by pc on 4/16/2016.
 */
public class ChampionsLeague {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        TreeMap<String, Team> teams = new TreeMap<>();

        while (true) {
            String input = sc.nextLine();
            if (input.equals("stop")) {
                break;
            }

            String[] inputArgs= input.split("\\|");
            String team1Name = inputArgs[0].trim();
            String team2Name = inputArgs[1].trim();
            String[] result1 = inputArgs[2].trim().split(":");
            String[] result2 = inputArgs[3].trim().split(":");

            int team1OwnSoilGoals = Integer.parseInt(result1[0]);
            int team2AwaySoilGoals = Integer.parseInt(result1[1]);
            int team2OwnSoilGoals = Integer.parseInt(result2[0]);
            int team1AwaySoilGoals = Integer.parseInt(result2[1]);


            if (!teams.containsKey(team1Name)){
                teams.put(team1Name , new Team());
                teams.get(team1Name).name = team1Name;
            }

            if (!teams.containsKey(team2Name)){
                teams.put(team2Name , new Team());
                teams.get(team2Name).name = team2Name;
            }

            teams.get(team1Name).opponents.add(team2Name);
            teams.get(team2Name).opponents.add(team1Name);

            if ((team1AwaySoilGoals + team1OwnSoilGoals) > (team2AwaySoilGoals + team2OwnSoilGoals)){
                teams.get(team1Name).count++;
            }else if((team1AwaySoilGoals + team1OwnSoilGoals) < (team2AwaySoilGoals + team2OwnSoilGoals)){
                teams.get(team2Name).count++;
            }else {
                if(team1AwaySoilGoals > team2AwaySoilGoals){
                    teams.get(team1Name).count++;
                }else {
                    teams.get(team2Name).count++;
                }
            }
        }

        teams.entrySet().stream().sorted((p1, p2) -> Integer.compare(p2.getValue().count, p1.getValue().count))
                .forEach(pair -> {
                    System.out.println(pair.getValue().name);
                    System.out.println("- Wins: " + pair.getValue().count);
                    System.out.print("- Opponents: ");

                    StringBuilder sb = new StringBuilder();
                    ArrayList<String> sortedOpponents = pair.getValue().opponents;
                    sortedOpponents.sort(Comparator.<String>naturalOrder());

                    for (String opponent : sortedOpponents) {
                        sb.append(opponent + ", ");
                    }

                    String opponents = sb.toString().substring(0, sb.length() - 2);
                    System.out.println(opponents);
                });
    }

    static class Team implements Comparable {
        public ArrayList<String> opponents;
        public int count;
        public String name;

        public Team() {
            opponents = new ArrayList<>();
            count = 0;
        }

        @Override
        public int compareTo(Object o) {
            Team other = (Team) o;
            return Integer.compare(other.count, this.count);
        }
    }
}
