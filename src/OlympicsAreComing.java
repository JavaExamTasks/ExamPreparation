import java.util.*;

/**
 * Created by pc on 4/20/2016.
 */
public class OlympicsAreComing {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        HashMap<String, HashSet<String>> players = new HashMap<>();
        LinkedHashMap<String, Integer> wins = new LinkedHashMap<>();

        String input = console.nextLine();

        while (!input.equals("report")) {
            String[] inputArgs = input
                    .replaceAll("\\s+", " ")
                    .trim()
                    .split("\\|");

            //Ivan ivanov | Bulgaria

            String player = inputArgs[0].trim();
            String country = inputArgs[1].trim();

            if (!players.containsKey(country)) {
                players.put(country, new HashSet<String>());
                wins.put(country, 0);
            }

            players.get(country).add(player);
            int winsCount = wins.get(country);
            wins.put(country, winsCount + 1);

            input = console.nextLine();
        }

        //Bulgaria (2 participants): 2 wins

        wins.entrySet().stream()
                .sorted((pair1, pair2) ->
                        Integer.compare(pair2.getValue(), pair1.getValue()))
                .forEach(entry -> {
                    String country = entry.getKey();
                    System.out.printf("%s (", country);
                    int participants = players.get(country).size();
                    System.out.print(participants + " participants): ");
                    System.out.println(entry.getValue() + " wins");
        });
    }
}
