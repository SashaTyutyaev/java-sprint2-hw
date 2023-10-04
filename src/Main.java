import java.sql.SQLOutput;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
public class Main {

    public static void main(String[] args) {
            YearReport yearReport = new YearReport();
            MonthReport monthReport1 = new MonthReport();
            MonthReport monthReport2 = new MonthReport();
            MonthReport monthReport3 = new MonthReport();
            HashMap<Integer,MonthReport> checkReport = new HashMap<>();
            Scanner scanner = new Scanner(System.in);

            while (true){
                printMenu();
                int command = scanner.nextInt();
                if (command == 1){
                    monthReport1.loadFile("m.202101.csv");
                    monthReport2.loadFile("m.202102.csv");
                    monthReport3.loadFile("m.202103.csv");
                    checkReport.put(1,monthReport1);
                    checkReport.put(2,monthReport2);
                    checkReport.put(3,monthReport3);
                    if (!checkReport.isEmpty()) {
                        System.out.println("Месячные отчеты считаны!");
                    }
                } else if (command == 2){
                    yearReport.loadFile("y.2021.csv");
                    if (!yearReport.yearData.isEmpty()) {
                        System.out.println("Годовой отчет считан!");
                    }
                } else if (command == 3){
                    Checker checker = new Checker(checkReport,yearReport);
                    if (checkReport.isEmpty()){
                        System.out.println("Сначала считайте месячные отчеты");
                        continue;
                    }
                    if(yearReport.yearData.isEmpty()){
                        System.out.println("Сначала считайте годовой отчет");
                    } else {
                        int checkAccess = checker.check();
                        if (checkAccess == 0){
                            System.out.println("Проверка пройдена");
                        } else {
                            System.out.println("Ошибка в месяце № " + checkAccess);
                        }
                    }
                } else if(command == 4){
                    if (checkReport.isEmpty()) {
                        System.out.println("Сначала считайте все месячные отчеты");
                        continue;
                    }
                    if (yearReport.yearData.isEmpty()){
                        System.out.println("Сначала считайте годовой отчет");
                    } else {
                        for (Integer month : checkReport.keySet()){
                            System.out.println("Месяц № " + month);
                            MonthReport report = checkReport.get(month);
                            Month monthRep = report.getTopProduct();
                            int sum = monthRep.unitPrice * monthRep.quantity;
                            System.out.println("Самый прибыльный товар: " + monthRep.itemName + " на сумму " + sum);
                            Month monthRep2 = report.getTopExpense();
                            int sum2 = monthRep2.unitPrice * monthRep2.quantity;
                            System.out.println("Самая большая трата " + monthRep2.itemName + " на сумму " + sum2);
                        }
                        System.out.println("2021 год");
                        for (int i = 0; i < yearReport.yearData.size(); i ++){
                            Year yearInfo = yearReport.yearData.get(i);
                            if (!yearInfo.isExpense){
                                System.out.println("Прибыль за " + yearInfo.month + " месяц " + yearReport.getYearPerMonthIncome(yearInfo.month));
                            }
                        }
                        int averageIncome = yearReport.getAverageYearIncome();
                        int averageExpense = yearReport.getAverageYearExpense();
                        System.out.println("Средний доход " + averageIncome);
                        System.out.println("Средний расход " + averageExpense);
                    }
                }else if(command == 0){
                    System.out.println("Конец программы");
                    break;
                }else {
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

