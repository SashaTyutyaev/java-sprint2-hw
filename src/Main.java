import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Main {

    public static void main(String[] args) {
        MonthReport monthReport = new MonthReport();
            Scanner scanner = new Scanner(System.in);
            Checker checker = new Checker();

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
                    checker.printMonthInfo();
                } else if(command == 5){
                    checker.printYearInfo();
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
        System.out.println("4 - Вывести информацию о месячных отчетах");
        System.out.println("5 - Вывести информацию о годовом отчете");
        System.out.println("0 - Выход");
    }


}

