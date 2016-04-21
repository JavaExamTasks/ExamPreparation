import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/8/2016.
 */
public class Events {
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        int n = Integer.parseInt(console.nextLine());
        String input = console.nextLine();

        TreeMap<String, TreeMap<String, ArrayList<String>>> events = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            Pattern pattern = Pattern.compile("^#([a-zA-Z]+):\\s*?@([a-zA-Z]+)\\s*?(([0-1]{1}[0-9]{1}|[2]{1}[0-3]{1}):[0-5]{1}[0-9]{1})$");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String name = matcher.group(1);
                String location = matcher.group(2);
                String time = matcher.group(3);

                if (!events.containsKey(location)) {
                    events.put(location, new TreeMap<>());
                }

                if (!events.get(location).containsKey(name)) {
                    events.get(location).put(name, new ArrayList<String>());
                }

                events.get(location).get(name).add(time);
            }

            input = console.nextLine();
        }

        String[] locations = input.split(",");
        TreeSet<String> targetLocations = new TreeSet<>();
        int length = targetLocations.size();

        for (int i = 0; i < locations.length; i++) {
            targetLocations.add(locations[i]);
        }

        StringBuilder output = new StringBuilder();

        for (String target : targetLocations) {
            if (events.containsKey(target)) {
                output.append(target + ":\n");
                TreeMap<String, ArrayList<String>> list = events.get(target);

                int count = 1;

                for (String name : list.keySet()){
                    ArrayList<String> timeList = list.get(name);

                    output.append(String.format("%d. %s -> ", count ++, name, timeList.get(0)));
                    timeList.stream().sorted().forEach(e -> output.append(e + ", " ));
                    output.deleteCharAt(output.length()-2);
                    output.append("\n");
                }
            }
        }

        String result = output.toString();
        System.out.println(result);
    }
}