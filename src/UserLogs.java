import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 4/17/2016.
 */
public class UserLogs {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        TreeMap<String, User> users = new TreeMap<>();

        while (!input.equals("end")) {
            Pattern pattern = Pattern.compile("IP=(.+) message=(.+) user=(.+)");
            Matcher matcher = pattern.matcher(input);

            while (matcher.find()) {
                String ip = matcher.group(1);
                String user = matcher.group(3);

                if (!users.containsKey(user)) {
                    users.put(user, new User(user));
                }

                if (users.get(user).ips.get(ip) == null) {
                    users.get(user).ips.put(ip, 0);
                }

                int count = users.get(user).ips.get(ip);
                users.get(user).ips.put(ip, count + 1);
            }

            input = sc.nextLine();
        }

        for (Map.Entry<String, User> pair : users.entrySet()) {
            System.out.println(pair.getKey() + ": ");

            LinkedHashMap<String, Integer> ips = pair.getValue().ips;

            int i = 0;

            for (Map.Entry<String, Integer> innerPair : ips.entrySet()) {

                System.out.print(innerPair.getKey() + " => ");
                System.out.print(innerPair.getValue());

                if (i != ips.size() - 1) {
                    System.out.print(", ");
                } else {
                    System.out.println(".");
                }

                i++;
            }
        }
    }

    static class User {
        public String name;
        public LinkedHashMap<String, Integer> ips;

        public User(String name) {
            ips = new LinkedHashMap<>();
            this.setName(name) ;
        }

        public void setName(String name) {
            this.name = name;
        }
        public String getName() {
            return name;
        }
    }
}
