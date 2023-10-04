import java.util.ArrayList;
import java.util.HashMap;

public class Checker {

    HashMap<Integer,MonthReport> checkReport;
    YearReport yearReport;

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

}
