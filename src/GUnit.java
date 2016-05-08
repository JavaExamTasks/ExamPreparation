import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 5/5/2016.
 */
public class GUnit {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String line = sc.nextLine();

        Pattern pattern =
                Pattern.compile("^([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+) \\| ([A-Z][a-zA-Z0-9]+)$");

        TreeMap<String, TreeMap<String, ArrayList<String>>> classes= new TreeMap<>();

        while (!line.equals("It's testing time!")){
            Matcher matcher = pattern.matcher(line);

            if(matcher.find()){
                String clas = matcher.group(1);
                String method = matcher.group(2);
                String test = matcher.group(3);

                 if (!classes.containsKey(clas)) {
                         classes.put(clas, new TreeMap<>());
                 }
                 if (!classes.get(clas).containsKey(method)) {
                          classes.get(clas).put(method, new ArrayList<>());
                 }
                if (!classes.get(clas).get(method).contains(test)) {
                    classes.get(clas).get(method).add(test);
                }
            }
            line = sc.nextLine();
        }

        classes.entrySet().stream().sorted((e1, e2) ->
            Integer.compare(e1.getValue().size(), e2.getValue().size()))
                .sorted((e1, e2) -> Integer.compare(getCount(e2.getValue()), getCount(e1.getValue())))
                .forEach(pair -> {
                    System.out.println(pair.getKey() + ":");

                    TreeMap<String, ArrayList<String>> methods = pair.getValue();
                    methods.entrySet().stream().sorted((e1, e2) ->
                            Integer.compare(e2.getValue().size(), e1.getValue().size()))
                            .forEach(pair2 -> {
                                System.out.println("##" + pair2.getKey());
                                ArrayList<String> tests = pair2.getValue();
                                tests.stream().sorted((e1,e2) -> {
                                    if (e1.length() != e2.length()) {
                                        return Integer.compare(e1.length(), e2.length());
                                    }

                                    return e1.compareTo(e2);
                                }).forEach(e1 -> {
                                    System.out.println("####" + e1);
                                });

                            });
                });

    }

    private static int getCount(TreeMap<String, ArrayList<String>> value) {
        int count = 0;

        for (ArrayList<String> tests : value.values()) {
            count += tests.size();
        }

        return count;
    }
}
