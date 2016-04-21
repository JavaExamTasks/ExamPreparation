import java.util.*;

/**
 * Created by pc on 4/18/2016.
 */
public class SchoolSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());

        TreeMap<String, Student> students = new TreeMap<>();

        for (int i = 0; i < n; i++) {
            String[] arg = sc.nextLine().split("\\s+");

            String firstN = arg[0];
            String lastN = arg[1];
            String fullName = firstN + " " + lastN;
            String subject = arg[2];
            int grade = Integer.parseInt(arg[3]);

            if (!students.containsKey(fullName)){
                students.put(fullName, new Student(fullName));
            }

            if (students.get(fullName).grades.get(subject) == null) {
                students.get(fullName).grades.put(subject, new ArrayList<>());
            }

            students.get(fullName).grades.get(subject).add(grade);
        }

        for (Map.Entry<String, Student> pair : students.entrySet()) {
            System.out.print(pair.getKey() + ": ");

            TreeMap<String, ArrayList<Integer>> subjects = pair.getValue().grades;

            StringBuilder output = new StringBuilder();
            output.append("[");

            for (Map.Entry<String, ArrayList<Integer>> innerPair : subjects.entrySet()) {
                output.append(innerPair.getKey());
                output.append(" - ");
                double average = pair.getValue().averageGrade(innerPair.getKey());
                output.append(String.format("%.2f", average));
                output.append(", ");
            }

            output.replace(output.length() - 2, output.length(), "");
            output.append("]");

            System.out.println(output.toString());
        }
    }

    static class Student {
        public String fullName;

        public TreeMap<String, ArrayList<Integer>> grades;

        public Student(String fullName) {
            grades = new TreeMap<>();
            this.fullName = fullName;
        }

        public double averageGrade(String subject){
            double average = 0;

            ArrayList<Integer> allGrades = grades.get(subject);
            double count = allGrades.size();

            for (Integer grade : allGrades) {
                average += grade;
            }

            average /= count;

            return average;
        }
    }
}
