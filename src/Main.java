import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Main {

    public static void main(String[] args) {
            YearReport yearReport = new YearReport();
            HashMap<Integer,MonthReport> checkReport = new HashMap<>();
            Scanner scanner = new Scanner(System.in);
            Checker checker = new Checker(checkReport,yearReport);

            while (true){
                printMenu();
                int command = scanner.nextInt();
                if (command == 1){
                    checker.scanMonth();
                } else if (command == 2){
                    checker.scanYear();
                } else if (command == 3){
                    checker.checkerReport();
                } else if(command == 4){
                    checker.printMonthAndYearInfo();
                } else if(command == 0){
                    System.out.println("Конец программы");
                    break;
                } else {
                    System.out.println("Такой команды нет");
                }
            }
    }


    public static void printMenu(){
        System.out.println("Меню:");
        System.out.println("1 - Считать все месячные отчеты");
        System.out.println("2 - Считать годовой отчет");
        System.out.println("3 - Сверить отчеты");
        System.out.println("4 - Вывести информацию обо всех отчетах");
        System.out.println("0 - Выход");
    }


}

