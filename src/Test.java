import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by pc on 5/3/2016.
 */
public class Test {
    static BigDecimal month = new BigDecimal("30");

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BigDecimal initialCapital = new BigDecimal(sc.nextLine());

        String line = sc.nextLine();

        int day = 0;
        List<EmployeeSet> employees = new LinkedList<>();

        boolean hasBankrupt = false;

        while (!line.equals("END")){
            String[]tokens = line.split(";");
            long hired = Long.parseLong(tokens[0]);
            long fired = Long.parseLong(tokens[1]);
            BigDecimal salary = new BigDecimal(tokens[2]);

            employees.add(new EmployeeSet(hired, 0, salary));

            day++;
            for (EmployeeSet employee : employees) {
                employee.day++;

                if (employee.day % 365 == 0){
                    employee.salary = employee.salary.multiply(new BigDecimal("1.006"));
                }

                if (day % 30 == 0) {


                    if (employee.day < 30) {
                        BigDecimal salaryToReceive = employee.salary
                                .divide(month, 9, BigDecimal.ROUND_UP)
                                .setScale(7, BigDecimal.ROUND_DOWN)
                                .multiply(new BigDecimal(employee.day))
                                .multiply(new BigDecimal(employee.count));

                        initialCapital.subtract(salaryToReceive);
                    } else {
                        BigDecimal salaryToReceive = employee.salary
                                .divide(month, 9, BigDecimal.ROUND_UP)
                                .setScale(7, BigDecimal.ROUND_DOWN)
                                .multiply(month)
                                .multiply(new BigDecimal(employee.count));

                        initialCapital.subtract(salaryToReceive);
                    }
                }
                if (fired > 0){
                    employee.count -= fired;
                    if (employee.count < 0){
                        fired = employee.count * -1;
                        employee.count = 0;
                    }else{
                        fired = 0;
                    }
                }
            }


            for (int i = 3; i < tokens.length; i++) {
                String[] token = tokens[i].split(":");
                String reason = token[0];
                BigDecimal money = new BigDecimal(token[1]);

                if (reason.equals("Product development") || reason.equals("Unconditional funding")){
                    initialCapital = initialCapital.add(money);
                } else{
                    initialCapital = initialCapital.subtract(money);
                }
            }

            if(initialCapital.compareTo(BigDecimal.ZERO) <= 0){
                hasBankrupt = true;
                break;
            }

            line = sc.nextLine();
        }

        if (hasBankrupt){
            System.out.println("BANKRUPTCY: " + initialCapital.setScale(4, BigDecimal.ROUND_DOWN).abs());
        }else{
            BigInteger employeesCount = BigInteger.ZERO;

            for (EmployeeSet employee : employees) {
                employeesCount = employeesCount.add(new BigInteger(Long.toString(employee.count)));
            }

            System.out.println(employeesCount + " " + initialCapital.setScale(4, BigDecimal.ROUND_DOWN));
        }

    }

    static class EmployeeSet{
        public long count;
        public int day;
        public BigDecimal salary;

        public EmployeeSet(long count, int day, BigDecimal salary){
            this.count = count;
            this.day = day;
            this.salary = salary;
        }
    }
}
