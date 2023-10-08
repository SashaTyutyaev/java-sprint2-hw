import java.io.File;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class Checker {
    MonthReport monthReport = new MonthReport();
    HashMap<Integer, MonthReport> checkReport = new HashMap<>();
    YearReport yearReport = new YearReport();
    boolean isLoadedMonth = false;
    boolean isLoadedYear = false;




    public Checker() {
    }

    public int check() {
            for (Integer month : checkReport.keySet()) {
                int expensesFromMonth = checkReport.get(month).getExpenses();
                int incomeFromMonth = checkReport.get(month).getIncome();
                int expensesFromYear = yearReport.getYearPerMonthExpenses(month);
                int incomeFromYear = yearReport.getYearPerMonthIncome(month);
                if (expensesFromMonth != expensesFromYear || incomeFromMonth != incomeFromYear) {
                    return month;
                }
            }
        return 0;
    }

    public void scanMonth() {
        for (int i = 1;i < 4;i++) {
            monthReport = new MonthReport();
            String filename = "m.20210" + i + ".csv";
            monthReport.loadFile(filename);
            checkReport.put(i,monthReport);
        }
        if (!checkReport.isEmpty()) {
            isLoadedMonth = true;
            System.out.println("Месячные отчеты считаны!");
        }
    }

    public void scanYear() {
        yearReport.loadFile("y.2021.csv");
        if (!yearReport.yearData.isEmpty()) {
            isLoadedYear = true;
            System.out.println("Годовой отчет считан!");
        }
    }

    public void checkerReport() {
        if (!isLoadedMonth) {
            System.out.println("Сначала считайте месячные отчеты");
            return;
        }
        if (!isLoadedYear) {
            System.out.println("Сначала считайте годовой отчет");
        } else {
            int checkAccess = check();
            if (checkAccess == 0) {
                System.out.println("Проверка пройдена");
            } else {
                System.out.println("Ошибка в месяце № " + checkAccess);
            }

        }
    }

    public void isFileExists(){
        for (int i = 1; i < 4;i ++){
            String pathname = "resources/m.20210" + i + ".csv";
            File file = new File(pathname);
            if (file.length() == 0){
                System.out.println("Ошибка! Отсутсвуют данные за месяц №" + i);
                return;
            }
        }
    }


    public void printMonthInfo() {
        if (!isLoadedMonth) {
            System.out.println("Сначала считайте все месячные отчеты");
        } else {
            isFileExists();
            return;
        }
            for (Integer month : checkReport.keySet()) {
                System.out.println("Месяц № " + month);
                MonthReport report = checkReport.get(month);
                Month monthRep = report.getTopProduct();
                int sum = monthRep.unitPrice * monthRep.quantity;
                System.out.println("Самый прибыльный товар: " + monthRep.itemName + " на сумму " + sum);
                Month monthRep2 = report.getTopExpense();
                int sum2 = monthRep2.unitPrice * monthRep2.quantity;
                System.out.println("Самая большая трата " + monthRep2.itemName + " на сумму " + sum2);
            }
        }



        public void printYearInfo() {
            if (!isLoadedYear) {
                System.out.println("Сначала считайте годовой отчет");
            } else {
                System.out.println("2021 год");
                for (int i = 0; i < yearReport.yearData.size(); i++) {
                    Year yearInfo = yearReport.yearData.get(i);
                    if (!yearInfo.isExpense) {
                        System.out.println("Прибыль за " + yearInfo.month + " месяц " + (yearReport.getYearPerMonthIncome(yearInfo.month) - yearReport.getYearPerMonthExpenses(yearInfo.month)));
                    }
                }
                int averageIncome = yearReport.getAverageYearIncome();
                int averageExpense = yearReport.getAverageYearExpense();
                System.out.println("Средний доход " + averageIncome);
                System.out.println("Средний расход " + averageExpense);
            }
        }

}
