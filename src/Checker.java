import java.util.HashMap;

public class Checker {
    MonthReport monthReport1 = new MonthReport();
    MonthReport monthReport2 = new MonthReport();
    MonthReport monthReport3 = new MonthReport();
    HashMap<Integer,MonthReport> checkReport;
    YearReport yearReport;
    boolean isLoadedMonth = false;
    boolean isLoadedYear = false;

    public Checker(HashMap<Integer,MonthReport> checkReport, YearReport yearReport){
        this.checkReport = checkReport;
        this.yearReport = yearReport;
    }

    public int check(){
        for (Integer month : checkReport.keySet()){
            int expensesFromMonth = checkReport.get(month).getExpenses();
            int incomeFromMonth = checkReport.get(month).getIncome();
            int expensesFromYear = yearReport.getYearPerMonthExpenses(month);
            int incomeFromYear = yearReport.getYearPerMonthIncome(month);
            if (expensesFromMonth != expensesFromYear || incomeFromMonth != incomeFromYear){
                return month;
            }
        }
        return 0;
    }

    public void scanMonth(){
        monthReport1.loadFile("m.202101.csv");
        monthReport2.loadFile("m.202102.csv");
        monthReport3.loadFile("m.202103.csv");
        checkReport.put(1,monthReport1);
        checkReport.put(2,monthReport2);
        checkReport.put(3,monthReport3);
        if (!checkReport.isEmpty()) {
            isLoadedMonth = true;
            System.out.println("Месячные отчеты считаны!");
        }
    }

    public void scanYear(){
        yearReport.loadFile("y.2021.csv");
        if (!yearReport.yearData.isEmpty()) {
            isLoadedYear = true;
            System.out.println("Годовой отчет считан!");
        }
    }

    public void checkerReport(){
        if (!isLoadedMonth){
            System.out.println("Сначала считайте месячные отчеты");
            return;
        }
        if(!isLoadedYear){
            System.out.println("Сначала считайте годовой отчет");
        } else {
            int checkAccess = check();
            if (checkAccess == 0){
                System.out.println("Проверка пройдена");
            } else {
                System.out.println("Ошибка в месяце № " + checkAccess);
            }
        }
    }

    public void printMonthAndYearInfo(){
        if (!isLoadedMonth) {
            System.out.println("Сначала считайте все месячные отчеты");
            return;
        }
        if (!isLoadedYear){
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